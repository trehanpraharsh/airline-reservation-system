import React from 'react';
import Greeting from './Greetings';
import Cookies from 'js-cookie'
import { useDispatch } from "react-redux";
import { logout } from '../redux/userSlice';
import { useNavigate } from 'react-router-dom'


const Header = () => {

  const dispatch=useDispatch();
  const navigate=useNavigate();

  const handleLogout=()=>{
    Cookies.remove('user')
    dispatch(logout())
    navigate('/')
    
  }
  return (
    <div className="header">
      <div className="logo-section">
        <h1>SUNFLY</h1>
        <div style={{display:'flex', columnGap:'10px', alignItems:'center'}}>
        <Greeting/>
        <button style={{border:'none', padding:'5px 10px', backgroundColor:'red', borderRadius:'10px', color:'white'}} onClick={handleLogout}>Logout</button>
        </div>
      </div>
      
    </div>
  );
};

export default Header;
