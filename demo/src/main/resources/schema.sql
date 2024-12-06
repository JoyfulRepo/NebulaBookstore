-- DROP SCHEMA --
DROP SCHEMA COMPANY;

-- START CREATING OUR ASSIGNMENT SCHEMA AND DATABASE --
-- CREATE DB SCHEMA --
CREATE SCHEMA COMPANY;

-- CREATE ADMIN --
CREATE USER IF NOT EXISTS 'admin_user' @'localhost' IDENTIFIED BY 'admin_password';

GRANT ALL PRIVILEGES ON COMPANY.* TO 'admin_user' @'localhost';

GRANT GRANT OPTION ON *.* TO 'admin_user' @'localhost';

FLUSH PRIVILEGES;

-- CREATE USER --
CREATE USER IF NOT EXISTS 'staff_user' @'localhost' IDENTIFIED BY 'staff_password';

GRANT
SELECT
	ON COMPANY.* TO 'staff_user' @'localhost';

GRANT SHOW VIEW ON COMPANY.* TO 'staff_user' @'localhost';

GRANT
INSERT
	ON COMPANY.* TO 'staff_user' @'localhost';

GRANT DELETE ON COMPANY.* TO 'staff_user' @'localhost';

FLUSH PRIVILEGES;

-- Access user view through cmd run this --
-- Line 1: cd "C:\Program Files\MySQL\MySQL Server 8.0\bin"
-- Line 2: mysql -u staff_user -p --
-- Run code, check user by: SELECT CURRENT_USER(); --
-- exit: EXIT --
-- CREATE DB TABLES --
-- Customer supports account --
CREATE TABLE COMPANY.Users (
	UserID INT PRIMARY KEY AUTO_INCREMENT,
	Username VARCHAR(50) NOT NULL,
	PasswordHash VARCHAR(255) NOT NULL,
	-- Store hashed password --
	Role ENUM('admin', 'ITstaff') NOT NULL DEFAULT 'ITstaff'
);

-- Shipper table --
CREATE TABLE COMPANY.SHIPPER (
	EmployeeID INT AUTO_INCREMENT,
	ShipperName VARCHAR(100) NOT NULL,
	Gender ENUM('Male', 'Female', 'Other'),
	DoB DATE NOT NULL,
	IdentityCardNum VARCHAR(50) NOT NULL UNIQUE,
	PhoneNumber VARCHAR(20) NOT NULL,
	Address VARCHAR(200) NOT NULL,
	StartDate DATE NOT NULL,
	Salary DECIMAL(10, 2) NOT NULL CHECK (Salary > 0),
	VehicleNumber VARCHAR(20) NOT NULL,
	VehicleType VARCHAR(30) NOT NULL,
	Username VARCHAR(50) NOT NULL,
	PasswordHash VARCHAR(255) NOT NULL,
	-- Store hashed password --
	-- Set key --
	PRIMARY KEY (EmployeeID)
);

-- Manager table --
CREATE TABLE COMPANY.MANAGER (
	EmployeeID INT AUTO_INCREMENT,
	ManagerName VARCHAR(100) NOT NULL,
	Gender ENUM('Male', 'Female', 'Other'),
	DoB DATE NOT NULL,
	IdentityCardNum VARCHAR(50) NOT NULL UNIQUE,
	PhoneNumber VARCHAR(20) NOT NULL,
	Address VARCHAR(200) NOT NULL,
	StartDate DATE NOT NULL,
	Salary DECIMAL(10, 2) NOT NULL CHECK (Salary > 0),
	PerformanceScore INT,
	Username VARCHAR(50) NOT NULL,
	PasswordHash VARCHAR(255) NOT NULL,
	-- Store hashed password --
	-- Set key --
	PRIMARY KEY (EmployeeID)
);

-- Staff table --
CREATE TABLE COMPANY.STAFF (
	EmployeeID INT AUTO_INCREMENT,
	StaffName VARCHAR(100) NOT NULL,
	Gender ENUM('Male', 'Female', 'Other'),
	DoB DATE NOT NULL,
	IdentityCardNum VARCHAR(50) NOT NULL UNIQUE,
	PhoneNumber VARCHAR(20) NOT NULL,
	Address VARCHAR(200) NOT NULL,
	StartDate DATE NOT NULL,
	Salary DECIMAL(10, 2) NOT NULL CHECK (Salary > 0),
	Username VARCHAR(50) NOT NULL,
	PasswordHash VARCHAR(255) NOT NULL,
	-- Store hashed password --
	-- Set key --
	PRIMARY KEY (EmployeeID)
);

-- Storage table --
CREATE TABLE COMPANY.STORAGE (
	StorageID INT AUTO_INCREMENT,
	StorageName VARCHAR(100) NOT NULL,
	Address VARCHAR(200) NOT NULL,
	Capacity INT NOT NULL CHECK (Capacity > 0),
	-- Set key --
	PRIMARY KEY (StorageID)
);

-- Book table --
CREATE TABLE COMPANY.BOOK (
	BookID INT AUTO_INCREMENT,
	Title VARCHAR(200) NOT NULL,
	Price DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK (Price > 0),
	CoverImage VARCHAR(500),
	Quantity INT NOT NULL DEFAULT 0 CHECK (Quantity >= 0),
	PublicationDate DATE,
	BriefDescription TEXT,
	Status ENUM('Upcoming', 'In Stock', 'Out Of Stock') NOT NULL DEFAULT 'UPCOMING',
	-- Set key --
	PRIMARY KEY (BookID)
);

-- Publisher table --
CREATE TABLE COMPANY.PUBLISHER (
	PublisherID INT AUTO_INCREMENT,
	PublisherName VARCHAR(100) NOT NULL,
	PublisherAddress VARCHAR(200) NOT NULL,
	ContactNumber VARCHAR(20) NOT NULL,
	-- Set key --
	PRIMARY KEY (PublisherID)
);

-- Author table --
CREATE TABLE COMPANY.AUTHOR (
	AuthorID INT AUTO_INCREMENT,
	AuthorName VARCHAR(100) NOT NULL,
	DoB DATE NOT NULL,
	AuthorBio TEXT,
	-- Up to 65,535 characters --
	-- Set key --
	PRIMARY KEY (AuthorID)
);

-- Genre table --
CREATE TABLE COMPANY.GENRE (
	GenreID INT AUTO_INCREMENT,
	GenreName VARCHAR(100) NOT NULL,
	-- Set key --
	PRIMARY KEY (GenreID)
);

