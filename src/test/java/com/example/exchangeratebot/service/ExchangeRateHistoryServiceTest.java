package com.example.exchangeratebot.service;

import com.example.exchangeratebot.model.HistoricalRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Map;

class ExchangeRateHistoryServiceTest {

    private static final String json = "{\n" +
            "    \"success\": true,\n" +
            "    \"timeseries\": true,\n" +
            "    \"start_date\": \"2022-04-21\",\n" +
            "    \"end_date\": \"2022-05-01\",\n" +
            "    \"base\": \"USD\",\n" +
            "    \"rates\": {\n" +
            "        \"2022-04-21\": {\n" +
            "            \"KZT\": 445.61015\n" +
            "        },\n" +
            "        \"2022-04-22\": {\n" +
            "            \"KZT\": 443.777114\n" +
            "        },\n" +
            "        \"2022-04-23\": {\n" +
            "            \"KZT\": 445.252023\n" +
            "        },\n" +
            "        \"2022-04-24\": {\n" +
            "            \"KZT\": 445.252023\n" +
            "        },\n" +
            "        \"2022-04-25\": {\n" +
            "            \"KZT\": 449.464501\n" +
            "        },\n" +
            "        \"2022-04-26\": {\n" +
            "            \"KZT\": 451.804353\n" +
            "        },\n" +
            "        \"2022-04-27\": {\n" +
            "            \"KZT\": 447.317041\n" +
            "        },\n" +
            "        \"2022-04-28\": {\n" +
            "            \"KZT\": 446.489759\n" +
            "        },\n" +
            "        \"2022-04-29\": {\n" +
            "            \"KZT\": 444.845146\n" +
            "        },\n" +
            "        \"2022-04-30\": {\n" +
            "            \"KZT\": 444.845146\n" +
            "        },\n" +
            "        \"2022-05-01\": {\n" +
            "            \"KZT\": 444.845146\n" +
            "        }\n" +
            "    }\n" +
            "}";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetHistoricalRatesToTengeResponse() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        HistoricalRate historicalRate = mapper.readValue(json, HistoricalRate.class);
        System.out.println(historicalRate);
        StringBuilder sb = new StringBuilder();
        Map<String, Map<String, Double>> rates = historicalRate.getRates();
        sb.append("Start Date:").append(historicalRate.getStartDate()).append("\n");
        sb.append("End Date:").append(historicalRate.getEndDate()).append("\n");
        sb.append("\n");
        rates.forEach((key, value) -> {
            sb.append(key).append(":").append("\n");
            value.forEach((key1, value1) -> {
                sb.append(key1).append(": ").append(value1).append("\n");
            });
            sb.append("\n");
        });


        System.out.println(sb.toString());

        Assertions.assertNotNull(sb);
    }
}