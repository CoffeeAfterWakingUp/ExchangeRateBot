package com.example.exchangeratebot.service;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author Olzhas Syrbek
 * * Special app invoker for Heroku free plan.
 * * Provides app not too sleep after 30 min inactive.
 * *
 */


@Service
@Slf4j
@Getter
@Setter
public class PingTask {

    @Value("${pingtask.url}")
    private String url;

    @Scheduled(fixedRateString = "${pingtask.period}")
    public void pingMe() {
        try {
            URL url1 = new URL(getUrl());
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            log.info("Ping {}, OK: response code {}", url1.getHost(), connection.getResponseCode());
            connection.disconnect();
        } catch (IOException e) {
            log.error("Ping FAILED");
            e.printStackTrace();
        }

    }
}
