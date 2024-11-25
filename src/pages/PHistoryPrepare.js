import React from "react";

import SidebarLogged from "../components/SidebarLogged";
import ShipStatusBar from "../components/ShipStatusBar";
import PreparingCard from "../components/PreparingCard";

import '../styles/PagePHPrepare.css'

function PHistoryPrepare () {

    const prepareCardInfo = {
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
        outDeliDate: 'Mon, Nov 11st, 2024',
    };

    return (
        <div className="ph-prepare-page">
            <div className="sidebar">
                <SidebarLogged/>
            </div>
            <div className="ph-prepare-content">
                <div className="prepare-status-bar">
                    <ShipStatusBar />
                </div>
                <div className="prepare-cards-list">
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                    <div className="prepare-cart">
                        <PreparingCard 
                            prepareCardInfo={prepareCardInfo} />
                    </div>
                </div>
            </div>
            
        </div>
    );
}

export default PHistoryPrepare;