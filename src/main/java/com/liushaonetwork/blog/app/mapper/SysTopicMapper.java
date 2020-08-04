package com.liushaonetwork.blog.app.mapper;

import com.liushaonetwork.blog.app.DO.SysTopic;
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
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.liushaonetwork.blog.app.mapper.SysTopicDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Component
public interface SysTopicMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    BasicColumn[] selectList = BasicColumn.columnList(id, branch, topicName);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<SysTopic> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SysTopic> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("SysTopicResult")
    Optional<SysTopic> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "SysTopicResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "branch", property = "branch", jdbcType = JdbcType.VARCHAR),
            @Result(column = "topic_name", property = "topicName", jdbcType = JdbcType.VARCHAR)
    })
    List<SysTopic> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysTopic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysTopic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    default int insert(SysTopic record) {
        return MyBatis3Utils.insert(this::insert, record, sysTopic, c ->
                c.map(id).toProperty("id")
                        .map(branch).toProperty("branch")
                        .map(topicName).toProperty("topicName")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5375997+08:00", comments = "Source Table: sys_topic")
    default int insertMultiple(Collection<SysTopic> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysTopic, c ->
                c.map(id).toProperty("id")
                        .map(branch).toProperty("branch")
                        .map(topicName).toProperty("topicName")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    default int insertSelective(SysTopic record) {
        return MyBatis3Utils.insert(this::insert, record, sysTopic, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(branch).toPropertyWhenPresent("branch", record::getBranch)
                        .map(topicName).toPropertyWhenPresent("topicName", record::getTopicName)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    default Optional<SysTopic> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysTopic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    default List<SysTopic> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysTopic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    default List<SysTopic> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysTopic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    default Optional<SysTopic> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysTopic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    static UpdateDSL<UpdateModel> updateAllColumns(SysTopic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(branch).equalTo(record::getBranch)
                .set(topicName).equalTo(record::getTopicName);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysTopic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(branch).equalToWhenPresent(record::getBranch)
                .set(topicName).equalToWhenPresent(record::getTopicName);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5385969+08:00", comments = "Source Table: sys_topic")
    default int updateByPrimaryKey(SysTopic record) {
        return update(c ->
                c.set(branch).equalTo(record::getBranch)
                        .set(topicName).equalTo(record::getTopicName)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-18T16:32:06.5395947+08:00", comments = "Source Table: sys_topic")
    default int updateByPrimaryKeySelective(SysTopic record) {
        return update(c ->
                c.set(branch).equalToWhenPresent(record::getBranch)
                        .set(topicName).equalToWhenPresent(record::getTopicName)
                        .where(id, isEqualTo(record::getId))
        );
    }
}