package com.liushaonetwork.blog.app.DO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Generated;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysTopic {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.id")
    private Integer id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.branch")
    private String branch;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.topic_name")
    private String topicName;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.id")
    public Integer getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.branch")
    public String getBranch() {
        return branch;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.branch")
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.topic_name")
    public String getTopicName() {
        return topicName;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source field: sys_topic.topic_name")
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}