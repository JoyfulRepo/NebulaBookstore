import React, { useState } from "react";
import "../styles/BookCard.css";
import addBookButton from "../assets/add-book-icon.svg";

function BookCard({
    name,
    rating,
    price,
    imageUrl,
    publicationDate,
    description,
    genre,
    author,
    publisher,
}) {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleCardClick = () => {
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    return (
        <>
            <div className="book-card" onClick={handleCardClick}>
                <img src={imageUrl} alt={name} className="book-cover" />
                <p className="book-name">{name}</p>
                <p className="book-rating">Rating: {rating}</p>
                <p className="book-price">
                    Price: <span className="price">{price} vnđ</span>
                </p>
                <button
                    className="add-book-button"
                    style={{
                        border: "none",
                        background: "none",
                        cursor: "pointer",
                        padding: 0,
                    }}
                    onClick={(e) => {
                        e.stopPropagation(); 
                        alert("Added to cart!");
                    }}
                >
                    <img
                        src={addBookButton}
                        alt="Add to Cart"
                        style={{ width: "128px", height: "32px" }}
                    />
                </button>
            </div>

            {isModalOpen && (
                <div className="modal-overlay" onClick={handleCloseModal}>
                    <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                        <button className="close-modal" onClick={handleCloseModal}>
                            ✖
                        </button>
                        <div className="modal-inner">
                            <div className="modal-img">
                                <img
                                    src={imageUrl}
                                    alt={name}
                                    className="modal-book-cover"
                                />
                            </div>
                            <div className="modal-c-content">
                                <h2>{name}</h2>
                                <p><strong>Price:</strong> {price} vnđ</p>
                                <p><strong>Rating:</strong> {rating}</p>
                                <p><strong>Publication Date:</strong> {publicationDate}</p>
                                <p><strong>Description:</strong> {description}</p>
                                <p><strong>Genre:</strong> {genre}</p>
                                <p><strong>Author:</strong> {author}</p>
                                <p><strong>Publisher:</strong> {publisher}</p>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}

export default BookCard;