package com.myapplication.weatherdemo.openweathermap;

import com.myapplication.weatherdemo.exception.AppException;
import com.myapplication.weatherdemo.model.WeatherForecastData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapDataParserTest {
    @Spy
    private OpenWeatherMapDataParser parser;

    @Test
    public void testParseOpenWeatherMapAPIRespondSuccessfully() throws AppException {
        String openWeatherMapAPIRespond = "{\"coord\":{\"lon\":-122.67,\"lat\":45.63},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":21.23,\"pressure\":1018,\"humidity\":49,\"temp_min\":18,\"temp_max\":23},\"visibility\":16093,\"wind\":{\"speed\":1.5,\"deg\":350},\"clouds\":{\"all\":1},\"dt\":1534658160,\"sys\":{\"type\":1,\"id\":2963,\"message\":0.0041,\"country\":\"US\",\"sunrise\":1534684586,\"sunset\":1534734663},\"id\":5814616,\"name\":\"Vancouver\",\"cod\":200}";
        WeatherForecastData weatherForecastData = parser.parse(openWeatherMapAPIRespond);
        String expectedCityName = "Vancouver";
        String expectedOverallWeatherDescr = "clear sky";
        double expectedTemperatureInC = 21.23d;
        double expectedTemperatureInF = 70.21d;
        LocalDate expectedCurrentDate = LocalDate.of(2018, 8, 18);
        LocalTime expectedSunsetTime = LocalTime.of(20, 11, 03);
        LocalTime expectedSunriseTime = LocalTime.of(6, 16, 26);

        assertEquals("The extracted city name should match the expected city name.", expectedCityName,
                weatherForecastData.getCityName());
        assertEquals("The extracted overall weather description should match the expected description.",
                expectedOverallWeatherDescr, weatherForecastData.getOverallWeatherDescr());
        assertEquals(expectedTemperatureInC, weatherForecastData.getTemperatureInC(), 0.01);
        assertEquals(expectedTemperatureInF, weatherForecastData.getGetTemperatureInF(), 0.01);
        assertEquals("The current date should match the expected date.", expectedCurrentDate,
                weatherForecastData.getCurrentDate());
        assertEquals("The sunset time should match the expected time", expectedSunsetTime,
                weatherForecastData.getSunsetTime());
        assertEquals("The sunrise time should match the expected time", expectedSunriseTime,
                weatherForecastData.getSunriseTime());
    }

    //TODO
    @Test
    public void testFailExtractingCityName() throws AppException {
        String openWeatherMapAPIRespond = "{\"id\": 264373}";
        WeatherForecastData weatherForecastData = parser.parse(openWeatherMapAPIRespond);
        String expectedCityName = "";
        assertEquals("The extracted city name should be empty string.", expectedCityName,
                weatherForecastData.getCityName());
    }

    //TODO
    @Test
    @Ignore
    public void testFailExtractingCurrentDate() {
        fail("Test not implemented yet.");
    }

    //TODO
    @Test
    @Ignore
    public void testFailExtractingOverallWeatherDescr() {
        fail("Test not implemented yet.");
    }

    //TODO
    @Test
    @Ignore
    public void testFailExtractingTemperatureInC() {
        fail("Test not implemented yet.");
    }

    //TODO
    @Test
    @Ignore
    public void testFailExtractingTemperatureInF() {
        fail("Test not implemented yet.");
    }

    //TODO
    @Test
    @Ignore
    public void testFailExtractingSunriseTime() {
        fail("Test not implemented yet.");
    }

    //TODO
    @Test
    @Ignore
    public void testFailExtractingSunsetTime() {
        fail("Test not implemented yet.");
    }

}
