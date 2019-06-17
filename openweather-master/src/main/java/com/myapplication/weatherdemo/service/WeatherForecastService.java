package com.myapplication.weatherdemo.service;

import com.myapplication.weatherdemo.exception.AppException;
import com.myapplication.weatherdemo.model.WeatherForecastData;

/**
 * An interface to service restclient's request for current weather data
 */
public interface WeatherForecastService {
    WeatherForecastData getCurrentWeatherData(String city) throws AppException;
}
