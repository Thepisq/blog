package com.liushaonetwork.blog.app.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source Table: user")
    public static final User user = new User();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source field: user.id")
    public static final SqlColumn<Long> id = user.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source field: user.username")
    public static final SqlColumn<String> username = user.username;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source field: user.password")
    public static final SqlColumn<String> password = user.password;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source field: user.phone")
    public static final SqlColumn<String> phone = user.phone;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source field: user.email")
    public static final SqlColumn<String> email = user.email;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source field: user.introduction")
    public static final SqlColumn<String> introduction = user.introduction;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8912427+08:00", comments = "Source field: user.roles")
    public static final SqlColumn<String> roles = user.roles;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-20T13:47:14.8904699+08:00", comments = "Source Table: user")
    public static final class User extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> introduction = column("introduction", JDBCType.VARCHAR);

        public final SqlColumn<String> roles = column("roles", JDBCType.VARCHAR);

        public User() {
            super("user");
        }
    }
}