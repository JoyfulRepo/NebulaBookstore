import React from "react";

import '../styles/ShippingCard.css'

function ShippingCard({shippingCardInfo}) {

    return (
        <div className="shipping-card">
            <img src={shippingCardInfo.imgUrl} className="shipping-book-cover"></img>
            <div className="ship-card-info">
                <p className="shipg-book-name">{shippingCardInfo.title}</p>
                <div className="ship-book-info">
                    <div className="ship-order-info">
                        <p className="sp-prop">Price: <span className="or-value">{shippingCardInfo.price}</span></p>
                        <p className="sp-prop">Quantity: <span className="or-value">{shippingCardInfo.quantity}</span></p>
                        <p className="sp-prop">Total Price: <span className="or-value" id="spcart-total">{shippingCardInfo.total}</span></p>
                        <p className="sp-prop">Payment Method: <span className="or-value" id="spcart-payMethod">{shippingCardInfo.payment}</span></p>
                        <p className="sp-prop">Payment Status: <span className="or-value" id="spcart-payStatus">{shippingCardInfo.payStatus}</span></p>
                    </div>
                    <div className="ship-shipper-info">
                        <p className="sp-prop">Shipped by: <span className="or-value">{shippingCardInfo.shippedBy}</span></p>
                        <p className="sp-prop">Shipper: <span className="or-value">{shippingCardInfo.shipper}</span></p>
                        <p className="sp-prop">Shipping Code: <span className="or-value">{shippingCardInfo.shipCode}</span></p>
                    </div>
                    <div className="deli-date">
                    <p className="sp-prop">Delivery Date: <span className="or-value">{shippingCardInfo.deliDate}</span></p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ShippingCard;