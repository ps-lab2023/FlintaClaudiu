import { useState, useEffect } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import "../style/HotelsPage.css";
import "../style/Footer.css";
import "../style/Header.css";
import Header from './Header';
import Footer from './Footer';
import axios from 'axios';
import Header2 from './NormalUser/Header2';

function FinalReservationMessage() {
  const [rooms, setRooms] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  
  const email = localStorage.getItem("email");
  const roomId = location.state.id;
  const hotelId = location.state.hotelId;
  const startDateActual = location.state.startDate;
  const endDateActual = location.state.endDate;
  const noMaxOfGuestsActual = location.state.noMaxOfGuests;

  
  console.log('userId:', email);
  console.log('start date:', startDateActual);
  console.log('end date:', endDateActual);
  console.log('noMax of guests:', noMaxOfGuestsActual);
  console.log('roomId:', roomId);


  useEffect(() => {
    if (roomId && email) {
      console.log("A intrat aici");
      const url = `http://localhost:8081/reservation/addres?id=${roomId}&email=${email}&startDate=${startDateActual}&endDate=${endDateActual}&hotelId=${hotelId}`;
      axios.post(url).then((response) => {
        const reservationId = response.data.id;
        console.log(reservationId);
        axios.post(`http://localhost:8081/send-email/${reservationId}`);

      });
    }
  }, [roomId, startDateActual, endDateActual, hotelId]);


  return (
    <div>
      <Header2/>
          <div className="hotel-card">
           <p>Reservation Created Successfully</p>
          </div>
    </div>
  );
}

export default FinalReservationMessage;