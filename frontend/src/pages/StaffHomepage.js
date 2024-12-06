import React from "react";

function StaffHomepage() {
    const workingHours = [
        { day: "Monday", hours: "8:00 - 16:00" },
        { day: "Tuesday", hours: "8:00 - 16:00" },
        { day: "Wednesday", hours: "8:00 - 16:00" },
        { day: "Thursday", hours: "8:00 - 16:00" },
        { day: "Friday", hours: "8:00 - 16:00" },
        { day: "Saturday", hours: "8:00 - 12:00" },
    ];

    return (
        <div
            className="staff-homepage"
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
                Staff Homepage
            </h1>
            <p style={{ textAlign: "center", marginBottom: "20px" }}>
                Below is your weekly working schedule:
            </p>

            <div style={{ overflowX: "auto", width: "90%", maxWidth: "800px" }}>
                <table
                    className="working-schedule-table"
                    border="1"
                    style={{
                        width: "100%",
                        textAlign: "center",
                        borderCollapse: "collapse",
                        backgroundColor: "#ffffff",
                    }}
                >
                    <thead>
                        <tr>
                            {workingHours.map((day, index) => (
                                <th key={index}>{day.day}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            {workingHours.map((day, index) => (
                                <td key={index}>{day.hours}</td>
                            ))}
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default StaffHomepage;