package com.example.openactiveconsumerandapi.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.stereotype.Component;


@Component
public class ElasticsearchService {
    private final ElasticsearchClient esClient;

    public ElasticsearchService(ElasticsearchClient esClient) {
        this.esClient = esClient;
    }

}
