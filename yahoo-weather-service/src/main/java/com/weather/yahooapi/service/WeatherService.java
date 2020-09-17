package com.weather.yahooapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.weather.yahooapi.component.WeatherComponent;
import com.weather.yahooapi.domain.CityForecasts;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    @Autowired
    private WeatherComponent weatherComponent;

    public WeatherService() { }

    public CityForecasts getTheWeather(String city) {
        return weatherComponent.getTheWeather(city);
    }
}
