import React from "react";
import { NavLink } from "react-router-dom";

import '../styles/ShipStatusBar.css'

function ShipStatusBar() {

    return (
        <div className="st-bar">
            <NavLink to='/PurchaseHistory/Preparing' className="st-mn-lnk" activeClassName='active'>
                <div className="st-txt">Preparing</div>
                <div className="active-bar"></div>
            </NavLink>
            <NavLink to='/PurchaseHistory/Shipping' className="st-mn-lnk" activeClassName='active'>
                <div className="st-txt">Shipping</div>
                <div className="active-bar"></div>
            </NavLink>
            <NavLink to='/PurchaseHistory/Delivered' className="st-mn-lnk" activeClassName='active'>
                <div className="st-txt">Delivered</div>
                <div className="active-bar"></div>
            </NavLink>
        </div>
    );
}

export default ShipStatusBar;