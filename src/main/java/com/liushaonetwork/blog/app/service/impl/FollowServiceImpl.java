package com.liushaonetwork.blog.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.liushaonetwork.blog.app.DO.Follow;
import com.liushaonetwork.blog.app.DO.follow.FollowUser;
import com.liushaonetwork.blog.app.VO.FollowFrontVO;
import com.liushaonetwork.blog.app.VO.UserFrontVO;
import com.liushaonetwork.blog.app.mapper.FollowDynamicSqlSupport;
import com.liushaonetwork.blog.app.mapper.FollowMapper;
import com.liushaonetwork.blog.app.service.FollowService;
import com.liushaonetwork.blog.app.service.UserService;
import com.liushaonetwork.blog.app.utils.mapstruct.FollowTrans;
import com.liushaonetwork.blog.app.utils.mapstruct.UserTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author 13496
 */
@Service
public class FollowServiceImpl implements FollowService {
    private final FollowMapper followMapper;
    private final UserService userService;

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
            return null;
        }

        //获取关注对象的id信息
        List<FollowUser> idList = JSON.parseArray((String) follow.getFollowing(), FollowUser.class);

        //根据id查询基本信息(在User表查询)
        List<UserFrontVO> followingList = new ArrayList<>();
        for (FollowUser u : idList) {
            followingList.add(UserTrans.INSTANCE.dto2FtVo(userService.oneUser(u.getId())));
        }

        //根据id查询关注信息(在Follow表查询)
        List<Follow> following2List = new ArrayList<>();
        for (UserFrontVO uf : followingList) {
            following2List.add(followMapper.selectOne(c -> c.where(FollowDynamicSqlSupport.userId, isEqualTo(uf.getUserId()))).orElseThrow(() -> new RuntimeException("找不到" + userId + "的关注信息")));
        }

        //根据关注对象的基本信息和关注信息返回到前端
        return FollowTrans.INSTANCE.getListFrontVo(followingList, following2List);
    }

    @Override
    public List<FollowFrontVO> getAllByPage(Long userId, int page, int size) {
        return null;
    }


}
