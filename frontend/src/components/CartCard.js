import React, { useState } from "react";

import minusIcon from "../assets/minus-icon.svg";
import plusIcon from "../assets/plus-icon.svg";
import tickIcon from "../assets/tick-icon.svg";

import "../styles/CompCardCart.css";

function CartCard({
  imgUrl,
  name,
  rating,
  price,
  remaining,
  cartAmount,
  onToggle,
}) {
  const [quantity, setQuantity] = useState(cartAmount);
  const [isChecked, setIsChecked] = useState(false);

  const handleDecrease = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  const handleIncrease = () => {
    if (quantity < remaining) {
      setQuantity(quantity + 1);
    }
  };

  const handleInputChange = (event) => {
    const value = event.target.value;
    if (/^\d*$/.test(value)) {
      const number = parseInt(value, 10);
      if (number > 0 && number <= remaining) {
        setQuantity(number);
      } else if (value === "") {
        setQuantity("");
      }
    }
  };

  const handleBlur = () => {
    if (quantity === "" || quantity < 1) {
      setQuantity(1);
    }
  };

  const handleCheckboxChange = (event) => {
    const checked = event.target.checked;
    setIsChecked(checked);
    if (onToggle) {
      onToggle({ name, price, cartAmount: quantity }, checked);
    }
  };

  return (
    <div className="cart-card">
      <label className="c-tick-box-container">
        <input
          type="checkbox"
          className="c-toggle-checkbox"
          checked={isChecked}
          onChange={handleCheckboxChange}
        />
        <div className="c-box">
          <img src={tickIcon} className="c-tick-icon" alt="Tick" />
        </div>
      </label>
      <div className="cart-info">
        <img src={imgUrl} className="c-book-img" alt={name} />
        <div className="c-book-info">
          <p className="c-book-name">{name}</p>
          <p className="c-book-price">Price {price.toLocaleString()} vnđ</p>
          <p className="c-book-rating">Rating: {rating}</p>
          <p className="c-book-remaining">Remaining: {remaining}</p>
        </div>
        <div className="c-purchase-container">
          <div className="c-amount-modify">
            <button
              className="c-minus-btn"
              onClick={handleDecrease}
              disabled={quantity <= 1}
            >
              <img src={minusIcon} alt="Decrease" />
            </button>
            <input
              type="text"
              className="c-amount-input"
              value={quantity}
              onChange={handleInputChange}
              onBlur={handleBlur}
            />
            <button
              className="c-add-btn"
              onClick={handleIncrease}
              disabled={quantity >= remaining}
            >
              <img src={plusIcon} alt="Increase" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CartCard;
