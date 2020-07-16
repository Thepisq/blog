package com.zbnetwork.blog.app.DTO;

import lombok.Data;

import javax.annotation.Generated;

/**
 * @author 13496
 */
@Data
public class UserDTO{
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String introduction;

    private String roles;


}
