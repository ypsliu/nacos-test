package com.demo.elastic.service;

import com.demo.elastic.config.DBHelper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/9
 * Time: 16:27
 * Description: No Description
 */
@Slf4j
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private RestHighLevelClient client;
    private static final String POSITIOIN_INDEX = "position";

    @Override
    public List<Map<String, Object>> searchPos(String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }//getPosition(keyword); //条件搜索
        SearchRequest searchRequest = new SearchRequest(POSITIOIN_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //分页 index = (当前页-1)*一页显示条数
        searchSourceBuilder.from((pageNo - 1) * pageSize);
        searchSourceBuilder.size(pageSize);
        //精准匹配
        // TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("positionName",keyword);
        // searchSourceBuilder.query(termQueryBuilder);
        QueryBuilder builder = QueryBuilders.matchQuery("positionName", keyword);
        searchSourceBuilder.query(builder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        SearchHit[] hits = searchResponse.getHits().getHits();
        System.out.println(hits.length);
        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }

    @Override
    public void importAll() throws IOException {
        writeMysqlDataToES(POSITIOIN_INDEX);
    }

    private void writeMysqlDataToES(String tableName) {
        BulkProcessor bulkProcessor = getBulkProcessor(client);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.getConn();
            log.info("Start handle data :" + tableName);
            String sql = "SELECT * from " + tableName;
            ps = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            // 根据自己需要 设置
            ps.setFetchSize(20);
            rs = ps.executeQuery();
            ResultSetMetaData colData = rs.getMetaData();
            ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
            // bulkProcessor 添加的数据支持的方式并不多，查看其api发现其支持map键值对的 方式，故笔者在此将查出来的数据转换成hashMap方式
            HashMap<String, String> map = null;
            int count = 0;
            String c = null;
            String v = null;
            while (rs.next()) {
                count++;
                map = new HashMap<String, String>(128);
                for (int i = 1; i <= colData.getColumnCount(); i++) {
                    c = colData.getColumnName(i);
                    v = rs.getString(c);
                    map.put(c, v);
                }dataList.add(map);
                // 每1万条写一次，不足的批次的最后再一并提交
                if (count % 10000 == 0) {
                    log.info("Mysql handle data number : " + count);
                    // 将数据添加到 bulkProcessor 中
                    for (HashMap<String, String> hashMap2 : dataList) {
                        bulkProcessor.add( new IndexRequest(POSITIOIN_INDEX).source(hashMap2));
                    }
                    // 每提交一次便将map与list清空
                    map.clear(); dataList.clear();
                }
            }// 处理未提交的数据
             for (HashMap<String, String> hashMap2 : dataList) {
                 bulkProcessor.add( new IndexRequest(POSITIOIN_INDEX).source(hashMap2));
                 System.out.println(hashMap2);
             }
             log.info("-------------------------- Finally insert number total : " + count);
             // 将数据刷新到es, 注意这一步执行后并不会立即生效，取决于bulkProcessor设置的 刷新时间
            bulkProcessor.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
                boolean terminatedFlag = bulkProcessor.awaitClose(150L, TimeUnit.SECONDS);
                log.info(String.valueOf(terminatedFlag));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private BulkProcessor getBulkProcessor(RestHighLevelClient client) {
        BulkProcessor bulkProcessor = null;
        try {
            BulkProcessor.Listener listener = new BulkProcessor.Listener() {
                @Override
                public void beforeBulk(long executionId, BulkRequest request) {
                    log.info("Try to insert data number : " + request.numberOfActions());
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                    log.info("************** Success insert data number : " + request.numberOfActions() + " , id: " + executionId);
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                    log.error("Bulk is unsuccess : " + failure + ", executionId: " + executionId);
                }
            };
            BiConsumer<BulkRequest, ActionListener<BulkResponse>> bulkConsumer = (request, bulkListener) -> client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener);
            BulkProcessor.Builder builder = BulkProcessor.builder(bulkConsumer, listener);
            builder.setBulkActions(5000);
            builder.setBulkSize(new ByteSizeValue(100L, ByteSizeUnit.MB));
            builder.setConcurrentRequests(10);
            builder.setFlushInterval(TimeValue.timeValueSeconds(100L));
            builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3));
            // 注意点：让参数设置生效
            bulkProcessor = builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                bulkProcessor.awaitClose(100L, TimeUnit.SECONDS);
            } catch (Exception e1) {
                log.error(e1.getMessage());
            }
        } return bulkProcessor;
    }
}
