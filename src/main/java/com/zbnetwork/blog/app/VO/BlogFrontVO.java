package com.zbnetwork.blog.app.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 13496
 * 在前端网页展示的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogFrontVO implements Serializable {
    static final long serialVersionUID = 1L;
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
