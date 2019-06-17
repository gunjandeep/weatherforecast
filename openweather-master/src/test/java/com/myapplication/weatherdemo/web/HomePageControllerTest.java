package com.myapplication.weatherdemo.web;

import com.myapplication.weatherdemo.service.impl.WeatherForecastServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(HomePageController.class)
public class HomePageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherForecastServiceImpl currentWeatherService;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/weather-forecast-home-data-page"))
                .andExpect(status().isOk())
                .andExpect(view().name("weather-forecast-home-data-page"))
                .andExpect(content().string(containsString( "Weather  Forecaster  Application")));
    }
}
