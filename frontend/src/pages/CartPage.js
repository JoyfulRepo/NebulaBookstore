import React, { useState } from "react";

import SidebarLogged from "../components/SidebarLogged";
import SearchBox from "../components/SearchBox";
import CartCard from "../components/CartCard";

import buyNowIcon from "../assets/buy-now-icon.svg";
import "../styles/PageCart.css";

function CartPage() {
  const [selectedItems, setSelectedItems] = useState([]);

  const cartList = [
    {
      imgUrl: "/images/book_cover/sample-book-cover.svg",
      name: "You're a good friend, Capybara",
      rating: 5,
      price: 250000,
      remaining: 400,
      cartAmount: 1,
    },
    {
      imgUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1598823299i/42844155.jpg",
      name: "Harry Potter and the Sorcerer’s Stone",
      rating: 5,
      price: 400000,
      remaining: 100,
      cartAmount: 1,
    },
    {
      imgUrl:
        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1474169725i/15881.jpg",
      name: "Harry Potter and the Chamber of Secrets",
      rating: 5,
      price: 350000,
      remaining: 70,
      cartAmount: 1,
    },
  ];

  const handleToggleItem = (item, isChecked) => {
    if (isChecked) {
      setSelectedItems((prev) => [...prev, item]);
    } else {
      setSelectedItems((prev) =>
        prev.filter((selected) => selected.name !== item.name)
      );
    }
  };

  const totalItems = selectedItems.reduce(
    (sum, item) => sum + item.cartAmount,
    0
  );
  const totalPrice = selectedItems.reduce(
    (sum, item) => sum + item.price * item.cartAmount,
    0
  );

  return (
    <div className="cart-page">
      <div className="sidebar">
        <SidebarLogged />
      </div>
      <SearchBox />
      <div className="c-main-content">
        <div className="c-cart-list">
          {cartList.map((cartItem, index) => (
            <div className="c-cart-item-container" key={index}>
              <CartCard
                imgUrl={cartItem.imgUrl}
                name={cartItem.name}
                rating={cartItem.rating}
                price={cartItem.price}
                remaining={cartItem.remaining}
                cartAmount={cartItem.cartAmount}
                onToggle={(item, isChecked) =>
                  handleToggleItem(item, isChecked)
                }
              />
            </div>
          ))}
        </div>
        <div className="summary-box">
          <p className="summary-txt">SUMMARY</p>
          <p className="num-items">Selected Items: {totalItems}</p>
          <p className="total">Total: {totalPrice.toLocaleString()} vnđ</p>
          <div className="c-underline"></div>
          <div className="purchase-btn">
            <img src={buyNowIcon} className="purchase-icon" alt="Purchase" />
            <p className="purchase-txt">Purchase now</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CartPage;
