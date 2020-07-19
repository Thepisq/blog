package com.zbnetwork.blog.app.DO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4957118+08:00", comments = "Source field: blog.id")
    private Integer id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4997012+08:00", comments = "Source field: blog.author_id")
    private Integer authorId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4997012+08:00", comments = "Source field: blog.title")
    private String title;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.likes")
    private Integer likes;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.clicks")
    private Integer clicks;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.collects")
    private Integer collects;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.comments")
    private Integer comments;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.first_push_date")
    private Date firstPushDate;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.last_push_date")
    private Date lastPushDate;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.topic_id")
    private Integer topicId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.content")
    private String content;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.498704+08:00", comments = "Source field: blog.id")
    public Integer getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4997012+08:00", comments = "Source field: blog.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4997012+08:00", comments = "Source field: blog.author_id")
    public Integer getAuthorId() {
        return authorId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4997012+08:00", comments = "Source field: blog.author_id")
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4997012+08:00", comments = "Source field: blog.title")
    public String getTitle() {
        return title;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.4997012+08:00", comments = "Source field: blog.title")
    public void setTitle(String title) {
        this.title = title;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.likes")
    public Integer getLikes() {
        return likes;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.clicks")
    public Integer getClicks() {
        return clicks;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.clicks")
    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.collects")
    public Integer getCollects() {
        return collects;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.collects")
    public void setCollects(Integer collects) {
        this.collects = collects;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.comments")
    public Integer getComments() {
        return comments;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5006979+08:00", comments = "Source field: blog.comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.first_push_date")
    public Date getFirstPushDate() {
        return firstPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.first_push_date")
    public void setFirstPushDate(Date firstPushDate) {
        this.firstPushDate = firstPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.last_push_date")
    public Date getLastPushDate() {
        return lastPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.last_push_date")
    public void setLastPushDate(Date lastPushDate) {
        this.lastPushDate = lastPushDate;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.topic_id")
    public Integer getTopicId() {
        return topicId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.topic_id")
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.content")
    public String getContent() {
        return content;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5016967+08:00", comments = "Source field: blog.content")
    public void setContent(String content) {
        this.content = content;
    }
}