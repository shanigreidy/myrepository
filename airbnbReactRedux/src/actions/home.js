export const fetchOriginalLocationsData = (dispatch)=> { 
  return (dispatch) =>{
    fetch('/api/hotels')
    .then( response => {
                if(response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then( data => {
                   console.log(data)
                   dispatch({
                        type: 'SHOW_LOCATIONS_DATA',
                        payload: data
                    });
            })
            .catch( error => {
                console.error(`fetch operation failed: ${error.message}`);
            });
  }
}

export const fetchCitiesData = (dispatch)=> { 
  return (dispatch) =>{
    fetch('/api/cities')
    .then( response => {
                if(response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then( data => {
                   console.log(data)
                   dispatch({
                        type: 'SHOW_CITIES_DATA',
                        payload: data
                    });
            })
            .catch( error => {
                console.error(`fetch operation failed: ${error.message}`);
            });
  }
}

export const fetchReviewsData = (reviewsTableName, dispatch)=> { 
  return (dispatch) =>{
    fetch('/api/' + reviewsTableName)
    .then( response => {
                if(response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then( data => {
                   dispatch({
                        type: 'SHOW_REVIEWS_DATA',
                        payload: data
                    });
            })
            .catch( error => {
                console.error(`fetch operation failed: ${error.message}`);
            });
  }
}

export const fetchUsersData = (dispatch)=> { 
  return (dispatch) =>{
    fetch('/api/users')
    .then( response => {
                if(response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then( data => {
                   dispatch({
                        type: 'SHOW_USERS_DATA',
                        payload: data
                    });
            })
            .catch( error => {
                console.error(`fetch operation failed: ${error.message}`);
            });
  }
}


export const changeLocationsData = (locations)=> { 
  return {
            type:"SET_LOCATIONS",
            payload: locations
         }
}

