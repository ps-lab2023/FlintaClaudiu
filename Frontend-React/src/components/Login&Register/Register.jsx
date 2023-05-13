import React, { useState } from "react";
import axios from 'axios';
import Header1 from "../Header1";
import "../../style/Login.css";

function Register() {
  const [fname, setFirstName] = useState("");
  const [lname, setLastName] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [isChecked, setIsChecked] = useState(false);
  const [data, setData] = useState(null);

  const handleCheckboxChange = (e) => {
    setIsChecked(e.target.checked);
  };


  async function save(event) {
    event.preventDefault();
    try {
      await axios.post("http://localhost:8081/user/add", {
      firstName: fname,
      lastName: lname,
      email: email,
      telephone: phone,
      password: password,
      type: isChecked ? 0 : 1,
      status: 1

      });
      alert("Employee Registation Successfully");

    } catch (err) {
      alert(err);
    }
  }


  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Fisrt Name:", fname);
    console.log("Last Name:", lname);
    console.log("Phone:", phone);
    console.log("Username:", email);
    console.log("Password:", password);
  };

    return (
      <div className="container-register">
      <Header1/>
      <div className="register-box">
        <img
          src="https://logos-download.com/wp-content/uploads/2016/04/Booking-com-logo-logotype.png"
        />
        <h3>Register Now</h3>
        <form onSubmit={handleSubmit}>
          <div className="user-box">
            <input
              type="text"
              name="fname"
              value={fname}
              onChange={(event) => setFirstName(event.target.value)}
              required
            />
            <label>Fisrt Name</label>
          </div>
          <div className="user-box">
            <input
              type="text"
              name="lname"
              value={lname}
              onChange={(event) => setLastName(event.target.value)}
              required
            />
            <label>Last Name</label>
          </div>
          <div className="user-box">
            <input
              type="tel"
              name="phone"
              pattern="[+]{1}[0-9]{11,14}"
              value={phone}
              onChange={(event) => setPhone(event.target.value)}
              required
            />
            <label>Phone</label>
          </div>
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
          <div>
              <label>
              <input
              type="checkbox"
               checked={isChecked}
               onChange={handleCheckboxChange}
              />
              Register as an Admin?
              </label>
              </div>
          <button onClick={save} type="submit" className="login-btn">
           Register
          </button>
          {data && <div>{JSON.stringify(data)}</div>}
          
        </form>
        </div>
      </div>
    );
  }
  
  export default Register;