package com.liushaonetwork.blog.app.DTO;

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
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -2461783957512246583L;

    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String introduction;

    private String roles;


}
