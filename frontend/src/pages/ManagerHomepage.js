import React from "react";

import '../styles/ManagerHomepage.css'

function ManagerHomepage () {

    const staffData = [
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
        { name: "Nguyen Van A", role: "IT Staff", workingHours: "8:00 - 16:00" },
    ];
    
    return (
        <div
            className="manager-homepage"
            style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                minHeight: "100vh", 
                backgroundColor: "rgba(219, 237, 243, 0.2)", 
            }}
        >
            <div style={{ width: "80%", maxWidth: "800px" }}>
                <h1 style={{ textAlign: "center" }}>Manager Homepage</h1>
                <p style={{ textAlign: "center" }}>Welcome to the Manager Homepage. Below is the staff information:</p>

                
                <div
                    style={{
                        overflowY: "auto",
                        maxHeight: "400px",
                        border: "1px solid #ccc",
                        borderRadius: "8px",
                        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    }}
                >
                    <table
                        className="staff-table"
                        border="1"
                        style={{
                            width: "100%",
                            textAlign: "left",
                            borderCollapse: "collapse",
                            backgroundColor: "#ffffff",
                        }}
                    >
                        <thead>
                            <tr>
                                <th>Staff Name</th>
                                <th>Role</th>
                                <th>Working Hours</th>
                            </tr>
                        </thead>
                        <tbody>
                            {staffData.map((staff, index) => (
                                <tr key={index}>
                                    <td>{staff.name}</td>
                                    <td>{staff.role}</td>
                                    <td>{staff.workingHours}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default ManagerHomepage;