package com.zbnetwork.blog.app.mapper;

import com.zbnetwork.blog.app.DO.Blog;
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

import static com.zbnetwork.blog.app.mapper.BlogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Component
public interface BlogMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2645601+08:00", comments = "Source Table: blog")
    BasicColumn[] selectList = BasicColumn.columnList(id, authorId, title, likes, clicks, collects, comments, firstPushDate, lastPushDate, topicId, content);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2525938+08:00", comments = "Source Table: blog")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2535916+08:00", comments = "Source Table: blog")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.254588+08:00", comments = "Source Table: blog")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Blog> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.255584+08:00", comments = "Source Table: blog")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Blog> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.255584+08:00", comments = "Source Table: blog")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("BlogResult")
    Optional<Blog> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2565824+08:00", comments = "Source Table: blog")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "BlogResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "author_id", property = "authorId", jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "likes", property = "likes", jdbcType = JdbcType.INTEGER),
            @Result(column = "clicks", property = "clicks", jdbcType = JdbcType.INTEGER),
            @Result(column = "collects", property = "collects", jdbcType = JdbcType.INTEGER),
            @Result(column = "comments", property = "comments", jdbcType = JdbcType.INTEGER),
            @Result(column = "first_push_date", property = "firstPushDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_push_date", property = "lastPushDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "topic_id", property = "topicId", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<Blog> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.258576+08:00", comments = "Source Table: blog")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.258576+08:00", comments = "Source Table: blog")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, blog, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2595732+08:00", comments = "Source Table: blog")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, blog, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2595732+08:00", comments = "Source Table: blog")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2595732+08:00", comments = "Source Table: blog")
    default int insert(Blog record) {
        return MyBatis3Utils.insert(this::insert, record, blog, c ->
                c.map(id).toProperty("id")
                        .map(authorId).toProperty("authorId")
                        .map(title).toProperty("title")
                        .map(likes).toProperty("likes")
                        .map(clicks).toProperty("clicks")
                        .map(collects).toProperty("collects")
                        .map(comments).toProperty("comments")
                        .map(firstPushDate).toProperty("firstPushDate")
                        .map(lastPushDate).toProperty("lastPushDate")
                        .map(topicId).toProperty("topicId")
                        .map(content).toProperty("content")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2615678+08:00", comments = "Source Table: blog")
    default int insertMultiple(Collection<Blog> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, blog, c ->
                c.map(id).toProperty("id")
                        .map(authorId).toProperty("authorId")
                        .map(title).toProperty("title")
                        .map(likes).toProperty("likes")
                        .map(clicks).toProperty("clicks")
                        .map(collects).toProperty("collects")
                        .map(comments).toProperty("comments")
                        .map(firstPushDate).toProperty("firstPushDate")
                        .map(lastPushDate).toProperty("lastPushDate")
                        .map(topicId).toProperty("topicId")
                        .map(content).toProperty("content")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2615678+08:00", comments = "Source Table: blog")
    default int insertSelective(Blog record) {
        return MyBatis3Utils.insert(this::insert, record, blog, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(authorId).toPropertyWhenPresent("authorId", record::getAuthorId)
                        .map(title).toPropertyWhenPresent("title", record::getTitle)
                        .map(likes).toPropertyWhenPresent("likes", record::getLikes)
                        .map(clicks).toPropertyWhenPresent("clicks", record::getClicks)
                        .map(collects).toPropertyWhenPresent("collects", record::getCollects)
                        .map(comments).toPropertyWhenPresent("comments", record::getComments)
                        .map(firstPushDate).toPropertyWhenPresent("firstPushDate", record::getFirstPushDate)
                        .map(lastPushDate).toPropertyWhenPresent("lastPushDate", record::getLastPushDate)
                        .map(topicId).toPropertyWhenPresent("topicId", record::getTopicId)
                        .map(content).toPropertyWhenPresent("content", record::getContent)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2655572+08:00", comments = "Source Table: blog")
    default Optional<Blog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, blog, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2655572+08:00", comments = "Source Table: blog")
    default List<Blog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, blog, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.266555+08:00", comments = "Source Table: blog")
    default List<Blog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, blog, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.266555+08:00", comments = "Source Table: blog")
    default Optional<Blog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2675518+08:00", comments = "Source Table: blog")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, blog, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2675518+08:00", comments = "Source Table: blog")
    static UpdateDSL<UpdateModel> updateAllColumns(Blog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(authorId).equalTo(record::getAuthorId)
                .set(title).equalTo(record::getTitle)
                .set(likes).equalTo(record::getLikes)
                .set(clicks).equalTo(record::getClicks)
                .set(collects).equalTo(record::getCollects)
                .set(comments).equalTo(record::getComments)
                .set(firstPushDate).equalTo(record::getFirstPushDate)
                .set(lastPushDate).equalTo(record::getLastPushDate)
                .set(topicId).equalTo(record::getTopicId)
                .set(content).equalTo(record::getContent);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2685494+08:00", comments = "Source Table: blog")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Blog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(authorId).equalToWhenPresent(record::getAuthorId)
                .set(title).equalToWhenPresent(record::getTitle)
                .set(likes).equalToWhenPresent(record::getLikes)
                .set(clicks).equalToWhenPresent(record::getClicks)
                .set(collects).equalToWhenPresent(record::getCollects)
                .set(comments).equalToWhenPresent(record::getComments)
                .set(firstPushDate).equalToWhenPresent(record::getFirstPushDate)
                .set(lastPushDate).equalToWhenPresent(record::getLastPushDate)
                .set(topicId).equalToWhenPresent(record::getTopicId)
                .set(content).equalToWhenPresent(record::getContent);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2695475+08:00", comments = "Source Table: blog")
    default int updateByPrimaryKey(Blog record) {
        return update(c ->
                c.set(authorId).equalTo(record::getAuthorId)
                        .set(title).equalTo(record::getTitle)
                        .set(likes).equalTo(record::getLikes)
                        .set(clicks).equalTo(record::getClicks)
                        .set(collects).equalTo(record::getCollects)
                        .set(comments).equalTo(record::getComments)
                        .set(firstPushDate).equalTo(record::getFirstPushDate)
                        .set(lastPushDate).equalTo(record::getLastPushDate)
                        .set(topicId).equalTo(record::getTopicId)
                        .set(content).equalTo(record::getContent)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-07-27T21:55:08.2705442+08:00", comments = "Source Table: blog")
    default int updateByPrimaryKeySelective(Blog record) {
        return update(c ->
                c.set(authorId).equalToWhenPresent(record::getAuthorId)
                        .set(title).equalToWhenPresent(record::getTitle)
                        .set(likes).equalToWhenPresent(record::getLikes)
                        .set(clicks).equalToWhenPresent(record::getClicks)
                        .set(collects).equalToWhenPresent(record::getCollects)
                        .set(comments).equalToWhenPresent(record::getComments)
                        .set(firstPushDate).equalToWhenPresent(record::getFirstPushDate)
                        .set(lastPushDate).equalToWhenPresent(record::getLastPushDate)
                        .set(topicId).equalToWhenPresent(record::getTopicId)
                        .set(content).equalToWhenPresent(record::getContent)
                        .where(id, isEqualTo(record::getId))
        );
    }
}