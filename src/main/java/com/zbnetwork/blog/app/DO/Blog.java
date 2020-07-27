package com.zbnetwork.blog.app.DO;

import javax.annotation.Generated;
import java.time.LocalDateTime;

public class Blog {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2416226+08:00", comments = "Source field: blog.id")
    private Long id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2436167+08:00", comments = "Source field: blog.author_id")
    private Long authorId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.title")
    private String title;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.likes")
    private Integer likes;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.clicks")
    private Integer clicks;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.collects")
    private Integer collects;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.comments")
    private Integer comments;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.first_push_date")
    private LocalDateTime firstPushDate;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.last_push_date")
    private LocalDateTime lastPushDate;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.topic_id")
    private Integer topicId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.content")
    private String content;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2436167+08:00", comments = "Source field: blog.id")
    public Long getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2436167+08:00", comments = "Source field: blog.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.author_id")
    public Long getAuthorId() {
        return authorId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.author_id")
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.title")
    public String getTitle() {
        return title;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.title")
    public void setTitle(String title) {
        this.title = title;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.likes")
    public Integer getLikes() {
        return likes;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.clicks")
    public Integer getClicks() {
        return clicks;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.clicks")
    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2446139+08:00", comments = "Source field: blog.collects")
    public Integer getCollects() {
        return collects;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.collects")
    public void setCollects(Integer collects) {
        this.collects = collects;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.comments")
    public Integer getComments() {
        return comments;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.first_push_date")
    public LocalDateTime getFirstPushDate() {
        return firstPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.first_push_date")
    public void setFirstPushDate(LocalDateTime firstPushDate) {
        this.firstPushDate = firstPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.last_push_date")
    public LocalDateTime getLastPushDate() {
        return lastPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.last_push_date")
    public void setLastPushDate(LocalDateTime lastPushDate) {
        this.lastPushDate = lastPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.topic_id")
    public Integer getTopicId() {
        return topicId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.topic_id")
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.content")
    public String getContent() {
        return content;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2456112+08:00", comments = "Source field: blog.content")
    public void setContent(String content) {
        this.content = content;
    }
}