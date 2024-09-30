import { configureStore } from "@reduxjs/toolkit";
import dashboardReducer from './redux/dashboardSlice';
import flightsReducer from './redux/FlightSlice';
import userReducer from './redux/userSlice'

const store = configureStore({
  reducer: {
    flights: flightsReducer,
    dashboard: dashboardReducer,
    user:userReducer
  },
});

export default store;
