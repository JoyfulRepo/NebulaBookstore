import React from "react";

import editButton from '../assets/edit-btn.svg'
import deleteButton from '../assets/delete-btn.svg'

import '../styles/AddrInfoBox.css'

function AddressInfoBox ({addrInfo}) {

    return (
        <div className="addr-info-box">
            <div className="r-info">
                <p className="u-prop">Name: <span className="value">{addrInfo.name}</span></p>
                <p className="u-prop">Contact: <span className="value">{addrInfo.contact}</span></p>
            </div>
            <div className="r-address-info">
                <p className="u-prop">Address: <span className="value">{addrInfo.address}</span></p>
                <p className="u-prop">Ward: <span className="value">{addrInfo.ward}</span></p>
                <p className="u-prop">District: <span className="value">{addrInfo.dist}</span></p>
                <p className="u-prop">City: <span className="value">{addrInfo.city}</span></p>
                <p className="u-prop">Country: <span className="value">{addrInfo.country}</span></p>
            </div>
            <div className="modify-btns">
                <img src={editButton} className="mod-btn"></img>
                <img src={deleteButton} className="mod-btn"></img>
            </div>
        </div>
    );
}

export default AddressInfoBox;