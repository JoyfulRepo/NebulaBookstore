import React from "react";
import { NavLink } from "react-router-dom";

import storeName from '../assets/store-name-login.svg'

import '../styles/ITStaffLogin.css'

function ITStaffLoginPage() {

    return (
        <div className="itstaff-login-page">
            <div className="itstaff-store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="itstaff-loginfo-box">
                <p className="itstaff-welcome-back-prompt">Welcome back!</p>
                <div className="itstaff-underline"></div>
                <div className="itstaff-box">Username</div>
                <div className="itstaff-box">Password</div>
                <NavLink to='/ITStaff/Home' className="itstaff-box" id="itstaff-btn">LOGIN</NavLink>
            </div>
        </div>
    );
}

export default ITStaffLoginPage;