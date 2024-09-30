import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Header from "./Header";
import Info from "./Info";
import Buttons from "./Buttons";
import '../App.css';
import Cookies from 'js-cookie'
import { fetchFlights , deleteFlight } from '../redux/FlightSlice'; // Update the import path as needed

const RemoveFlightTable = () => {
  const dispatch = useDispatch();
  const admin=useSelector((state)=>state.user.user)
  const userFromCookie = Cookies.get('user');
  const user = userFromCookie ? JSON.parse(userFromCookie) : null;
  const { flights, loading, error } = useSelector((state) => state.flights);

  // Fetch flights on component mount
  useEffect(() => {
    dispatch(fetchFlights(admin.airlineUniqueCode));
  }, [dispatch]);

  const handleDelete = (flight_id) => {
    if (window.confirm("Are you sure you want to delete this flight?")) {
      dispatch(deleteFlight(flight_id)); // Dispatch delete action for the flight
    }
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
            <th>Action</th>
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
                  <button onClick={() => handleDelete(flight.flight_id)} style={{padding:'5px 10px', backgroundColor:'red', color:'white', border:'none', borderRadius:'10px'}}>Delete</button>
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

export default RemoveFlightTable;
