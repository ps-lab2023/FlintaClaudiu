import "../../style/Admin/HotelsAdmin.css";
import React, { useState } from "react";
import axios from 'axios';
import Header2 from "./Header2";


function ChangePassNormal() {

    const [oldPass, setOldPass] = useState("");
    const [newPass, setNewPass] = useState("");
    const email = localStorage.getItem("email");


    const handleSubmit = (event) => {
      event.preventDefault();
      console.log("New pass", newPass);
    };

    async function changePass(event) {
      event.preventDefault();
      try {
        console.log("email", email);
        await axios.put(`http://localhost:8081/user/change?email=${email}&oldPass=${oldPass}&newPass=${newPass}`, {
        oldPass: oldPass,
        newPass: newPass
  
        });
        alert("Change Password Successfully");
  
      } catch (err) {
        alert(err);
      }
    }



    return (
      <div className="container2">
        <form2 onSubmit={handleSubmit}>
        <ul class="admin-hotels">
            
            <input type="password" name="oldPass" placeholder="Old Password" value={oldPass}
              onChange={(event) => setOldPass(event.target.value)}
              required></input>
               <br></br>
            <input type="password" name="newPass" placeholder="New Password" value={newPass} onChange={(event) => setNewPass(event.target.value)}
              required></input>
            <br></br>
            <br></br>
             <button type="submit" class="change-btn" onClick={changePass}>Change</button>
           
            
        </ul>
        
       
        </form2>
        <Header2/>
      </div>
    );
  }
  
  export default ChangePassNormal;