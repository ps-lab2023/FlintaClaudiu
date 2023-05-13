import { useState, useEffect } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import "../style/HotelsPage.css";
import "../style/Footer.css";
import "../style/Header.css";
import Header from './Header';

function RoomsPage() {
  const [rooms, setRooms] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  const hotelId = location.state.id;

  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [noMaxOfGuests, setNoMaxOfGuests] = useState("");

  useEffect(() => {
    fetch(`http://localhost:8081/hotel/rooms/${hotelId}`)
      .then((response) => response.json())
      .then((data) => setRooms(data));
  }, []);

  console.log('rooms:', rooms);

  const handleInfoClick = (id, startDate, endDate, noMaxOfGuests) => {
    //navigate to rooms with hotel id
    navigate("dates_unlogged", { state: { id:id,  startDate: startDate, endDate: endDate, noMaxOfGuests: noMaxOfGuests } });
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
              <br></br>
            </div>
            <p>{hotel.address}</p>
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
            <button onClick={() => handleInfoClick(hotelId, startDate, endDate, noMaxOfGuests)}>Filter</button>
</div>

          </div>
      </div>
      <Header/>
    </div>
  );
}

export default RoomsPage;