package com.demo.elastic;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/20
 * Time: 14:17
 * Description: No Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {
    @Autowired
    RestHighLevelClient client;
    //创建索引库
    /*
       PUT /elasticsearch_test
       {
        "settings": {},
        "mappings": {
            "properties": {
                "description": {
                    "type": "text",
                    "analyzer": "ik_max_word"
                 },
                 "name": {
                    "type": "keyword" },
                 "pic": {
                    "type": "text",
                    "index": false },
                 "studymodel": {
                    "type": "keyword"
                 }
            }
          }
        }
      */
    @Test
    public void createIndex() throws Exception{
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("elasticsearch_test");
        createIndexRequest.settings(Settings.builder().build());
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .field("properties")
                    .startObject()
                        .field("studymodel")
                            .startObject()
                            .field("index","true")
                            .field("type","keyword")
                            .endObject()
                        .field("name")
                            .startObject()
                            .field("index","true")
                            .field("type","integer")
                            .endObject()
                        .field("description")
                            .startObject()
                            .field("index", "true")
                            .field("type", "text")
                            .field("analyzer", "ik_max_word")
                            .endObject()
                        .field("pic")
                            .startObject()
                            .field("index", "false")
                            .field("type", "text")
                            .endObject()
                    .endObject()
                .endObject();
        createIndexRequest.mapping("doc",xContentBuilder);
        //操作索引的客户端
        IndicesClient indicesClient = client.indices();
        //执行创建索引库
        CreateIndexResponse createIndexResponse =
                indicesClient.create(createIndexRequest, RequestOptions.DEFAULT);
        //得到响应
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }
}
