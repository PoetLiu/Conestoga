use AdvDatabase;
DROP TABLE IF EXISTS Customer;

create table Customer
(Customer_ID int, 
First_Name varchar(30), 
Last_Name varchar(30), 
Email varchar(30), 
Address varchar(30), 
City varchar(30), 
State varchar (30), 
Zip int);

insert into Customer
values 
(1, 'Ken','Sánchez', 'ken0@adventure-works.com', '5725 Glaze Drive', 'San Francisco','California',94109),
(2, 'Terri', 'Duffy', 'terri0@adventure-works.com', '25 N State St', 'Chicago','Illinois',60610),
(3, 'Roberto', 'Tamburello', 'roberto0@adventure-works.com','6657 Sand Pointe Lane','Seattle','Washington', 98104),
(4, 'Rob', 'Walters', 'rob0@adventure-works.com', 'Stevens Creek Shopping Center','Dallas','Texas',75201 ),
(5, 'Gail', 'Erickson', 'gail0@adventure-works.com', '9178 Jumping St.','San Jose','California',95112),
(6, 'Steve', 'Smith','steve@adventure-works.com', '25 N Lane', 'San Francisco','California',94109),
(7, 'Brian', 'Wells','brian@adventure-works.com', '21 E Lane', 'San Jose','Illinois',95112),
(8, 'Sam' , 'Jordan','sam@adventure-works.com','2 N Lane','Seattle', 'Washington',98104);

