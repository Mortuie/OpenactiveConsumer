package com.example.openactiveconsumerandapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Location {
    private String type;
    private String identifier;
    private String name;
    private String description;
    private Address address;
}
