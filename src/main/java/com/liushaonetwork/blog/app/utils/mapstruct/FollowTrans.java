package com.liushaonetwork.blog.app.utils.mapstruct;

import com.alibaba.fastjson.JSON;
import com.liushaonetwork.blog.app.DO.Follow;
import com.liushaonetwork.blog.app.DO.follow.FollowUser;
import com.liushaonetwork.blog.app.VO.FollowFrontVO;
import com.liushaonetwork.blog.app.VO.UserFrontVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13496
 */
@Mapper(componentModel = "spring")
public interface FollowTrans {
    FollowTrans INSTANCE = Mappers.getMapper(FollowTrans.class);

    default FollowFrontVO getFrontVo(UserFrontVO user, Follow follow) {
        if (user == null || follow == null) {
            return null;
        }
        FollowFrontVO frontVO = new FollowFrontVO();
        frontVO.setUser(user);

        int followersNum = 0;
        int followingNum = 0;

        if (null != follow.getFollowers()) {
            followersNum = JSON.parseArray((String) follow.getFollowers(), FollowUser.class).size();
        }

        if (null != follow.getFollowing()) {
            followingNum = JSON.parseArray((String) follow.getFollowing(), FollowUser.class).size();
        }

        frontVO.setFollowersNum(followersNum);
        frontVO.setFollowingNum(followingNum);

        return frontVO;
    }

    default List<FollowFrontVO> getListFrontVo(List<UserFrontVO> userList, List<Follow> followList) {
        if (userList == null || followList == null || userList.size() != followList.size()) {
            return null;
        }
        List<FollowFrontVO> frontVOList = new ArrayList<>(userList.size());
        for (int i = 0; i < userList.size(); i++) {
            frontVOList.add(getFrontVo(userList.get(i), followList.get(i)));
        }

        return frontVOList;
    }
}
