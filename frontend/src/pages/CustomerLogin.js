import React from "react";
import { NavLink } from "react-router-dom";

import storeName from '../assets/store-name-login.svg'

import '../styles/CustomerLogin.css'

function CustomerLogin() {

    return (
        <div className="customer-login-page">
            <div className="cuslog-store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="customer-loginfo-box">
                <p className="welcome-back-prompt">Welcome back!</p>
                <div className="cuslog-underline"></div>
                <div className="cus-log-box">Username</div>
                <div className="cus-log-box">Password</div>
                <NavLink to='/Home' className="cus-log-box" id="cus-log-btn">LOGIN</NavLink>
                <p className="cus-log-forgot-pass">Forgot password?</p>
                <div className="sep-line"></div>
                <NavLink to='/Customer/CreateAccount' className='cus-log-box' id="cus-create-acc-btn">I AM A NEWBIE</NavLink>
            </div>
        </div>
    );
}

export default CustomerLogin;