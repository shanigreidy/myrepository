import React, { Component } from 'react';
import {concat,times,constant,floor} from 'lodash';
import css from './Rating.css'

export default class Rating extends Component{
    constructor(props){
        super(props);
        this.maxRating = 5;
        this.rating = this.props.rating;
        this.emptyStars=[];
        this.fullStars = [];
        this.renderStars = [];
    }

    componentWillReceiveProps(nextProps){
        this.rating = nextProps.rating;
        this.emptyStars.length = 0;
        this.fullStars.length = 0;
        this.renderStars.length = 0;
    }

    calcStarsRating(){
        let halfStar = this.rating - _.floor(this.rating);
        this.fullStars = _.times(_.floor(this.rating),_.constant(1));
        
        if(halfStar){
            this.halfStar =  _.times(1,_.constant(0.5));
            this.emptyStars = _.times(this.maxRating-_.floor(this.rating)-1,_.constant(0));
            this.stars = _.concat(this.fullStars, this.halfStar, this.emptyStars);  
        }
        else{
            this.emptyStars = _.times(this.maxRating-_.floor(this.rating),_.constant(0));
            this.stars = _.concat(this.fullStars, this.emptyStars);     
        }
    }

    render(){
        this.calcStarsRating();
        let cl = "";

        for(let item of this.stars){
            if(item === 1){
                    cl='fa fa-star';
            }
            else if(item === 0.5){ 
                    cl='fa fa-star-half-o';
            }
            else{
                    cl='fa fa-star-o';
            }

            this.renderStars.push(<i className={cl}></i>)
        }

        return (
            <div class={css.rating}>{this.renderStars}</div>
        )
    }
}