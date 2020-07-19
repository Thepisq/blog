package com.zbnetwork.blog.app.DO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Generated;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysRole {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.id")
    private Integer id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.name")
    private String name;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.authority")
    private String authority;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.id")
    public Integer getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.name")
    public String getName() {
        return name;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.name")
    public void setName(String name) {
        this.name = name;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.authority")
    public String getAuthority() {
        return authority;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source field: sys_role.authority")
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}