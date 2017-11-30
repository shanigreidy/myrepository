import React, { Component } from 'react';
import Jumbotron from '../../components/location/jumbotron/Jumbotron.view';
import MenuItems from '../../components/location/menuItems/MenuItems.view';
import Booking from '../../components/location/booking/Booking.view';
import Header from '../../components/location/header/Header.view';
import LocationFeatures from '../../components/location/locationFeatures/LocationFeatures.view';
import Description from '../../components/location/description/Description.view';
import Reviews from '../../components/location/reviews/Reviews.view';
import GoogleMaps from '../../components/location/googleMaps/GoogleMaps.view';
import WishList from '../../components/location/wishList/WishList.view';

import {mean} from 'lodash';
import { connect } from 'react-redux'
import { getLocationById, getCityLocationById } from '../../actions/location'
import { fetchReviewsData } from '../../actions/home'
import css from './Location.css'


class Location extends Component {
    constructor(props){
        super(props);
        this.locationId = this.props.match.params.id;
    }

    getLocation(){
       let { onGetLocationById, originalLocationsData } = this.props;

       if(originalLocationsData.length > 0){
            onGetLocationById(this.locationId, originalLocationsData);
       }

       this.getLocationCity();
       this.getLocationReviews();
    }

    getLocationCity(){
        let { citiesData, onGetCityLocationById ,location } = this.props;
        
        if(citiesData.length > 0){
            onGetCityLocationById(citiesData, location);
        }
    }

    getLocationReviews(){
        let { onFetchReviewsData, location } = this.props;


        if(Object.keys(location).length !== 0){
            onFetchReviewsData(location.hotelName);
        }
    }

    initRating(){
        let ratingArr = [];
        let reviews = this.props.reviewsData;

        for(let review of reviews){
            ratingArr.push(review.rating);
        }
    
        this.calcAvgOfRating(ratingArr);
    }

    calcAvgOfRating(ratingArr){
        this.rating = _.mean(ratingArr);
    }

    render() {
        this.getLocation();
        this.initRating();
        let { location, locationCity, reviewsData} = this.props;

        return (
           <div class={css.clearfix}>
               <Jumbotron imageUrl={location.hotelMainPicUrl}/>
                <div class={`${css.clearfix} ${css.detailsContainer}`}>
                    <MenuItems/>
                    <div class={css.bookingAndWishListContainer}>
                        <Booking price={location.hotelPricePerNight} 
                                 maxGuests={location.hotelMaxGuests}/>
                        <WishList/>
                    </div>
                    <Header cityName={locationCity.cityName} reviews={reviewsData}
                            countryName={"bla"} 
                            locationName={location.hotelName}
                            rating={this.rating}/>
                    <div class={css.line}></div>
                    <LocationFeatures/>
                    <div class={css.line}></div>
                    <Description description={location.hotelDescription}/>          
                    <div class={css.line}></div>    
                    <Reviews reviews={reviewsData}/>        
                    <GoogleMaps/>              
                </div>
           </div>
        )
    }
}

function mapStateToProps(state) {
	const { originalLocationsData , citiesData, location, locationCity,reviewsData } = state
	return {
  	    originalLocationsData , citiesData, location, locationCity, reviewsData 
	}
}

function mapDispatchToProps(dispatch) {
	return {
        onGetLocationById: (locationId,originalLocationsData) => 
                            dispatch(getLocationById(locationId,originalLocationsData)),		
		onGetCityLocationById: (citiesData, location) => 
                                dispatch(getCityLocationById(citiesData, location)),
        onFetchReviewsData: (locationName) => dispatch(fetchReviewsData(locationName)),

	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Location)

