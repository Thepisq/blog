package com.zbnetwork.blog.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 13496
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private Integer id;
    private Integer authorId;
    private Date pushDate;
    private Integer likes;
    private Integer clicks;
    private Integer collects;
    private Integer comments;
    private String content;
}
