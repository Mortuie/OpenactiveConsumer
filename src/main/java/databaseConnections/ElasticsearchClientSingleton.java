package databaseConnections;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.List;

public class ElasticsearchClientSingleton {
    private static ElasticsearchClient client = null;
    private static ElasticsearchTransport elasticsearchTransport = null;
    private static List<String> neededIndexes = List.of("users");

    private ElasticsearchClientSingleton() throws IOException {
        RestClient restClient = RestClient.builder(
            new HttpHost("localhost", 9200)
        ).build();

        elasticsearchTransport = new RestClientTransport(
            restClient, new JacksonJsonpMapper()
        );

        client = new ElasticsearchClient(elasticsearchTransport);

        this.setupIndexes();
    }

    public static ElasticsearchClient getClient() throws IOException {
        if (client == null) {
            new ElasticsearchClientSingleton();
        }

        return client;
    }

    public static void closeClient() throws IOException {
        elasticsearchTransport.close();
    }


    private void setupIndexes() throws IOException {
        for (String index: neededIndexes) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(index)
                .build();
            try {
                client.indices().create(createIndexRequest);
            } catch (ElasticsearchException e) {
                boolean indexAlreadyExists = e.getMessage().contains("already exists");
                // if index already exists do nothing
                if (!indexAlreadyExists) {
                    throw e;
                }
            }
        }
    }
}
