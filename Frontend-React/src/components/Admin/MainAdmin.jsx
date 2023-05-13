import "../../style/Header.css";
import "../../style/Admin/TableAdmin.css";
import HeaderAdmin from "./HeaderAdmin";

import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Header() {


  const [data, setData] = useState([]);
  const [data2, setDataRooms] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        `http://localhost:8081/room`,
      );
      setDataRooms(result.data);
    }
fetchData();});

useEffect(() => {
  const fetchData = async () => {
    const result = await axios(
      `http://localhost:8081/hotel`,
    );
    setData(result.data);
  }
fetchData();});


    return (
      <div>
        <HeaderAdmin/>
        <div className='table-container-hotels'>
        <h1>Real-time Data Table Hotels</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Country</th>
            <th>Region</th>
            <th>Rating</th>
            <th>Grade</th>
          </tr>
        </thead>
        <tbody>
          {data.map((row) => (
            <tr key={row.id}>
              <td>{row.id}</td>
              <td>{row.name}</td>
              <td>{row.country}</td>
              <td>{row.region}</td>
              <td>{row.rating}</td>
              <td>{row.grade}</td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>

      <div className='table-container-rooms'>
        <h1>Real-time Data Table Rooms</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price/Night</th>
            <th>Hotel Id</th>
            <th>Hotel Name</th>
          </tr>
        </thead>
        <tbody>
          {data2.map((row) => (
            <tr key={row.id}>
              <td>{row.id}</td>
              <td>{row.name}</td>
              <td>{row.pricePerNight}</td>
              <td>{row.hotel.id}</td>
              <td>{row.hotel.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>
      </div>
    );
  }
  export default Header;