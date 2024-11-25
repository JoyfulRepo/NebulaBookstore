import React from "react";
import { NavLink } from "react-router-dom";
import '../styles/SidebarLogged.css'

import nebulaLogo from '../assets/nebula-logo.svg'
import homeIcon from '../assets/home-icon.svg'
import exploringIcon from '../assets/exploring-icon.svg'
import wishlistIcon from '../assets/wishlist-icon.svg'
import cartIcon from '../assets/cart-icon.svg'
import profileIcon from '../assets/profile-icon.svg'
import purchaseHistoryIcon from '../assets/purchase-history-icon.svg'
import settingsIcon from '../assets/settings-icon.svg'
import avaImg from '../assets/profile-img.svg'

function SidebarLogged() {
    const menuItems = [
        { label: "Home", icon: homeIcon, link: "/Home" },
        { label: "Exploring", icon: exploringIcon, link: "/Explore" },
        { label: "Wishlist", icon: wishlistIcon, link: "/Wishlist" },
        { label: "Cart", icon: cartIcon, link:"/Cart" },
        { label: "Profile", icon: profileIcon, link:"/Profile" },
        { label: "Purchase History", icon: purchaseHistoryIcon, link:"/PurchaseHistory/Shipping" },
        { label: "Settings", icon: settingsIcon, link:"/Settings" },
    ];
  
    return (
        <div className='sidebar-logged'>
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

            <div className="ava-name-container">
                <NavLink to="/Profile" className="profile-link">
                    <img src={avaImg} className="ava-img"></img>
                    <p className="name">Loopy</p>
                </NavLink>
            </div>
        </div>
    );
}
export default SidebarLogged;