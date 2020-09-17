package com.weather.yahooapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityForecasts {

    public Location location;
    public CurrentObservation current_observation;
    public List<Forecast> forecasts;

}
