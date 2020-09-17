import React, { Component } from 'react';
import Day from './day/day';
import './forecast.css';

class Forecast extends Component {

    render() {
        let forecasts = [];
        if (this.props.forecasts) {
            this.props.forecasts.map( (day) => {
                forecasts.push(<Day day={ day.day } high={ day.high } low={ day.low } ></Day>);
            });
        }

        return (
            <div class="forecast-container">
                { forecasts }
            </div>
        );
    }
}

export default Forecast;