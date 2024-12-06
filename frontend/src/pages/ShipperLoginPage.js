import React from "react";
import { NavLink } from "react-router-dom";

import storeName from '../assets/store-name-login.svg'

import '../styles/ShipperLogin.css'

function ShipperLoginPage() {

    return (
        <div className="shipper-login-page">
            <div className="shipper-store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="shipper-loginfo-box">
                <p className="shipper-welcome-back-prompt">Welcome back!</p>
                <div className="shipper-underline"></div>
                <div className="shipper-box">Username</div>
                <div className="shipper-box">Password</div>
                <NavLink to='/Shipper/Home' className="shipper-box" id="shipper-btn">LOGIN</NavLink>
            </div>
        </div>
    );
}

export default ShipperLoginPage;