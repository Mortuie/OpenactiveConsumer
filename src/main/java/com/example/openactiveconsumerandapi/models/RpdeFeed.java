package com.example.openactiveconsumerandapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class RpdeFeed {
    // next url
    private String next;
    private List<FacilityUse> items;
}
