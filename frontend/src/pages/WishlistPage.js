import React from "react";

import SidebarLogged from "../components/SidebarLogged";
import SearchBox from "../components/SearchBox";
import WishCard from "../components/WishCard";

import '../styles/PageWishlist.css'

function WishlistPage () {

    const wishlist = [
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
        {
            imgUrl : "/images/book_cover/sample-book-cover.svg",
            name: "You're a good friend, Capybara",
            rating: 5,
            price: "99,999",
            remaining: 999,
            wishAmount: 99,
        },
    ];

    return (
        <div className="wishlist-page">
            <div className="sidebar">
                <SidebarLogged />
            </div>
            <SearchBox />
            <div className="main-content">
                {wishlist.map((book, index) => (
                    <WishCard 
                        imgUrl={book.imgUrl}
                        name={book.name}
                        price={book.price}
                        rating={book.rating}
                        remaining={book.remaining}
                        wishAmount={book.wishAmount}
                        className="wish-card-container">    
                    </WishCard>)
                )}
                
            </div>
        </div>
    );
}

export default WishlistPage;