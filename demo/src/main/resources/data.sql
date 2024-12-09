USE COMPANY;
#################### Customer related ####################
# Create new customer
DELIMITER $$
CREATE PROCEDURE RegisterCustomer(
    IN p_Username VARCHAR(50),
    IN p_PasswordHash VARCHAR(255),
    IN p_CustomerName VARCHAR(100),
    IN p_Email VARCHAR(100),
    IN p_Gender ENUM('Male', 'Female', 'Other'),
    IN p_PhoneNumber VARCHAR(20),
    IN p_JoinDate DATE
)
BEGIN
    # Check if Username already exists
    IF EXISTS (SELECT 1 FROM COMPANY.CUSTOMER WHERE Username = p_Username) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Username already exists';
    END IF;
    # Check if Email already exists
    IF EXISTS (SELECT 1 FROM COMPANY.CUSTOMER WHERE Email = p_Email) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Email already exists';
    END IF;
    # Insert the new customer into the table
    INSERT INTO COMPANY.CUSTOMER (
        Username, PasswordHash, CustomerName, Email, Gender, PhoneNumber, JoinDate
    ) VALUES (
        p_Username, p_PasswordHash, p_CustomerName, p_Email, p_Gender, p_PhoneNumber, p_JoinDate
    );
    # Notify the caller
    SELECT LAST_INSERT_ID() AS NewCustomerID;
END$$
DELIMITER ;

#Example usage
CALL RegisterCustomer(
    'john_doe',
    SHA2('securepassword', 256), -- Hash the password
    'John Doe',
    'john.doe@example.com',
    'Male',
    '1234567890',
    CURDATE() -- Current date as join date
);

# View account details
DELIMITER $$
CREATE FUNCTION ViewAccountDetails(
    p_CustomerID INT
)
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE accountDetails JSON;
    SELECT JSON_OBJECT(
        'CustomerName', CustomerName,
        'Gender', Gender,
        'Email', Email,
        'JoinDate', JoinDate,
        'PhoneNumber', PhoneNumber,
        'Username', Username,
        'PasswordHash', PasswordHash
    ) INTO accountDetails
    FROM COMPANY.CUSTOMER
    WHERE CustomerID = p_CustomerID;
    -- Return the JSON object
    RETURN accountDetails;
END$$
DELIMITER ;

# Example usage
SELECT ViewAccountDetails(1);

# Function to get order history
DELIMITER $$
CREATE FUNCTION GetOrderHistory(
    p_CustomerID INT
)
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE orderHistory JSON;
    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'OrderDate', OrderDate,
            'Status', Status,
            'TotalAmount', TotalAmount,
            'PaymentMethod', PaymentMethod
        )
    ) INTO orderHistory
    FROM COMPANY.ORDER
    WHERE CustomerID = p_CustomerID;
    RETURN orderHistory;
END$$
DELIMITER ;

# Example usage
SELECT GetOrderHistory(10);

# Edit customer profile
DELIMITER $$
CREATE FUNCTION EditCustomerProfile(
    p_CustomerID INT,
    p_NewName VARCHAR(100),
    p_NewEmail VARCHAR(100),
    p_NewPasswordHash VARCHAR(255),
    p_NewPhoneNumber VARCHAR(20)
) 
RETURNS VARCHAR(255)
DETERMINISTIC
NO SQL
BEGIN
    DECLARE v_Message VARCHAR(255);
    -- Check if customer exists
    IF NOT EXISTS (SELECT 1 FROM COMPANY.CUSTOMER WHERE CustomerID = p_CustomerID) THEN
        SET v_Message = 'Customer not found';
        RETURN v_Message;
    END IF;
    -- Update the customer profile information
    UPDATE COMPANY.CUSTOMER
    SET 
        CustomerName = p_NewName,
        Email = p_NewEmail,
        PasswordHash = p_NewPasswordHash,
        PhoneNumber = p_NewPhoneNumber
    WHERE CustomerID = p_CustomerID;
    -- Check if the update was successful
    IF ROW_COUNT() > 0 THEN
        SET v_Message = 'Profile updated successfully';
    ELSE
        SET v_Message = 'Profile update failed';
    END IF;
    RETURN v_Message;
END $$
DELIMITER ;

#Example
SELECT EditCustomerProfile(1, 'New Name', 'newemail@example.com', 'newhashedpassword', '1234567890');

# Delete customer
DELIMITER $$
CREATE PROCEDURE DeleteCustomerAccount(
    IN p_CustomerID INT
)
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM COMPANY.CUSTOMER 
        WHERE CustomerID = p_CustomerID
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Customer account does not exist';
    END IF;
    DELETE FROM COMPANY.CUSTOMER
    WHERE CustomerID = p_CustomerID;
END$$
DELIMITER ;

#Example usage
CALL DeleteCustomerAccount(10);

