package com.example.exchangeratebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalRate implements Serializable {
    @JsonProperty("base")
    private String base;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("rates")
    private Map<String, Map<String, Double>> rates;

}
