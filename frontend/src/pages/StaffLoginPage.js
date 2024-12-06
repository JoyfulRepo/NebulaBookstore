import React from "react";
import { NavLink } from "react-router-dom";

import storeName from '../assets/store-name-login.svg'

import '../styles/StaffLogin.css'

function StaffLoginPage() {

    return (
        <div className="staff-login-page">
            <div className="staff-store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="staff-loginfo-box">
                <p className="staff-welcome-back-prompt">Welcome back!</p>
                <div className="staff-underline"></div>
                <div className="staff-box">Username</div>
                <div className="staff-box">Password</div>
                <NavLink to='/Staff/Home' className="staff-box" id="staff-btn">LOGIN</NavLink>
            </div>
        </div>
    );
}

export default StaffLoginPage;