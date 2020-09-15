import React, { Component } from 'react';

class City extends Component {

    constructor(props) {
        super(props);
        this.keyPress = this.keyPress.bind(this);
        this.refCity = React.createRef();
    }

    keyPress(event) {
        // this.props.login(this.refs.name.value.trim());
        if (event.key === 'Enter') {
            let node = this.refCity.current;
            this.props.city(node.value.trim().replace(/\s/g, '').toLowerCase());
            node.value = '';
        }
    }

    render() {
        return (
            <div class="city-container">
                <h1>Enter a city</h1>
                <input onKeyPress={ this.keyPress } ref={ this.refCity } ></input>
            </div>
        );
    }
}

export default City;