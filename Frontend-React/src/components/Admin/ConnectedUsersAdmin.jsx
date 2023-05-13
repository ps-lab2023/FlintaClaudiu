import "../../style/Header.css";
import "../../style/Admin/TableAdmin.css";
import HeaderAdmin from "./HeaderAdmin";

import React, { useState, useEffect } from 'react';
import axios from 'axios';

function ConnectedUsersAdmin() {


  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        `http://localhost:8081/user`,
      );
      setData(result.data);
    }
fetchData();});



    return (
      <div>
        <HeaderAdmin/>
        <div className='table-container-hotels'>
        <h1>Real-time Data Table Users</h1>
      <table>
        <thead>
          <tr>
            <th>User ID</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {data.map((row) => (
            <tr key={row.id}>
              <td>{row.id}</td>
              <td>{row.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>

    
      </div>
    );
  }
  export default ConnectedUsersAdmin;