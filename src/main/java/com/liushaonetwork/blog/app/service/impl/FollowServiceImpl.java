package com.liushaonetwork.blog.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.liushaonetwork.blog.app.DO.Follow;
import com.liushaonetwork.blog.app.DO.follow.FollowUser;
import com.liushaonetwork.blog.app.DTO.UserDTO;
import com.liushaonetwork.blog.app.VO.FollowFrontVO;
import com.liushaonetwork.blog.app.VO.UserFrontVO;
import com.liushaonetwork.blog.app.mapper.FollowDynamicSqlSupport;
import com.liushaonetwork.blog.app.mapper.FollowMapper;
import com.liushaonetwork.blog.app.service.FollowService;
import com.liushaonetwork.blog.app.service.UserService;
import com.liushaonetwork.blog.app.utils.mapstruct.FollowTrans;
import com.liushaonetwork.blog.app.utils.mapstruct.UserTrans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 * follow表除了userid其他的有可能为空
 */
@Slf4j
@Service
public class FollowServiceImpl implements FollowService {
    private final FollowMapper followMapper;
    private final UserService userService;

    private static final String TYPE_1 = "following";
    private static final String TYPE_2 = "followers";

    @Autowired
    public FollowServiceImpl(FollowMapper followMapper, UserService userService) {
        this.followMapper = followMapper;
        this.userService = userService;
    }

    /**
     * 获取关注列表
     */
    @Override
    public List<FollowFrontVO> getFollowing(Long userId) {
        Follow follow = followMapper.selectOne(c -> c.where(FollowDynamicSqlSupport.userId, isEqualTo(userId)))
                .orElse(null);

        if (follow == null) {
            log.info("当前用户的关注列表为空(未初始化)");
            return null;
        }
        //获取关注对象的id信息
        List<FollowUser> idList = JSON.parseArray((String) follow.getFollowing(), FollowUser.class);

        //根据id查询基本信息(在User表查询)
        List<UserFrontVO> followingList = new ArrayList<>();
        for (FollowUser u : idList) {
            UserDTO user = userService.oneUser(u.getId());
            if (user == null) {
                continue;
            }
            followingList.add(UserTrans.INSTANCE.dto2FtVo(user));
        }
        if (followingList.size() == 0) {
            return null;
        }

        //根据id查询关注信息(在Follow表查询)
        List<Follow> following2List = new ArrayList<>();
        for (UserFrontVO uf : followingList) {
            following2List.add(followMapper.selectOne(c -> c.where(FollowDynamicSqlSupport.userId, isEqualTo(uf.getUserId()))).orElseGet(() -> new Follow()));
        }

        //根据关注对象的基本信息和关注信息返回到前端
        return FollowTrans.INSTANCE.getListFrontVo(followingList, following2List);
    }

    @Override
    public List<FollowFrontVO> getAllByPage(Long userId, int page, int size) {
        return null;
    }

    //userId 关注 followingUserId -> 前者的表中写入后者id信息,一般先调用这个
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int toggleFollowing(Long userId, Long followingUserId) {
        if (userService.findUserExists(userId) != 1L || userService.findUserExists(followingUserId) != 1L) {
            return 0;
        }
        log.info("添加用户{id=" + followingUserId + "}到{id=" + userId + "}的关注列表");

        return followMapper.selectByPrimaryKey(userId)
                .map(f -> {
                    log.info("获取到用户{id=" + userId + "}的关注列表");
                    return toggleFollow(userId, followingUserId, TYPE_1);
                })
                .orElseGet(() -> {
                    log.info("用户{id=" + userId + "}的关注列表正在初始化");
                    return generatorWith(userId, followingUserId, TYPE_1);
                });

    }

    //followersUserId 被 userId 关注 -> 前者的表中写入后者的id信息,一般后调用这个
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int toggleFollowers(Long followersUserId, Long userId) {
        if (userService.findUserExists(userId) != 1L || userService.findUserExists(followersUserId) != 1L) {
            return 0;
        }
        log.info("添加用户{id=" + userId + "}到{id=" + followersUserId + "}的粉丝列表");

        return followMapper.selectByPrimaryKey(followersUserId)
                .map(f -> {
                    log.info("获取到用户{id=" + followersUserId + "}(非当前用户)的关注列表");
                    return toggleFollow(followersUserId, userId, TYPE_2);
                })
                .orElseGet(() -> {
                    log.info("用户{id=" + followersUserId + "}(非当前用户)的关注列表正在初始化");
                    return generatorWith(followersUserId, userId, TYPE_2);
                });
    }

