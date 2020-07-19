package com.zbnetwork.blog.app.service;

import com.zbnetwork.blog.app.DO.User;
import com.zbnetwork.blog.app.DTO.UserDTO;

import java.util.List;

/**
 * @author 13496
 */
public interface UserService {
    /**
     * find one User
     */
    UserDTO oneUser(int id);

    /**
     * save one user
     */
    int saveUser(User user);

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
}
