export function location(state = {}, action) {
  switch (action.type) {
    case 'GET_LOCATION':
      let location = (action.locations).filter(location => location.hotelId == action.id);
      return location[0];
    default:
       return state;
  }
}

export function locationCity(state = {}, action) {
  switch (action.type) {
    case 'GET_LOCATION_CITY':
      let cities = (action.cities).filter(city => city.cityId == action.location.cityId);
      if(cities.length > 0){
        return cities[0]
      } 
      return state;
    default:
       return state;
  }
}
