import React, { Component } from 'react';
import Rating from '../../../shared/rating/Rating.view';
import {mean} from 'lodash';
import {Link} from 'react-router-dom'
import css from './Location.css'
import { connect } from 'react-redux'
import { fetchReviewsData } from '../../../actions/home'

class Location extends Component{
   constructor(props){
       super(props);
       this.location = this.props.location;
       this.rating = 0;
   }

    componentWillMount(){
       this.props.onFetchReviewsData(this.location.hotelName);
    }

    initRating(){
        let ratingArr = [];
        let reviews = this.props.reviewsData;

        for(let review of reviews)
        {
            ratingArr.push(review.rating);
        }
    
        this.calcAvgOfRating(ratingArr);
    }

    calcAvgOfRating(ratingArr){
        this.rating = _.mean(ratingArr);
    }

    render(){
        
            let location = this.location;
            let { reviewsData } = this.props;

            if(reviewsData.length > 0){
                this.initRating();
            }    
            
            return (
                <div class={css.location}>
                     <Link class={css.link} to={`/location/${location.hotelId}`}>
                        <h3>{location.hotelName}</h3>
                        <img class={css.imgLocation} src={location.hotelMainPicUrl}></img>
                        <div class={css.price}>{location.hotelPricePerNight}</div>
                        <Rating rating={this.rating}/>
                        <div class={css.reviews}>{reviewsData.length} reviews</div>
                    </Link>
                </div>
            )
        }
    }

function mapStateToProps(state) {
	const { reviewsData } = state
	return {
  	   reviewsData 
	}
}

function mapDispatchToProps(dispatch) {
	return {
		onFetchReviewsData: (locationName) => dispatch(fetchReviewsData(locationName)),
	}
}


export default connect(mapStateToProps, mapDispatchToProps)(Location)
