import React, { Component } from 'react';
import css from './MenuItems.css'

export default function MenuItems(){
 
    let menuItems = ['Location', 'The Host', 'Reviews', 'Overview'];

    return (
        <ul class={`${css.clearfix} ${css.itemsList}`}>
            {
                menuItems.map(item => <li key={item}><a href="">{item}</a></li>)
            }
        </ul>
    )
}