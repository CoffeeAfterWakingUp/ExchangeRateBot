package com.example.exchangeratebot.service;

import com.example.exchangeratebot.enums.CurrencyBase;
import com.example.exchangeratebot.model.HistoricalRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@Slf4j
public class ExchangeRateHistoryService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeRateHistoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HistoricalRate getHistoricalRatesToTenge(LocalDate startDate, LocalDate endDate, String base) {
        HistoricalRate response = null;
        String symbol = CurrencyBase.KZT.getCurrencyText();
        try {
            String uri = "https://api.apilayer.com/exchangerates_data/timeseries?start_date={startDate}&end_date={endDate}&base={base}&symbols={symbol}";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("apikey", "j9hXT1cpaYJKmNnF4iQc0C7YoyhZiclz");
            httpHeaders.add("user-agent", "Application");
            HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

            response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<HistoricalRate>() {
                    },
                    startDate, endDate, base, symbol).getBody();
            log.info("Response:{}", response);
            return response;
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            throw e;
        }
    }


}
