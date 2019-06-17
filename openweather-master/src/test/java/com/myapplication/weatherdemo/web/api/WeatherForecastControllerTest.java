package com.myapplication.weatherdemo.web.api;

import com.myapplication.weatherdemo.model.WeatherForecastData;
import com.myapplication.weatherdemo.service.impl.WeatherForecastServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CurrentWeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherForecastServiceImpl currentWeatherService;

    @Test
    public void getCurrentWeatherData() throws Exception {
        given(currentWeatherService.getCurrentWeatherData(any(String.class)))
                .willReturn(any(WeatherForecastData.class));
        mockMvc.perform(get("/current-weather-data/Vancouver")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
