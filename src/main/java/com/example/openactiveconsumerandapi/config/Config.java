package com.example.openactiveconsumerandapi.config;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public JacksonJsonpMapper jacksonJsonpMapper() {
        JacksonJsonpMapper jacksonJsonpMapper = new JacksonJsonpMapper();

        jacksonJsonpMapper.objectMapper().registerModule(new JavaTimeModule());
        jacksonJsonpMapper.objectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return jacksonJsonpMapper;
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient restClient = RestClient.builder(
            new HttpHost("localhost", 9200)
        ).build();
        ElasticsearchTransport elasticsearchTransport = new RestClientTransport(
            restClient, jacksonJsonpMapper()
        );
        return new ElasticsearchClient(elasticsearchTransport);
    }
}
