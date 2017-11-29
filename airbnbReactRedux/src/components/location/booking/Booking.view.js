import React, { Component } from 'react';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import {Link} from 'react-router-dom'
import css from './Booking.css'

export default class Booking extends Component {
    constructor(){
        super();
        this.guests = [];``
        this.state = {startDate: moment(), endDate: moment()};
        this.startDateHandleChange = this.startDateHandleChange.bind(this);
        this.endDateHandleChange = this.endDateHandleChange.bind(this);
    }

    componentWillReceiveProps(nextProps){
        this.guests.length = 0;
    }

     startDateHandleChange(date) {
        this.setState({startDate: date});
     }

     endDateHandleChange(date) {
        this.setState({endDate: date});
     }

    createGuestsOptions(maxGuests){
        let maxGuestsLength = parseInt(maxGuests.maxGuests);

        for(let i=1; i<=maxGuestsLength; i++){
            this.guests.push(i);
        }
    }

    render() {
        let {price, maxGuests} = this.props;
        this.createGuestsOptions({maxGuests});

        return (
        <div class={css.container}>
            <h2 class={css.price}>{price} per night</h2>
            <div class={css.checkDate}>
                <h4 class={css.checkInTitle}>Check In</h4>
                <h4 class={css.checkOutTitle}>Check Out</h4>  
            </div>
            <div class={css.datePickers}>
                <DatePicker class={css.startDate} selected={this.state.startDate} 
                            onChange={this.startDateHandleChange}/>
                <DatePicker class={css.endDate} selected={this.state.endDate} 
                            onChange={this.endDateHandleChange}/>               
            </div>
            <h4 class={css.guestsTitle}>Guests</h4>
            <select class={css.guestsOptions}>
                {
                    this.guests.map(item=><option>{item} Guests</option>)
                }
            </select>
            <Link to={'/reservations'} class={css.button}>Request to Book</Link>
        </div>
        )
    }

}