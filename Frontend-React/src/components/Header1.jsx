import "../style/Header1.css";
import LiveHintTextBox from "./LiveHintTextBox";
import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

function Header1() {

	const [inputValue, setInputValue] = useState("");
  const hintOptions = ["Brasov", "Bucharest", "Timisoara", "Cluj", "Iasi", "Constanta","Oradea","Targu-Mures", "Bacau", "Arad", "Craiova"];
  const navigate = useNavigate();

  const handleInputChange = (event) => {
    const value = event.target.value;
    setInputValue(value);
  };

  const handleHintClick = (hint) => {
    // Update the input value with the clicked hint
    setInputValue(hint);
  };

  const handleInfoClick = () => {
    // Update the input value with the clicked hint
	navigate("/hotels_unlogged", { state: { region: inputValue } });
  };

  const filterOptions = () => {
    // Filter the hint options based on the current input value
    return hintOptions.filter((option) =>
      option.toLowerCase().startsWith(inputValue.toLowerCase())
    );
  };

    return (
      <div>
        <header>
		<div class="logo">Booking.com</div>
		<ul class="navbar">
			<li><a href="/all_hotels_unlogged">Hotels</a></li>
			<li><a href="https://www.esky.ro/?msclkid=2bd2d637cb0e11669be6b26465266c2e&utm_source=bing&utm_medium=cpc&utm_campaign=%5BSFComp%5D%20Competitors&utm_term=vola&utm_content=vola.ro">Flights</a></li>
			<li><a href="https://www.rentalcars.com/?affiliateCode=msn_new_en&preflang=en&label=msn-iKoD1d7ouP9q7g2pqrIpGA-76278735418981&adcamp=Generic%20-%20Exact&adco=cpc&utm_medium=cpc&utm_source=bing&utm_term=iKoD1d7ouP9q7g2pqrIpGA&msclkid=8e7fb433fdb018ca378bd06053e31a20">Car rentals</a></li>
			<li><a href="#">Attractions</a>
				<ul>
					<li><a href="#">Museums</a></li>
					<li><a href="#">Zoos</a></li>
					<li><a href="#">Parks</a></li>
					<li><a href="#">Landmarks</a></li>
				</ul>
			</li>
			<li><a href="#">Deals</a></li>
			<li><a href="#">Partner with us</a></li>
		</ul>
		<div class="search-box_1">
		
		<input type="text" placeholder="Where are you going?" value={inputValue} onChange={handleInputChange} />
      {inputValue !== "" && (
        <ul className="hint-list">
          {filterOptions().map((option, index) => (
            <li key={index} onClick={() => handleHintClick(option)}>
              {option}
            </li>
          ))}
        </ul>
      )}
	  <button onClick={() => handleInfoClick()}>Search</button>
		</div>
	</header>
      </div>
    );
  }
  
  export default Header1;