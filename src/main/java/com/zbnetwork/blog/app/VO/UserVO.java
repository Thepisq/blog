package com.zbnetwork.blog.app.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 13496
 */
@Data
public class UserVO implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String introduction;
    private String roles;
}
