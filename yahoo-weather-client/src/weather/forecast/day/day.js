import React, { Component } from 'react';
import './day.css';

class Day extends Component {

    render() {
        return (
            <div class="day">
                <b>{ this.props.day }</b> <br/>
                High { this.props.high } <br/>
                Low { this.props.low } <br/>
            </div>
        );
    }
}

export default Day;