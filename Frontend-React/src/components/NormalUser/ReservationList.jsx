import { useState, useEffect, useMemo } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import "../../style/HotelsPage.css";
import "../../style/Footer.css";
import "../../style/Header.css";
import Header2 from './Header2';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';

import axios from 'axios';

function ReservationList() {

    
  const [rooms, setRooms] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  const email = localStorage.getItem("email");
  const username = localStorage.getItem("username");
  
  const [refresh, setRefresh] = useState(false);

  console.log('userId:', email);

  useEffect(() => {
    fetch(`http://localhost:8081/reservation/findReservations?email=${email}`)
      .then((response) => response.json())
      .then((data) => setRooms(data));
  }, [email,refresh]);



  const handleDeleteClick = async (reservationId) => {
    try {

    
      axios.post(`http://localhost:8081/send-email2/${reservationId}`);
      // Call the API to delete the reservation
      await fetch(`http://localhost:8081/reservation/delete/${reservationId}`, {
        method: 'DELETE',
      });

  
      // Reload the current page to get the updated list of reservations
      setRefresh(!refresh);

    } catch (error) {
      console.error(error);
    }
  };


  return (
    <div className="hotels-container">
      <h2>Reservation List for user {username}</h2>
      <div className="hotels-list">
        {rooms.map((hotel) => (
          <div className="hotel-card" key={hotel.id}>
            <div className="hotel-info">
              <p>Reservation id: {hotel.id}</p>
              <p>Start date: {hotel.startDate}</p>
              <p>End date: {hotel.endDate}</p>
            </div>
            <p>Hotel: {hotel.hotelName}</p>
            <p>Address: {hotel.hotelAddress}</p>
            <button onClick={() => handleDeleteClick(hotel.id)}><FontAwesomeIcon icon={faTrash} /></button>
          </div>
        ))}
      </div>
      <Header2/>
    </div>
  );
}

export default ReservationList;

//  <button onClick={() => handleInfoClick(hotel)}>More Info</button>