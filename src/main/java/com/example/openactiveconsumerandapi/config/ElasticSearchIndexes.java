package com.example.openactiveconsumerandapi.config;

import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ElasticSearchIndexes {
    public final static String DATA_FEEDS_INDEX = "data_feeds_index";
    public final static String FACILITY_USE_INDEX = "facility_use_index";
    private final static List<String> neededIndicies = List.of(DATA_FEEDS_INDEX);

    public static List<String> getNeededIndicies() {
        return neededIndicies;
    }
}
