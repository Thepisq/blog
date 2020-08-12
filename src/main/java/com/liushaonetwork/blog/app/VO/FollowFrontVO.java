package com.liushaonetwork.blog.app.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 13496
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowFrontVO implements Serializable {
    private static final long serialVersionUID = -1596361780216344266L;

    private UserFrontVO user;

    private int followingNum;

    private int followersNum;
}
