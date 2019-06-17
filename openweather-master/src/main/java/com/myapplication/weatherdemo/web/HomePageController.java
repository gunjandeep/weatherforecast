package com.myapplication.weatherdemo.web;

import com.myapplication.weatherdemo.view.ViewForm;
import com.myapplication.weatherdemo.exception.AppException;
import com.myapplication.weatherdemo.model.WeatherForecastData;
import com.myapplication.weatherdemo.service.impl.WeatherForecastServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A controller to display the web page for accepting weather forecasted data requests
 */
@Controller
public class HomePageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

    private WeatherForecastServiceImpl currentWeatherService;

    @Autowired
    public HomePageController(WeatherForecastServiceImpl currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping("/weather-forecast-home-data-page")
    public String home(Model model) {
        ViewForm viewForm = new ViewForm();
        model.addAttribute("command", viewForm);

        return "weather-forecast-home-data-page";
    }

    @GetMapping(value = "/weather-forecast-data-for-city")
    public WeatherForecastData getCurrentWeatherData(@RequestParam String cityName, Model model) {
        LOGGER.info("getCurrentWeatherData service to get current weather data for {}", cityName);

        try {
            WeatherForecastData weatherForecastData = currentWeatherService.getCurrentWeatherData(cityName);
            model.addAttribute("currentDate", weatherForecastData.getCurrentDate());
            model.addAttribute("cityName", cityName);
            model.addAttribute("overallWeatherDescr", weatherForecastData.getOverallWeatherDescr());
            model.addAttribute("temperatureInC", weatherForecastData.getTemperatureInC());
            model.addAttribute("temperatureInF", weatherForecastData.getGetTemperatureInF());
            model.addAttribute("sunriseTime", weatherForecastData.getSunriseTime());
            model.addAttribute("sunsetTime", weatherForecastData.getSunsetTime());
            return weatherForecastData;
        } catch (AppException e) {
            LOGGER.error("Failed to call  weather service to get current weather data for city {}", cityName);
            return null;
        }
    }

}
