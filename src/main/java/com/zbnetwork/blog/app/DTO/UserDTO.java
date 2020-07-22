package com.zbnetwork.blog.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 13496
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String introduction;
    private String roles;


}
