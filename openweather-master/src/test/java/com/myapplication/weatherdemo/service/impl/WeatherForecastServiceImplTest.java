package com.myapplication.weatherdemo.service.impl;

import com.myapplication.weatherdemo.exception.AppException;
import com.myapplication.weatherdemo.model.WeatherForecastData;
import com.myapplication.weatherdemo.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@TestPropertySource(locations= "classpath:application-test.properties")
public class CurrentWeatherServiceImplTest {
    @Autowired
    private WeatherForecastServiceImpl currentWeatherService;

    @Test
    public void testGetCurrentWeatherData() throws AppException {
        String cityName = "Vancouver";
        WeatherForecastData weatherForecastData = currentWeatherService.getCurrentWeatherData(cityName);
        assertEquals("City name should match the input city name.", cityName, weatherForecastData.getCityName());
    }
}
