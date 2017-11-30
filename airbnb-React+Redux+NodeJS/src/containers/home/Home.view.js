import React, { Component } from 'react';
import { connect } from 'react-redux'
import Jumbotron from '../../components/home/jumbotron/Jumbotron.view';
import Search from '../../components/home/search/Search.view';
import Locations from '../../components/home/locations/Locations.view';
import { changeLocationsData, fetchOriginalLocationsData } from '../../actions/home'


class Home extends Component {
    constructor(){
        super();
    }

    setLocations(locations){
        this.props.onChangeLocationsData(locations);
    }

    render() {
        let { originalLocationsData, citiesData, displayedLocationsData } = this.props;
        return (           
                <div>             
                    <Jumbotron/>
                    <Search displayedLocations={displayedLocationsData} originalLocations={originalLocationsData} 
                            cities={citiesData} filteredLocations={locations => this.setLocations(locations)}/>
                    <Locations displayedLocations={displayedLocationsData}/>                             
                </div>
        )
    }

}
			
function mapStateToProps(state) {
	const { originalLocationsData , citiesData ,displayedLocationsData} = state
	return {
  	    originalLocationsData, citiesData, displayedLocationsData 
	}
}

function mapDispatchToProps(dispatch) {
	return {
		onChangeLocationsData: (locations) => dispatch(changeLocationsData(locations)),
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Home)
