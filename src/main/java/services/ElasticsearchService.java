package services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import databaseConnections.ElasticsearchClientSingleton;

import java.io.IOException;

public class ElasticsearchService {
    ElasticsearchClient client = ElasticsearchClientSingleton.getClient();


    public ElasticsearchService() throws IOException {
    }
}
