package com.myapplication.weatherdemo.web.api;

import com.myapplication.weatherdemo.exception.AppException;
import com.myapplication.weatherdemo.model.WeatherForecastData;
import com.myapplication.weatherdemo.service.impl.WeatherForecastServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for Restful services
 */
@RestController
@RequestMapping("/current-weather-data")
public class WeatherForecastController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherForecastController.class);

    private WeatherForecastServiceImpl currentWeatherService;

    @Autowired
    public WeatherForecastController(WeatherForecastServiceImpl currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    /**
     * @deprecated  To be removed by next release
     */
    @Deprecated
    @GetMapping("/{cityName}")
    WeatherForecastData getCurrentWeatherData(@PathVariable String cityName) {
        try {
            return currentWeatherService.getCurrentWeatherData(cityName);
        } catch (AppException e) {
            LOGGER.error("Failed to call current weather service to get current weather data for city {}", cityName);
            return null;
        }
    }
}
