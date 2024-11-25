import React from "react"

import wrtReviewIcon from '../assets/write-review-icon.svg'

import '../styles/DeliveredCard.css'

function DeliveredCard({deliveredCardInfo}) {

    return (
        <div className="delivered-card">
            <img src={deliveredCardInfo.imgUrl} className="dl-book-cover"></img>
            <div className="delivered-card-info">
                <p className="delivered-book-name">{deliveredCardInfo.title}</p>
                <div className="delivered-book-info">
                    <div className="delivered-order-info">
                        <p className="dl-prop">Price: <span className="dl-or-value">{deliveredCardInfo.price}</span></p>
                        <p className="dl-prop">Quantity: <span className="dl-or-value">{deliveredCardInfo.quantity}</span></p>
                        <p className="dl-prop">Total Price: <span className="dl-or-value" id="dl-spcart-total">{deliveredCardInfo.total}</span></p>
                        <p className="dl-prop">Payment Method: <span className="dl-or-value" id="dl-spcart-payMethod">{deliveredCardInfo.payment}</span></p>
                        <p className="dl-prop">Payment Status: <span className="dl-or-value" id="dl-spcart-payStatus">{deliveredCardInfo.payStatus}</span></p>
                    </div>
                    <div className="dl-shipper-info">
                        <p className="dl-prop">Shipped by: <span className="dl-or-value">{deliveredCardInfo.shippedBy}</span></p>
                        <p className="dl-prop">Shipper: <span className="dl-or-value">{deliveredCardInfo.shipper}</span></p>
                        <p className="dl-prop">Shipping Code: <span className="dl-or-value">{deliveredCardInfo.shipCode}</span></p>
                    </div>
                    <div className="dl-deli-date">
                        <p className="dl-prop">Delivery Date: <span className="dl-or-value">{deliveredCardInfo.deliDate}</span></p>
                        <p className="dl-prop">Delivery Date (Actual): <span className="dl-or-value">{deliveredCardInfo.actDate}</span></p>
                        <div className="wrt-review-btn">
                            <img src={wrtReviewIcon} className="wrt-review-icon"></img>
                            <p className="wrt-review-txt">Write Review</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default DeliveredCard;
