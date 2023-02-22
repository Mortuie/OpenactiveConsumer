package com.example.openactiveconsumerandapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FacilityUse {
    private Long modified;
    private String kind;
    private String state;
    private FacilityUseData data;
}
