package com.example.openactiveconsumerandapi.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Address {
    private String type;
    private String postalCode;
}
