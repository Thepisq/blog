package com.zbnetwork.blog.app.mapper;

import com.zbnetwork.blog.app.DO.SysRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.zbnetwork.blog.app.mapper.SysRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface SysRoleMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, authority);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source Table: sys_role")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source Table: sys_role")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source Table: sys_role")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<SysRole> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5336104+08:00", comments = "Source Table: sys_role")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SysRole> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("SysRoleResult")
    Optional<SysRole> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "SysRoleResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "authority", property = "authority", jdbcType = JdbcType.VARCHAR)
    })
    List<SysRole> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    default int insert(SysRole record) {
        return MyBatis3Utils.insert(this::insert, record, sysRole, c ->
                c.map(id).toProperty("id")
                        .map(name).toProperty("name")
                        .map(authority).toProperty("authority")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    default int insertMultiple(Collection<SysRole> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysRole, c ->
                c.map(id).toProperty("id")
                        .map(name).toProperty("name")
                        .map(authority).toProperty("authority")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5346088+08:00", comments = "Source Table: sys_role")
    default int insertSelective(SysRole record) {
        return MyBatis3Utils.insert(this::insert, record, sysRole, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(name).toPropertyWhenPresent("name", record::getName)
                        .map(authority).toPropertyWhenPresent("authority", record::getAuthority)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    default Optional<SysRole> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    default List<SysRole> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    default List<SysRole> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    default Optional<SysRole> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    static UpdateDSL<UpdateModel> updateAllColumns(SysRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(authority).equalTo(record::getAuthority);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(authority).equalToWhenPresent(record::getAuthority);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    default int updateByPrimaryKey(SysRole record) {
        return update(c ->
                c.set(name).equalTo(record::getName)
                        .set(authority).equalTo(record::getAuthority)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5356054+08:00", comments = "Source Table: sys_role")
    default int updateByPrimaryKeySelective(SysRole record) {
        return update(c ->
                c.set(name).equalToWhenPresent(record::getName)
                        .set(authority).equalToWhenPresent(record::getAuthority)
                        .where(id, isEqualTo(record::getId))
        );
    }
}