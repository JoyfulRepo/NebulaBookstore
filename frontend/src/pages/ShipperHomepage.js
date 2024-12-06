import React from "react";

function ShipperHomepage() {
    // Sample data for the orders
    const orders = [
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
        {
            orderId: "ABC8899KOL",
            books: [
                { title: "You're a good friend, Capybara", quantity: 2 },
                { title: "You're a good friend, Capybara", quantity: 1 },
            ],
            address: {
                street: "888 Joyful Street",
                ward: "Brilliant Ward",
                district: "Magnificent District",
                city: "Significant City",
                country: "Happy Country",
            },
            receiverName: "Loopy",
            contactNumber: "(+84)999 888 989",
        },
    ];

    return (
        <div
            className="shipper-homepage"
            style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
                minHeight: "100vh",
                backgroundColor: "#f9f9f9",
            }}
        >
            <h1 style={{ textAlign: "center", marginBottom: "20px" }}>
                Shipper Homepage
            </h1>
            <p style={{ textAlign: "center", marginBottom: "20px" }}>
                Below is your shipping orders:
            </p>

            {/* Table */}
            <div style={{ overflowX: "auto", width: "90%", maxWidth: "1000px" }}>
                <table
                    className="orders-table"
                    border="0"
                    style={{
                        width: "100%",
                        textAlign: "left",
                        borderCollapse: "collapse",
                        backgroundColor: "#ffffff",
                    }}
                >
                    <thead>
                        <tr style={{ borderBottom: "2px solid #ccc" }}>
                            <th>Shipping Code</th>
                            <th>List of Books and Quantities</th>
                            <th>Address</th>
                            <th>Receiver Name</th>
                            <th>Contact Number</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders.map((order, index) => (
                            <tr
                                key={index}
                                style={{
                                    borderBottom: "1px solid #ddd",
                                }}
                            >
                                <td>{order.orderId}</td>
                                <td>
                                    {order.books.map((book, bookIndex) => (
                                        <div key={bookIndex}>
                                            {book.title} (x{book.quantity})
                                        </div>
                                    ))}
                                </td>
                                <td>
                                    {`${order.address.street}, ${order.address.ward}, ${order.address.district}, ${order.address.city}, ${order.address.country}`}
                                </td>
                                <td>{order.receiverName}</td>
                                <td>{order.contactNumber}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default ShipperHomepage;