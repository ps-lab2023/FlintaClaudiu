import { useState, useEffect } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import "../style/HotelsPage.css";
import "../style/Footer.css";
import "../style/Header.css";
import Header2 from './NormalUser/Header2';
import axios from "axios";

function RoomsPage() {
  const [rooms, setRooms] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  const hotelId = location.state.id;

  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [noMaxOfGuests, setNoMaxOfGuests] = useState("");
  
  const [reservationId, setReservationId] = useState("");

  const [value, setValue] = useState(0);

  
  const email = localStorage.getItem("email");

  const handleChange = (event) => {
    setValue(event.target.value);
  };


  useEffect(() => {
    fetch(`http://localhost:8081/hotel/rooms/${hotelId}`)
      .then((response) => response.json())
      .then((data) => setRooms(data));
  }, []);

  console.log('rooms:', rooms);

  const handleInfoClick = (id, startDate, endDate, noMaxOfGuests) => {
    //navigate to rooms with hotel id
    navigate("dates", { state: { id:id,  startDate: startDate, endDate: endDate, noMaxOfGuests: noMaxOfGuests } });
  };

  async function handleAddReview(event) {
    event.preventDefault();
    try {
      await axios.post(`http://localhost:8081/hotel/rating?email=${email}&reservationId=${reservationId}&grade=${value}`).then((res) =>
        {
         console.log(res.data);
        
         if (res.data.message == "Thanks for review")
         {
           alert("Thanks for review");
         }
         else if(res.data.message == "Internal H ERROR")
         {
          alert("Internal H ERROR");
         }
         else if(res.data.message == "Cannot make review for future reservations")
         {
          alert("Cannot make review for future reservations");
         }
         else if(res.data.message == "Given reservation does not correspond to this email")
         {
          alert("Given reservation does not correspond to this email");
         }
          else
         {
            alert("No reservation with given id");
         }
      }, fail => {
       console.error(fail); // Error!
}); }
     catch (err) {
      alert(err);
    }
  
  }
  

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

      <div className='search-container-review'>
      <div className="search-card-review">
      <div class="input-list">
            <h3>REVIEW SYSTEM</h3>
            <div>For making reviews, you must have a past reservation here</div>
            <div>Reservation id:</div>
            <input type="number" name="reservationId"  placeholder="Reservation id" value={reservationId}
              onChange={(event) => setReservationId(event.target.value)}
              required ></input>
            <div>Grade: {value}</div>
            <input
        type="range"
        min="1"
        max="5"
        value={value}
        onChange={handleChange}
      />
      <br></br>

            <button onClick={handleAddReview}>Add review</button>
</div>

          </div>
      </div>
      <Header2/>
    </div>
  );
}

export default RoomsPage;