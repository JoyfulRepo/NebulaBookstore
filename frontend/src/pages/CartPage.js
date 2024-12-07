import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import SidebarLogged from "../components/SidebarLogged";
import SearchBox from "../components/SearchBox";
import CartCard from "../components/CartCard";

import buyNowIcon from "../assets/buy-now-icon.svg";
import "../styles/PageCart.css";

function CartPage() {
  const [selectedItems, setSelectedItems] = useState({});
  const navigate = useNavigate();

  const cartList = [
    {
      id: 1,
      imgUrl: "/images/book_cover/sample-book-cover.svg",
      name: "You're a good friend, Capybara",
      rating: 5,
      price: 99999,
      remaining: 999,
      cartAmount: 999,
    },
    {
      id: 2,
      imgUrl: "/images/book_cover/sample-book-cover.svg",
      name: "You're a good friend, Capybara",
      rating: 5,
      price: 99999,
      remaining: 999,
      cartAmount: 999,
    },
    {
      id: 3,
      imgUrl: "/images/book_cover/sample-book-cover.svg",
      name: "Another Capybara",
      rating: 5,
      price: 99999,
      remaining: 999,
      cartAmount: 999,
    },
  ];

  const handleToggleItem = (item, isChecked) => {
    setSelectedItems((prev) => {
      const updated = { ...prev };
      if (isChecked) {
        updated[item.id] = item.cartAmount;
      } else {
        delete updated[item.id];
      }
      return updated;
    });
  };

  const handleQuantityChange = (itemId, newQuantity) => {
    setSelectedItems((prev) => {
      const updated = { ...prev };
      if (updated[itemId] !== undefined) {
        updated[itemId] = newQuantity;
      }
      return updated;
    });
  };

  const handlePurchaseNow = () => {
    const selectedItemsList = cartList
      .filter((item) => Object.keys(selectedItems).includes(item.id.toString()))
      .map((item) => ({
        ...item,
        cartAmount: selectedItems[item.id],
      }));
    navigate("/Payment", { state: { selectedItems: selectedItemsList } });
  };

  const totalItems = Object.values(selectedItems).reduce(
    (sum, qty) => sum + qty,
    0
  );
  const totalPrice = Object.entries(selectedItems).reduce((sum, [id, qty]) => {
    const item = cartList.find((item) => item.id === parseInt(id));
    return sum + item.price * qty;
  }, 0);

  return (
    <div className="cart-page">
      <div className="sidebar">
        <SidebarLogged />
      </div>
      <SearchBox />
      <div className="c-main-content">
        <div className="c-cart-list">
          {cartList.map((cartItem) => (
            <div className="c-cart-item-container" key={cartItem.id}>
              <CartCard
                {...cartItem}
                selected={selectedItems[cartItem.id] !== undefined}
                onToggle={(isChecked) => handleToggleItem(cartItem, isChecked)}
                onQuantityChange={(newQuantity) =>
                  handleQuantityChange(cartItem.id, newQuantity)
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
          <div className="purchase-btn" onClick={handlePurchaseNow}>
            <img src={buyNowIcon} className="purchase-icon" alt="Purchase" />
            <p className="purchase-txt">Purchase now</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CartPage;
