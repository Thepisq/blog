package com.liushaonetwork.blog.app.DO;

import lombok.ToString;

import javax.annotation.Generated;
import java.io.Serializable;

@ToString
public class Follow implements Serializable {
    private static final long serialVersionUID = 8576534605737258639L;
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8455289+08:00", comments = "Source field: follow.user_id")
    private Long userId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.following")
    private Object following;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.followers")
    private Object followers;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.user_id")
    public Long getUserId() {
        return userId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.user_id")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.following")
    public Object getFollowing() {
        return following;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.following")
    public void setFollowing(Object following) {
        this.following = following;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.followers")
    public Object getFollowers() {
        return followers;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-10T21:38:42.8485197+08:00", comments = "Source field: follow.followers")
    public void setFollowers(Object followers) {
        this.followers = followers;
    }
}