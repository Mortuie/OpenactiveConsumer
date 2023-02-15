package com.example.openactiveconsumerandapi.scheduledTasks;


import com.example.openactiveconsumerandapi.models.Users;
import com.example.openactiveconsumerandapi.services.ElasticsearchService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling()
public class UrlScraper  {
    private static final String url = "https://gorest.co.in/public/v2/users";
    //    private final static String url = "https://opendata.leisurecloud.live/api/feeds/Oxford-Univesity-live-facility-uses";
    private ObjectMapper objectMapper;
    private ElasticsearchService elasticsearchService;


    public UrlScraper(ObjectMapper objectMapper, ElasticsearchService elasticsearchService) {
        this.objectMapper = objectMapper;
        this.elasticsearchService = elasticsearchService;
    }

    @Scheduled(fixedDelay = 1000)
    public void memes() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = client.execute(new HttpGet(url));

            String bodyAsString = EntityUtils.toString(response.getEntity());
            List<Users> users = objectMapper.readValue(bodyAsString, new TypeReference<>() {});
            elasticsearchService.saveObjectsToIndex(ElasticsearchService.USERS_INDEX, users);
        } catch (Exception e) {
            System.out.println("\n\nERROR\n\n");
            System.out.println(e);
        }
    }
}
