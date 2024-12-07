import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import "../styles/PaymentPage.css";

function PaymentPage() {
    const location = useLocation();
    const navigate = useNavigate();
    const selectedItems = location.state?.selectedItems || [];

    const [paymentMethod, setPaymentMethod] = useState(""); // State to track selected payment method
    const [address, setAddress] = useState(""); // State to track delivery address
    const [cardDetails, setCardDetails] = useState({
        cardNumber: "",
        ccv: "",
        expiryDate: "",
    });

    // Group items by book name and calculate total price
    const groupedItems = selectedItems.reduce((acc, item) => {
        const existingItem = acc.find((i) => i.name === item.name);
        if (existingItem) {
            existingItem.cartAmount += item.cartAmount;
        } else {
            acc.push({ ...item });
        }
        return acc;
    }, []);
    const totalPrice = groupedItems.reduce(
        (sum, item) => sum + item.price * item.cartAmount,
        0
    );

    const handlePaymentChange = (event) => {
        setPaymentMethod(event.target.value);
    };

    const handleCardDetailChange = (field, value) => {
        setCardDetails((prev) => ({ ...prev, [field]: value }));
    };

    const handleFinishPayment = () => {
        if (!address.trim()) {
            alert("Please enter your delivery address!");
            return;
        }

        if (!paymentMethod) {
            alert("Please select a payment method!");
            return;
        }

        if (
            paymentMethod === "credit_card" &&
            (!cardDetails.cardNumber || !cardDetails.ccv || !cardDetails.expiryDate)
        ) {
            alert("Please fill in all credit card details!");
            return;
        }

        alert("Payment successfully processed!");
        navigate("/"); // Navigate back to the homepage
    };

    return (
        <div
            className="payment-page"
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                minHeight: "100vh",
                textAlign: "center",
                backgroundColor: "#f9f9f9",
            }}
        >
            <div className="selected-items" style={{ width: "80%", margin: "0 auto" }}>
                <h1>Payment Page</h1>
                <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
                    <thead>
                        <tr>
                            <th>Book</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        {groupedItems.map((item, index) => (
                            <tr key={index}>
                                <td>{item.name}</td>
                                <td>{item.cartAmount}</td>
                                <td>{item.price.toLocaleString()} vnđ</td>
                                <td>
                                    {(item.price * item.cartAmount).toLocaleString()} vnđ
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <h3 style={{ textAlign: "right", marginTop: "20px" }}>
                    Total Price: {totalPrice.toLocaleString()} vnđ
                </h3>
            </div>

            <div style={{ marginTop: "30px", width: "80%" }}>
                <label
                    htmlFor="delivery-address"
                    style={{ display: "block", marginBottom: "10px", fontWeight: "bold" }}
                >
                    Delivery Address:
                </label>
                <textarea
                    id="delivery-address"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                    placeholder="Enter your full delivery address here..."
                    rows={4}
                    style={{
                        width: "100%",
                        padding: "10px",
                        fontSize: "16px",
                        borderRadius: "5px",
                        border: "1px solid #ccc",
                        resize: "none",
                    }}
                ></textarea>
            </div>

            <div style={{ marginTop: "20px", width: "80%" }}>
                <label style={{ display: "block", marginBottom: "10px", fontWeight: "bold" }}>
                    Please select payment method:
                </label>
                <select
                    value={paymentMethod}
                    onChange={handlePaymentChange}
                    style={{
                        width: "100%",
                        padding: "10px",
                        fontSize: "16px",
                        borderRadius: "5px",
                        border: "1px solid #ccc",
                    }}
                >
                    <option value="" disabled>
                        Select a payment method
                    </option>
                    <option value="cash">Cash</option>
                    <option value="credit_card">Credit Card</option>
                </select>
            </div>

            {paymentMethod === "credit_card" && (
                <div style={{ marginTop: "20px", width: "80%" }}>
                    <h4 style={{ textAlign: "left", marginBottom: "10px" }}>Credit Card Details</h4>
                    <div style={{ marginBottom: "10px" }}>
                        <input
                            type="text"
                            placeholder="Card Number"
                            value={cardDetails.cardNumber}
                            onChange={(e) =>
                                handleCardDetailChange("cardNumber", e.target.value)
                            }
                            style={{
                                width: "100%",
                                padding: "10px",
                                fontSize: "16px",
                                borderRadius: "5px",
                                border: "1px solid #ccc",
                                marginBottom: "10px",
                            }}
                        />
                    </div>
                    <div style={{ display: "flex", gap: "10px" }}>
                        <input
                            type="text"
                            placeholder="CCV"
                            value={cardDetails.ccv}
                            onChange={(e) => handleCardDetailChange("ccv", e.target.value)}
                            style={{
                                flex: 1,
                                padding: "10px",
                                fontSize: "16px",
                                borderRadius: "5px",
                                border: "1px solid #ccc",
                            }}
                        />
                        <input
                            type="text"
                            placeholder="Expiry Date (MM/YY)"
                            value={cardDetails.expiryDate}
                            onChange={(e) =>
                                handleCardDetailChange("expiryDate", e.target.value)
                            }
                            style={{
                                flex: 1,
                                padding: "10px",
                                fontSize: "16px",
                                borderRadius: "5px",
                                border: "1px solid #ccc",
                            }}
                        />
                    </div>
                </div>
            )}

            <button
                onClick={handleFinishPayment}
                style={{
                    marginTop: "30px",
                    padding: "15px 30px",
                    fontSize: "18px",
                    backgroundColor: "#00818A",
                    color: "white",
                    border: "none",
                    borderRadius: "5px",
                    cursor: "pointer",
                }}
            >
                Finish Payment
            </button>
        </div>
    );
}

export default PaymentPage;