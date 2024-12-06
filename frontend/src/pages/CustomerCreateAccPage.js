import React from "react";
import { NavLink } from "react-router-dom";

import storeName from '../assets/store-name-login.svg'

import '../styles/PageCustomerCreateAcc.css'

function CustomerCreateAccPage () {

    return (
        <div className="cus-create-acc-page">
            <div className="cus-cracc-store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="customer-createacc-info-box">
                <p className="create-acc-prompt">Welcome new friend!</p>
                <div className="cus-createacc-underline"></div>
                <div className="cus-create-acc-box">Username</div>
                <div className="cus-create-acc-box">Password</div>
                <div className="cus-create-acc-box">Confirm password</div>
                <NavLink to='/Home' className="cus-create-acc-box" id="cus-create-acc-crpage-btn">JOIN OUR UNIVERSE!</NavLink>
                <p className="cus-create-acc-already">Already in our universe?</p>
            </div>
        </div>
    );
}

export default CustomerCreateAccPage;