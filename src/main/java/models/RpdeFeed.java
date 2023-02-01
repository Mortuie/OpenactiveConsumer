package models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RpdeFeed {
    private String next;
}