#################### Books related ####################
# Create wish list
DELIMITER $$
CREATE PROCEDURE CreateWishlist(
    IN p_CustomerID INT,
    IN p_BookID INT
)
BEGIN
	#Check if the customer exists
    IF NOT EXISTS (
        SELECT 1 
        FROM COMPANY.CUSTOMER 
        WHERE CustomerID = p_CustomerID
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Customer does not exist';
    END IF;
    # Check if the book exists
    IF NOT EXISTS (
        SELECT 1 
        FROM COMPANY.BOOK 
        WHERE BookID = p_BookID
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Book does not exist';
    END IF;
    # Check if the book is already in the customer's wishlist
    IF EXISTS (
        SELECT 1 
        FROM COMPANY.CUSTOMER_WISH_BOOK 
        WHERE CustomerID = p_CustomerID AND BookID = p_BookID
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Book is already in the wishlist';
    END IF;
    INSERT INTO COMPANY.CUSTOMER_WISH_BOOK (CustomerID, BookID)
    VALUES (p_CustomerID, p_BookID);
END$$
DELIMITER ;
# Example Usage
CALL CreateWishlist(1, 10);

# Create review
DELIMITER $$
CREATE PROCEDURE CreateReview(
    IN p_CustomerID INT,
    IN p_BookID INT,
    IN p_Rating INT,
    IN p_Review VARCHAR(1000)
)
BEGIN
    # Validate if the customer exists
    IF NOT EXISTS (
        SELECT 1 
        FROM COMPANY.CUSTOMER 
        WHERE CustomerID = p_CustomerID
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Customer does not exist';
    END IF;
    # Validate if the book exists
    IF NOT EXISTS (
        SELECT 1 
        FROM COMPANY.BOOK 
        WHERE BookID = p_BookID
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Book does not exist';
    END IF;
    # Check if the rating is within the valid range (1-5)
    IF p_Rating < 1 OR p_Rating > 5 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Rating must be between 1 and 5';
    END IF;
    # Check if the review already exists for this customer and book
    IF EXISTS (
        SELECT 1 
        FROM COMPANY.REVIEW 
        WHERE CustomerID = p_CustomerID AND BookID = p_BookID
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Review already exists for this book by the customer';
    END IF;
    # Insert the review into the REVIEW table
    INSERT INTO COMPANY.REVIEW (Rating, Review, CustomerID, BookID)
    VALUES (p_Rating, p_Review, p_CustomerID, p_BookID);
END$$
DELIMITER ;
#Example usage
CALL CreateReview(1,10,5,'Amazing book! A must-read for everyone.');

#View book details
DELIMITER $$
CREATE FUNCTION ViewBookDetails(p_BookID INT)
RETURNS JSON
READS SQL DATA
BEGIN
    DECLARE bookDetails JSON;
    SELECT JSON_OBJECT(
        'Title', b.Title,
        'Price', b.Price,
        'Status', b.Status,
        'Quantity', b.Quantity,
        'PublicationDate', b.PublicationDate,
        'CoverImage', b.CoverImage,
        'BriefDescription', b.BriefDescription,
        'Genre', (
            SELECT JSON_ARRAYAGG(g.GenreName)
            FROM COMPANY.BOOK_HAS_GENRE bg
            JOIN COMPANY.GENRE g ON bg.GenreID = g.GenreID
            WHERE bg.BookID = b.BookID
        ),
        'Author', (
            SELECT JSON_ARRAYAGG(a.AuthorName)
            FROM COMPANY.BOOK_HAS_AUTHOR ba
            JOIN COMPANY.AUTHOR a ON ba.AuthorID = a.AuthorID
            WHERE ba.BookID = b.BookID
        ),
        'Publisher', p.PublisherName
    )
    INTO bookDetails
    FROM COMPANY.BOOK b
    LEFT JOIN COMPANY.PUBLISHER p ON b.PublisherID = p.PublisherID
    WHERE b.BookID = p_BookID;
    RETURN bookDetails;
END$$
DELIMITER ;
#Example usage
SELECT ViewBookDetails(2) AS BookDetails;

# View book review
DELIMITER $$
CREATE FUNCTION ViewBookReviews(p_BookID INT)
RETURNS JSON
READS SQL DATA
BEGIN
    DECLARE reviews JSON;
    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'CustomerName', c.CustomerName,
            'Rating', r.Rating,
            'Review', r.Review
        )
    )
    INTO reviews
    FROM COMPANY.REVIEW r
    JOIN COMPANY.CUSTOMER c ON r.CustomerID = c.CustomerID
    WHERE r.BookID = p_BookID;
    RETURN reviews;
END$$
DELIMITER ;
#Example usage
SELECT ViewBookReviews(1);

#Modify wishlist
DELIMITER $$
CREATE PROCEDURE UpdateWishlist(
    IN p_CustomerID INT,
    IN p_BookID INT,
    IN p_Action ENUM('ADD', 'REMOVE')
)
BEGIN
    # Check the action: ADD or REMOVE
    IF p_Action = 'ADD' THEN
        IF NOT EXISTS (SELECT 1 FROM COMPANY.CUSTOMER_WISH_BOOK WHERE CustomerID = p_CustomerID AND BookID = p_BookID) THEN
            INSERT INTO COMPANY.CUSTOMER_WISH_BOOK (CustomerID, BookID)
            VALUES (p_CustomerID, p_BookID);
        ELSE
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Book already in the wishlist';
        END IF;
    ELSEIF p_Action = 'REMOVE' THEN
        # Remove the book from the wishlist
        DELETE FROM COMPANY.CUSTOMER_WISH_BOOK
        WHERE CustomerID = p_CustomerID AND BookID = p_BookID;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid action. Use ADD or REMOVE.';
    END IF;
END$$
DELIMITER ;
#Example usage
CALL UpdateWishlist(1, 10, 'ADD'); -- Adds book with ID 10 to customer with ID 1's wishlist
CALL UpdateWishlist(1, 10, 'REMOVE'); -- Removes book with ID 10 from customer with ID 1's wishlist

#Update review
DELIMITER $$
CREATE PROCEDURE UpdateReview(
    IN p_CustomerID INT,
    IN p_BookID INT,
    IN p_NewRating INT,
    IN p_NewReview VARCHAR(1000)
)
BEGIN
    # Check if the customer has already reviewed the book
    IF EXISTS (SELECT 1 FROM COMPANY.REVIEW WHERE CustomerID = p_CustomerID AND BookID = p_BookID) THEN
        # Update the existing review
        UPDATE COMPANY.REVIEW
        SET Rating = p_NewRating, Review = p_NewReview
        WHERE CustomerID = p_CustomerID AND BookID = p_BookID;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No existing review found for this book from this customer.';
    END IF;
END$$
DELIMITER ;

#Example usage
# Updates the review for book with ID 10 from customer with ID 1 with new rating 4 and new review text
CALL UpdateReview(1, 10, 4, 'This book was quite good. I really enjoyed it !!'); 

#Remove books from wishlist
DELIMITER $$
CREATE PROCEDURE RemoveBookFromWishlist(
    IN p_CustomerID INT,
    IN p_BookID INT
)
BEGIN
    # Check if the book exists in the customer's wishlist
    IF EXISTS (SELECT 1 FROM COMPANY.CUSTOMER_WISH_BOOK WHERE CustomerID = p_CustomerID AND BookID = p_BookID) THEN
        # Remove the book from the wishlist
        DELETE FROM COMPANY.CUSTOMER_WISH_BOOK
        WHERE CustomerID = p_CustomerID AND BookID = p_BookID;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'This book is not in the customer\'s wishlist';
    END IF;
END$$
DELIMITER ;

#Example usage
# Removes the book with ID 10 from the wishlist of the customer with ID 1
CALL RemoveBookFromWishlist(7, 7); 

#################### Purchasing and checkout ####################
#Create order from a cart of customer
DELIMITER $$
CREATE PROCEDURE CreateOrderForCustomer(
	IN p_CartID INT,
    IN p_CustomerID INT,
    IN p_PaymentMethod ENUM('Online Banking', 'Cash', 'Credit Card', 'Debit Card'),
    IN p_PaymentStatus ENUM('Paid', 'Unpaid', 'Refunded'),
    IN p_ExpectedArrival DATE,
    IN p_Destination VARCHAR(255),
    IN p_EmployeeID INT
)
BEGIN
	DECLARE p_OrderDate DATE;
    DECLARE p_TotalAmount Decimal(10,2);
    SET p_OrderDate = CURDATE();
    
	IF p_CartID IS NULL OR p_CustomerID IS NULL OR p_PaymentMethod IS NULL OR p_PaymentMethod IS NULL 
				OR p_ExpectedArrival IS NULL OR  p_PaymentStatus IS NULL OR p_Destination IS NULL OR p_EmployeeID IS NULL 
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Input parameters cannot be NULL';
    END IF;
    
    -- Calculate the total amount for the order by summing the prices of books in the cart
    SELECT SUM(b.Price * c.Quantity) INTO p_TotalAmount
    FROM COMPANY.CART_CONTAIN_BOOK c
    JOIN COMPANY.BOOK b ON c.BookID = b.BookID
    WHERE c.CartID = p_CartID;

    -- Check if the cart is empty
    IF p_TotalAmount = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cart is empty or invalid items in the cart';
    END IF;

    -- Insert a new order into the ORDER table
    INSERT INTO COMPANY.ORDER (OrderDate, ExpectedArrival, Destination, Status, TotalAmount, PaymentMethod, PaymentStatus, CartID, CustomerID, EmployeeID)
    VALUES (p_OrderDate, p_ExpectedArrival, p_Destination, 'Pending', p_TotalAmount, p_PaymentMethod, p_PaymentStatus, p_CartID, p_CustomerID, p_EmployeeID);
END$$
DELIMITER ;
-- Example --
CALL CreateOrderForCustomer(
    2,                           -- CartID
    2,                           -- CustomerID
    'Credit Card',                 -- PaymentMethod
    'Paid',                        -- PaymentStatus
    '2024-12-10',                  -- ExpectedArrival
    '456 Elm St, Cityville',       -- Destination
    7                            -- EmployeeID
);

-- View Cart --
DELIMITER $$
CREATE PROCEDURE ViewCartInformation(
    IN p_CustomerID INT
)
BEGIN
    -- Fetch cart details for the specified customer
    SELECT 
        c.CustomerID,
        cu.CustomerName,
        cu.Email,
        b.BookID,
        b.Title,
        b.Price,
        cb.Quantity,
        (b.Price * cb.Quantity) AS TotalBookPrice,
        SUM(b.Price * cb.Quantity) OVER (PARTITION BY c.CustomerID) AS TotalCartValue
    FROM 
        COMPANY.CART c
    INNER JOIN 
        COMPANY.CART_CONTAIN_BOOK cb ON c.CartID = cb.CartID AND c.CustomerID = cb.CustomerID
    INNER JOIN 
        COMPANY.BOOK b ON cb.BookID = b.BookID
    INNER JOIN 
        COMPANY.CUSTOMER cu ON c.CustomerID = cu.CustomerID
    WHERE 
        c.CustomerID = p_CustomerID;
END$$
DELIMITER ;

-- Example Usage:
CALL ViewCartInformation(2); 

-- Get Order Detail --
DELIMITER $$
-- Function to View Order Details and All Books in the Cart as JSON
CREATE FUNCTION GetOrderDetailsJSON(p_OrderID INT)
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE orderJSON JSON;
    DECLARE booksJSON JSON;
	DECLARE gotCartID int;
    -- Get order details: OrderDate, ExpectedArrival, Destination, Status, TotalAmount, PaymentMethod, PaymentStatus, CartID, CustomerID, EmployeeID
    SELECT JSON_OBJECT(
        'OrderID', OrderID,
        'OrderDate', OrderDate,
        'ExpectedArrival', ExpectedArrival,
        'Destination', Destination,
        'Status', Status,
        'TotalAmount', TotalAmount,
        'PaymentMethod', PaymentMethod,
        'PaymentStatus', PaymentStatus,
        'CartID', CartID,
        'CustomerID', CustomerID,
        'EmployeeID', EmployeeID
    ), CartID
    INTO orderJSON, gotCartID
    FROM COMPANY.ORDER
    WHERE OrderID = p_OrderID;
    -- Check if order exists
    IF orderJSON IS NULL THEN
        RETURN JSON_OBJECT('Error', 'Order not found');
    END IF;
    -- Add book details from cart_contain_book
    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'BookID', BookID,
            'Quantity', Quantity
        )
    )
    INTO booksJSON
    FROM cart_contain_book
    WHERE CartID = gotCartID;
    -- Check if books exist in the cart
    IF booksJSON IS NULL THEN
        SET booksJSON = JSON_ARRAY();
    END IF;
    -- Combine order and book details into a single JSON
    RETURN JSON_MERGE_PATCH(orderJSON, JSON_OBJECT('Books', booksJSON));
END $$
DELIMITER ;
-- Example Usage:
SELECT GetOrderDetailsJSON(1);

-- Modify Cart (This is divided into 2 function for ease of management) --
DELIMITER $$
-- Procedure to Add a Book to a Cart
CREATE PROCEDURE AddBookToCart(
    IN p_CartID INT,
    IN p_CustomerID INT,
    IN p_BookID INT,
    IN p_Quantity INT
)
BEGIN
    -- Check if the cart exists
    IF NOT EXISTS (SELECT 1 FROM cart WHERE CartID = p_CartID AND CustomerID = p_CustomerID) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cart not found for the specified customer';
    END IF;

    -- Check if the book already exists in the cart
    IF EXISTS (SELECT 1 FROM cart_contain_book WHERE CartID = p_CartID AND CustomerID = p_CustomerID AND BookID = p_BookID) THEN
        -- Update the quantity if the book exists
        UPDATE cart_contain_book
        SET Quantity = Quantity + p_Quantity
        WHERE CartID = p_CartID AND CustomerID = p_CustomerID AND BookID = p_BookID;
    ELSE
        -- Add the book to the cart if it doesn't exist
        INSERT INTO cart_contain_book (CartID, CustomerID, BookID, Quantity)
        VALUES (p_CartID, p_CustomerID, p_BookID, p_Quantity);
    END IF;

    -- Update the LastModified date of the cart
    UPDATE cart
    SET LastModified = CURDATE()
    WHERE CartID = p_CartID;
END $$
DELIMITER ;

DELIMITER $$
-- Procedure to Remove a Book from a Cart
CREATE PROCEDURE RemoveBookFromCart(
    IN p_CartID INT,
    IN p_CustomerID INT,
    IN p_BookID INT
)
BEGIN
    -- Check if the cart exists
    IF NOT EXISTS (SELECT 1 FROM cart WHERE CartID = p_CartID AND CustomerID = p_CustomerID) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cart not found for the specified customer';
    END IF;

    -- Check if the book exists in the cart
    IF NOT EXISTS (SELECT 1 FROM cart_contain_book WHERE CartID = p_CartID AND CustomerID = p_CustomerID AND BookID = p_BookID) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Book not found in the cart';
    END IF;

    -- Remove the book from the cart
    DELETE FROM cart_contain_book
    WHERE CartID = p_CartID AND CustomerID = p_CustomerID AND BookID = p_BookID;

    -- Update the LastModified date of the cart
    UPDATE cart
    SET LastModified = CURDATE()
    WHERE CartID = p_CartID;
END $$
DELIMITER ;
-- Example Usage:
CALL AddBookToCart(2, 2, 10, 2);
CALL RemoveBookFromCart(2, 2, 10);

#################### Adminstration ####################
DELIMITER $$
CREATE FUNCTION ViewActivityLogs(
    p_UserID INT,             -- Optional filter by user ID, NULL for all users
    p_StartDate DATE,         -- Optional start date for filtering, NULL for no lower bound
    p_EndDate DATE            -- Optional end date for filtering, NULL for no upper bound
)
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE logs JSON;
    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'LogID', LogID,
            'ActionType', ActionType,
            'ActionDescription', ActionDescription,
            'Date', Date,
            'Time', Time,
            'CustomerID', CustomerID
        )
    ) INTO logs
    FROM COMPANY.ACTIVITYLOG
    WHERE 
        (CustomerID = p_UserID OR p_UserID IS NULL)
        AND (Date >= p_StartDate OR p_StartDate IS NULL)
        AND (Date <= p_EndDate OR p_EndDate IS NULL);
    -- Return the JSON array
    RETURN logs;
