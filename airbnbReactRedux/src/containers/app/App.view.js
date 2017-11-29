import React, { Component } from 'react';
import { connect } from 'react-redux'
import { fetchOriginalLocationsData,fetchCitiesData} from '../../actions/home'
import LocationDetails from '../location/Location.view';
import Home from '../home/Home.view';
import { withRouter } from 'react-router'
// import ReservationsHome from './reservations/Home';
import Header from '../../shared/header/Header.view';
import Footer from '../../shared/footer/Footer.view';
import Register from '../../shared/register/Register.view';
import {
  BrowserRouter,
  Route,
} from 'react-router-dom'

 class App extends Component {
    componentWillMount(){
       let { onFetchOriginalLocationsData, onFetchCitiesData } = this.props;

       onFetchOriginalLocationsData();
       onFetchCitiesData();
    }
    
    render() {     
      return(
          <BrowserRouter>
            <div>
              <Header/> 
              <Route exact path="/" component={Home}/>
              <Route path="/location/:id" component={LocationDetails}/>
              <Route path="/register" component={Register}/>              
              {/*<Route path="/reservations" component={ReservationsHome}/>*/}
              <Footer/>
            </div>
          </BrowserRouter>

      )
    }
 }
			
function mapStateToProps(state) {
	const { originalLocationsData, citiesData } = state
	return {
  	    originalLocationsData, citiesData 
	}
}

function mapDispatchToProps(dispatch) {
	return {
		onFetchOriginalLocationsData: () => dispatch(fetchOriginalLocationsData()),
		onFetchCitiesData: () => dispatch(fetchCitiesData()),
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(App)
