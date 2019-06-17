package com.myapplication.weatherdemo.service.impl;

import com.myapplication.weatherdemo.exception.AppException;
import com.myapplication.weatherdemo.restclient.HttpClient;
import com.myapplication.weatherdemo.model.WeatherForecastData;
import com.myapplication.weatherdemo.openweathermap.OpenWeatherMapDataParser;
import com.myapplication.weatherdemo.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);

    private OpenWeatherMapDataParser openWeatherMapDataParser;

    private HttpClient httpClient;

    @Autowired
    public WeatherForecastServiceImpl(OpenWeatherMapDataParser openWeatherMapDataParser,
                                      HttpClient httpClient) {
        this.openWeatherMapDataParser = openWeatherMapDataParser;
        this.httpClient = httpClient;
    }

    @Override
    public WeatherForecastData getCurrentWeatherData(String cityName) throws AppException {
        ResponseEntity<String> response = httpClient.getCurrentWeatherDataByCity(cityName);
        if (StringUtils.isEmpty(response.getBody())) {
            throw new AppException("The respond is null or empty.");
        }

        WeatherForecastData weatherForecastData = openWeatherMapDataParser.parse(response.getBody());
        if (weatherForecastData == null) {
            throw new AppException(String.format("Get null value after parsing respond %s", response.getBody()));
        }

        LOGGER.debug("Current weather data after parsing: {} ", weatherForecastData.toString());
        return weatherForecastData;
    }
}
