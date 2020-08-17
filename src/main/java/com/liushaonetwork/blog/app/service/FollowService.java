package com.liushaonetwork.blog.app.service;

import com.liushaonetwork.blog.app.VO.FollowFrontVO;

import java.util.List;

/**
 * @author 13496
 */
public interface FollowService {
    List<FollowFrontVO> getFollowing(Long userId);

    List<FollowFrontVO> getAllByPage(Long userId, int page, int size);

    int toggleFollowing(Long userId, Long followingUserId);

    int toggleFollowers(Long followersUserId, Long userId);

    boolean isCurrentUserFollowing(Long userId, Long authorId);
}
