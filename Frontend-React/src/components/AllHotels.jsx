import { useState, useEffect } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import "../style/HotelsPage.css";
import "../style/Footer.css";
import "../style/Header.css";
import Header from './Header';

function AllHotels() {
  const [hotels, setHotels] = useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  const [selectedValue, setSelectedValue] = useState("");
  const [noOfGuests, setNoOfGuests] = useState(1);

  const handleSelectChange = (event) => {
    setSelectedValue(event.target.value);
  };

  function handleSearch() {
    navigate("/hotels_unlogged2", { state: { region: selectedValue, guests: noOfGuests } });
  }

  useEffect(() => {
    fetch(`http://localhost:8081/hotel`)
      .then((response) => response.json())
      .then((data) => setHotels(data));
  }, []);

  console.log('hotels:', hotels);

  const handleInfoClick = (id) => {
    //navigate to rooms with hotel id
    navigate("rooms_unlogged", { state: { id: id } });
  };
  

  return (
    <div className="hotels-container">
      <h2>All Hotels</h2>
      <div className="hotels-list">
        {hotels.map((hotel) => (
          <div className="hotel-card" key={hotel.id}>
            <img src={hotel.image} alt={hotel.name} />
            <div className="hotel-info">
              <h3>{hotel.name}</h3>
              <p>{hotel.address}</p>
              <p>{hotel.region}, {hotel.country}</p>
              <div className="stars">Rating:  
              {[...Array(hotel.rating)].map((_, index) => (
                <span key={index} className="filled">
                  ★
                </span>
              ))}
              {[...Array(5 - hotel.rating)].map((_, index) => (
                <span key={index} className="unfilled">
                  ☆
                </span>
              ))}
            </div>
              <br></br>
            </div>
            
            <button onClick={() => handleInfoClick(hotel.id)}>Find More</button>
          </div>
        ))}
      </div>
      <div className='search-container'>
      <div className="search-card">
      <div class="input-list">
      <div>Location:</div>
      <select value={selectedValue} onChange={handleSelectChange}>
        <option value="">Where are you going?</option>
        <option value="Brasov">Brasov, Romania</option>
        <option value="Cluj">Cluj, Romania</option>
        <option value="Bucharest">Bucharest, Romania</option>
      </select>
            <div>How many of you?</div>
            <input type="number" name="guests"  placeholder="Number of guests" value={noOfGuests}
              onChange={(event) => setNoOfGuests(event.target.value)}
              required ></input>
            <br></br>
            <br></br>
            <button type="submit"  onClick={handleSearch}>Search</button>
</div>

          </div>
      </div>
      <Header/>
    </div>
  );
}

export default AllHotels;