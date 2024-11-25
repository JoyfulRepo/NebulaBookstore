import React from "react";
import { NavLink } from "react-router-dom";
import '../styles/Sidebar.css'

import nebulaLogo from '../assets/nebula-logo.svg'
import homeIcon from '../assets/home-icon.svg'
import exploringIcon from '../assets/exploring-icon.svg'
import wishlistIcon from '../assets/wishlist-icon.svg'
import cartIcon from '../assets/cart-icon.svg'
import profileIcon from '../assets/profile-icon.svg'
import purchaseHistoryIcon from '../assets/purchase-history-icon.svg'
import settingsIcon from '../assets/settings-icon.svg'

function Sidebar() {
    const menuItems = [
        { label: "Home", icon: homeIcon, link: "/" },
        { label: "Exploring", icon: exploringIcon, link: "/Login/Customer" },
        { label: "Wishlist", icon: wishlistIcon, link: "/Login/Customer" },
        { label: "Cart", icon: cartIcon, link:"/Login/Customer" },
        { label: "Profile", icon: profileIcon, link:"/Login/Customer" },
        { label: "Purchase History", icon: purchaseHistoryIcon, link:"/Login/Customer" },
        { label: "Settings", icon: settingsIcon, link:"/Login/Customer" },
    ];
  
    return (
        <div className='sidebar'>
            <div className="logo">
                <NavLink to='/'>
                    <img src={nebulaLogo} alt="Nebula Bookstore Logo"></img>
                </NavLink>
            </div>

            <div className="menu">
                {
                    menuItems.map((item, index) => (
                        <li key={index} className="menu-item">
                            <NavLink to={item.link} className="menu-link" activeClassName='active'>
                                <div className="active-item"></div>
                                <div className="menu-content-box">
                                    <img src={item.icon} alt={`${item.label} Icon`} className="menu-icon"></img>
                                    <span className="menu-label">{item.label}</span>
                                </div>
                            </NavLink>
                        </li>
                    ))
                }
            </div>

            <div className="login-button">
                <NavLink to="/Login" className="login-link">
                    LOGIN
                </NavLink>
            </div>
        </div>
    );
}
export default Sidebar;
