import React, { Component } from 'react';
import Location from '../location/Location.view';
import css from './Locations.css';

export default class Locations extends Component {
    constructor(){
        super();
        this.locations = [];
    }
    
    render() {
        this.locations = this.props.displayedLocations;
        
        return (
            <div class={css.container}>               
                 {
                     this.locations.map((item) => <Location location={item}/>)
                 }     
            </div>
        )
    }
}
