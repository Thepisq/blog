package com.liushaonetwork.blog.app.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class SysRoleDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5326126+08:00", comments = "Source Table: sys_role")
    public static final SysRole sysRole = new SysRole();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source field: sys_role.id")
    public static final SqlColumn<Integer> id = sysRole.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source field: sys_role.name")
    public static final SqlColumn<String> name = sysRole.name;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source field: sys_role.authority")
    public static final SqlColumn<String> authority = sysRole.authority;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source Table: sys_role")
    public static final class SysRole extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> authority = column("authority", JDBCType.VARCHAR);

        public SysRole() {
            super("sys_role");
        }
    }
}