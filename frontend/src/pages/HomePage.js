import React from "react";

import '../styles/PageHome.css'

import SidebarLogged from "../components/SidebarLogged";
import BookList from "../components/BookList";
import SearchBox from "../components/SearchBox";

function HomePage() {
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
        <div className="home-page">
            <div className="home-sidebar">
                <SidebarLogged />
            </div>
            <SearchBox />
            <div className="home-main-content">
                <div className="home-list-container">
                    <BookList   listName="Science Books" booksInfo={sampleBookInfo}/>
                </div>
                <div className="home-list-container">
                    <BookList   listName="Adventure Books" booksInfo={sampleBookInfo}/>
                </div>
            </div>
        </div>
    );
}

export default HomePage;