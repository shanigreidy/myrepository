import React, { Component } from 'react';
import css from './Logo.css';

export default function Logo(){     
    return (
        <a href="/"><img class={`${css.clearfix} ${css.logo}`} src="../static/icons/logo.jpg"></img></a>
    )
}