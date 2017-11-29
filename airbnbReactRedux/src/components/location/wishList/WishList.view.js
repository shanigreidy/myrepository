import React, { Component } from 'react';
import css from './WishList.css'

export default class WishList extends Component {

    render() {
        return (
            <div class={css.container}>         
                <h2><i class="fa fa-heart-o"></i> &nbsp;
                    Save To Wish List</h2>
                <h4>87908 travelers saved this place</h4>
                <div>
                    <i class="fa fa-facebook"></i>
                    <i class="fa fa-instagram"></i>
                    <i class="fa fa-twitter"></i>
                    <i class="fa fa-envelope"></i>
                </div>
            </div>
        )
    }
}