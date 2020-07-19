package com.zbnetwork.blog.app.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class SysTopicDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source Table: sys_topic")
    public static final SysTopic sysTopic = new SysTopic();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source field: sys_topic.id")
    public static final SqlColumn<Integer> id = sysTopic.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source field: sys_topic.branch")
    public static final SqlColumn<String> branch = sysTopic.branch;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source field: sys_topic.topic_name")
    public static final SqlColumn<String> topicName = sysTopic.topicName;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5366023+08:00", comments = "Source Table: sys_topic")
    public static final class SysTopic extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> branch = column("branch", JDBCType.VARCHAR);

        public final SqlColumn<String> topicName = column("topic_name", JDBCType.VARCHAR);

        public SysTopic() {
            super("sys_topic");
        }
    }
}