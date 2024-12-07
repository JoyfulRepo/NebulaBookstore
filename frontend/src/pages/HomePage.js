import React from "react";

import "../styles/PageHome.css";

import SidebarLogged from "../components/SidebarLogged";
import BookList from "../components/BookList";
import SearchBox from "../components/SearchBox";

function HomePage() {
  const sampleFictionBookInfo = [
    {
      name: "Harry Potter and the Sorcerer’s Stone",
      rating: 5,
      price: 400000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1598823299i/42844155.jpg",
    },
    {
      name: "Harry Potter and the Chamber of Secrets",
      rating: 5,
      price: 350000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1474169725i/15881.jpg",
    },
    {
      name: "Harry Potter and the Prisoner of Azkaban",
      rating: 5,
      price: 350000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1630547330i/5.jpg",
    },
    {
      name: "Harry Potter and the Goblet of Fire",
      rating: 5,
      price: 400000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1627044952i/58613424.jpg",
    },
    {
      name: "Harry Potter and the Order of the Phoenix",
      rating: 5,
      price: 350000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1546910265i/2.jpg",
    },
    {
      name: "Harry Potter and the Half-Blood Prince",
      rating: 5,
      price: 320000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1627043894i/58613345.jpg",
    },
    {
      name: "Harry Potter and the Deathly Hallows",
      rating: 5,
      price: 380000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1627042661i/58613224.jpg",
    },
  ];

  const sampleHorrorBookInfo = [
    {
      name: "Deadly Animals",
      rating: 5,
      price: 450000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1712758755i/203579073.jpg",
    },
    {
      name: "She's Always Hungry: Stories",
      rating: 5,
      price: 600000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1714600043i/201033505.jpg",
    },
    {
      name: "I Am the Dark That Answers When You Call",
      rating: 5,
      price: 490000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1710879434i/200982292.jpg",
    },
    {
      name: "Something is Killing the Children, Vol. 8",
      rating: 5,
      price: 300000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1718819480i/207298687.jpg",
    },
    {
      name: "Perfect Girl",
      rating: 5,
      price: 500000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1710616939i/200982280.jpg",
    },
    {
      name: "American Rapture",
      rating: 5,
      price: 550000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1712784134i/203579244.jpg",
    },
    {
      name: "Coup de Grâce",
      rating: 5,
      price: 600000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1706635900i/204642427.jpg",
    },
  ];

  return (
    <div className="home-page">
      <div className="home-sidebar">
        <SidebarLogged />
      </div>
      <SearchBox />
      <div className="home-main-content">
        <div className="home-list-container">
          <BookList listName="Fiction" booksInfo={sampleFictionBookInfo} />
        </div>
        <div className="home-list-container">
          <BookList listName="Horror" booksInfo={sampleHorrorBookInfo} />
        </div>
      </div>
    </div>
  );
}

export default HomePage;
