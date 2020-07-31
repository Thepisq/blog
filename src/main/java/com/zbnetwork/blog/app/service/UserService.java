package com.zbnetwork.blog.app.service;

import com.zbnetwork.blog.app.DO.User;
import com.zbnetwork.blog.app.DTO.UserDTO;
import com.zbnetwork.blog.app.utils.userdetails.UserUd;

import java.util.List;

/**
 * @author 13496
 */
public interface UserService {
    /**
     * find one User
     */
    UserDTO oneUser(Long id);

    UserDTO oneUser(String username);

    /**
     * save one user
     */
    int saveUser(User user);

    int saveUser(String username, String password);

    /**
     * update one user
     */
    int updateUser(UserDTO userDTO);

    /**
     * find all
     */
    List<UserDTO> findAll(int pageNum, int pageSize);

    /**
     * find user exists
     */
    long findUserExists(String username);

    long findUserExists(Long id);

    /**
     * upgrade User's authorize (up to ROLE_BLOG)
     */
    int upgrade(UserUd userUd);
}
