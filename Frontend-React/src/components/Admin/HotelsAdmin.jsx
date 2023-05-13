import "../../style/Admin/HotelsAdmin.css";
import HeaderAdmin from "./HeaderAdmin";
import React, { useState } from "react";
import axios from 'axios';


function HotelsAdmin() {

    const [name, setName] = useState("");
    const [country, setCountry] = useState("");
    const [region, setRegion] = useState("");
    const [address, setAddress] = useState("");
    const [phone, setPhone] = useState("");
    const [email, setEmail] = useState("");
    const [rating, setRating] = useState("");
    const [image, setImage] = useState("");
    
    const [itemId, setItemId] = useState('');

    const handleItemIdChange = (event) => {
      setItemId(event.target.value);
    }

    const handleSubmit = (event) => {
      event.preventDefault();
      console.log("Name", name);
      console.log("Country:", country);
      console.log("Region:", region);
      console.log("Address:", address);
      console.log("Phone:", phone);
      console.log("Email", email);
      console.log("Rating:", rating);
      console.log("Image", image);
    };

    const handleSelectCountry = (event) => {
      setCountry(event.target.value);
    };

    const handleSelectRegion = (event) => {
      setRegion(event.target.value);
    };

    async function add(event) {
      event.preventDefault();
      try {
        await axios.post("http://localhost:8081/hotel/add", {
        name: name,
        country: country,
        region: region,
        address: address,
        phone: phone,
        email: email,
        rating: rating,
        image: image
  
        });
        console.log("Name", name);
        console.log("Country:", country);
        console.log("Region:", region);
        console.log("Address:", address);
        console.log("Phone:", phone);
        console.log("Email", email);
        alert("Hotel Registation Successfully");
  
      } catch (err) {
        alert("One of the fields type does not correspond. Please, check again!");
      }
    }



  async function deleteHotel(event) {
    event.preventDefault();
    try {

      console.log("Id", itemId);
      await axios.delete(`http://localhost:8081/hotel/delete/${itemId}`);

      alert("Delete Successfully");

    } catch (err) {
      alert(err);
    }
  }


    return (
      <div className="container2">
        <form2 onSubmit={handleSubmit}>
        <ul class="admin-hotels">
            
            <input type="text" name="name" placeholder="Name" value={name}
              onChange={(event) => setName(event.target.value)}
              required></input>
            <select name="country" value={country} onChange={handleSelectCountry}>
                    <option value="">Select Country</option>
                    <option value="Romania">Romania</option>
            </select>
            <select name="region" value={region} onChange={handleSelectRegion}>
                    <option value="">Select Region</option>
                    <option value="Brasov">Brasov, Romania</option>
                    <option value="Cluj">Cluj, Romania</option>
                    <option value="Bucharest">Bucharest, Romania</option>
                    <option value="Iasi">Iasi, Romania</option>
                    <option value="Timisoara">Timisoara, Romania</option>
                    <option value="Constanta">Constanta, Romania</option>
                    <option value="Targu-Mures">Targu-Mures, Romania</option>
                    <option value="Oradea">Oradea, Romania</option>
            </select>
            <input type="text" name="address" placeholder="Address" value={address} onChange={(event) => setAddress(event.target.value)}
              required></input>
            <input type="text" name="phone" placeholder="Phone" value={phone} onChange={(event) => setPhone(event.target.value)}
              required></input>
            <input type="text" name="email" placeholder="Email" value={email} onChange={(event) => setEmail(event.target.value)}
              required></input> 
            <input type="text" name="rating" placeholder="Rating (1-5 stars)" value={rating} onChange={(event) => setRating(event.target.value)}
              required></input> 
              <input type="text" name="image" placeholder="Hotel image url" value={image} onChange={(event) => setImage(event.target.value)}
              required></input> 
            <br></br>
             <button type="submit" class="add-btn" onClick={add}>Add</button>
           
            
        </ul>
        
       
        </form2>
        
        <form3>
        <ul class="admin-hotels">
            
            <input type="text" name="id" placeholder="Plese, insert Hotel ID" value={itemId} onChange={handleItemIdChange}></input>
            <br></br>
             <button type="submit" class="delete-btn" onClick={deleteHotel}>Delete</button>
           
            
        </ul>
        
       
        </form3>
        <HeaderAdmin/>
      </div>
    );
  }
  
  export default HotelsAdmin;