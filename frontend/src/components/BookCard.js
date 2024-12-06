import React from 'react'
import '../styles/BookCard.css'
import addBookButton from '../assets/add-book-icon.svg'

function BookCard ({name, rating, price, imageUrl}) {
    return (
        <div className='book-card'>
            <img src={imageUrl} alt={name} className='book-cover'></img>
            <p className='book-name'>{name}</p>
            <p className='book-rating'>Rating: {rating}</p>
            <p className='book-price'>Price: <span className='price'>{price} vnđ</span></p>
            <button
                className="add-book-button"
                /*onClick={onAddBook}*/
                style={{
                    border: "none",
                    background: "none",
                    cursor: "pointer",
                    padding: 0,
                }}
            >
                <img
                    src={addBookButton}
                    alt="Add to Cart"
                    style={{ width: "128px", height: "32px" }}
                />
            </button>
        </div>
    );
}

export default BookCard