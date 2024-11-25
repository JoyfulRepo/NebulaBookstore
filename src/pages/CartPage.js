import React from "react";

import SidebarLogged from "../components/SidebarLogged";
import SearchBox from "../components/SearchBox";
import CartCard from "../components/CartCard";

import tickIcon from '../assets/tick-icon.svg'
import buyNowIcon from '../assets/buy-now-icon.svg'
import '../styles/PageCart.css'


function CartPage() {

    const cartList = [
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name : "You're a good friend, Capybara",
            rating : 5,
            price : "99,999",
            remaining : 999,
            cartAmount : 999,
        },
    ];

    return (
        <div className="cart-page">
            <div className="sidebar">
                <SidebarLogged/>
            </div>
            <SearchBox />
            <div className="c-main-content">
                <div className="c-cart-list">
                    {cartList.map((cartItem, index) =>
                        <div className="c-cart-item-container">
                            <CartCard 
                                imgUrl={cartItem.imgUrl}
                                name={cartItem.name}
                                rating={cartItem.rating}
                                price={cartItem.price}
                                remaining={cartItem.remaining}
                                cartAmount={cartItem.cartAmount}/>
                        </div>
                    )}
                </div>
                <div className="summary-box">
                    <p className="summary-txt">SUMMARY</p>
                    <p className="num-items">Selected Items: 999</p>
                    <p className="total">Total: 99,999,999 vnđ</p>
                    <div className="c-underline"></div>
                    <div className="purchase-btn">
                        <img src={buyNowIcon} className="purchase-icon"></img>
                        <p className="purchase-txt">Purchase now</p>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default CartPage;