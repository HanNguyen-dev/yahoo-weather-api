package com.weather.yahooapi.controller;

import com.weather.yahooapi.service.WeatherService;
import com.weather.yahooapi.domain.CityForecasts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@CrossOrigin("http://localhost:3000")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    public WeatherController() { }

    @GetMapping("/weather")
    public ResponseEntity<CityForecasts> getForecasts(@RequestParam(value = "city", required = true) String city) {
        return new ResponseEntity<>(weatherService.getTheWeather(city), HttpStatus.OK);
    }

}