-- Customer table --
CREATE TABLE COMPANY.CUSTOMER (
	CustomerID INT AUTO_INCREMENT,
	Username VARCHAR(50) NOT NULL UNIQUE,
	PasswordHash VARCHAR(255) NOT NULL,
	-- Store hashed password --
	CustomerName VARCHAR(100) NOT NULL,
	Email VARCHAR(100) NOT NULL UNIQUE,
	Gender ENUM('Male', 'Female', 'Other') NOT NULL,
	PhoneNumber VARCHAR(20),
	JoinDate DATE NOT NULL,
	-- Set key --
	PRIMARY KEY (CustomerID)
);

-- Order table --
CREATE TABLE COMPANY.ORDER (
	OrderID INT AUTO_INCREMENT,
	OrderDate DATE NOT NULL,
	ExpectedArrival DATE NOT NULL,
	Destination VARCHAR(255) NOT NULL,
	Status ENUM('Pending', 'Shipping', 'Delivered', 'Cancelled') NOT NULL,
	TotalAmount DECIMAL(10, 2) NOT NULL CHECK (TotalAmount >= 0),
	PaymentMethod ENUM(
		'Online Banking',
		'Cash',
		'Credit Card',
		'Debit Card'
	) NOT NULL,
	PaymentStatus ENUM('Paid', 'Unpaid', 'Refunded') NOT NULL,
	-- Set key --
	PRIMARY KEY (OrderID)
);

-- Discount table --
CREATE TABLE COMPANY.DISCOUNT (
	DiscountID INT AUTO_INCREMENT,
	StartDate DATE NOT NULL,
	EndDate DATE NOT NULL,
	Amount DECIMAL(10, 2) NOT NULL CHECK (Amount > 0),
	-- Set key --
	PRIMARY KEY (DiscountID)
);

-- Alter STAFF table to add foreign key constraint --
ALTER TABLE
	COMPANY.STAFF
ADD
	COLUMN StorageID INT;

ALTER TABLE
	COMPANY.STAFF
ADD
	CONSTRAINT FK_Staff_Storage FOREIGN KEY (StorageID) REFERENCES COMPANY.STORAGE(StorageID) ON DELETE
SET
	NULL ON UPDATE CASCADE;

-- Alter Storage table to add foreign key constraint --
ALTER TABLE
	COMPANY.STORAGE
ADD
	COLUMN ManagerID INT;

ALTER TABLE
	COMPANY.STORAGE
ADD
	CONSTRAINT FK_Storage_Manager FOREIGN KEY (ManagerID) REFERENCES COMPANY.MANAGER(EmployeeID) ON DELETE
SET
	NULL ON UPDATE CASCADE;

-- Alter Book table to add foreign key constraint --
ALTER TABLE
	COMPANY.BOOK
ADD
	COLUMN PublisherID INT,
ADD
	CONSTRAINT FK_PublisherID FOREIGN KEY (PublisherID) REFERENCES COMPANY.PUBLISHER(PublisherID) ON DELETE
SET
	NULL ON UPDATE CASCADE;