END$$
DELIMITER ;
-- Example usage --
SELECT ViewActivityLogs(1, NULL, NULL) AS UserLogs;
SELECT ViewActivityLogs(2, "2024-10-01", "2024-11-01") AS UserLogs;

-- Add Employee Function (This must be divide into 3 function because how db organized) --
-- Add Shipper --
DELIMITER $$
CREATE PROCEDURE AddEmployeeShipper(
    IN p_Name VARCHAR(100),        -- Employee's name
    IN p_Gender ENUM('Male', 'Female', 'Other'), -- Employee's gender
    IN p_DoB DATE,                -- Employee's birthdate
    IN p_IdentityCardNum VARCHAR(50), -- Unique ID card number
    IN p_PhoneNumber VARCHAR(20),     -- Employee's phone number
    IN p_Address VARCHAR(200),        -- Employee's address
    IN p_StartDate DATE,              -- Employee's start date
    IN p_Salary DECIMAL(10, 2),       -- Employee's salary
    IN p_VehicleNumber VARCHAR(20),
    IN p_VehicleType VARCHAR(30),
    IN p_Username VARCHAR(50),        -- Unique username
    IN p_PasswordHash VARCHAR(255)   -- Hashed password
)
BEGIN
        INSERT INTO COMPANY.SHIPPER (
            ShipperName, Gender, DoB, IdentityCardNum, PhoneNumber, 
            Address, StartDate, Salary, VehicleNumber, VehicleType, Username, PasswordHash
        )
        VALUES (
            p_Name, p_Gender, p_DoB, p_IdentityCardNum, p_PhoneNumber, 
            p_Address, p_StartDate, p_Salary,p_VehicleNumber, p_VehicleType, p_Username, p_PasswordHash
        );
