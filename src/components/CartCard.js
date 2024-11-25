import React from "react";

import minusIcon from '../assets/minus-icon.svg'
import plusIcon from '../assets/plus-icon.svg'
import tickIcon from '../assets/tick-icon.svg'

import '../styles/CompCardCart.css'

function CartCard ({imgUrl, name, rating, price, remaining, cartAmount}) {

    return (
        <div className="cart-card">
            <label className="c-tick-box-container">
                <input type="checkbox" className="c-toggle-checkbox" />
                <div className="c-box">
                    <img src={tickIcon} className="c-tick-icon"></img>
                </div>
            </label>
            <div className="cart-info">
                <img src={imgUrl} className="c-book-img"></img>
                <div className="c-book-info">
                    <p className="c-book-name">{name}</p>
                    <p className="c-book-price">Price {price} vnđ</p>
                    <p className="c-book-rating">Rating: {rating}</p>
                    <p className="c-book-remaining">Remaining: {remaining}</p>
                </div>
                <div className="c-purchase-container">
                    <div className="c-amount-modify">
                        <img src={minusIcon} className="c-minus-btn"></img>
                        <p className="c-amount-num">{cartAmount}</p>
                        <img src={plusIcon} className="c-add-btn"></img>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CartCard;