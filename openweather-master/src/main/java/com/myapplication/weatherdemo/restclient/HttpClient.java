package com.myapplication.weatherdemo.restclient;

import com.myapplication.weatherdemo.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * A restclient restclient to consume external Restful services
 */
@Component
public class HttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    private String url;

    private String appid;

    private RestTemplate restTemplate;

    public HttpClient(@Value("${url}") String url,
            @Value("${appid}") String appid,
            RestTemplateBuilder restTemplateBuilder) {
        this.url = url;
        this.appid = appid;
        this.restTemplate = restTemplateBuilder.build();
     }

    public ResponseEntity<String> getCurrentWeatherDataByCity(String cityName) throws AppException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("q", cityName)
                .queryParam("appid", appid);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(builder.build(false).toUriString(), String.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                LOGGER.debug(response.getBody());
                return response;
            } else if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                LOGGER.error("Bad request returned when calling {}", builder.toUriString());
                throw new AppException(String.format("Bad request returned when calling %", builder.toUriString()));
            } else {
                LOGGER.error("Http status code returned {} when calling {}", response.getStatusCode(),
                        builder.toUriString());
                throw new AppException(String.format("Http status code returned %s when calling %s",
                        response.getStatusCode(), builder.toUriString()));
            }
        } catch (RestClientException ex) {
            LOGGER.error("Failed to call {}", builder.toUriString());
            throw new AppException(ex, String.format("Failed to call %s", builder.toUriString()));
        }
    }
}