END$$
DELIMITER ;
-- Add Manager -- 
DELIMITER $$
CREATE PROCEDURE AddEmployeeManager(
    IN p_Name VARCHAR(100),        -- Employee's name
    IN p_Gender ENUM('Male', 'Female', 'Other'), -- Employee's gender
    IN p_DoB DATE,                -- Employee's birthdate
    IN p_IdentityCardNum VARCHAR(50), -- Unique ID card number
    IN p_PhoneNumber VARCHAR(20),     -- Employee's phone number
    IN p_Address VARCHAR(200),        -- Employee's address
    IN p_StartDate DATE,              -- Employee's start date
    IN p_Salary DECIMAL(10, 2),       -- Employee's salary
    IN p_PerformanceScore INT,        -- Employee's performance score
    IN p_Username VARCHAR(50),        -- Unique username
    IN p_PasswordHash VARCHAR(255)   -- Hashed password
)
BEGIN
    INSERT INTO COMPANY.MANAGER (
        ManagerName, Gender, DoB, IdentityCardNum, PhoneNumber, 
        Address, StartDate, Salary, PerformanceScore, Username, PasswordHash
    )
    VALUES (
        p_Name, p_Gender, p_DoB, p_IdentityCardNum, p_PhoneNumber, 
        p_Address, p_StartDate, p_Salary, p_PerformanceScore, p_Username, p_PasswordHash
    );
