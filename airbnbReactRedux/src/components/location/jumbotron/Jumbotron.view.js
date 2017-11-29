import React, { Component } from 'react';
import css from './Jumbotron.css'

export default function Jumbotron({imageUrl}){
    return (
        <div class={css.container}>
            <img src={imageUrl}></img>
        </div>
    )
}