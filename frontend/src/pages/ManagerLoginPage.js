import React from "react";
import { NavLink } from "react-router-dom";

import storeName from '../assets/store-name-login.svg'

import '../styles/ManagerLogin.css'

function ManagerLoginPage () {

    return (
        <div className="manager-login-page">
            <div className="manager-store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="manager-loginfo-box">
                <p className="manager-welcome-back-prompt">Welcome back!</p>
                <div className="manager-underline"></div>
                <div className="manager-box">Username</div>
                <div className="manager-box">Password</div>
                <NavLink to='/Manager/Home' className="manager-box" id="manager-btn">LOGIN</NavLink>
            </div>
        </div>
    );
}

export default ManagerLoginPage;