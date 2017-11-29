export const getLocationById = (id, locations) =>{
    return {
        type:"GET_LOCATION",
        id,
        locations  
    }
}

export const getCityLocationById = (cities, location) =>{
    return {
        type:"GET_LOCATION_CITY",
        cities,
        location  
    }
}
