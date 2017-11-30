import React from 'react';
import css from './Footer.css'

export default function Footer(){     
    return (
        <footer class={css.footer}>
            <h4><i class="fa fa-copyright"></i> shani greidy</h4>
            <content>Contact information: <a href="">shani@example.com</a></content>
        </footer>
    )
}