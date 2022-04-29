package com.demo.elastic.example;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Objects;

/**
 * es工具类
 */
public class EsSimpleQuery {

    /**
     * 查询构造器：对应分页，排序功能
     */
    private NativeSearchQueryBuilder searchBuilder;

    /**
     * 条件构造器：对应查询条件
     */
    private BoolQueryBuilder conditionBuilder;


    /**
     * 构造器，初始化查询构造器和条件构造器
     *
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery() {
        this.searchBuilder = new NativeSearchQueryBuilder();
        this.conditionBuilder = QueryBuilders.boolQuery();
    }

    /**
     * 分页
     *
     * @param page 页码
     * @param size 一页数据量
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery page(Integer page, Integer size) {
        if (Objects.isNull(page) || Objects.isNull(size) || page < 1 || size < 1) {
            return this;
        }
        this.searchBuilder.withPageable(PageRequest.of(page - 1, size));
        return this;
    }

    /**
     * 排序，目前不支持多字段排序，如果重复使用，则替换之前的排序方式
     *
     * @param fieldName 属性名
     * @param order     生序、降序
     * @author Lison 2022-03-23
     */
//    public EsSimpleQuery sort(String fieldName, OrderTypeEnum order) {
//        return sort(true, fieldName, order);
//    }

    /**
     * 排序，目前不支持多字段排序，如果重复使用，则替换之前的排序方式
     *
     * @param condition 根据条件决定是否排序
     * @param fieldName 属性名
     * @param order     生序、降序
     * @author Lison 2022-03-23
     */
//    public EsSimpleQuery sort(boolean condition, String fieldName, OrderTypeEnum order) {
//        if (condition) {
//            this.searchBuilder = this.searchBuilder.withSorts(SortBuilders.fieldSort(fieldName).order(getEsOrder(order)));
//        }
//        return this;
//    }

    /**
     * and 连接多字段匹配，多字段条件之间采用or连接
     * 相当于：and (fieldName1 = query or fieldName2 = query or fieldName2 = query ...)
     *
     * @param condition  根据条件决定是否附加该条件
     * @param query      搜索词
     * @param fieldNames 字段名称数据
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andMultiMatch(boolean condition, Object query, String... fieldNames) {
        if (condition) {
            this.conditionBuilder = this.conditionBuilder.must(QueryBuilders.multiMatchQuery(query, fieldNames));
        }
        return this;
    }

    /**
     * and 连接字段匹配
     * 相当于：and fieldName = text
     *
     * @param condition 根据条件决定是否附加该条件
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andMatch(boolean condition, String fieldName, Object text) {
        if (condition) {
            this.conditionBuilder = this.conditionBuilder.must(QueryBuilders.matchQuery(fieldName, text));
        }
        return this;
    }

    /**
     * and 连接字段匹配
     * 相当于：and fieldName = text
     *
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andMatch(String fieldName, Object text) {
        return andMatch(true, fieldName, text);
    }

    /**
     * and 连接字段不匹配
     * 相当于： and fieldName != text
     *
     * @param condition 根据条件决定是否附加该条件
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andNotMatch(boolean condition, String fieldName, Object text) {
        if (condition) {
            this.conditionBuilder = this.conditionBuilder.mustNot(QueryBuilders.matchQuery(fieldName, text));
        }
        return this;
    }

    /**
     * and 连接字段不匹配
     * 相当于： and fieldName != text
     *
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andNotMatch(String fieldName, Object text) {
        return andNotMatch(true, fieldName, text);
    }


    /**
     * and 连接范围查询
     *
     * @param fieldName 字段名
     * @param from      起始
     * @param to        结束
     * @author Lison 2022-03-24
     */
    public EsSimpleQuery andRange(String fieldName, Object from, Object to) {

        if (Objects.isNull(from) && Objects.isNull(to)) {
            return this;
        }

        RangeQueryBuilder rangeBuilder = QueryBuilders.rangeQuery(fieldName);

        if (Objects.nonNull(from)) {
            rangeBuilder = rangeBuilder.from(from);
        }

        if (Objects.nonNull(to)) {
            rangeBuilder = rangeBuilder.to(to);
        }

        this.conditionBuilder = this.conditionBuilder.filter(rangeBuilder);

        return this;
    }

    /**
     * and 连接in操作
     * 相当于： and fieldName in (a,b,c)
     *
     * @param condition 根据条件决定是否附加该条件
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andIn(boolean condition, String fieldName, Object... text) {
        if (condition) {
            this.conditionBuilder = this.conditionBuilder.must(QueryBuilders.termsQuery(fieldName, text));
        }
        return this;
    }

    /**
     * and 连接in操作
     * 相当于： and fieldName in (a,b,c)
     *
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andIn(String fieldName, Object... text) {
        return andIn(true, fieldName, text);
    }

    /**
     * and 连接 not in操作
     * 相当于： and fieldName not in (a,b,c)
     *
     * @param condition 根据条件决定是否附加该条件
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andNotIn(boolean condition, String fieldName, Object text) {
        if (condition) {
            this.conditionBuilder = this.conditionBuilder.mustNot(QueryBuilders.matchQuery(fieldName, text));
        }
        return this;
    }

    /**
     * and 连接 not in操作
     * 相当于： and fieldName not in (a,b,c)
     *
     * @param fieldName 字段名称
     * @param text      值
     * @author Lison 2022-03-23
     */
    public EsSimpleQuery andNotIn(String fieldName, Object... text) {
        return andNotIn(true, fieldName, text);
    }


    /**
     * 条件构造器添加到搜索构造器中，并构建搜索实例
     *
     * @author Lison 2022-03-23
     */
    public NativeSearchQuery build() {
        return this.searchBuilder.withQuery(this.conditionBuilder).build();
    }


    /**
     * 通用枚举转es排序枚举
     *
     * @param status 状态
     * @author Lison 2022-03-23
     */
//    private SortOrder getEsOrder(OrderTypeEnum status) {
//        if (status.equals(OrderTypeEnum.ASC)) {
//            return SortOrder.ASC;
//        }
//        return SortOrder.DESC;
//    }

}
