import React, { Component } from 'react';
import Review from '../review/Review.view';
import css from './Reviews.css'

export default function Reviews({reviews}){
        return (
            <div class={css.container}>
                <h1>Reviews:</h1>
                {
                    reviews.map(item=> <Review review={item}/>)
                }
            </div>
            
        )
}