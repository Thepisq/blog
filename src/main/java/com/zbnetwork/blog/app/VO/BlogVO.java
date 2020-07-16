package com.zbnetwork.blog.app.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 13496
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer id;
    private Integer authorId;
    private Date pushDate;
    private Integer likes;
    private Integer clicks;
    private Integer collects;
    private Integer comments;
    private String content;
}
