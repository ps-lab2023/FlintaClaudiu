import "../style/MainSearch.css";
import "../style/Footer.css";
import React, { useState } from 'react';
import Slideshow from "./Slideshow";
import Header from "./Header";
import ImageSlider from "./Lists/ImageSlider";
import Footer from "./Footer";

import { useLocation, useNavigate } from "react-router-dom";



function MainSearch () {


    const [selectedValue, setSelectedValue] = useState("");
    const [noOfGuests, setNoOfGuests] = useState(1);
    const navigate = useNavigate();

    const handleSelectChange = (event) => {
      setSelectedValue(event.target.value);
    };

    function handleSearch() {
      navigate("/hotels_unlogged2", { state: { region: selectedValue, guests: noOfGuests } });
    }

    return (
   <div class="page-container">
    
    
  
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
            <ImageSlider/>
            </form5>
            <Header/>

        </div>

      
        <Footer/>
        </div>
     
    );
  }
  
  export default MainSearch;