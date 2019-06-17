package com.myapplication.weatherdemo.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A model class for holding current weather data
 */
public class WeatherForecastData {
    private LocalDate currentDate;

    private String cityName;

    private String overallWeatherDescr;

    private double temperatureInC;

    private double getTemperatureInF;

    private LocalTime sunsetTime;

    private LocalTime sunriseTime;

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOverallWeatherDescr() {
        return overallWeatherDescr;
    }

    public void setOverallWeatherDescr(String overallWeatherDescr) {
        this.overallWeatherDescr = overallWeatherDescr;
    }

    public LocalTime getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(LocalTime sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public LocalTime getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(LocalTime sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public double getTemperatureInC() {
        return temperatureInC;
    }

    public void setTemperatureInC(double temperatureInC) {
        this.temperatureInC = temperatureInC;
    }

    public double getGetTemperatureInF() {
        return getTemperatureInF;
    }

    public void setGetTemperatureInF(double getTemperatureInF) {
        this.getTemperatureInF = getTemperatureInF;
    }

    @Override
    public String toString() {
        return "WeatherForecastData{" +
                "currentDate=" + currentDate +
                ", cityName='" + cityName + '\'' +
                ", overallWeatherDescr='" + overallWeatherDescr + '\'' +
                ", temperatureInC=" + temperatureInC +
                ", getTemperatureInF=" + getTemperatureInF +
                ", sunsetTime=" + sunsetTime +
                ", sunriseTime=" + sunriseTime +
                '}';
    }
}
