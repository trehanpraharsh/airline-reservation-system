import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchFlights } from '../redux/FlightSlice'; // Import the fetchFlights action
import Header from "./Header";
import Info from "./Info";
import Buttons from "./Buttons";
import '../App.css';
import Cookies from 'js-cookie'

const FlightTable = () => {
  const dispatch = useDispatch();
  const admin=useSelector((state)=>state.user.user)
  const userFromCookie = Cookies.get('user');
  const user = userFromCookie ? JSON.parse(userFromCookie) : null;
  const { flights, status, error } = useSelector((state) => state.flights);

  // Dispatch the fetchFlights action when the component mounts
  useEffect(() => {
    dispatch(fetchFlights(admin.airlineUniqueCode));
  }, [dispatch]);

  // Render loading state
  if (status === 'loading') {
    return <div>Loading...</div>;
  }

  // Render error state
  if (status === 'failed') {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="container">
          <Header />
          <Info />
          <Buttons />
    <div className="flight-table">
      <h2>Flight Details</h2>
      <table>
        <thead>
          <tr>
            <th>Flight ID</th>
            <th>Source Airport</th>
            <th>Destination Airport</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight) => (
            <tr key={flight.flight_id}>
              <td>{flight.flight_id}</td>
              <td>{flight.source_airport}</td>
              <td>{flight.destination_airport}</td>
              <td>{flight.departure_time}</td>
              <td>{flight.arrival_time}</td>
              <td style={{color:`${flight.status=='ON_TIME'?'#73EC8B':flight.status=='DELAYED'?'#4379F2':'#C7253E'}`}}>{flight.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    </div>
  );
};

export default FlightTable;
