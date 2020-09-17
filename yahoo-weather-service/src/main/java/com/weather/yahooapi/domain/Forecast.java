package com.weather.yahooapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {
    public String day;
    public Integer date;
    public Integer low;
    public Integer high;
    public String text;
    public Integer code;
}