package com.example.openactiveconsumerandapi.routes;


import com.example.openactiveconsumerandapi.models.FacilityUse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facility-uses")
public class FacilityUses {
    FacilityUses() {}


    @GetMapping("/:id")
    public FacilityUse getFacilityUseById() {


        return FacilityUse.builder().build();
    }

    @GetMapping("/")
    public List<FacilityUse> searchFacilityUse() {


        return List.of(FacilityUse.builder().build());
    }
}
