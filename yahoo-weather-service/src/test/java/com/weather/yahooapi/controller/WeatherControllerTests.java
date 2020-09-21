package com.weather.yahooapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.weather.yahooapi.domain.*;
import com.weather.yahooapi.service.WeatherService;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherServiceMock;

	@Test
	public void getForecastWithValidRequest() throws Exception {
        CityForecasts dallasForecasts = generateForecasts();

		when(weatherServiceMock.getTheWeather("dallas,tx")).thenReturn(dallasForecasts);

		mockMvc.perform(get("/weather?city=dallas,tx")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.location.city").value("Dallas"))
			.andExpect(jsonPath("$.current_observation.wind.chill").value(82));
    }

    @Test
	public void invalidRequestCityNotFound() throws Exception {
	}

    public CityForecasts generateForecasts() {
        Location location = Location.builder()
            .city("Dallas")
            .region("TX")
            .woeid(2388929)
            .country("United States")
            .lat(32.0)
            .Long(-96.0)
            .timezone_id("America/Chicago")
            .build();
        CurrentObservation.Wind wind = CurrentObservation.Wind.builder()
            .chill(82)
            .direction(45)
            .speed(11L).build();
        CurrentObservation.Atmosphere atmosphere = CurrentObservation.Atmosphere.builder()
            .humidity(48)
            .visibility(10.0)
            .pressure(29.0)
            .rising(0).build();
        CurrentObservation.Astronomy astronomy = CurrentObservation.Astronomy.builder()
            .sunrise("7:15 am")
            .sunset("7:25 pm").build();
        CurrentObservation.Condition condition = CurrentObservation.Condition.builder()
            .text("Sunny")
            .code(32)
            .temperature(82).build();
        CurrentObservation current_observation = CurrentObservation.builder()
            .wind(wind)
            .atmosphere(atmosphere)
            .astronomy(astronomy)
            .condition(condition).build();

        List<Forecast> forecasts = new ArrayList<>();
        forecasts.add(Forecast.builder()
                            .day("Sun")
                            .date(1600578000)
                            .low(61)
                            .high(82)
                            .text("Sunny")
                            .code(32).build());
        forecasts.add(Forecast.builder()
                            .day("Mon")
                            .date(1600664400)
                            .low(66)
                            .high(72)
                            .text("Showers")
                            .code(11).build());

        return CityForecasts.builder()
                            .location(location)
                            .current_observation(current_observation)
                            .forecasts(forecasts).build();
    }

}
