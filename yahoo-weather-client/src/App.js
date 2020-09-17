import React, { Component } from 'react';
import City from './city/city';
import Weather from './weather/weather'
import axios from 'axios';
import { OAuth } from 'oauth';
import './App.css';

class App extends Component {
    baseURL = 'https://weather-ydn-yql.media.yahoo.com/forecastrss';

    constructor(prop) {
        super();
        this.state = {};
    }

    usingOAuth(city) {
        const APPID = process.env.REACT_APP_APPID;
        const CLIENT_ID = process.env.REACT_APP_CLIENT_ID;
        const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET;
        console.log(city);

        const URL = this.baseURL + '?location=' + city + '&format=json';
        let header = { "X-Yahoo-App-Id": APPID };
        let request = new OAuth(
            null,
            null,
            CLIENT_ID,
            CLIENT_SECRET,
            '1.0',
            null,
            'HMAC-SHA1',
            null,
            header
        );

        request.get(
            URL,
            null,
            null,
            (err, data, result) => {
                if (err) {
                    console.error(err);
                } else {
                    this.setState( { forecast: JSON.parse(data) });
                    console.log(data);
                }
            }
        );

    }
    requestAPI(city) {
        const URL = this.baseURL + '?city=' + encodeURIComponent(city);

        axios.get(URL).then(
                (response) => {
                    console.log(response);
                    this.setState( { forecast: response.data })
                })
            .catch(
                (err) => {
                    console.error(err);
                });
        }

    updateCity(city) {
        this.setState( { city: city } );
        this.usingOAuth(city);
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