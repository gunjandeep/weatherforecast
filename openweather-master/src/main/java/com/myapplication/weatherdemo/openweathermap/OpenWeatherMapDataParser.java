package com.myapplication.weatherdemo.openweathermap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapplication.weatherdemo.exception.AppException;
import com.myapplication.weatherdemo.model.WeatherForecastData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.*;

/**
 * A parser to extract data from OpenWeatherMap API Respond
 */
@Component
public class OpenWeatherMapDataParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapDataParser.class);

    private static final String API_RESPOND_NAME = "name";

    private static final String API_RESPOND_WEATHER = "weather";

    private static final String API_RESPOND_MAIN = "main";

    private static final String API_RESPOND_TEMP = "temp";

    private static final String API_RESPOND_SYS = "sys";

    private static final String API_RESPOND_SUNSET = "sunset";

    private static final String API_RESPOND_SUNRISE = "sunrise";

    private static final String API_RESPOND_DT = "dt";

    private static final String API_RESPOND_DESCRIPTION = "description";

    public WeatherForecastData parse(String openWeatherMapAPIRespond) throws AppException {
        LOGGER.debug(openWeatherMapAPIRespond);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(openWeatherMapAPIRespond);
            WeatherForecastData weatherForecastData = new WeatherForecastData();
            weatherForecastData.setCityName(extractCityName(jsonNode));
            weatherForecastData.setCurrentDate(extractCurrentDate(jsonNode));
            weatherForecastData.setOverallWeatherDescr(extractOverallWeatherDescrFromJsonNode(jsonNode));
            weatherForecastData.setTemperatureInC(extractTemperatureFromJsonNode(jsonNode));
            weatherForecastData.setGetTemperatureInF(convertCelsiusToFahrenheit(weatherForecastData.getTemperatureInC()));
            weatherForecastData.setSunriseTime(extractSunriseTimeFromJsonNode(jsonNode));
            weatherForecastData.setSunsetTime(extractSunsetTimeFromJsonNode(jsonNode));
            return weatherForecastData;
        } catch (IOException e) {
            String errorMessage = String.format("Failed to parse the respond from OpenWeatherMap: %s",
                    openWeatherMapAPIRespond);
            throw new AppException(e, errorMessage);
        }
    }

    private String extractCityName(JsonNode jsonNode) {
        JsonNode nameNode = jsonNode.get(API_RESPOND_NAME);
        if (nameNode == null) {
            LOGGER.error("Cannot find city name from respond.");
            return "";
        }

        return nameNode.textValue();
    }

    private LocalDate extractCurrentDate(JsonNode jsonNode) {
        JsonNode dateNode = jsonNode.get(API_RESPOND_DT);
        if (dateNode == null) {
            LOGGER.error("Cannot find current date from respond.");
            return LocalDate.of(2000, 1, 1);
        }

        long currentDate = dateNode.asLong();
        return Instant.ofEpochSecond(currentDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private String extractOverallWeatherDescrFromJsonNode(JsonNode jsonNode) {
        JsonNode weatherNode = jsonNode.get(API_RESPOND_WEATHER);
        if (weatherNode == null) {
            LOGGER.error("Cannot find weather description from respond.");
            return "";
        }

        StringBuilder weatherDescrBuilder = new StringBuilder();

        for (int i = 0; i < weatherNode.size(); ++i) {
            JsonNode descrNode = weatherNode.get(i).get(API_RESPOND_DESCRIPTION);
            if (descrNode != null) {
                weatherDescrBuilder.append(descrNode.textValue()).append(",");
            }
        }

        if (weatherDescrBuilder.length() > 0) {
            weatherDescrBuilder.deleteCharAt(weatherDescrBuilder.length() - 1);
        }

        return weatherDescrBuilder.toString();
    }

    private double extractTemperatureFromJsonNode(JsonNode jsonNode) {
        JsonNode mainNode = jsonNode.get(API_RESPOND_MAIN);
        if (mainNode == null) {
            LOGGER.error("Cannot find temperature from respond.");
            return -1000d;
        }

        double temperature = mainNode.get(API_RESPOND_TEMP).asDouble();
        return temperature;
    }

    private LocalTime extractSunriseTimeFromJsonNode(JsonNode jsonNode) {
        LocalTime sunriseTime = extractTimeFromJsonNode(jsonNode, API_RESPOND_SUNRISE);
        if (sunriseTime.equals(LocalTime.of(0, 0, 0))) {
            LOGGER.error("Cannot find sunrise time from respond.");
        }

        return sunriseTime;
    }

    private LocalTime extractSunsetTimeFromJsonNode(JsonNode jsonNode) {
        LocalTime sunsetTime = extractTimeFromJsonNode(jsonNode, API_RESPOND_SUNSET);
        if (sunsetTime.equals(LocalTime.of(0, 0, 0))) {
            LOGGER.error("Cannot find sunset time from respond.");
        }

        return sunsetTime;
    }

    private LocalTime extractTimeFromJsonNode(JsonNode jsonNode, String timeKey) {
        JsonNode sysNode = jsonNode.get(API_RESPOND_SYS);
        if (sysNode == null) {
            return LocalTime.of(0, 0, 0);
        }

        long sunrise = sysNode.get(timeKey).asLong();
        return Instant.ofEpochSecond(sunrise).atZone(ZoneId.systemDefault()).toLocalTime();
    }

    private double convertCelsiusToFahrenheit(double temperature) {
        return (temperature * 9 / 5.0) + 32;
    }
}