    @Override
    public boolean isCurrentUserFollowing(Long userId, Long authorId) {
        if (userService.findUserExists(userId) != 1L || userService.findUserExists(authorId) != 1L) {
            return false;
        }
        log.info("检查是否当前用户关注了此页面的作者");
        Follow f = followMapper.selectByPrimaryKey(userId).orElseGet(() -> null);
        if (f == null || f.getFollowing() == null) {
            return false;
        }
        List<FollowUser> followingList = JSON.parseArray((String) f.getFollowing(), FollowUser.class);
        boolean result = false;
        for (int i = 0; i < followingList.size(); i++) {
            if (followingList.get(i).getId().equals(authorId)) {
                log.info("当前用户已经关注了此页面的作者");
                result = true;
            }
        }
        return result;
    }


    //将userId与followUserId的用户关注起来或是取消关注，根据是否关注和type决定此次操作的结果
    private int toggleFollow(Long userId, Long followUserId, String type) {
        Follow f = followMapper.selectByPrimaryKey(userId).orElseGet(() -> {
            return null;
        });
        if (f == null) {
            log.info("获取不到用户{id=" + userId + "}的关注信息,return 0");
            return 0;
        }
        List<FollowUser> idList = new ArrayList<>();
        if (TYPE_1.equalsIgnoreCase(type) && f.getFollowing() != null) {
            idList = JSON.parseArray((String) f.getFollowing(), FollowUser.class);
        } else if (TYPE_2.equalsIgnoreCase(type) && f.getFollowers() != null) {
            idList = JSON.parseArray((String) f.getFollowers(), FollowUser.class);
        } else {
            log.info("用户{id=" + userId + "}的‘关注’或‘粉丝’内容为空");
        }

        boolean alreadyHas = false;

        if (idList != null && idList.size() >= 1) {
            for (int i = idList.size() - 1; i >= 0; i--) {
                if (idList.get(i).getId().equals(followUserId)) {
                    alreadyHas = true;
                    idList.remove(i);
                }
            }
        }

        if (alreadyHas) {
            String followUserList = JSON.toJSONString(idList);
            if (TYPE_1.equalsIgnoreCase(type)) {
                f.setFollowing(followUserList);
            } else if (TYPE_2.equalsIgnoreCase(type)) {
                f.setFollowers(followUserList);
            } else {
                return 0;
            }
            log.info("您成功的取关了TA");
            followMapper.updateByPrimaryKeySelective(f);
            return -1;
        } else {
            FollowUser fu = new FollowUser();
            fu.setId(followUserId);
            fu.setFollowTime(LocalDateTime.now());

            idList.add(fu);
            String followUserList = JSON.toJSONString(idList);

            if (TYPE_1.equalsIgnoreCase(type)) {
                f.setFollowing(followUserList);
            } else if (TYPE_2.equalsIgnoreCase(type)) {
                f.setFollowers(followUserList);
            } else {
                return 0;
            }

            log.info("您成功关注了TA");
            return followMapper.updateByPrimaryKeySelective(f);
        }

    }

    //初始化并插入一条数据
    private int generatorWith(Long userId, Long followUserId, String type) {
        if (followMapper.selectByPrimaryKey(userId).orElseGet(() -> null) != null) {
            return 0;
        }
        Follow f = new Follow();
        f.setUserId(userId);

        FollowUser fu = new FollowUser();
        fu.setId(followUserId);
        fu.setFollowTime(LocalDateTime.now());

        List<FollowUser> idList = new ArrayList<>(1);
        idList.add(fu);

        if (TYPE_1.equalsIgnoreCase(type)) {
            f.setFollowing(JSON.toJSONString(idList));
        } else if (TYPE_2.equalsIgnoreCase(type)) {
            f.setFollowers(JSON.toJSONString(idList));
        }
        log.info("用户{id=" + userId + "}的follow表初始化成功");
        return followMapper.insertSelective(f);
    }


}
