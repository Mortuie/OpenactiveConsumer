package com.example.openactiveconsumerandapi.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.example.openactiveconsumerandapi.config.ElasticSearchIndexes;
import com.example.openactiveconsumerandapi.models.BaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
@Slf4j
public class ElasticsearchService {
    private final ElasticsearchClient esClient;
    private final ObjectMapper objectMapper;
    private final List<String> neededIndicies;

    public ElasticsearchService(ElasticsearchClient esClient, ObjectMapper objectMapper) {
        this.esClient = esClient;
        this.objectMapper = objectMapper;
        this.neededIndicies = ElasticSearchIndexes.getNeededIndicies();
    }

    @PostConstruct
    public void setupIndicies() throws IOException {
        for (String index: neededIndicies) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(index)
                .build();

            try {
                esClient.indices().create(createIndexRequest);
            } catch (ElasticsearchException e) {
                boolean indexAlreadyExists = e.getMessage().contains("already exists");
                if (!indexAlreadyExists) {
                    throw e;
                }
            }
        }
    }

    public <T extends BaseModel> void saveObjectToIndex(String index, T obj) throws IOException {
        try {
            esClient.index(i -> i
                .index(index)
                .id(obj.getId())
                .document(obj)
            );
        } catch (Exception e) {
            System.out.println("memes");
            e.printStackTrace();
            throw e;
        }
    }

    public <T extends BaseModel> void saveObjectsToIndex(String index, List<T> objectList) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (BaseModel obj: objectList) {
            br.operations(op -> op
                .index(idx -> idx
                    .index(index)
                    .id(obj.getId())
                    .document(obj)
                )
            );
        }

        BulkResponse result = esClient.bulk(br.build());

        if (result.errors()) {
            log.atError().log("Bulk upsert had errors");
            for (BulkResponseItem item: result.items()) {
                if (item.error() != null) {
                    log.atError().log(item.error().reason());
                }
            }
        }
    }
}
