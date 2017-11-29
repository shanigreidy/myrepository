import React, { Component } from 'react';
import css from './Description.css'

export default function Description({description}){
        return (
            <div class={css.container}>
                <h1>Description:</h1>
                <content>{description}</content>
            </div>     
        )
}