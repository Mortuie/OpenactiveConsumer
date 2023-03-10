package com.example.openactiveconsumerandapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FacilityUseData extends BaseModel {
    private String type;
    private String name;
    private String attendeeInstructions;
    private Location location;
}
