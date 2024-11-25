import React from "react";
import { NavLink } from "react-router-dom";

import SidebarLogged from "../components/SidebarLogged";

import logoutButton from '../assets/logout-icon.svg'

import '../styles/PageSettings.css'

function SettingsPage() {

    return (
        <div className="settings-page">
            <div className="sidebar">
                <SidebarLogged/>
            </div>
            <div className="sp-main-content">
                <div className="sp-logout">
                    <p className="logout-txt">Logout</p>
                    <NavLink to="/" className="logout-btn">
                        <img src={logoutButton}></img>
                        <p className="see-again-prompt">See you again!</p>
                    </NavLink>
                </div>
            </div>
        </div>
    );
}

export default SettingsPage;