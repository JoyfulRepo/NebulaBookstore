import React from "react";

import '../styles/SearchBox.css'

import searchIcon from '../assets/search-icon.svg'

function SearchBox () {

    return (
        <div className="search-box">
            <img src={searchIcon} alt="Search Icon" className="search-icon"></img>
            <span className="search-text">Search books</span>
        </div>
    );
}

export default SearchBox