package com.example.demo.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author XZQ
 * @Date 2021/9/4 23:52
 **/
public class TestElastic {
    public static void main(String[] args) {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        IndexRequest request = new IndexRequest("posts4");
        request.id("333");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2021-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        try {
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            System.out.println(indexResponse.toString());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
