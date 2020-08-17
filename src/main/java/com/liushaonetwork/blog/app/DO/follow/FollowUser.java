package com.liushaonetwork.blog.app.DO.follow;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 13496
 * 在“follow”表中用于储存用户信息(转换为json)
 */
@Data
@NoArgsConstructor
public class FollowUser implements Serializable {
    private static final long serialVersionUID = 9185480770179616112L;

    private Long id;

    private LocalDateTime followTime;
}
