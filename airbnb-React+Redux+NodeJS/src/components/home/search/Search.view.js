import React, { Component } from 'react';
import css from './Search.css';

export default class Search extends Component {
    constructor(props){
        super(props);
        this.locations = [];
        this.guests = [];
        this.cities = [];
        this.originalLocations = [];
        this.citiesNames = [];
        this.filterCitiesList = this.filterCitiesList.bind(this); 
        this.filterGuestsList = this.filterGuestsList.bind(this);       
    }

    initProps(){
        let { displayedLocations, originalLocations, cities } = this.props;
        this.locations = displayedLocations;
        this.originalLocations = originalLocations;
        this.cities = cities;
        this.guests.length=0;
    }

    initMaxGuests(){
        let locations = this.originalLocations;
        let maxGuests = 0;

        for(let location of locations){
            let guests = location.hotelMaxGuests;

            if(guests > maxGuests){
                maxGuests= guests;        
            }
        }

        this.createGuestsOptions(maxGuests);
    }

    createGuestsOptions(maxGuests){
        for(let i=1; i<=maxGuests; i++){
            this.guests.push(i);
        }
    }

    filterGuestsList(event){
        let input = event.target.value;
        let filteredLocationsByGuests = (this.originalLocations).filter(item => item.hotelMaxGuests <= input);
        this.props.filteredLocations(filteredLocationsByGuests);
    }

    filterCitiesList(event){
        let input = event.target.value;
        let filteredCities = (this.cities).filter(item => item.cityName.toLowerCase().includes(input.toLowerCase()));
        let filteredLocationsByCity = [];

        for(let location of this.originalLocations){
            for(let city of filteredCities){
                if(location.cityId == city.cityId){
                    filteredLocationsByCity.push(location);
                }
            }
        }  

        this.props.filteredLocations(filteredLocationsByCity);
    }

    render() {
        this.initProps();
        this.initMaxGuests(); 

        return (
            <div class={css.container}>               
                City:   <input list="cities" placeholder="location.." onChange={(e)=> this.filterCitiesList(e)}/>
                        <datalist id="cities">
                            {
                                this.cities.map(item=> <option value={item.cityName}></option>)
                            }
                        </datalist>
    
                Guests: <select class={css.guestsList} onChange={(e)=> this.filterGuestsList(e)}>
                            {
                                this.guests.map(item=> <option value={item}>{item}</option>)
                            }
                        </select>
            </div>
        )
    }

}