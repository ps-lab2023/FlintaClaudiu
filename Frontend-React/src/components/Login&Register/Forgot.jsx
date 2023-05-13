import React, { useState } from "react";
import Header1 from "../Header1";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import "../../style/Login.css";


function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Email:", email);
    console.log("Password:", password);
  };

  async function forgot(event) {
    event.preventDefault();
    try {
      
      await axios.post(`http://localhost:8081/send-email3/${email}`, navigate("/forgot/forgot_pass"), {
       
      }, fail => {
       console.error(fail); // Error!
}); }
     catch (err) {
      alert(err);
    }
  
  }

  return (
    <div className="container">
      <Header1/>
      <div className="login-box">
        <img
          src="https://logos-download.com/wp-content/uploads/2016/04/Booking-com-logo-logotype.png"
        />
        <h2>Get you password</h2>
        <form onSubmit={handleSubmit}>
          <div className="user-box">
            <input
              type="text"
              name="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
            />
            <label>Email</label>
          </div>
          <button type="submit" className="login-btn" onClick={forgot}>
            Send Password
          </button>
        </form>
        <div className="register">
          <p>
            Don't have an account? <a href="/register">Register now</a>
          </p>
        </div>
      </div>
     
    </div>
  );

}

export default Login;
