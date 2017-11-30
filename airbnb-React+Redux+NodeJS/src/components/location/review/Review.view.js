import React, { Component } from 'react';
import Rating from '../../../shared/rating/Rating.view';
import css from './Review.css'

export default function Review({review}){
        return (
            <div class={css.container}>
                <div class={css.review}>
                    <h2>{review.reviewAuthor}</h2>
                    <div class={css.ratingColor}>
                        <Rating rating={review.rating}/>
                    </div>
                </div>
                <content>{review.reviewDescription}</content>
                <div class={css.line}></div>      
            </div>
            
        )
}