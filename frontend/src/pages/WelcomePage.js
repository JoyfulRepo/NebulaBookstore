import React from "react";

import "../styles/PageWelcome.css";

import Sidebar from "../components/Sidebar";
import BookList from "../components/BookList";
import SearchBox from "../components/SearchBox";

function WelcomePage() {
  const sampleBookInfo = [
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
    {
      name: "You're a good friend, Capybara",
      rating: 5,
      price: "99,999",
      imageUrl: "/images/book_cover/sample-book-cover.svg",
    },
  ];

  const sampleBSBookInfo = [
    {
      name: "The Wedding People",
      rating: 5,
      price: 450000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1721918653i/198902277.jpg",
    },
    {
      name: "The God of the Woods",
      rating: 5,
      price: 500000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1717970585i/199700434.jpg",
    },
    {
      name: "All the Colors of the Dark",
      rating: 5,
      price: 700000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1718910617i/203019740.jpg",
    },
    {
      name: "The Night We Lost Him",
      rating: 5,
      price: 520000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1708711207i/207299448.jpg",
    },
    {
      name: "Society of Lies",
      rating: 5,
      price: 600000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1729005765i/202102003.jpg",
    },
  ];
  const sampleNRBookInfo = [
    {
      name: "Rental House",
      rating: 5,
      price: 450000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1708055761i/208584952.jpg",
    },
    {
      name: "Three Days in June",
      rating: 5,
      price: 500000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1719007741i/213243949.jpg",
    },
    {
      name: "Good Dirt",
      rating: 5,
      price: 700000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1721437195i/213618132.jpg",
    },
    {
      name: "Famous Last Words",
      rating: 5,
      price: 520000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1726491569i/212421066.jpg",
    },
    {
      name: "Cross My Heart",
      rating: 5,
      price: 600000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1719020968i/214152422.jpg",
    },
  ];
  const sampleRBookInfo = [
    {
      name: "Steve Jobs",
      rating: 5,
      price: 450000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1511288482i/11084145.jpg",
    },
    {
      name: "Spare",
      rating: 5,
      price: 500000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1673458354i/62296528.jpg",
    },
    {
      name: "Becoming",
      rating: 5,
      price: 700000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1528206996i/38746485.jpg",
    },
    {
      name: "Finding Me",
      rating: 5,
      price: 520000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1650842995i/58687126.jpg",
    },
    {
      name: "Greenlights",
      rating: 5,
      price: 600000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1604281659i/52838315.jpg",
    },
  ];

  return (
    <div className="welcome-page">
      <div className="sidebar">
        <Sidebar />
      </div>
      <SearchBox />
      <div className="main-content">
        <div className="list-container">
          <BookList listName="Best Sellers" booksInfo={sampleBSBookInfo} />
        </div>
        <div className="list-container">
          <BookList listName="New Arrivals" booksInfo={sampleNRBookInfo} />
        </div>
        <div className="list-container">
          <BookList listName="Recommendations" booksInfo={sampleRBookInfo} />
        </div>
      </div>
    </div>
  );
}

export default WelcomePage;