END$$
DELIMITER ;
-- Add for Staff --
DELIMITER $$
CREATE PROCEDURE AddEmployeeStaff(
    IN p_Name VARCHAR(100),        -- Employee's name
    IN p_Gender ENUM('Male', 'Female', 'Other'), -- Employee's gender
    IN p_DoB DATE,                -- Employee's birthdate
    IN p_IdentityCardNum VARCHAR(50), -- Unique ID card number
    IN p_PhoneNumber VARCHAR(20),     -- Employee's phone number
    IN p_Address VARCHAR(200),        -- Employee's address
    IN p_StartDate DATE,              -- Employee's start date
    IN p_Salary DECIMAL(10, 2),       -- Employee's salary
    IN p_Username VARCHAR(50),        -- Unique username
    IN p_PasswordHash VARCHAR(255)   -- Hashed password
)
BEGIN
    INSERT INTO COMPANY.STAFF (
        StaffName, Gender, DoB, IdentityCardNum, PhoneNumber, 
        Address, StartDate, Salary, Username, PasswordHash
    )
    VALUES (
        p_Name, p_Gender, p_DoB, p_IdentityCardNum, p_PhoneNumber, 
        p_Address, p_StartDate, p_Salary, p_Username, p_PasswordHash
    );
END$$
DELIMITER ;
-- Example Usage --
CALL AddEmployeeShipper(
    'John Doe',                   -- Name
    'Male',                        -- Gender
    '1985-04-15',                  -- Date of Birth
    'ID1234567890',                -- Identity Card Number
    '123-456-7890',                -- Phone Number
    '123 Shipper St, Cityville',   -- Address
    '2024-01-01',                  -- Start Date
    5000.00,                       -- Salary
    'ABC123',                      -- Vehicle Number
    'Truck',                       -- Vehicle Type
    'johndoe_shipper',             -- Username
    'hashedpassword123'            -- Hashed Password
);
CALL AddEmployeeManager(
    'Alice Smith',                 -- Name
    'Female',                      -- Gender
    '1990-07-22',                  -- Date of Birth
    'ID9876543210',                -- Identity Card Number
    '321-654-0987',                -- Phone Number
    '456 Manager Ave, Cityville',  -- Address
    '2024-02-01',                  -- Start Date
    7000.00,                       -- Salary
    85,                            -- Performance Score
    'alicesmith_manager',          -- Username
    'hashedpassword456'            -- Hashed Password
);
CALL AddEmployeeStaff(
    'Bob Johnson',                 -- Name
    'Male',                         -- Gender
    '1995-03-30',                   -- Date of Birth
    'ID1122334455',                 -- Identity Card Number
    '456-789-0123',                 -- Phone Number
    '789 Staff Rd, Cityville',      -- Address
    '2024-03-01',                   -- Start Date
    4000.00,                        -- Salary
    'bobjohnson_staff',             -- Username
    'hashedpassword789'             -- Hashed Password
);

