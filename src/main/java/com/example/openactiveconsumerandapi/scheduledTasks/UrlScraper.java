package com.example.openactiveconsumerandapi.scheduledTasks;


import com.example.openactiveconsumerandapi.config.ElasticSearchIndexes;
import com.example.openactiveconsumerandapi.models.FeedMetadata;
import com.example.openactiveconsumerandapi.models.RpdeFeed;
import com.example.openactiveconsumerandapi.services.ElasticsearchService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@EnableScheduling
public class UrlScraper  {
    private ObjectMapper objectMapper;
    private ElasticsearchService elasticsearchService;

    public UrlScraper(ObjectMapper objectMapper, ElasticsearchService elasticsearchService) {
        this.objectMapper = objectMapper;
        this.elasticsearchService = elasticsearchService;
    }


    @Scheduled(fixedDelay = 1000)
    public void memes() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String tempUrl = "https://opendata.leisurecloud.live/api/feeds/Oxford-Univesity-live-facility-uses";
        try {
            while (true) {
                System.out.println("next url: " + tempUrl);
                CloseableHttpResponse response = client.execute(new HttpGet(tempUrl));
                String bodyAsString = EntityUtils.toString(response.getEntity());
                RpdeFeed rpdeFeed = objectMapper.readValue(bodyAsString, new TypeReference<>() {});
                tempUrl = rpdeFeed.getNext();
                int sizeOfFeed = rpdeFeed.getItems().size();
                if (sizeOfFeed == 0) {
                    System.out.println("No more items left...");
                    FeedMetadata feedMetadata = FeedMetadata.builder()
                        .id("OXFORD_UNI_FUs")
                        .nextUrl(tempUrl)
                        .nextFetchTime(
                            LocalDateTime.now().plusSeconds(30)
                        )
                        .build();
                    System.out.println("Getting to here");
                    elasticsearchService.saveObjectToIndex(ElasticSearchIndexes.DATA_FEEDS_INDEX, feedMetadata);
                    System.out.println("Getting to here22");
                    break;
                }
            }
            System.out.println("We are done fetching all the pages for feed" + tempUrl);
        } catch (Exception e) {
            System.out.println("\n\nERROR\n\n");
            System.out.println(e.getMessage());
        }
    }
}
