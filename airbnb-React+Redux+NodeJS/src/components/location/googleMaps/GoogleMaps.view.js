import React, { Component } from 'react';
import css from './GoogleMaps.css'

export default class GoogleMaps extends Component {

    componentDidMount(){
        this.map = new google.maps.Map(this.$map, {
        center: {lat: 36.1699412 , lng: -115.1398296},
        zoom: 9 });
    }

    render() {
        return (
             <div ref={(el) => this.$map = el} class={css.showMap}></div>
        )
    }

}