import React from "react"

import cancelIcon from '../assets/cancel-order-icon.svg'

import '../styles/PrepareCard.css'

function PreparingCard({prepareCardInfo}) {

    return (
        <div className="prepare-card">
            <img src={prepareCardInfo.imgUrl} className="prp-book-cover"></img>
            <div className="prepare-card-info">
                <p className="prepare-book-name">{prepareCardInfo.title}</p>
                <div className="prepare-book-info">
                    <div className="prepare-order-info">
                        <p className="prp-prop">Price: <span className="prp-or-value">{prepareCardInfo.price}</span></p>
                        <p className="prp-prop">Quantity: <span className="prp-or-value">{prepareCardInfo.quantity}</span></p>
                        <p className="prp-prop">Total Price: <span className="prp-or-value" id="prp-spcart-total">{prepareCardInfo.total}</span></p>
                        <p className="prp-prop">Payment Method: <span className="prp-or-value" id="prp-spcart-payMethod">{prepareCardInfo.payment}</span></p>
                        <p className="prp-prop">Payment Status: <span className="prp-or-value" id="prp-spcart-payStatus">{prepareCardInfo.payStatus}</span></p>
                    </div>
                    <div className="prepare-shipper-info">
                        <p className="prp-prop">Shipped by: <span className="prp-or-value">{prepareCardInfo.shippedBy}</span></p>
                        <p className="prp-prop">Shipper: <span className="prp-or-value">{prepareCardInfo.shipper}</span></p>
                        <p className="prp-prop">Shipping Code: <span className="prp-or-value">{prepareCardInfo.shipCode}</span></p>
                    </div>
                    <div className="prepare-deli-date">
                        <p className="prp-prop">Out for Delivery: <span className="prp-or-value">{prepareCardInfo.outDeliDate}</span></p>
                        <div className="cancel-order-btn">
                            <img src={cancelIcon} className="cancel-order-icon"></img>
                            <p className="cancel-order-txt">Cancel Order</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default PreparingCard;
