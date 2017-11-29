import React, { Component } from 'react';
import css from './LocationFeatures.css'

export default function LocationFeatures(){
    return (
        <div class={css.container}>
                <div class={css.featureContainer}>
                    <i class="fa fa-home fa-3x"></i>
                    <span>Entire home/apt</span>
                </div>
                <div class={css.featureContainer}>
                    <i class="fa fa-users fa-3x"></i>
                    <span>4 Guests</span>
                </div>    
                <div class={css.featureContainer}>
                    <i class="fa fa-bed fa-3x"></i>
                    <span>1 Bed</span>
                </div>     
                <div class={css.featureContainer}>
                    <i class="fa fa-wifi fa-3x"></i>
                    <span>free wifi</span>
                </div>                    
        </div>
        
    )
}