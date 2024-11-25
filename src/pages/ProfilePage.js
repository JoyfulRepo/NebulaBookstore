import React from "react";

import SidebarLogged from "../components/SidebarLogged";
import PersonalInfoBox from "../components/PersonalInfoBox";
import AddressInfoBox from "../components/AddressInfoBox";

import '../styles/PageProfile.css'

function ProfilePage() {
    const psnInfo = {
        avaImg: '/images/profile/profile-img.svg',
        userID: '11112024',
        username: 'Cute Loopy',
        name: 'Loopy Loopy',
        dob: '11/11/2024',
        nationality: 'Loopese',
        address: '888 Joyful Street',
        ward: 'Brilliant Ward',
        dist: 'Magnificient District',
        city: 'Significant City',
        country: 'Happy Country',
    }

    const addrInfoList = [
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
        {
            name: 'Loopy',
            contact: '(+84)999 888 989',
            address: '888 Joyful Street',
            ward: 'Brilliant Ward',
            dist: 'Magnificient District',
            city: 'Significant City',
            country: 'Happy Country',
        },
    ];

    return (
        <div className="profile-page">
            <div className="sidebar">
                <SidebarLogged/>
            </div>
            <div className="pp-main-content">
                <div className="peronal-info">
                    <p className="tpe-txt">Personal Information</p>
                    <PersonalInfoBox 
                        psnInfo={psnInfo}
                    />
                </div>
                <div className="shipping-addresses">
                    <p className="tpe-txt">Shipping Addresses</p>
                    {addrInfoList.map((addrInfo, index) => (
                        <AddressInfoBox 
                            addrInfo={addrInfo} />
                    ))}
                </div>
            </div>
        </div>
    );
}

export default ProfilePage;