-- Read Employee Info (also needs to divide into 3 functions) --

-- Read Shipper Info --
DELIMITER $$
CREATE FUNCTION ViewShipperDetails(
    p_EmployeeID INT
)
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE shipperDetails JSON;
    SELECT JSON_OBJECT(
        'ShipperName', ShipperName,
        'Gender', Gender,
        'DoB', DoB,
        'IdentityCardNum', IdentityCardNum,
        'PhoneNumber', PhoneNumber,
        'Address', Address,
        'StartDate', StartDate,
        'Salary', Salary,
        'VehicleNumber', VehicleNumber,
        'VehicleType', VehicleType,
        'Username', Username
    ) INTO shipperDetails
    FROM COMPANY.SHIPPER
    WHERE EmployeeID = p_EmployeeID;
    RETURN shipperDetails;
END$$
DELIMITER ;

-- Read Manager Info --
DELIMITER $$
CREATE FUNCTION ViewManagerDetails(
    p_EmployeeID INT
)
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE managerDetails JSON;
    SELECT JSON_OBJECT(
        'ManagerName', ManagerName,
        'Gender', Gender,
        'DoB', DoB,
        'IdentityCardNum', IdentityCardNum,
        'PhoneNumber', PhoneNumber,
        'Address', Address,
        'StartDate', StartDate,
        'Salary', Salary,
        'PerformanceScore', PerformanceScore,
        'Username', Username
    ) INTO managerDetails
    FROM COMPANY.MANAGER
    WHERE EmployeeID = p_EmployeeID;
    RETURN managerDetails;
END$$
DELIMITER ;

-- Read Staff Info -- 
DELIMITER $$
CREATE FUNCTION ViewStaffDetails(
    p_EmployeeID INT
)
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE staffDetails JSON;
    SELECT JSON_OBJECT(
        'StaffName', StaffName,
        'Gender', Gender,
        'DoB', DoB,
        'IdentityCardNum', IdentityCardNum,
        'PhoneNumber', PhoneNumber,
        'Address', Address,
        'StartDate', StartDate,
        'Salary', Salary,
        'Username', Username
    ) INTO staffDetails
    FROM COMPANY.STAFF
    WHERE EmployeeID = p_EmployeeID;
    RETURN staffDetails;
END$$
DELIMITER ;

-- Example Usage --
-- Call the function to view Shipper details
SELECT ViewShipperDetails(1);
-- Call the function to view Manager details
SELECT ViewManagerDetails(1);
-- Call the function to view Staff details
SELECT ViewStaffDetails(1);

-- Update Employee Function --
DELIMITER $$
CREATE PROCEDURE ModifyEmployeeDetails(
    IN p_EmployeeID INT,
    IN p_EmployeeType ENUM('Shipper', 'Manager', 'Staff'),
    IN p_PhoneNumber VARCHAR(20),
    IN p_Address VARCHAR(200),
    IN p_Salary DECIMAL(10, 2)		-- Only allow for modification of Phone, Number, Address, Salary
)
BEGIN
    -- Update details for Shipper
    IF p_EmployeeType = 'Shipper' THEN
        UPDATE COMPANY.SHIPPER
        SET PhoneNumber = p_PhoneNumber,
            Address = p_Address,
            Salary = p_Salary
        WHERE EmployeeID = p_EmployeeID;

    -- Update details for Manager
    ELSEIF p_EmployeeType = 'Manager' THEN
        UPDATE COMPANY.MANAGER
        SET PhoneNumber = p_PhoneNumber,
            Address = p_Address,
            Salary = p_Salary
        WHERE EmployeeID = p_EmployeeID;

    -- Update details for Staff
    ELSEIF p_EmployeeType = 'Staff' THEN
        UPDATE COMPANY.STAFF
        SET PhoneNumber = p_PhoneNumber,
            Address = p_Address,
            Salary = p_Salary
        WHERE EmployeeID = p_EmployeeID;
    END IF;
END$$
DELIMITER ;

-- Example Usage --
CALL ModifyEmployeeDetails(
    1,              -- EmployeeID (for example, Shipper with ID 1)
    'Shipper',      -- EmployeeType
    '987-654-3210', -- New phone number
    '456 New Address, Cityville',  -- New address
    5500.00       -- New salary
);
CALL ModifyEmployeeDetails(
    2,              -- EmployeeID (for example, Manager with ID 2)
    'Manager',      -- EmployeeType
    '555-555-5555', -- New phone number
    '789 Manager Ave, Cityville', -- New address
    7000.00        -- New salary
);

