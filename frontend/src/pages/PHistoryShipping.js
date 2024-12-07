import React from "react";

import SidebarLogged from "../components/SidebarLogged";
import ShipStatusBar from "../components/ShipStatusBar";
import ShippingCard from "../components/ShippingCard";

import '../styles/PagePHShipping.css'

function PHistoryShipping () {
    const shippingCardInfo = {
        imgUrl: '/images/book_cover/sample-book-cover.svg',
        title: "You're a good friend, Capybara",
        price: "99,999",
        quantity: 999,
        total: '99,999,999',
        payment: 'Cash',
        payStatus: 'Not paid',
        shippedBy: 'Very Fast Express',
        shipper: 'Cute Shipper',
        shipCode: 'ABC8899KOL',
        deliDate: 'Mon, Nov 11st, 2024',
    }
    
    return (
        <div className="ph-shipping-page">
            <div className="sidebar">
                <SidebarLogged/>
            </div>
            <div className="ph-shipping-content">
                <div className="shipg-status-bar">
                    <ShipStatusBar />
                </div>
                <div className="shipping-cards-list">
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>

                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                    <div className="shipping-cart">
                        <ShippingCard 
                            shippingCardInfo={shippingCardInfo}/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default PHistoryShipping;