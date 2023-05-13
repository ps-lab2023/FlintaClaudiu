import "../../style/Admin/RoomsAdmin.css";
import HeaderAdmin from "./HeaderAdmin";
import React, { useState } from "react";
import axios from 'axios';
import Footer from "../Footer";


function RoomsAdmin() {

    const [name, setName] = useState("");
    const [pricePerNight, setPricePerNight] = useState("");
    const [hotelId, setHotelId] = useState("");
    const [noMaxOfGuests, setNoMaxOfGuests] = useState("");
    const [image, setImage] = useState("");

    const [roomIdDel, setRoomIdDel] = useState("");

    const [roomIdUpd, setRoomIdUpd] = useState("");
    const [newPrice, setNewPrice] = useState("");

    const handleItemIdChange = (event) => {
      setRoomIdDel(event.target.value);
    }


    const handleSubmit = (event) => {
      event.preventDefault();
      console.log("Name", name);
      console.log("PricePerNight:", pricePerNight);
      console.log("HotelId:", hotelId);
    };

    async function add(event) {
      event.preventDefault();
      try {
        console.log("Name", name);
        console.log("PricePerNight:", pricePerNight);
        console.log("HotelId:", hotelId);
        console.log("Capacity:", noMaxOfGuests);

        await axios.post("http://localhost:8081/room/add", {
        name: name,
        pricePerNight: pricePerNight,
        hotelId: hotelId,
        capacity: noMaxOfGuests,
        image: image

        
  
        });
        alert("Room Registation Successfully");
  
      } catch (err) {
        alert(err);
      }
    }

    async function deleteRoom(event) {
      event.preventDefault();
      try {
        console.log("Id", roomIdDel);
  
        await axios.delete(`http://localhost:8081/room/delete/${roomIdDel}`);
  
        alert("Delete Room Successfully");
  
      } catch (err) {
        alert(err);
      }
    }

    async function updateRoom(event) {
      event.preventDefault();
      try {
  
        await axios.put(`http://localhost:8081/room/update?id=${roomIdUpd}&price=${newPrice}`);
  
        alert("Update Price Successfully");
  
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
            <input type="text" name="pricePerNight" placeholder="Introduce Price per Night" value={pricePerNight} onChange={(event) => setPricePerNight(event.target.value)}
              required></input>
            <input type="text" name="hotelId" placeholder="Introduce Hotel id" value={hotelId} onChange={(event) => setHotelId(event.target.value)}
              required></input>
              <input type="text" name="noMaxOfGuests" placeholder="Max. number of guests" value={noMaxOfGuests} onChange={(event) => setNoMaxOfGuests(event.target.value)}
              required></input> 
               <input type="text" name="image" placeholder="Hotel image url" value={image} onChange={(event) => setImage(event.target.value)}
              required></input> 
            <br></br>
             <button type="submit" class="add-room-btn" onClick={add}>Add</button>
           
            
        </ul>
        
       
        </form2>
        
        <form3>
        <ul class="admin-hotels">
            
            <input type="text" name="roomIdDel" placeholder="Plese, insert Room ID" value={roomIdDel} onChange={handleItemIdChange}
              required></input>
            <br></br>
             <button type="submit" class="delete-room-btn" onClick={deleteRoom}>Delete</button>
           
            
        </ul>
        
       
        </form3>

        <form4>
        <ul class="admin-hotels">
            
            <input type="text" name="id" placeholder="Plese, insert Room ID" value={roomIdUpd} onChange={(event) => setRoomIdUpd(event.target.value)}
              required></input>
            <input type="text" name="updatedPrice" placeholder="Plese, insert updated price" value={newPrice} onChange={(event) => setNewPrice(event.target.value)}
              required></input>
            <br></br>
             <button type="submit" class="update-room-btn" onClick={updateRoom}>Update</button>
           
            
        </ul>
        
       
        </form4>
        <HeaderAdmin/>
      </div>
    );
  }
  
  export default RoomsAdmin;