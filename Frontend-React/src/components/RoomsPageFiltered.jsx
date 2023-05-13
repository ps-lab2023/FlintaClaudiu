import { useState, useEffect, useMemo } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import "../style/HotelsPage.css";
import "../style/Footer.css";
import "../style/Header.css";
import Header2 from './NormalUser/Header2';

function RoomsPageFiltered() {
  const [rooms, setRooms] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  const hotelId = location.state.id;
  const email = localStorage.getItem("email");
  const startDateActual = location.state.startDate;
  const endDateActual = location.state.endDate;
  const noMaxOfGuestsActual = location.state.noMaxOfGuests;

  console.log('userId:', email);
  console.log('start date:', startDateActual);
  console.log('end date:', endDateActual);
  console.log('noMax of guests:', noMaxOfGuestsActual);
  console.log('hotel id', hotelId);

  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [noMaxOfGuests, setNoMaxOfGuests] = useState("");

  useEffect(() => {
    fetch(`http://localhost:8081/hotel/rooms/dates?id=${hotelId}&startDate=${startDateActual}&endDate=${endDateActual}&noMaxOfGuests=${noMaxOfGuestsActual}`)
      .then((response) => response.json())
      .then((data) => setRooms(data));
  }, []);

  const handleInfoClick = (id, startDate, endDate, noMaxOfGuests) => {
    navigate("/hotels2/rooms/dates", { state: { id:id,  startDate: startDate, endDate: endDate, noMaxOfGuests: noMaxOfGuests } });
  };

  const handleInfoClick2 = (roomId) => {
    navigate("/reservation", { state: { id:roomId,  startDate: startDateActual, endDate: endDateActual, noMaxOfGuests: noMaxOfGuestsActual, hotelId: hotelId } });
  };




  return (
    <div className="hotels-container">
      <h2>Rooms in Hotel with ID {hotelId}</h2>
      <div className="hotels-list">
        {rooms.map((hotel) => (
          <div className="hotel-card" key={hotel.id}>
            <img src={hotel.image} alt={hotel.name} />
            <div className="hotel-info">
              <h3>{hotel.name}</h3>
              <p>Price per Night: {hotel.pricePerNight} RON</p>
              <p>Start Date: {startDateActual}</p>
              <p>End Date: {endDateActual}</p>
              <br></br>
            </div>
            <p>{hotel.address}</p>
            <button onClick={() => handleInfoClick2(hotel.id)}>Make Reservation</button>
          </div>
        ))}
      </div>
      <div className='search-container'>
      <div className="search-card">
      <div class="input-list">
            <div>Start Date:</div>
            <input type="date" name="checkin" value={startDate}
              onChange={(event) => setStartDate(event.target.value)}
              required></input>
            <div>End Date:</div>
            <input type="date" name="checkout" value={endDate}
              onChange={(event) => setEndDate(event.target.value)}
              required></input>
            <div>How many of you?</div>
            <input type="number" name="guests"  placeholder="Number of guests" value={noMaxOfGuests}
              onChange={(event) => setNoMaxOfGuests(event.target.value)}
              required ></input>
            <br></br>
            <br></br>
            <button  onClick={() => handleInfoClick(hotelId, startDate, endDate, noMaxOfGuests)}>Filter</button>
</div>

          </div>
      </div>
      <Header2/>
    </div>
  );
}

export default RoomsPageFiltered;