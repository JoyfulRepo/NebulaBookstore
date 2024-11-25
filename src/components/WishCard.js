import React from "react";

import addToCartIcon from '../assets/add-to-cart-icon.svg'
import buyNowIcon from '../assets/buy-now-icon.svg'
import minusIcon from '../assets/minus-icon.svg'
import plusIcon from '../assets/plus-icon.svg'

import '../styles/CompWishCard.css'

function WishCard({imgUrl, name, price, rating, remaining, wishAmount}) {

    return (
        <div className="wish-card">
            <img src={imgUrl} className="w-book-img"></img>
            <div className="w-book-info">
                <p className="w-book-name">{name}</p>
                <p className="w-book-price">Price {price} vnđ</p>
                <p className="w-book-rating">Rating: {rating}</p>
                <p className="w-book-remaining">Remaining: {remaining}</p>
            </div>
            <div className="w-purchase-container">
                <div className="w-amount-modify">
                    <img src={minusIcon} className="minus-btn"></img>
                    <p className="w-amount-num">{wishAmount}</p>
                    <img src={plusIcon} className="add-btn"></img>
                </div>
                <div className="w-make-decision">
                    <div className="w-add-to-cart-btn">
                        <img src={addToCartIcon} className="w-add-to-cart-icon"></img>
                        <p className="add-to-cart-txt">Add to cart</p>
                    </div>
                    <div className="w-buy-now-btn">
                        <img src={buyNowIcon} className="w-buy-now-icon"></img>
                        <p className="buy-now-txt">Buy now</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default WishCard;