import "../style/Header.css";
import { useNavigate } from 'react-router-dom';

function Header() {

	const navigate = useNavigate();

	const login = () => {
		navigate('/login'); // Navigate back to the login page after logout
	  };

	  const register = () => {
		navigate('/register'); // Navigate back to the login page after logout
	  };

    return (
      <div>
        <header>
		<div class="logo">Booking.com</div>
        <div class="register">
            <button onClick={register}>Register</button>
		</div>
        <div class="login">
			<button onClick={login}>Login</button>
		</div>
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
	</header>
      </div>
    );
  }
  
  export default Header;