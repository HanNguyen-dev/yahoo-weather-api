package com.weather.yahooapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    public String city;
    public String region;
    public Integer woeid;
    public String country;
    public long lat;
    public long Long;
    public String timezone_id;
}