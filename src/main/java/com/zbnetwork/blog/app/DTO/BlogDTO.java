package com.zbnetwork.blog.app.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author 13496
 */
@Data
public class BlogDTO {
    private Integer id;

    private Integer authorId;

    private Date pushDate;

    private String content;
}
