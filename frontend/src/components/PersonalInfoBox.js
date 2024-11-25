import React from "react";

import editButton from '../assets/edit-btn.svg'

import '../styles/PersonalInfoBox.css'

function PersonalInfoBox ({psnInfo}) {

    return (
        <div className="psn-info-box">
            <img src={psnInfo.avaImg} className="profile-img"></img>
            <div className="psn-info">
                <p className="u-prop">User id: <span className="value">{psnInfo.userID}</span></p>
                <p className="u-prop">Username: <span className="value">{psnInfo.username}</span></p>
                <p className="u-prop">Name: <span className="value">{psnInfo.name}</span></p>
                <p className="u-prop">Date of Birth: <span className="value">{psnInfo.dob}</span></p>
                <p className="u-prop">Nationality: <span className="value">{psnInfo.nationality}</span></p>
            </div>
            <div className="address-info">
                <p className="u-prop">Address: <span className="value">{psnInfo.address}</span></p>
                <p className="u-prop">Ward: <span className="value">{psnInfo.ward}</span></p>
                <p className="u-prop">District: <span className="value">{psnInfo.dist}</span></p>
                <p className="u-prop">City: <span className="value">{psnInfo.city}</span></p>
                <p className="u-prop">Country: <span className="value">{psnInfo.country}</span></p>
            </div>
            <img src={editButton} className="edit-btn"></img>
        </div>
    );
}

export default PersonalInfoBox;