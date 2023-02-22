package com.example.openactiveconsumerandapi.config;

import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ElasticSearchIndexes {
    public final static String DATA_FEEDS_INDEX = "data_feeds_index";
    private final List<String> neededIndicies = List.of(DATA_FEEDS_INDEX);

    public List<String> getNeededIndicies() {
        return neededIndicies;
    }
}
