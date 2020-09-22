package com.weather.yahooapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class WeatherExceptionController {

	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="City not found") // 404
	@ExceptionHandler(CityNotFoundException.class)
	public void handleNotFound(CityNotFoundException ex) {
		log.error("Requested city not found");
	}

}
