export function originalLocationsData(state = [], action) {
  switch (action.type) {
    case 'SHOW_LOCATIONS_DATA':
      return action.payload
    default:
       return state;
  }
}

export function citiesData(state = [], action) {
  switch (action.type) {
    case 'SHOW_CITIES_DATA':
      return action.payload
    default:
       return state;
  }
}

export function reviewsData(state = [], action) {
  switch (action.type) {
    case 'SHOW_REVIEWS_DATA':
      return action.payload
    default:
       return state;
  }
}


export function displayedLocationsData(state = [], action) {
  switch (action.type) {
    case 'SHOW_LOCATIONS_DATA':
      return action.payload
    case 'SET_LOCATIONS':
      return action.payload
    default:
       return state;
  }
}