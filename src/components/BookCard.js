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
            <img src={addBookButton} className='add-button'></img>
        </div>
    );
}

export default BookCard