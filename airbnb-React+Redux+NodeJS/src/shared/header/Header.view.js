import React, { Component } from 'react';
import MenuItems from '../menuItems/MenuItems.view';
import Logo from '../logo/Logo.view';
import {Link} from 'react-router-dom'
import css from './Header.css'
import Register from '../register/Register.view'

export default function Header(){
        return (
            <div class={css.clearfix}>
                <Logo/>
                <MenuItems/>
                <Link to={'/register'}>
                    <h3>register</h3>
                </Link>
            </div>
        )
}