import React, { useState } from "react";
import Header1 from "../Header1";
import Header2 from "../NormalUser/Header2";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import "../../style/Login.css";
import MainSearchLogged from "../NormalUser/MainSearchLogged";


function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Email:", email);
    console.log("Password:", password);
  };

  async function login(event) {
    event.preventDefault();
    try {
      await axios.post("http://localhost:8081/user/login", {
        email: email,
        password: password,}).then((res) =>
        {
         console.log(res.data);
        
         if (res.data.message == "Email not exits")
         {
           alert("Email not exits");
         }
         else if(res.data.message == "Login Success")
         {
            localStorage.setItem("username", res.data.username);
            localStorage.setItem("email", email);
            if (res.data.type == "ADMIN"){
            navigate('/admin');
            } else {
              navigate('/home');
            }
           
         }
          else
         {
            alert("Incorrect Email and Password not match");
         }
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
        <h2>Sign in to your account</h2>
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
          <div className="user-box">
            <input
              type="password"
              name="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              required
            />
            <label>Password</label>
          </div>
          <div className="forgot-password">
            <a href="/forgot">Forgot password?</a>
          </div>
          <button type="submit" className="login-btn" onClick={login}>
            Login
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
