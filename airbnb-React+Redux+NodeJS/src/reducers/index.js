import { combineReducers } from 'redux'
import { originalLocationsData, citiesData, displayedLocationsData, reviewsData } from './home'
import { location, locationCity } from './location'

const rootReducer = combineReducers({
    originalLocationsData, citiesData, reviewsData, displayedLocationsData, location, locationCity
})

export default rootReducer

