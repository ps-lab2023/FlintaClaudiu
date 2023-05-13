import "../../style/MainSearch.css";
import React, { useState } from 'react';
import Slideshow from "../Slideshow";
import Header2 from "./Header2";
import ImageSliderLogged from "../Lists/ImageSliderLogged";
import Footer from "../Footer";


import { useNavigate } from "react-router-dom";

function MainSearch () {

    const [country, setCountry] = useState('');
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [guests, setNumGuests] = useState(1);

    const [selectedValue, setSelectedValue] = useState("");
    const [noOfGuests, setNoOfGuests] = useState(1);
    const navigate = useNavigate();

    const handleSelectChange = (event) => {
      setSelectedValue(event.target.value);
    };

    function handleSearch() {
      navigate("/hotels2", { state: { region: selectedValue, guests: noOfGuests } });
    }

    const containerStyles = {
        width: "500px",
        height: "280px",
        margin: "0 auto",
    };

    const handleCountryChange = (event) => {
        setCountry(event.target.value);
      };
    
      const handleDatesChange = ({ startDate, endDate }) => {
        setStartDate(startDate);
        setEndDate(endDate);
      };
    
      const handleNumGuestsChange = (event) => {
        setNumGuests(event.target.value);
      };
    
      const handleSubmit = (event) => {
        event.preventDefault();
        // Do something with the search query
      };


    return (
   <div>
    
        <form1>
        <ul class="search_main">
               <select value={selectedValue} onChange={handleSelectChange}>
        <option value="">Where are you going?</option>
        <option value="Brasov">Brasov, Romania</option>
        <option value="Cluj">Cluj, Romania</option>
        <option value="Bucharest">Bucharest, Romania</option>
      </select>
            <input type="number" name="guests"  placeholder="Number of guests" value={noOfGuests}
              onChange={(event) => setNoOfGuests(event.target.value)}
              required ></input>
            <button type="submit"  onClick={handleSearch}>Search</button>
        </ul>
        </form1>
        <div st>
            <Slideshow/>
            <form5>
            <ImageSliderLogged/>
            </form5>
            <Header2/>
        </div>

        
        <Footer/>
        </div>
     
    );
  }
  
  export default MainSearch;