import React, { Component } from 'react';
import City from './city/city';
import Weather from './weather/weather'
import axios from 'axios';
import './App.css';

class App extends Component {
    constructor(prop) {
        super();
        this.state = {};
    }

    getForecasts(city) {
        const URL = process.env.REACT_APP_BASE_URL + '?city=' + encodeURIComponent(city);

        axios.get(URL).then(
                (response) => {
                    this.setState( { forecast: response.data })
                })
            .catch(
                (err) => {
                    console.error(err);
                });
        }

    updateCity(city) {
        this.setState( { city: city } );
        this.getForecasts(city);
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <City city={ this.updateCity.bind(this) }></City>
                </header>
                <Weather forecast={ this.state.forecast }></Weather>
            </div>
        );
    }
}

export default App;