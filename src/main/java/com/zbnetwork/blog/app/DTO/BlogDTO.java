package com.zbnetwork.blog.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 13496
 * 包含部分用户字段的Blog对象
 * 'authorName'为'authorId'在User表(的'id')所对应的'username'
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private Long id;
    private Long authorId;
    private String authorName;
    private String title;
    private Integer likes;
    private Integer clicks;
    private Integer collects;
    private Integer comments;
    private Date firstPushDate;
    private Date lastPushDate;
    private Integer topicId;
    private String content;
}
