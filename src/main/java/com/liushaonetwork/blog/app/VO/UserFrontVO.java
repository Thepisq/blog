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
public class UserFrontVO implements Serializable {
    private static final long serialVersionUID = -3007246599838415969L;

    private Long userId;

    private String nickname;

    private String phone;

    private String email;

    private String introduction;

    private int likeNum;

    private int blogNum;
}
