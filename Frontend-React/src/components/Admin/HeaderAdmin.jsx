import { useNavigate } from 'react-router-dom';
import axios from "axios";

function HeaderAdmin() {

	const navigate = useNavigate();
    const username = localStorage.getItem("username");
	const email = localStorage.getItem("email");

	async function logout(event) {
		event.preventDefault();
		try {
		  await axios.put(`http://localhost:8081/user/logout?email=${email}`, navigate('/'), fail => {
		   console.error(fail); // Error!
	}); }
		 catch (err) {
		  alert(err);
		}
	  
	  }

    return ( 
      <div>
        <header>
            
        <div class="logo">Booking.com</div>
		<ul class="navbar">
			<li><a href="#">Hotels</a></li>
			<li><a href="#">Flights</a></li>
			<li><a href="#">Car rentals</a></li>
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
            <li><a href="#">Welcome, {username}</a>
				<ul>
                    <li><a href="/admin/hotels">ManageHotels</a></li>
                    <li><a href="/admin/rooms">ManageRooms</a></li>
                    <li><a href="/admin/viewUsers">ViewActiveUsers</a></li>
					<li><a href="/admin/changePass">ChangePassword</a></li>
					<li><a onClick={logout}>Log-out</a></li>
				</ul>
			</li>
           
		</ul>
	</header>
      </div>
    );
  }
  
  export default HeaderAdmin;