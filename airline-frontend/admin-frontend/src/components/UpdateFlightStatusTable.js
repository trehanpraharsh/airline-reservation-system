import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {  updateFlightStatus } from '../redux/dashboardSlice'; 
import { fetchFlights } from '../redux/FlightSlice';
import Header from "./Header";
import Info from "./Info";
import Buttons from "./Buttons";
import '../App.css';
import Cookies from 'js-cookie'

const UpdateFlightStatusTable = () => {
  const dispatch = useDispatch();
  const userFromCookie = Cookies.get('user');
  const user = userFromCookie ? JSON.parse(userFromCookie) : null;
  const admin=useSelector((state)=>state.user.user)
  const { flights, loading, error } = useSelector((state) => state.flights);

  // Fetch flights on component mount
  useEffect(() => {
    dispatch(fetchFlights(admin.airlineUniqueCode));
  }, [dispatch]);

  const handleStatusChange = (flightId, status) => {
    dispatch(updateFlightStatus({ flightId, status })); // Dispatch status update action
  };

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div className="container">
          <Header />
          <Info />
          <Buttons />
    <div className="flight-table">
      <table>
        <thead>
          <tr>
            <th>Flight ID</th>
            <th>Airline</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {flights.length > 0 ? (
            flights.map(flight => (
              <tr key={flight.flight_id}>
                <td>{flight.flight_id}</td>
                <td>{flight.airlineImage}</td>
                <td>{flight.source_airport}</td>
                <td>{flight.destination_airport}</td>
                <td>{flight.departure_time}</td>
                <td>{flight.arrival_time}</td>
                <td>
                  <select
                    value={flight.status}
                    onChange={(e) => handleStatusChange(flight.flight_id, e.target.value)}
                  >
                    <option value="ON_TIME">ON_TIME</option>
                    <option value="DELAYED">DELAYED</option>
                    <option value="CANCELLED">CANCELLED</option>
                  </select>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7">No flights available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
    </div>
  );
};

export default UpdateFlightStatusTable;
