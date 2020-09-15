import React, { Component } from 'react';
import './weather.css';

class Weather extends Component {

    constructor(props) {
        super(props);
        this.state = { };
    }

    static getDerivedStateFromProps(nextProps, prevState) {
        if (nextProps.forecast) {
            return {
                forecast: nextProps.forecast
            };
        }
    }


    render() {
        let locationBox = '';
        if (this.state.forecast) {
            locationBox = <div id="current">
                            <h1>
                                { this.state.forecast.current_observation.condition.temperature }&#176;F
                            </h1>
                            { this.state.forecast.location.city }, { this.state.forecast.location.region }<br/>
                            Wind Speed: { this.state.forecast.current_observation.wind.speed }<br/>
                            Humidity: { this.state.forecast.current_observation.atmosphere.humidity }<br/>
                            Pressure: { this.state.forecast.current_observation.atmosphere.pressure }<br/>


                          </div>
        }
        return (
            <div class="weather-container">
                { locationBox }
                <div></div>
            </div>
        );
    }
}

export default Weather;