-- DELETE (also divide into 3 function)--
-- Shipper --
DELIMITER $$
CREATE PROCEDURE DeleteShipper(
    IN p_EmployeeID INT
)
BEGIN
    DELETE FROM shipper
    WHERE EmployeeID = p_EmployeeID;
END$$
DELIMITER ;
-- Example --
CALL DeleteShipper(1);

-- Staff --
DELIMITER $$
CREATE PROCEDURE DeleteStaff(
    IN p_EmployeeID INT
)
BEGIN
    DELETE FROM staff
    WHERE EmployeeID = p_EmployeeID;
END$$
DELIMITER ;
-- Exampl --
CALL DeleteStaff(1);

-- Manager --
DELIMITER $$
CREATE PROCEDURE DeleteManager(
    IN p_EmployeeID INT
)
BEGIN
    DELETE FROM manager
    WHERE EmployeeID = p_EmployeeID;
END$$
DELIMITER ;
-- Example --
CALL DeleteManager(1);

-- ORDER --
-- Read Order byID --
DELIMITER $$
CREATE FUNCTION GetOrderByID(selectedorderID INT)
RETURNS JSON
DETERMINISTIC
BEGIN
    RETURN (
        SELECT JSON_OBJECT(
            'OrderID', OrderID,
            'OrderDate', OrderDate,
            'ExpectedArrival', ExpectedArrival,
            'Destination', Destination,
            'Status', Status,
            'TotalAmount', TotalAmount,
            'PaymentMethod', PaymentMethod,
            'PaymentStatus', PaymentStatus,
            'CartID', CartID,
            'CustomerID', CustomerID,
            'EmployeeID', EmployeeID
        )
        FROM `order`
        WHERE OrderID = selectedorderID
    );
END $$
DELIMITER ;

# Update order details
DELIMITER $$
CREATE PROCEDURE UpdateOrderByOrderID(
    IN p_OrderID INT,
    IN p_Status ENUM('Pending', 'Shipping', 'Delivered', 'Cancelled'),
    IN p_TotalAmount DECIMAL(10, 2),
    IN p_PaymentMethod ENUM('Online Banking', 'Cash', 'Credit Card', 'Debit Card'),
    IN p_PaymentStatus ENUM('Paid', 'Unpaid', 'Refunded'),
    IN p_Destination VARCHAR(255)
)
BEGIN
    -- Update the order in the ORDER table
    UPDATE COMPANY.ORDER
    SET 
        Status = p_Status,
        TotalAmount = p_TotalAmount,
        PaymentMethod = p_PaymentMethod,
        PaymentStatus = p_PaymentStatus,
        Destination = p_Destination
    WHERE 
        OrderID = p_OrderID;
    
    -- Check if the update was successful
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Order not found or no changes made';
    END IF;
END$$
DELIMITER ;

-- Example --
SELECT GetOrderByID(2);
CALL UpdateOrderByOrderID(
    1,                -- OrderID
    'Shipping',       -- Status
    150.00,           -- TotalAmount
    'Credit Card',    -- PaymentMethod
    'Paid',           -- PaymentStatus
    '123 New Street'  -- Destination
);

-- VIEW STAFF SCHEDULE --
DELIMITER $$
CREATE FUNCTION GetWorkSchedule(InputStaffID INT)
RETURNS JSON
DETERMINISTIC
BEGIN
    RETURN (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'ScheduleID', s.ScheduleID,
                'DayOfWeek', s.DayOfWeek,
                'Shift', s.Shift,
                'StorageID', w.StorageID
            )
        )
        FROM schedule s
        JOIN staff w ON w.EmployeeID = s.StaffID
        WHERE s.StaffID = InputStaffID
    );
END $$
DELIMITER ;
-- Example Use Case --
SELECT GetWorkSchedule(2);

-- DISCOUNT --
-- Add --
DELIMITER $$
CREATE PROCEDURE AddDiscount(
    IN InputStartDate DATE,
    IN InputEndDate DATE,
    IN InputAmount DECIMAL(10, 2)
)
BEGIN
    IF InputEndDate >= InputStartDate THEN
        INSERT INTO discount (StartDate, EndDate, Amount)
        VALUES (InputStartDate, InputEndDate, InputAmount);
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'EndDate must be greater than or equal to StartDate';
    END IF;
END $$
DELIMITER ;
-- Example --
CALL AddDiscount('2024-12-01', '2024-12-31', 10.00);

-- View Discount --
DELIMITER $$
CREATE FUNCTION GetDiscountByID(InputDiscountID INT)
RETURNS JSON
DETERMINISTIC
BEGIN
    RETURN (
        SELECT JSON_OBJECT(
            'DiscountID', DiscountID,
            'StartDate', StartDate,
            'EndDate', EndDate,
            'Amount', Amount
        )
        FROM discount
        WHERE DiscountID = InputDiscountID
    );
END $$
DELIMITER ;
-- Example --
SELECT GetDiscountByID(1);

-- Update --
DELIMITER $$
CREATE PROCEDURE UpdateDiscountByID(
    IN InputDiscountID INT,
    IN InputStartDate DATE,
    IN InputEndDate DATE,
    IN InputAmount DECIMAL(10, 2)
)
BEGIN
    UPDATE discount
    SET 
        StartDate = IFNULL(InputStartDate, StartDate),
        EndDate = IFNULL(InputEndDate, EndDate),
        Amount = IFNULL(InputAmount, Amount)
    WHERE DiscountID = InputDiscountID;
