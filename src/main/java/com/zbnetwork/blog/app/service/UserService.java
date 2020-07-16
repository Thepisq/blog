package com.zbnetwork.blog.app.service;

import com.zbnetwork.blog.app.DTO.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * find one User
     */
    UserDTO oneUser(int id);
    /**
     * save one user
     * @return
     */
    int saveUser(UserDTO userDTO);
    /**
     * update one user
     * @return
     */
    int updateUser(UserDTO userDTO);
    /**
     * find all
     */
    List<UserDTO> findAll(int pageNum, int pageSize);
}
