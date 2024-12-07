import React from "react";

import SidebarLogged from "../components/SidebarLogged";
import ShipStatusBar from "../components/ShipStatusBar";
import DeliveredCard from "../components/DeliveredCard";

import "../styles/PagePHDelivered.css";

function PHistoryDelivered() {
  const deliveredCardInfo = {
    imgUrl: "/images/book_cover/sample-book-cover.svg",
    title: "You're a good friend, Capybara",
    price: "99,999",
    quantity: 999,
    total: "99,999,999",
    payment: "Cash",
    payStatus: "Paid",
    shippedBy: "Very Fast Express",
    shipper: "Cute Shipper",
    shipCode: "ABC8899KOL",
    deliDate: "Mon, Nov 11st, 2024",
    actDate: "Mon, Nov 11st, 2024",
  };

  return (
    <div className="ph-delivered-page">
      <div className="sidebar">
        <SidebarLogged />
      </div>
      <div className="ph-delivered-content">
        <div className="delivered-status-bar">
          <ShipStatusBar />
        </div>
        <div className="delivered-cards-list">
          <div className="delivered-cart">
            <DeliveredCard deliveredCardInfo={deliveredCardInfo} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default PHistoryDelivered;
