package com.example.openactiveconsumerandapi.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseModel {
    private String name;
    private String email;
    private String gender;
    private String status;
}
