package com.zbnetwork.blog.app.VO;

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
public class UserVO implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer userId;
    private String nickname;
    private String phone;
    private String email;
    private String introduction;
    private int blogNum;
}
