import React, { Component } from 'react';
import Rating from '../../../shared/rating/Rating.view';
import css from './Header.css'

export default function Header({cityName,countryName,reviews,rating,locationName}){
        return (
            <div class={css.container}>
                <h1>{locationName}</h1>
                <div class={css.subHeader}>
                    <h3>{cityName},{countryName}</h3>
                    <div class={`${css.rating} ${css.ratingColor}`}>
                        <Rating rating={rating}/> 
                    </div>
                        <h3 class={css.reviews}>{reviews.length} Reviews</h3>  
                </div>                          
            </div>          
        )
}