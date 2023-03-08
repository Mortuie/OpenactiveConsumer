package com.example.openactiveconsumerandapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class FeedMetadata extends BaseModel {
    private String nextUrl;
    private LocalDateTime nextFetchTime;
}
