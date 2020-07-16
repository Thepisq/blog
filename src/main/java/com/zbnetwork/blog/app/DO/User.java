package com.zbnetwork.blog.app.DO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Generated;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User{
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.password")
    private String password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.phone")
    private String phone;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.email")
    private String email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.introduction")
    private String introduction;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9813428+08:00", comments="Source field: user.roles")
    private String roles;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.username")
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.password")
    public String getPassword() {
        return password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.password")
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.phone")
    public String getPhone() {
        return phone;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.email")
    public String getEmail() {
        return email;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.980346+08:00", comments="Source field: user.email")
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9813428+08:00", comments="Source field: user.introduction")
    public String getIntroduction() {
        return introduction;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9813428+08:00", comments="Source field: user.introduction")
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9813428+08:00", comments="Source field: user.roles")
    public String getRoles() {
        return roles;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9813428+08:00", comments="Source field: user.roles")
    public void setRoles(String roles) {
        this.roles = roles;
    }
}