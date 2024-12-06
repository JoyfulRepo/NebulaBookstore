import React, { useState } from "react";

import "../styles/SearchBox.css";
import searchIcon from "../assets/search-icon.svg";

function SearchBox() {
    const [searchTerm, setSearchTerm] = useState(""); 

    const handleInputChange = (event) => {
        setSearchTerm(event.target.value);
    };

    return (
        <div className="search-box">
            <img src={searchIcon} alt="Search Icon" className="search-icon" />
            <input
                type="text"
                value={searchTerm}
                onChange={handleInputChange}
                className="search-input"
                placeholder="Search books"
            />
        </div>
    );
}

export default SearchBox;