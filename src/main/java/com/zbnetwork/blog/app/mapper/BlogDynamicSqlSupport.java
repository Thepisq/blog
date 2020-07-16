package com.zbnetwork.blog.app.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BlogDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9674158+08:00", comments="Source Table: blog")
    public static final Blog blog = new Blog();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9674158+08:00", comments="Source field: blog.id")
    public static final SqlColumn<Integer> id = blog.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9674158+08:00", comments="Source field: blog.author_id")
    public static final SqlColumn<Integer> authorId = blog.authorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9684132+08:00", comments="Source field: blog.push_date")
    public static final SqlColumn<Date> pushDate = blog.pushDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9684132+08:00", comments="Source field: blog.likes")
    public static final SqlColumn<Integer> likes = blog.likes;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9684132+08:00", comments="Source field: blog.clicks")
    public static final SqlColumn<Integer> clicks = blog.clicks;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9684132+08:00", comments="Source field: blog.collects")
    public static final SqlColumn<Integer> collects = blog.collects;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9684132+08:00", comments="Source field: blog.comments")
    public static final SqlColumn<Integer> comments = blog.comments;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9684132+08:00", comments="Source field: blog.content")
    public static final SqlColumn<String> content = blog.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-16T13:36:17.9674158+08:00", comments="Source Table: blog")
    public static final class Blog extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> authorId = column("author_id", JDBCType.INTEGER);

        public final SqlColumn<Date> pushDate = column("push_date", JDBCType.DATE);

        public final SqlColumn<Integer> likes = column("likes", JDBCType.INTEGER);

        public final SqlColumn<Integer> clicks = column("clicks", JDBCType.INTEGER);

        public final SqlColumn<Integer> collects = column("collects", JDBCType.INTEGER);

        public final SqlColumn<Integer> comments = column("comments", JDBCType.INTEGER);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public Blog() {
            super("blog");
        }
    }
}