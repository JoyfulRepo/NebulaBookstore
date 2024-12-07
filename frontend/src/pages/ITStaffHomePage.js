import React, { useState } from "react";
import "../styles/ITStaffHomepage.css";

function ITStaffHomepage() {
    const [formData, setFormData] = useState({
        title: "",
        price: "",
        quantity: "",
        publicationDate: "",
        description: "",
        genre: "",
        author: "",
        publisher: "",
        bookImage: null,
    });

    const [imagePreview, setImagePreview] = useState(null);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleImageUpload = (e) => {
        const file = e.target.files[0];
        setFormData((prev) => ({ ...prev, bookImage: file }));

        // Create a preview of the uploaded image
        const reader = new FileReader();
        reader.onload = () => {
            setImagePreview(reader.result);
        };
        reader.readAsDataURL(file);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!formData.title || !formData.price || !formData.quantity || !formData.publicationDate) {
            alert("Please fill out all required fields!");
            return;
        }

        // Simulate API call or form submission
        console.log("Form data submitted:", formData);

        alert("Book added successfully!");
        // Reset form
        setFormData({
            title: "",
            price: "",
            quantity: "",
            publicationDate: "",
            description: "",
            genre: "",
            author: "",
            publisher: "",
            bookImage: null,
        });
        setImagePreview(null);
    };

    return (
        <div className="it-staff-page">
            <h1>IT Staff - Add Book</h1>
            <form onSubmit={handleSubmit} style={{ width: "80%", margin: "0 auto" }}>
                <div className="form-group">
                    <label htmlFor="title">Title *</label>
                    <input
                        type="text"
                        id="title"
                        name="title"
                        value={formData.title}
                        onChange={handleInputChange}
                        placeholder="Enter book title"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="price">Price *</label>
                    <input
                        type="number"
                        id="price"
                        name="price"
                        value={formData.price}
                        onChange={handleInputChange}
                        placeholder="Enter book price"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="quantity">Quantity *</label>
                    <input
                        type="number"
                        id="quantity"
                        name="quantity"
                        value={formData.quantity}
                        onChange={handleInputChange}
                        placeholder="Enter quantity"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="publicationDate">Publication Date *</label>
                    <input
                        type="date"
                        id="publicationDate"
                        name="publicationDate"
                        value={formData.publicationDate}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="description">Description</label>
                    <textarea
                        id="description"
                        name="description"
                        value={formData.description}
                        onChange={handleInputChange}
                        placeholder="Enter book description"
                        rows={4}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="genre">Genre</label>
                    <input
                        type="text"
                        id="genre"
                        name="genre"
                        value={formData.genre}
                        onChange={handleInputChange}
                        placeholder="Enter genre"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="author">Author</label>
                    <input
                        type="text"
                        id="author"
                        name="author"
                        value={formData.author}
                        onChange={handleInputChange}
                        placeholder="Enter author"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="publisher">Publisher</label>
                    <input
                        type="text"
                        id="publisher"
                        name="publisher"
                        value={formData.publisher}
                        onChange={handleInputChange}
                        placeholder="Enter publisher"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="bookImage">Book Image</label>
                    <input
                        type="file"
                        id="bookImage"
                        name="bookImage"
                        accept="image/*"
                        onChange={handleImageUpload}
                    />
                    {imagePreview && (
                        <div className="image-preview">
                            <p>Image Preview:</p>
                            <img src={imagePreview} alt="Preview" style={{ width: "150px", height: "auto" }} />
                        </div>
                    )}
                </div>
                <button type="submit" className="submit-btn">
                    Add Book
                </button>
            </form>
        </div>
    );
}

export default ITStaffHomepage;