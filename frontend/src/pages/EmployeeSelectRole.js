import React from "react";
import { NavLink } from "react-router-dom";

import storeName from '../assets/store-name-login.svg'

import '../styles/EmployeeSelectRole.css'

function EmployeeSelectRole() {

    return (
        <div className="employee-role-select-page">
            <div className="store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="ersp-role-select-box">
                <p className="ersp-role-select-prompt">Please select your specific role</p>
                <div className="ersp-underline"></div>
                <div className="ersp-role-select-buttons">
                    <NavLink to="/Login/ITStaff" className="ersp-role-button">
                        <p className="ersp-role-link">IT Staff</p> 
                    </NavLink>  
                    <NavLink to="/Login/Manager" className="ersp-role-button">
                        <p className="ersp-role-link">Manager</p> 
                    </NavLink>
                    <NavLink to="/Login/Staff" className="ersp-role-button">
                        <p className="ersp-role-link">Staff</p> 
                    </NavLink>
                    <NavLink to="/Login/Shipper" className="ersp-role-button">
                        <p className="ersp-role-link">Shipper</p> 
                    </NavLink>
                </div>
            </div>
        </div>
    );
}

export default EmployeeSelectRole;
