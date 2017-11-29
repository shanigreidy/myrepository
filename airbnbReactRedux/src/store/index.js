import { applyMiddleware, createStore } from 'redux'
import reduxLogger from "redux-logger"

import reduxThunk from 'redux-thunk'
import rootReducer from '../reducers'

const middleware = applyMiddleware(reduxThunk)
export default createStore(rootReducer,{},middleware)
