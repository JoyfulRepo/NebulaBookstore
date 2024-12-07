import React from "react";

import '../styles/PageWelcome.css'

import Sidebar from "../components/Sidebar";
import BookList from "../components/BookList";
import SearchBox from "../components/SearchBox";

function WelcomePage() {
    const sampleBookInfo = [
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        },
        {
            name: "You're a good friend, Capybara",
            rating: 5,
            price: '99,999',
            imageUrl: "/images/book_cover/sample-book-cover.svg",
        }
        
    ];

    return (
        <div className="welcome-page">
            <div className="sidebar">
                <Sidebar />
            </div>
            <SearchBox />
            <div className="main-content">
                <div className="list-container">
                    <BookList   listName="Best Sellers" booksInfo={sampleBookInfo}/>
                </div>
                <div className="list-container">
                    <BookList   listName="New Arrivals" booksInfo={sampleBookInfo}/>
                </div>
                <div className="list-container">
                    <BookList   listName="Popular Books" booksInfo={sampleBookInfo}/>
                </div>
            </div>
        </div>
    );
}

export default WelcomePage;