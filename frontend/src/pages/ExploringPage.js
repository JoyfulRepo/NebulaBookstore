import React from "react";

import '../styles/PageExplore.css'

import BookList from "../components/BookList";
import SearchBox from "../components/SearchBox";
import SidebarLogged from "../components/SidebarLogged";

function ExploringPage() {
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
        <div className="explore-page">
            <div className="explore-sidebar">
                <SidebarLogged />
            </div>
            <SearchBox />
            <div className="main-content">
                <div className="list-container">
                    <BookList   listName="Science Books" booksInfo={sampleBookInfo}/>
                </div>
                <div className="list-container">
                    <BookList   listName="Adventure Books" booksInfo={sampleBookInfo}/>
                </div>
            </div>
        </div>
    );
}

export default ExploringPage;