-- Create the relationship Book has Author --
CREATE TABLE COMPANY.BOOK_HAS_AUTHOR (
	BookID INT,
	AuthorID INT,
	PRIMARY KEY (BookID, AuthorID),
	FOREIGN KEY (BookID) REFERENCES COMPANY.BOOK(BookID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (AuthorID) REFERENCES COMPANY.AUTHOR(AuthorID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the relationship Book has Genre --
CREATE TABLE COMPANY.BOOK_HAS_GENRE (
	BookID INT,
	GenreID INT,
	PRIMARY KEY (BookID, GenreID),
	FOREIGN KEY (BookID) REFERENCES COMPANY.BOOK(BookID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (GenreID) REFERENCES COMPANY.GENRE(GenreID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the relationship Customer wish Book --
CREATE TABLE COMPANY.CUSTOMER_WISH_BOOK (
	CustomerID INT,
	BookID INT,
	PRIMARY KEY (CustomerID, BookID),
	FOREIGN KEY (CustomerID) REFERENCES COMPANY.CUSTOMER(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (BookID) REFERENCES COMPANY.BOOK(BookID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the relationship Storage has Book --
CREATE TABLE COMPANY.STORAGE_HAS_BOOK (
	StorageID INT,
	BookID INT,
	Quantity INT NOT NULL CHECK (Quantity >= 0),
	PRIMARY KEY (StorageID, BookID),
	FOREIGN KEY (StorageID) REFERENCES COMPANY.STORAGE(StorageID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (BookID) REFERENCES COMPANY.BOOK(BookID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create weak entity table Review --
CREATE TABLE COMPANY.REVIEW (
	ReviewID INT AUTO_INCREMENT,
	Rating INT NOT NULL CHECK (
		Rating BETWEEN 1
		AND 5
	),
	Review TEXT,
	CustomerID INT,
	BookID INT,
	PRIMARY KEY (ReviewID, CustomerID, BookID),
	-- Foreign key constraints --
	FOREIGN KEY (CustomerID) REFERENCES COMPANY.CUSTOMER(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (BookID) REFERENCES COMPANY.BOOK(BookID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create weak entity table Cart --
CREATE TABLE COMPANY.CART (
	CartID INT AUTO_INCREMENT,
	LastModified DATE,
	CustomerID INT,
	PRIMARY KEY (CartID, CustomerID),
	-- Foreign key constraints --
	FOREIGN KEY (CustomerID) REFERENCES COMPANY.CUSTOMER(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create weak entity table Activity log --
CREATE TABLE COMPANY.ACTIVITYLOG (
	LogID INT AUTO_INCREMENT,
	ActionType VARCHAR(50) NOT NULL,
	ActionDescription TEXT,
	Date DATE NOT NULL,
	Time TIME NOT NULL,
	CustomerID INT,
	PRIMARY KEY (LogID, CustomerID),
	FOREIGN KEY (CustomerID) REFERENCES COMPANY.CUSTOMER(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the relationship Order apply Discount --
CREATE TABLE COMPANY.ORDER_APPLY_DISCOUNT (
	OrderID INT,
	DiscountID INT,
	PRIMARY KEY (OrderID, DiscountID),
	FOREIGN KEY (OrderID) REFERENCES COMPANY.ORDER(OrderID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (DiscountID) REFERENCES COMPANY.DISCOUNT(DiscountID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create relationship Cart contain book --
CREATE TABLE COMPANY.CART_CONTAIN_BOOK(
	CartID INT,
	CustomerID INT,
	BookID INT,
	Quantity INT NOT NULL CHECK (Quantity >= 0),
	PRIMARY KEY(CartID, CustomerID, BookID),
	FOREIGN KEY (CartID) REFERENCES COMPANY.CART(CartID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (CustomerID) REFERENCES COMPANY.CART(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (BookID) REFERENCES COMPANY.BOOK(BookID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create relationship Customer has Coupon --
CREATE TABLE COMPANY.CUSTOMER_HAS_COUPON(
	CustomerID INT,
	DiscountID INT,
	NumOfCoupon INT NOT NULL CHECK (NumOfCoupon >= 0),
	PRIMARY KEY(CustomerID, DiscountID),
	FOREIGN KEY (CustomerID) REFERENCES COMPANY.Customer(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (DiscountID) REFERENCES COMPANY.Discount(DiscountID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Alter Order table to add foreign key constraint --
ALTER TABLE
	COMPANY.ORDER
ADD
	COLUMN CartID INT;

ALTER TABLE
	COMPANY.ORDER
ADD
	COLUMN CustomerID INT;

ALTER TABLE
	COMPANY.ORDER
ADD
	COLUMN EmployeeID INT;

ALTER TABLE
	COMPANY.ORDER
ADD
	CONSTRAINT FK_Order_Cart FOREIGN KEY (CartID) REFERENCES COMPANY.Cart(CartID) ON DELETE
SET
	NULL ON UPDATE CASCADE;

ALTER TABLE
	COMPANY.ORDER
ADD
	CONSTRAINT FK_Order_Cart_Customer FOREIGN KEY (CustomerID) REFERENCES COMPANY.Cart(CustomerID) ON DELETE
SET
	NULL ON UPDATE CASCADE;

ALTER TABLE
	COMPANY.ORDER
ADD
	CONSTRAINT FK_Order_Shipper FOREIGN KEY (EmployeeID) REFERENCES COMPANY.Shipper(EmployeeID) ON DELETE
SET
	NULL ON UPDATE CASCADE;

-- Create SCHEDULE table --
CREATE TABLE COMPANY.SCHEDULE (
	ScheduleID INT PRIMARY KEY AUTO_INCREMENT,
	StaffID INT NOT NULL,
	DayOfWeek ENUM(
		'Monday',
		'Tuesday',
		'Wednesday',
		'Thursday',
		'Friday',
		'Saturday',
		'Sunday'
	) NOT NULL,
	Shift ENUM('Shift 1', 'Shift 2', 'Shift 3') NOT NULL DEFAULT 'Shift 1',
	FOREIGN KEY (StaffID) REFERENCES COMPANY.STAFF(EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE
);

###################################### Indexing for the database ######################################
-- Index for Users table
CREATE INDEX idx_users_username ON COMPANY.Users(Username);

-- Index for Shipper table
CREATE INDEX idx_shipper_username ON COMPANY.SHIPPER(Username);

-- Index for Manager table
CREATE INDEX idx_manager_username ON COMPANY.MANAGER(Username);

-- Index for Staff table
CREATE INDEX idx_staff_username ON COMPANY.STAFF(Username);

-- Index for Book table
CREATE INDEX idx_book_title ON COMPANY.BOOK(Title);

CREATE INDEX idx_book_publisher ON COMPANY.BOOK(PublisherID);

-- Index for Publisher table
CREATE INDEX idx_publisher_contact ON COMPANY.PUBLISHER(ContactNumber);

-- Index for Author table
CREATE INDEX idx_author_name ON COMPANY.AUTHOR(AuthorName);

-- Index for Genre table
CREATE INDEX idx_genre_name ON COMPANY.GENRE(GenreName);

-- Index for Customer table
CREATE INDEX idx_customer_email ON COMPANY.CUSTOMER(Email);

CREATE INDEX idx_customer_username ON COMPANY.CUSTOMER(Username);

-- Index for Order table
CREATE INDEX idx_order_customer ON COMPANY.ORDER(CustomerID);

CREATE INDEX idx_order_shipper ON COMPANY.ORDER(EmployeeID);

CREATE INDEX idx_order_date ON COMPANY.ORDER(OrderDate);

-- Index for Discount table --
CREATE INDEX idx_discount_startdate ON COMPANY.DISCOUNT(StartDate);

-- Index for ActivityLog table --
CREATE INDEX idx_activity_log_date ON COMPANY.ACTIVITYLOG(Date);

-- Additional indexes for frequently visited columns
CREATE INDEX idx_book_publication_date ON COMPANY.BOOK(PublicationDate);

###################################### Some neccessary trigger ######################################
use company;

CREATE TRIGGER log_customer_password_change
AFTER
UPDATE
	ON COMPANY.CUSTOMER FOR EACH ROW BEGIN IF OLD.PasswordHash != NEW.PasswordHash THEN
INSERT INTO
	COMPANY.ACTIVITYLOG (
		ActionType,
		ActionDescription,
		Date,
		Time,
		CustomerID
	)
VALUES
	(
		'Password Change',
		'Customer changed their password',
		CURDATE(),
		CURTIME(),
		NEW.CustomerID
	);

END IF;

END;

CREATE TRIGGER log_customer_username_change
AFTER
UPDATE
	ON COMPANY.CUSTOMER FOR EACH ROW BEGIN IF OLD.Username != NEW.Username THEN
INSERT INTO
	COMPANY.ACTIVITYLOG (
		ActionType,
		ActionDescription,
		Date,
		Time,
		CustomerID
	)
VALUES
	(
		'Username Change',
		'Customer changed their username',
		CURDATE(),
		CURTIME(),
		NEW.CustomerID
	);

END IF;

END;

CREATE TRIGGER log_customer_email_change
AFTER
UPDATE
	ON COMPANY.CUSTOMER FOR EACH ROW BEGIN IF OLD.Email != NEW.Email THEN
INSERT INTO
	COMPANY.ACTIVITYLOG (
		ActionType,
		ActionDescription,
		Date,
		Time,
		CustomerID
	)
VALUES
	(
		'Email Change',
		CONCAT(
			'Customer changed their email from ',
			OLD.Email,
			' to ',
			NEW.Email
		),
		CURDATE(),
		CURTIME(),
		NEW.CustomerID
	);

END IF;

END;

CREATE TRIGGER log_customer_name_change
AFTER
UPDATE
	ON COMPANY.CUSTOMER FOR EACH ROW BEGIN IF OLD.CustomerName != NEW.CustomerName THEN
INSERT INTO
	COMPANY.ACTIVITYLOG (
		ActionType,
		ActionDescription,
		Date,
		Time,
		CustomerID
	)
VALUES
	(
		'Name Change',
		CONCAT(
			'Customer changed their name from ',
			OLD.CustomerName,
			' to ',
			NEW.CustomerName
		),
		CURDATE(),
		CURTIME(),
		NEW.CustomerID
	);

END IF;

END;

CREATE TRIGGER log_customer_phone_number_change
AFTER
UPDATE
	ON COMPANY.CUSTOMER FOR EACH ROW BEGIN IF OLD.PhoneNumber != NEW.PhoneNumber THEN
INSERT INTO
	COMPANY.ACTIVITYLOG (
		ActionType,
		ActionDescription,
		Date,
		Time,
		CustomerID
	)
VALUES
	(
		'Phone Number Change',
		CONCAT(
			'Customer changed their phone number from ',
			OLD.PhoneNumber,
			' to ',
			NEW.PhoneNumber
		),
		CURDATE(),
		CURTIME(),
		NEW.CustomerID
	);

END IF;

END;

###################################### Insert initial data ######################################
-- Insert test data for IT support account --
INSERT INTO
	COMPANY.Users (Username, PasswordHash, Role)
VALUES
	(
		'admin1',
		'5f4dcc3b5aa765d61d8327deb882cf99',
		'admin'
	),
	(
		'admin2',
		'5e884898da28047151d0e56f8dc62927',
		'admin'
	),
	(
		'itstaff1',
		'098f6bcd4621d373cade4e832627b4f6',
		'ITstaff'
	),
	(
		'itstaff2',
		'd41d8cd98f00b204e9800998ecf8427e',
		'ITstaff'
	),
	(
		'itstaff3',
		'e99a18c428cb38d5f260853678922e03',
		'ITstaff'
	),
	(
		'itstaff4',
		'81dc9bdb52d04dc20036dbd8313ed055',
		'ITstaff'
	),
	(
		'itstaff5',
		'96e79218965eb72c92a549dd5a330112',
		'ITstaff'
	),
	(
		'itstaff6',
		'c33367701511b4f6020ec61ded352059',
		'ITstaff'
	),
	(
		'itstaff7',
		'098f6bcd4621d373cade4e832627b4f6',
		'ITstaff'
	),
	(
		'itstaff8',
		'e10adc3949ba59abbe56e057f20f883e',
		'ITstaff'
	);

-- Insert data into MANAGER table
INSERT INTO
	COMPANY.MANAGER (
		ManagerName,
		Gender,
		DoB,
		IdentityCardNum,
		PhoneNumber,
		Address,
		StartDate,
		Salary,
		PerformanceScore,
		Username,
		PasswordHash
	)
VALUES
	(
		'Nguyen Van Nam',
		'Female',
		'1980-08-30',
		'ID789456',
		'1230984567',
		'123 Le Loi, phuong Ben Thanh, quan 1, thanh pho Ho Chi Minh',
		'2018-09-05',
		20000000.00,
		90,
		'nguyen.vannam',
		'hashed_password_1'
	),
	(
		'Tran Thi Lan',
		'Female',
		'1975-11-22',
		'ID987654',
		'5678901234',
		'100 Nguyen Trai, phuong 7, quan 5, thanh pho Ho Chi Minh',
		'2019-02-17',
		22000000.00,
		85,
		'tran.thilan',
		'hashed_password_2'
	),
	(
		'Le Quang Hung',
		'Female',
		'1978-03-15',
		'ID123789',
		'2345678901',
		'4 Hoang Dieu, phuong 12, quan 4, thanh pho Ho Chi Minh',
		'2020-05-10',
		21000000.00,
		88,
		'le.quanghung',
		'hashed_password_3'
	),
	(
		'Pham Minh Anh',
		'Male',
		'1983-12-01',
		'ID321654',
		'6789012345',
		'125 Nguyen Van Cu, phuong Nguyen Cu Trinh, quan 1, thanh pho Ho Chi Minh',
		'2017-08-12',
		28000000.00,
		92,
		'pham.minhanh',
		'hashed_password_4'
	),
	(
		'Hoang Thi Phuong',
		'Female',
		'1985-01-30',
		'ID159753',
		'9876543210',
		'21 Ly Thai To, phuong 9, quan 10, thanh pho Ho Chi Minh',
		'2016-03-22',
		26000000.00,
		89,
		'hoang.thiphuong',
		'hashed_password_5'
	),
	(
		'Vo Huu Tu',
		'Male',
		'1990-07-10',
		'ID963852',
		'5432167890',
		'50 Vo Van Kiet, phuong Co Giang, quan 1, thanh pho Ho Chi Minh',
		'2021-04-18',
		22000000.00,
		87,
		'vo.huutu',
		'hashed_password_6'
	),
	(
		'Do Thi Hanh',
		'Female',
		'1995-10-28',
		'ID852963',
		'3216549870',
		'32 Pham Ngu Lao, phuong Pham Ngu Lao, quan 1, thanh pho Ho Chi Minh',
		'2022-11-30',
		21000000.00,
		86,
		'do.thihanh',
		'hashed_password_7'
	),
	(
		'Bui Quoc Thao',
		'Male',
		'1982-09-14',
		'ID753951',
		'6549873210',
		'86 Hai Ba Trung, phuong 2, quan Binh Thanh, thanh pho Ho Chi Minh',
		'2019-12-19',
		24000000.00,
		90,
		'bui.quocthao',
		'hashed_password_8'
	),
	(
		'Phan Ngoc Minh',
		'Female',
		'1993-06-25',
		'ID147258',
		'4561237890',
		'45 Dien Bien Phu, phuong 15, quan Binh Thanh, thanh pho Ho Chi Minh',
		'2020-02-15',
		22000000.00,
		85,
		'phan.ngocminh',
		'hashed_password_9'
	),
	(
		'Dinh Thi Thu Huong',
		'Female',
		'1981-04-22',
		'ID258147',
		'7890123456',
		'245 Xo Viet Nghe Tinh, phuong 25, quan Binh Thanh, thanh pho Ho Chi Minh',
		'2021-07-21',
		42000000.00,
		84,
		'dinh.thithuhuong',
		'hashed_password_10'
	);

-- Insert data into STORAGE table
INSERT INTO
	COMPANY.STORAGE (StorageName, Address, Capacity, ManagerID)
VALUES
	(
		'Minh Huong Warehouse',
		'123 Tran Hung Dao, Phuong Co Giang, Quan 1, thanh pho Ho Chi Minh',
		100000,
		1
	),
	(
		'Quang Thinh Storage',
		'456 Le Hong Phong, Phuong 1, Quan 10, thanh pho Ho Chi Minh',
		75000,
		2
	),
	(
		'Lan Huong Depot',
		'789 Nguyen Thi Minh Khai, Phuong 6, Quan 3, thanh pho Ho Chi Minh',
		90000,
		3
	),
	(
		'Hong Phat Facility',
		'101 Nguyen Van Cu, Phuong 4, Quan 5, thanh pho Ho Chi Minh',
		85000,
		4
	),
	(
		'Thanh Binh Storage',
		'202 Ly Thai To, Phuong 9, Quan 10, thanh pho Ho Chi Minh',
		95000,
		5
	),
	(
		'Gia Bao Depot',
		'303 Hoang Dieu, Phuong 12, Quan 4, thanh pho Ho Chi Minh',
		110000,
		6
	),
	(
		'Vinh An Storage',
		'404 Cach Mang Thang Tam, Phuong 11, Quan 3, thanh pho Ho Chi Minh',
		80000,
		7
	),
	(
		'Kim Hoa Storage',
		'505 Pham Ngu Lao, Phuong Pham Ngu Lao, Quan 1, thanh pho Ho Chi Minh',
		65000,
		8
	),
	(
		'Phuoc Loc Storage',
		'606 Tran Van Dang, Phuong 11, Quan 3, thanh pho Ho Chi Minh',
		120000,
		9
	),
	(
		'Thanh Dat Storage',
		'707 Dien Bien Phu, Phuong 15, Quan Binh Thanh, thanh pho Ho Chi Minh',
		70000,
		10
	);

-- Insert data into SHIPPER table
INSERT INTO
	COMPANY.SHIPPER (
		ShipperName,
		Gender,
		DoB,
		IdentityCardNum,
		PhoneNumber,
		Address,
		StartDate,
		Salary,
		VehicleNumber,
		VehicleType,
		Username,
		PasswordHash
	)
VALUES
	(
		'Nguyen Minh Tu',
		'Male',
		'1985-06-15',
		'ID123456',
		'1234567890',
		'123 Tran Hung Dao, Quan 1, thanh pho Ho Chi Minh',
		'2020-01-01',
		11000000.00,
		'ABC-85-DE',
		'Truck',
		'nguyen.minhtu',
		'hashed_password_1'
	),
	(
		'Tran Thi Hoa',
		'Female',
		'1990-04-20',
		'ID654321',
		'0987654321',
		'456 Le Lai, Quan 1, thanh pho Ho Chi Minh',
		'2021-03-12',
		19000000.00,
		'GEF-49-HJ',
		'Van',
		'tran.thihoa',
		'hashed_password_2'
	),
	(
		'Le Van Hieu',
		'Male',
		'1987-02-05',
		'ID789123',
		'5678901230',
		'789 Nguyen Thi Minh Khai, Quan 3, thanh pho Ho Chi Minh',
		'2021-06-15',
		17000000.00,
		'JHI-11-KL',
		'Truck',
		'le.vanhieu',
		'hashed_password_3'
	),
	(
		'Hoang Thi Lan',
		'Female',
		'1992-11-12',
		'ID321456',
		'6789012345',
		'321 Le Hong Phong, Quan 10, thanh pho Ho Chi Minh',
		'2022-02-20',
		12000000.00,
		'LMN-23-OP',
		'Van',
		'hoang.thilan',
		'hashed_password_4'
	),
	(
		'Pham Van Thanh',
		'Male',
		'1980-07-28',
		'ID159753',
		'2345678901',
		'654 Cach Mang Thang Tam, Quan 3, thanh pho Ho Chi Minh',
		'2020-09-05',
		19000000.00,
		'QRS-67-TU',
		'Truck',
		'pham.vanthanh',
		'hashed_password_5'
	),
	(
		'Nguyen Thi Mai',
		'Female',
		'1988-05-16',
		'ID654789',
		'0987654321',
		'852 Dien Bien Phu, Quan Binh Thanh, thanh pho Ho Chi Minh',
		'2021-07-12',
		12000000.00,
		'VWX-89-YZ',
		'Van',
		'nguyen.thimai',
		'hashed_password_6'
	),
	(
		'Vu Hoang Khoa',
		'Male',
		'1995-03-11',
		'ID147852',
		'1234567890',
		'963 Hoang Sa, Quan 1, thanh pho Ho Chi Minh',
		'2023-01-30',
		18000000.00,
		'ABC-99-XY',
		'Truck',
		'vu.hoangkhoa',
		'hashed_password_7'
	),
	(
		'Cao Thi Kim',
		'Female',
		'1994-09-23',
		'ID258963',
		'3216549870',
		'159 Tran Van Dang, Quan 3, thanh pho Ho Chi Minh',
		'2023-08-25',
		14000000.00,
		'DEF-88-VW',
		'Van',
		'cao.thikim',
		'hashed_password_8'
	),
	(
		'Phan Van An',
		'Male',
		'1986-10-10',
		'ID369258',
		'6549873210',
		'951 Nguyen Van Cu, Quan 5, thanh pho Ho Chi Minh',
		'2022-11-18',
		13000000.00,
		'GHI-77-QR',
		'Truck',
		'phan.vanan',
		'hashed_password_9'
	),
	(
		'Doan Thi Nhung',
		'Female',
		'1991-12-05',
		'ID258147',
		'7890123456',
		'159 Le Lai, Quan 1, thanh pho Ho Chi Minh',
		'2023-02-14',
		12000000.00,
		'JKL-56-ZX',
		'Van',
		'doan.thinhung',
		'hashed_password_10'
	);

-- Insert data into STAFF table
INSERT INTO
	COMPANY.STAFF (
		StaffName,
		Gender,
		DoB,
		IdentityCardNum,
		PhoneNumber,
		Address,
		StartDate,
		Salary,
		StorageID,
		Username,
		PasswordHash
	)
VALUES
	(
		'Nguyen Van An',
		'Male',
		'1992-07-14',
		'ID112233',
		'1122334455',
		'234 Pho Le Loi, TP.HCM',
		'2021-06-20',
		12800000.00,
		1,
		'nguyen.vanan',
		'hashed_password_1'
	),
	(
		'Le Thi Nhi',
		'Female',
		'1988-05-25',
		'ID334455',
		'9988776655',
		'987 Pho Nguyen Hue, TP.HCM',
		'2019-11-09',
		12900000.00,
		2,
		'le.thinhi',
		'hashed_password_2'
	),
	(
		'Le Thi Hoa',
		'Female',
		'1993-02-03',
		'ID123321',
		'1123456789',
		'111 Pho Nguyen Trai, TP.HCM',
		'2020-04-15',
		13000000.00,
		3,
		'le.thihoa',
		'hashed_password_3'
	),
	(
		'Pham Minh Dung',
		'Male',
		'1985-09-10',
		'ID987123',
		'2345678901',
		'222 Pho Dong Khoi, TP.HCM',
		'2019-05-25',
		13100000.00,
		4,
		'pham.minhdung',
		'hashed_password_4'
	),
	(
		'Nguyen Thi Mai',
		'Female',
		'1990-11-20',
		'ID456789',
		'3456789012',
		'333 Pho Nguyen Thi Minh Khai, TP.HCM',
		'2021-01-10',
		13200000.00,
		5,
		'nguyen.thimai',
		'hashed_password_5'
	),
	(
		'Vu Hoang Khoa',
		'Male',
		'1995-07-28',
		'ID654321',
		'4567890123',
		'444 Pho Le Van Sy, TP.HCM',
		'2021-03-17',
		23300000.00,
		6,
		'vu.hoangkhoa',
		'hashed_password_6'
	),
	(
		'Cao Thi Hoa',
		'Female',
		'1988-01-21',
		'ID321654',
		'5678901234',
		'555 Pho Tran Hung Dao, TP.HCM',
		'2018-09-05',
		13400000.00,
		7,
		'cao.thihoa',
		'hashed_password_7'
	),
	(
		'Nguyen Van Hoang',
		'Male',
		'1975-04-16',
		'ID789456',
		'6789012345',
		'666 Pho Phan Xich Long, TP.HCM',
		'2022-02-20',
		13500000.00,
		8,
		'nguyen.vanhoang',
		'hashed_password_8'
	),
	(
		'Tran Thi Yen',
		'Female',
		'1994-06-17',
		'ID963852',
		'7890123456',
		'777 Pho Ben Thanh, TP.HCM',
		'2020-12-12',
		13600000.00,
		9,
		'tran.thiyen',
		'hashed_password_9'
	),
	(
		'Le Van Minh',
		'Male',
		'1996-07-03',
		'ID753951',
		'8901234567',
		'888 Pho Vo Thi Sau, TP.HCM',
		'2022-08-29',
		13700000.00,
		10,
		'le.vanminh',
		'hashed_password_10'
	);

-- Insert data into PUBLISHER table
INSERT INTO
	COMPANY.PUBLISHER (PublisherName, PublisherAddress, ContactNumber)
VALUES
	(
		'Pearson Education',
		'123 Academic Way',
		'5551234567'
	),
	('O’Reilly Media', '789 Tech Blvd', '5559876543'),
	(
		'Springer Nature',
		'456 Science St',
		'5556543210'
	),
	('Wiley', '321 Knowledge Ave', '5553216549'),
	(
		'Cambridge University Press',
		'222 Research Blvd',
		'5551237890'
	),
	('McGraw Hill', '333 Education Way', '5559871234'),
	('Elsevier', '444 Academic Rd', '5557894561'),
	('Routledge', '555 Insight St', '5554567891'),
	('MIT Press', '666 Innovation Ln', '5556547891'),
	(
		'Harvard University Press',
		'777 Book St',
		'5557896543'
	);

-- Insert data into BOOK table
INSERT INTO
	COMPANY.BOOK (
		Title,
		Price,
		CoverImage,
		Quantity,
		PublicationDate,
		BriefDescription,
		Status,
		PublisherID
	)
VALUES
	(
		'Learning SQL',
		450000.00,
		'sql.jpg',
		100,
		'2020-01-10',
		'A comprehensive guide to SQL.',
		'In Stock',
		1
	),
	(
		'Mastering Python',
		500000.00,
		'python.jpg',
		50,
		'2021-02-15',
		'Advanced techniques in Python programming.',
		'In Stock',
		2
	),
	(
		'Data Science Handbook',
		600000.00,
		'data_science.jpg',
		75,
		'2022-05-20',
		'Essential guide for data scientists.',
		'In Stock',
		3
	),
	(
		'Introduction to Machine Learning',
		550000.00,
		'machine_learning.jpg',
		40,
		'2021-10-30',
		'Beginner’s guide to machine learning.',
		'In Stock',
		4
	),
	(
		'Java Programming',
		500000.00,
		'java.jpg',
		80,
		'2023-01-05',
		'Comprehensive guide to Java.',
		'In Stock',
		5
	),
	(
		'Web Development with HTML & CSS',
		300000.00,
		'html_css.jpg',
		120,
		'2022-06-15',
		'Learn to build websites with HTML and CSS.',
		'In Stock',
		6
	),
	(
		'Digital Marketing Essentials',
		400000.00,
		'digital_marketing.jpg',
		60,
		'2021-08-22',
		'Fundamentals of digital marketing.',
		'In Stock',
		7
	),
	(
		'Cloud Computing Basics',
		500500.00,
		'cloud_computing.jpg',
		30,
		'2020-11-10',
		'Introduction to cloud technologies.',
		'In Stock',
		8
	),
	(
		'Cybersecurity Fundamentals',
		650000.00,
		'cybersecurity.jpg',
		50,
		'2022-09-05',
		'Protecting systems and data.',
		'In Stock',
		9
	),
	(
		'The Art of Programming',
		700000.00,
		'programming.jpg',
		25,
		'2023-03-18',
		'A deep dive into programming techniques.',
		'In Stock',
		10
	);

-- Insert data into AUTHOR table
INSERT INTO
	COMPANY.AUTHOR (AuthorName, DoB, AuthorBio)
VALUES
	(
		'John Doe',
		'1970-09-12',
		'Expert in databases and SQL.'
	),
	(
		'Jane Roe',
		'1982-03-30',
		'Python developer and educator.'
	),
	(
		'Alice Smith',
		'1985-01-15',
		'Data scientist and researcher.'
	),
	(
		'Bob Johnson',
		'1990-04-25',
		'Machine learning enthusiast.'
	),
	(
		'Cathy Brown',
		'1978-07-10',
		'Web developer and author.'
	),
	(
		'Daniel White',
		'1992-11-05',
		'Digital marketing expert.'
	),
	(
		'Eva Green',
		'1983-06-18',
		'Cloud computing specialist.'
	),
	(
		'Frank Blue',
		'1981-12-22',
		'Cybersecurity consultant.'
	),
	(
		'Grace Red',
		'1995-09-09',
		'Programming and software engineering advocate.'
	),
	(
		'Henry Gold',
		'1990-02-20',
		'Entrepreneur and tech writer.'
	);

-- Insert data into BOOK_HAS_AUTHOR table
INSERT INTO
	COMPANY.BOOK_HAS_AUTHOR (BookID, AuthorID)
VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 2),
	(7, 6),
	(8, 7),
	(9, 8),
	(10, 9);

-- Insert data into GENRE table
INSERT INTO
	COMPANY.GENRE (GenreName)
VALUES
	('Technology'),
	('Education'),
	('Science'),
	('Fiction'),
	('Non-Fiction'),
	('Biography'),
	('History'),
	('Business'),
	('Art'),
	('Mathematics');

-- Insert data into BOOK_HAS_GENRE table
INSERT INTO
	COMPANY.BOOK_HAS_GENRE (BookID, GenreID)
VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 2),
	(7, 2),
	(8, 2),
	(9, 3),
	(10, 4);

-- Insert data into CUSTOMER table
INSERT INTO
	COMPANY.CUSTOMER (
		Username,
		PasswordHash,
		CustomerName,
		Email,
		Gender,
		PhoneNumber,
		JoinDate
	)
VALUES
	(
		'Nguyen_Van_An',
		'password123',
		'Nguyen Van An',
		'nguyenvanan@gmail.com',
		'Male',
		'5559871234',
		'2022-05-15'
	),
	(
		'Le_Thi_Kim',
		'securepass456',
		'Le Thi Kim',
		'lethikim@gmail.com',
		'Female',
		'5551239876',
		'2022-07-20'
	),
	(
		'Pham_Thi_Mai',
		'mypassword789',
		'Pham Thi Mai',
		'phamthimai@gmail.com',
		'Female',
		'5554561234',
		'2022-09-01'
	),
	(
		'Hoang_Minh_Cuong',
		'brownpassword456',
		'Hoang Minh Cuong',
		'hoangminhcuong@gmail.com',
		'Male',
		'5557896543',
		'2022-10-12'
	),
	(
		'Vu_Hoang_Duy',
		'whitepass123',
		'Vu Hoang Duy',
		'vuhoangduy@gmail.com',
		'Male',
		'5551472583',
		'2022-11-05'
	),
	(
		'Nguyen_Thi_Trang',
		'blackpass321',
		'Nguyen Thi Trang',
		'nguyenthi_trang@gmail.com',
		'Female',
		'5552589630',
		'2022-12-15'
	),
	(
		'Cao_Thi_Lan',
		'greenpass234',
		'Cao Thi Lan',
		'caothilan@gmail.com',
		'Female',
		'5553697412',
		'2023-01-30'
	),
	(
		'Nguyen_Van_Hai',
		'redpass987',
		'Nguyen Van Hai',
		'nguyenvanhai@gmail.com',
		'Male',
		'5558529630',
		'2023-03-10'
	),
	(
		'Tran_Thi_Lan',
		'bluepass456',
		'Tran Thi Lan',
		'tranthilan@gmail.com',
		'Female',
		'5557891234',
		'2023-04-15'
	),
	(
		'Le_Van_Khoa',
		'silverpass789',
		'Le Van Khoa',
		'levankhoa@gmail.com',
		'Male',
		'5559517530',
		'2023-05-20'
	);

-- Insert data into DISCOUNT table
INSERT INTO
	COMPANY.DISCOUNT (StartDate, EndDate, Amount)
VALUES
	('2024-10-01', '2024-10-31', 100000.00),
	('2024-11-01', '2024-11-30', 150000.00),
	('2024-12-01', '2024-12-31', 200000.00),
	('2025-01-01', '2025-01-31', 50000.00),
	('2025-02-01', '2025-02-28', 250000.00),
	('2025-03-01', '2025-03-31', 1000000.00),
	('2025-04-01', '2025-04-30', 300000.00),
	('2025-05-01', '2025-05-31', 150000.00),
	('2025-06-01', '2025-06-30', 200000.00),
	('2025-07-01', '2025-07-31', 100000.00);

-- Insert data into CUSTOMER_WISH_BOOK table
INSERT INTO
	COMPANY.CUSTOMER_WISH_BOOK (CustomerID, BookID)
VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(7, 7),
	(8, 8),
	(9, 9),
	(10, 10);

-- Insert data into STORAGE_HAS_BOOK table
INSERT INTO
	COMPANY.STORAGE_HAS_BOOK (StorageID, BookID, Quantity)
VALUES
	(1, 1, 50),
	(2, 2, 25),
	(3, 3, 30),
	(4, 4, 20),
	(5, 5, 10),
	(6, 6, 15),
	(7, 7, 5),
	(8, 8, 35),
	(9, 9, 60),
	(10, 10, 40);

-- Insert data into REVIEW table
INSERT INTO
	COMPANY.REVIEW (Rating, Review, CustomerID, BookID)
VALUES
	(5, 'Sach hay, nen doc', 1, 1),
	(4, 'Sach rat hay va bo ich', 2, 2),
	(5, 'Chua nhieu thong tin bo ich', 3, 3),
	(4, 'Interesting read.', 4, 4),
	(3, 'Khong te', 5, 5),
	(5, 'A must-have for programmers.', 6, 6),
	(4, 'Learned a lot!', 7, 7),
	(5, 'Excellent material.', 8, 8),
	(4, 'Solid guide for beginners.', 9, 9),
	(5, 'Xinh dep tuyet voi', 10, 10);

-- Insert data into CART table
INSERT INTO
	COMPANY.CART (LastModified, CustomerID)
VALUES
	('2024-10-20', 1),
	('2024-10-21', 2),
	('2024-10-22', 3),
	('2024-10-23', 4),
	('2024-10-24', 5),
	('2024-10-25', 6),
	('2024-10-26', 7),
	('2024-10-27', 8),
	('2024-10-28', 9),
	('2024-10-29', 10);

-- Insert data into CART_CONTAIN_BOOK table
INSERT INTO
	COMPANY.CART_CONTAIN_BOOK (CartID, CustomerID, BookID, Quantity)
VALUES
	(1, 1, 1, 2),
	(2, 2, 2, 1),
	(3, 3, 3, 3),
	(4, 4, 4, 1),
	(5, 5, 5, 1),
	(6, 6, 6, 2),
	(7, 7, 7, 1),
	(8, 8, 8, 2),
	(9, 9, 9, 1),
	(10, 10, 10, 1);

-- Insert data into ORDER table
INSERT INTO
	COMPANY.ORDER (
		OrderDate,
		ExpectedArrival,
		Destination,
		Status,
		TotalAmount,
		PaymentMethod,
		PaymentStatus,
		CartID,
		CustomerID,
		EmployeeID
	)
VALUES
	(
		'2024-09-15',
		'2024-09-20',
		'123 Pho Le Loi, quan 1, thanh pho Ho Chi Minh',
		'Delivered',
		950000.00,
		'Online Banking',
		'Paid',
		1,
		1,
		1
	),
	(
		'2024-09-18',
		'2024-09-25',
		'456 Pho Nguyen Hue, quan 2, thanh pho Ho Chi Minh',
		'Shipping',
		450000.00,
		'Cash',
		'Unpaid',
		2,
		2,
		2
	),
	(
		'2024-09-20',
		'2024-09-27',
		'789 Pho Tran Hung Dao, quan 3, thanh pho Ho Chi Minh',
		'Delivered',
		600000.00,
		'Credit Card',
		'Paid',
		3,
		3,
		1
	),
	(
		'2024-09-22',
		'2024-09-30',
		'321 Pho Nguyen Trai, quan 4, thanh pho Ho Chi Minh',
		'Pending',
		550000.00,
		'Debit Card',
		'Unpaid',
		4,
		4,
		2
	),
	(
		'2024-09-25',
		'2024-10-02',
		'654 Pho Dong Khoi, quan 5, thanh pho Ho Chi Minh',
		'Delivered',
		500000.00,
		'Debit Card',
		'Paid',
		5,
		5,
		1
	),
	(
		'2024-09-28',
		'2024-10-05',
		'987 Pho Vo Thi Sau, quan 6, thanh pho Ho Chi Minh',
		'Cancelled',
		700000.00,
		'Cash',
		'Unpaid',
		6,
		6,
		2
	),
	(
		'2024-09-30',
		'2024-10-07',
		'246 Pho Ben Thanh, quan 9, thanh pho Ho Chi Minh',
		'Delivered',
		800000.00,
		'Credit Card',
		'Paid',
		7,
		7,
		1
	),
	(
		'2024-10-02',
		'2024-10-09',
		'135 Pho Phan Xich Long, quan 10, thanh pho Ho Chi Minh',
		'Shipping',
		900000.00,
		'Online Banking',
		'Unpaid',
		8,
		8,
		2
	),
	(
		'2024-10-05',
		'2024-10-12',
		'579 Pho Le Van Sy, quan 3, thanh pho Ho Chi Minh',
		'Delivered',
		1000000.00,
		'Debit Card',
		'Paid',
		9,
		9,
		1
	),
	(
		'2024-10-10',
		'2024-10-17',
		'753 Pho Nguyen Thi Minh Khai, quan 2, thanh pho Ho Chi Minh',
		'Shipping',
		1100000.00,
		'Cash',
		'Unpaid',
		10,
		10,
		2
	);

-- Insert data into CUSTOMER_HAS_COUPON table
INSERT INTO
	COMPANY.CUSTOMER_HAS_COUPON (customerID, discountID, NumOfCoupon)
VALUES
	(1, 1, 1),
	(1, 2, 2),
	(2, 3, 5),
	(3, 4, 6),
	(1, 10, 2),
	(4, 5, 2);

-- Insert data into ORDER_APPLY_DISCOUNT table
INSERT INTO
	COMPANY.ORDER_APPLY_DISCOUNT (OrderId, DiscountID)
VALUES
	(1, 1),
	(1, 2),
	(2, 3),
	(3, 4),
	(4, 5);

-- Insert data into ACTIVITYLOG table --
INSERT INTO
	COMPANY.ACTIVITYLOG (
		ActionType,
		ActionDescription,
		Date,
		Time,
		CustomerID
	)
VALUES
	(
		'PURCHASE',
		'Customer purchased 1 item.',
		'2024-10-04',
		'15:45:00',
		6
	),
	(
		'CHANGE_PASSWORD',
		'Customer changed their password.',
		'2024-10-06',
		'12:00:00',
		10
	),
	(
		'CHANGE_PROFILE',
		'Customer updated their profile information.',
		'2024-10-01',
		'09:15:00',
		2
	),
	(
		'REVIEW',
		'Customer left a review for product Y.',
		'2024-10-05',
		'10:30:00',
		8
	),
	(
		'CHANGE_PASSWORD',
		'Customer changed their password.',
		'2024-10-01',
		'08:30:00',
		1
	),
	(
		'PURCHASE',
		'Customer purchased 3 items.',
		'2024-10-02',
		'10:00:00',
		3
	),
	(
		'CHANGE_PROFILE',
		'Customer updated their profile picture.',
		'2024-10-05',
		'09:00:00',
		7
	),
	(
		'REVIEW',
		'Customer left a review for product X.',
		'2024-10-02',
		'11:00:00',
		4
	),
	(
		'PURCHASE',
		'Customer purchased 2 items.',
		'2024-10-06',
		'11:15:00',
		9
	),
	(
		'CHANGE_PASSWORD',
		'Customer changed their password.',
		'2024-10-03',
		'14:30:00',
		5
	);

-- Insert data into SCHEDULE table --
INSERT INTO
	COMPANY.SCHEDULE (StaffID, DayOfWeek, Shift)
VALUES
	(1, 'Monday', 'Shift 1'),
	(1, 'Tuesday', 'Shift 2'),
	(1, 'Wednesday', 'Shift 3'),
	(2, 'Monday', 'Shift 2'),
	(2, 'Thursday', 'Shift 1'),
	(2, 'Friday', 'Shift 3'),
	(3, 'Wednesday', 'Shift 1'),
	(3, 'Thursday', 'Shift 2'),
	(3, 'Saturday', 'Shift 3'),
	(4, 'Tuesday', 'Shift 1'),
	(4, 'Friday', 'Shift 2'),
	(5, 'Monday', 'Shift 3'),
	(5, 'Thursday', 'Shift 2'),
	(6, 'Wednesday', 'Shift 2'),
	(6, 'Friday', 'Shift 1'),
	(7, 'Tuesday', 'Shift 1'),
	(7, 'Thursday', 'Shift 3'),
	(8, 'Monday', 'Shift 2'),
	(8, 'Wednesday', 'Shift 3'),
	(9, 'Tuesday', 'Shift 2'),
	(9, 'Friday', 'Shift 1'),
	(10, 'Monday', 'Shift 1'),
	(10, 'Thursday', 'Shift 3');