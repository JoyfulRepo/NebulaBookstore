import React from "react";

import SidebarLogged from "../components/SidebarLogged";
import SearchBox from "../components/SearchBox";
import WishCard from "../components/WishCard";

import "../styles/PageWishlist.css";

function WishlistPage() {
  const wishlist = [
    {
      name: "Harry Potter and the Sorcerer’s Stone",
      rating: 5,
      price: 400000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1598823299i/42844155.jpg",
    },
    {
      name: "I Am the Dark That Answers When You Call",
      rating: 5,
      price: 490000,
      imageUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1710879434i/200982292.jpg",
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
            name={book.name}
            price={book.price}
            rating={book.rating}
            imgUrl={book.imageUrl}
            remaining={book.remaining || 100}
            wishAmount={book.wishAmount || 1}
            className="wish-card-container"
          ></WishCard>
        ))}
      </div>
    </div>
  );
}

export default WishlistPage;
