import css from '../public/index.css';

import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux'

import App from './containers/app/App.view'
import store from './store'  

ReactDOM.render(
    <Provider store={store}>
      <App/>
    </Provider>
  , document.querySelector('.main'));