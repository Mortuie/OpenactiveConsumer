package services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import databaseConnections.ElasticsearchClientSingleton;
import models.User;

import java.io.IOException;
import java.util.List;

public class ElasticsearchService {
    private static ElasticsearchClient client = null;
    private static ElasticsearchService service = null;

    private ElasticsearchService() throws IOException {
        client = ElasticsearchClientSingleton.getClient();
    }

    public static ElasticsearchService getEsService() throws IOException {
        if (service == null) {
            service = new ElasticsearchService();
        }

        return service;
    }


    public void insertUser(String index, User object) throws IOException {
        IndexResponse response = client.index(i -> i
            .index(index)
            .id(String.valueOf(object.getId()))
            .document(object)
        );

        System.out.println(response.version());
    }

    public void insertBulkUser(String index, List<User> users) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (User user: users) {
            br.operations(op -> op
                .index(i -> i
                    .index(index)
                    .id(String.valueOf(user.getId()))
                    .document(user)
                )
            );
        }

        BulkResponse response = client.bulk(br.build());

        System.out.println(response.errors());
    }
}