END $$
DELIMITER ;
-- Example --
CALL UpdateDiscountByID(1, '2024-12-05', '2024-12-25', 20.00);

-- Delete -- 
DELIMITER $$
CREATE PROCEDURE DeleteDiscountByID(InputDiscountID INT)
BEGIN
    DELETE FROM discount
    WHERE DiscountID = InputDiscountID;
END $$
DELIMITER ;
-- example --
CALL DeleteDiscountByID(1);

-- Storage --
DELIMITER $$
-- Add a Storage 
CREATE PROCEDURE AddStorage (
    IN inputStorageName VARCHAR(100),
    IN inputAddress VARCHAR(200),
    IN inputCapacity INT,
    IN inputManagerID INT
)
BEGIN
    INSERT INTO storage (StorageName, Address, Capacity, ManagerID)
    VALUES (inputStorageName, inputAddress, inputCapacity, inputManagerID);
END $$

-- Modify a Storage 
CREATE PROCEDURE ModifyStorage (
    IN inputStorageID INT,
    IN inputStorageName VARCHAR(100),
    IN inputAddress VARCHAR(200),
    IN inputCapacity INT,
    IN inputManagerID INT
)
BEGIN
    UPDATE storage
    SET StorageName = inputStorageName,
        Address = inputAddress,
        Capacity = inputCapacity,
        ManagerID = inputManagerID
    WHERE StorageID = inputStorageID;
END $$

-- Delete a Storage 
CREATE PROCEDURE DeleteStorage (
    IN inputStorageID INT
)
BEGIN
    DELETE FROM storage
    WHERE StorageID = inputStorageID;
END $$

DELIMITER ;
-- Example --
CALL AddStorage('Central Warehouse', '123 Main St', 500, 10);
CALL ModifyStorage(1, 'Updated Warehouse', '456 Elm St', 600, 2);
CALL DeleteStorage(1);

# Book management
# Add a book
DELIMITER $$
CREATE PROCEDURE AddNewBook(
    IN p_Title VARCHAR(200),
    IN p_Price DECIMAL(10, 2),
    IN p_CoverImage VARCHAR(500),
    IN p_Quantity INT,
    IN p_PublicationDate DATE,
    IN p_BriefDescription VARCHAR(1000),
    IN p_Status ENUM('Upcoming', 'In Stock', 'Out Of Stock'),
    IN p_PublisherID INT
)
BEGIN
    INSERT INTO COMPANY.BOOK (
        Title, Price, CoverImage, Quantity, PublicationDate, BriefDescription, Status, PublisherID
    )
    VALUES (
        p_Title, p_Price, p_CoverImage, p_Quantity, p_PublicationDate, p_BriefDescription, p_Status, p_PublisherID
    );
END$$
DELIMITER ;

#Example
CALL AddNewBook(
    'Book Title', 
    19.99, 
    'path/to/image.jpg', 
    100, 
    '2024-01-01', 
    'A brief description of the book.', 
    'In Stock', 
    1
);

#View books
DELIMITER $$
CREATE PROCEDURE ViewAllBooks()
BEGIN
    SELECT 
        b.BookID, b.Title, b.Price, b.CoverImage, b.Quantity, b.PublicationDate,
        b.BriefDescription, b.Status, p.PublisherName
    FROM COMPANY.BOOK b
    LEFT JOIN COMPANY.PUBLISHER p ON b.PublisherID = p.PublisherID;
END$$
DELIMITER ;

#example
CALL ViewAllBooks();

#Modify book details
DELIMITER $$
CREATE PROCEDURE ModifyBookDetails(
    IN p_BookID INT,
    IN p_Status ENUM('Upcoming', 'In Stock', 'Out Of Stock'),
    IN p_Quantity INT,
    IN p_BriefDescription VARCHAR(1000),
    IN p_CoverImage VARCHAR(500),
    IN p_Price DECIMAL(10, 2),
    IN p_GenreID INT
)
BEGIN
    -- Update book details
    UPDATE COMPANY.BOOK
    SET 
        Status = p_Status,
        Quantity = p_Quantity,
        BriefDescription = p_BriefDescription,
        CoverImage = p_CoverImage,
        Price = p_Price
    WHERE BookID = p_BookID;
    -- Update book-genre relationship
    DELETE FROM COMPANY.BOOK_HAS_GENRE WHERE BookID = p_BookID;
    INSERT INTO COMPANY.BOOK_HAS_GENRE (BookID, GenreID)
    VALUES (p_BookID, p_GenreID);
    -- Check if the update was successful
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Book not found or no changes made';
    END IF;
END$$
DELIMITER ;

CALL ModifyBookDetails(
    1, 
    'In Stock', 
    150, 
    'Updated book description.', 
    'updated/image/path.jpg', 
    24.99, 
    2 -- GenreID
);

#Remove book
DELIMITER $$
CREATE PROCEDURE RemoveBook(
    IN p_BookID INT
)
BEGIN
    DELETE FROM COMPANY.BOOK
    WHERE BookID = p_BookID;
    -- Check if the deletion was successful
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Book not found or already deleted';
    END IF;
END$$
DELIMITER ;

CALL RemoveBook(1);




