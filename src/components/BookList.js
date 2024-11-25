import React from "react";
import BookCard from "./BookCard";

import '../styles/BookList.css'

function BookList ({listName, booksInfo}) {
    return (
        <div className="book-list-container">
            <h3 className="list-name">{listName}</h3>
            <div className="cards-container">
                <div className="cards">
                    {booksInfo.map((book, index) => (
                        <BookCard
                            key={index}
                            name={book.name}
                            rating={book.rating}
                            price={book.price}
                            imageUrl={book.imageUrl}
                        />)
                    )}
            </div>
            </div>
        </div>
    );
}

export default BookList