package com.liushaonetwork.blog.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 13496
 * 包含部分用户字段的Blog对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO implements Serializable {
    private static final long serialVersionUID = 5038008672206053453L;

    private Long id;

    private Long authorId;

    private String authorName;

    private String title;

    private Integer likes;

    private Boolean isCurrentUserLikes;

    private Integer clicks;

    private Integer collects;

    private Integer comments;

    private LocalDateTime firstPushDate;

    private LocalDateTime lastPushDate;

    private Integer topicId;

    private String topicName;

    private String content;

    private String contentView;
}

