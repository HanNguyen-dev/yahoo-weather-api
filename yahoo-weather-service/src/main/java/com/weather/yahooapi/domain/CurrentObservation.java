package com.weather.yahooapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentObservation {

    public Wind wind;
    public Atmosphere atmosphere;
    public Astronomy astronomy;
    public Condition condition;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Wind {
        public Integer chill;
        public Integer direction;
        public Long speed;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Atmosphere {
        public Integer humidity;
        public Long visibility;
        public Long pressure;
        public Integer rising;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Astronomy {
        public String sunrise;
        public String sunset;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Condition {
        public String text;
        public Integer code;
        public Integer temperature;
    }

}
