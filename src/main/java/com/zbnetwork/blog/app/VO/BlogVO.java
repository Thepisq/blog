package com.zbnetwork.blog.app.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 13496
 */
@Data
public class BlogVO implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer id;

    private Integer authorId;

    private Date pushDate;

    private String content;
}
