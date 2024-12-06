import React from "react";
import { NavLink } from "react-router-dom";

import customerIcon from '../assets/customer-icon.svg'
import employeeIcon from '../assets/employee-icon.svg'
import storeName from '../assets/store-name-login.svg'

import '../styles/PageRoleSelect.css'

function RoleSelectPage () {

    return (
        <div className="login-page">
            <div className="store-name">
                <img src={storeName} alt="Nebula Bookstore"></img>
            </div>
            <div className="role-select-box">
                <p className="role-select-prompt">Are you our?</p>
                <div className="underline"></div>
                <div className="role-select-buttons">
                    <NavLink to="/Login/Customer" className="role-button">
                        <img src={customerIcon} alt="Customer Role"></img>
                        <p className="role-link">CUSTOMER</p> 
                    </NavLink>  
                    <NavLink to="/Login/EmployeeSelectRole" className="role-button">
                        <img src={employeeIcon} alt="Customer Role"></img>
                        <p className="role-link">EMPLOYEE</p> 
                    </NavLink>
                </div>
            </div>
        </div>
    );
}

export default RoleSelectPage