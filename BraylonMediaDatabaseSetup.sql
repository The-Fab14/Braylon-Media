DROP DATABASE IF EXISTS braylon_media_crm;

CREATE DATABASE braylon_media_crm;

USE braylon_media_crm;

CREATE TABLE state (
    state_id VARCHAR(2) PRIMARY KEY,
    state_name VARCHAR(256) NOT NULL
);

CREATE TABLE crm_user (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL,
    user_password VARCHAR(256) NOT NULL,
    did_password_change TINYINT(1) NOT NULL,
    user_role VARCHAR(256) NOT NULL,
    email_address VARCHAR(256) NOT NULL,
    state_id VARCHAR(2) NOT NULL,
    CONSTRAINT fk_crm_user_state 
		FOREIGN KEY (state_id)
		REFERENCES state (state_id)
);

CREATE TABLE crm_client (
	client_id INT PRIMARY KEY AUTO_INCREMENT,
    contact_first_name VARCHAR(256) NOT NULL,
    contact_last_name VARCHAR(256) NOT NULL,
    company_name VARCHAR(256) NOT NULL,
    street_address VARCHAR(256) NOT NULL,
    apt_unit VARCHAR(256) NOT NULL,
    city VARCHAR(256) NOT NULL,
    zip VARCHAR(5) NOT NULL,
    email_address VARCHAR(256) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    state_id VARCHAR(2) NOT NULL,
    CONSTRAINT fk_crm_client_state 
		FOREIGN KEY (state_id)
		REFERENCES state (state_id),
	user_id INT NOT NULL,
    CONSTRAINT fk_crm_client_crm_user
		FOREIGN KEY (user_id)
		REFERENCES crm_user (user_id)
);

CREATE TABLE visit (
	visit_id INT PRIMARY KEY AUTO_INCREMENT,
    date_visited DATE NOT NULL,
    visit_notes VARCHAR(5000) NULL,
    user_id INT NOT NULL,
    CONSTRAINT fk_visit_crm_user
		FOREIGN KEY (user_id)
		REFERENCES crm_user (user_id),
	client_id INT NOT NULL,
    CONSTRAINT fk_visit_crm_client
		FOREIGN KEY (client_id)
		REFERENCES crm_client (client_id)
);

CREATE TABLE crm_order (
	order_id INT PRIMARY KEY AUTO_INCREMENT,
    date_submitted DATE NOT NULL,
    date_installed DATE NOT NULL,
    date_completed DATE NOT NULL,
    order_total DECIMAL(12,2) NOT NULL,
    order_status VARCHAR(256) NOT NULL,
    order_comments VARCHAR(5000) NULL,
    client_id INT NOT NULL,
    CONSTRAINT fk_crm_order_crm_client
		FOREIGN KEY (client_id)
		REFERENCES crm_client (client_id)
);

CREATE TABLE product (
	product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(256) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE crm_order_product (
	order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    PRIMARY KEY pk_crm_order_product (order_id, product_id),
    CONSTRAINT fk_crm_order_product_crm_order 
		FOREIGN KEY (order_id)
		REFERENCES crm_order (order_id),
	CONSTRAINT fk_crm_order_product_product
		FOREIGN KEY (product_id)
		REFERENCES product (product_id)
);

INSERT INTO state(state_id, state_name) 
	VALUES
    ('AK', 'Alaska'),
	('AL', 'Alabama'),
	('AZ', 'Arizona'),
	('AR', 'Arkansas'),
	('CA', 'California'),
	('CO', 'Colorado'),
	('CT', 'Connecticut'),
	('DE', 'Delaware'),
	('DC', 'District of Columbia'),
	('FL', 'Florida'),
	('GA', 'Georgia'),
	('HI', 'Hawaii'),
	('ID', 'Idaho'),
	('IL', 'Illinois'),
	('IN', 'Indiana'),
	('IA', 'Iowa'),
	('KS', 'Kansas'),
	('KY', 'Kentucky'),
	('LA', 'Louisiana'),
	('ME', 'Maine'),
	('MD', 'Maryland'),
	('MA', 'Massachusetts'),
	('MI', 'Michigan'),
	('MN', 'Minnesota'),
	('MS', 'Mississippi'),
	('MO', 'Missouri'),
	('MT', 'Montana'),
	('NE', 'Nebraska'),
	('NV', 'Nevada'),
	('NH', 'New Hampshire'),
	('NJ', 'New Jersey'),
	('NM', 'New Mexico'),
	('NY', 'New York'),
	('NC', 'North Carolina'),
	('ND', 'North Dakota'),
	('OH', 'Ohio'),
	('OK', 'Oklahoma'),
	('OR', 'Oregon'),
	('PA', 'Pennsylvania'),
	('PR', 'Puerto Rico'),
	('RI', 'Rhode Island'),
	('SC', 'South Carolina'),
	('SD', 'South Dakota'),
	('TN', 'Tennessee'),
	('TX', 'Texas'),
	('UT', 'Utah'),
	('VT', 'Vermont'),
	('VA', 'Virginia'),
	('WA', 'Washington'),
	('WV', 'West Virginia'),
	('WI', 'Wisconsin'),
	('WY', 'Wyoming');

INSERT INTO crm_user (user_id, first_name, last_name, user_password, did_password_change, user_role, email_address, state_id) VALUES
	(1, 'George', 'Costanza', '$2a$10$yOO7PnSu4D.KsoSkXIndHOXCuG3ydIp3CpG5NModp9APR2O7IUFV2', 0, 'ROLE_USER', 'gcostanza@braylonm.com', 'NY'),
	(2, 'Elaine', 'Benes', '$2a$10$yOO7PnSu4D.KsoSkXIndHOXCuG3ydIp3CpG5NModp9APR2O7IUFV2', 0, 'ROLE_USER', 'ebenes@braylonm.com', 'NY'),
	(3, 'Cosmo', 'Kramer', '$2a$10$yOO7PnSu4D.KsoSkXIndHOXCuG3ydIp3CpG5NModp9APR2O7IUFV2', 0, 'ROLE_USER', 'ckramer@braylonm.com', 'NY'),
	(4, 'Frank', 'Costanza', '$2a$10$yOO7PnSu4D.KsoSkXIndHOXCuG3ydIp3CpG5NModp9APR2O7IUFV2', 0, 'ROLE_ADMIN', 'fcostanza@braylonm.com', 'NJ'),
	(5, 'Jay', 'Peterman', '$2a$10$yOO7PnSu4D.KsoSkXIndHOXCuG3ydIp3CpG5NModp9APR2O7IUFV2', 0,  'ROLE_ADMIN', 'jpeterman@braylon.com', 'NV');
    
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES 
('John', 'Martin','Ted Talks', '285 Young Road', 'Apt #301', 'Stone Mountain', '30385','jmartin@gmail.com','6783611390','GA',1),
('Doug', 'Smith','Some Company', '285 Young Road', 'Apt #301', 'Stone Mountain', '30385','jmartin@gmail.com','6783611390','GA',1),
('Jake', 'Porter','Ted Talks', '7816 Monogramm Lane', 'Apt #2200','Mint Hill','28227','cross_sounds@gmail.com','7045521694','NC',1),
('John', 'Smith','Ted Talks', '7816 Monogramm Lane', 'Apt #2200','Mint Hill','28227','cross_sounds@gmail.com','7045521694','NC',2),
('Alex', 'Cross','Live Sound Prodution', '7816 Monogramm Lane', 'Apt #2200','Mint Hill','28227','cross_sounds@gmail.com','7045521694','NC',2),
('Casey', 'Jones','Stage Audio', '3000 College Drive', 'Apt #305', 'Savannah', '30385','jstagestudios01@yahoo.com','2290052387','GA', 3),
('Meghan', 'Robinson','Sound Peristance', '25001 Venice Dr.', 'Apt #8759', 'Los Angeles','93736','s.persistance@microsoft.com','8063667834','LA',4),
('Joey', 'Martin','Creative Sound', '1026 Jay Street', 'Apt# 264', 'Charlotte','28208','creativesound@gmail.com','7048881497','NC', 5),
('Meghan', 'Markle','Royal British Studios', '9076 King St', 'Apt# 254', 'London','29837','royalty@royal.uk','8097261743','NC', 5),
('Tom', 'Hanks','Warner Bros', '2837 Film Blvd', 'Apt# 25', 'Los Angeles','89283','iamtomhanks@gmail.com','2738279384','CA', 4),
('Chris', 'Pratt','Guardians of the Galaxy', '9283 Marvel Way', 'Apt# 975', 'Orlando','87234','starlord@yahoo.com','7289280143','FL', 3),
('Tom', 'Holland','Marvel Studios', '2983 Webslinger Street', 'Apt# 273', 'New York City','78374','iamspiderman@gmail.com','9098273721','NY', 3),
('Leslie', 'Knope','Parks and Recreation Office', '982 Waffles Road', 'Apt# 254', 'Pawnee','28208','leslieknope@gmail.com','8728172837','IN', 2);

INSERT INTO visit(date_visited, user_id, client_id)
	VALUES
    ('2020-01-13', '1', '1'),
	('2020-01-14', '2', '2'),
	('2020-01-15', '3', '3'),
	('2020-01-16', '4', '4'),
	('2020-01-17', '5', '5'),
    ('2020-01-18', '5', '6'),
    ('2020-01-19', '5', '7'),
    ('2020-01-20', '4', '8'),
    ('2020-01-21', '1', '9'),
    ('2020-01-22', '2', '10'),
    ('2020-01-23', '3', '11'),
    ('2020-01-24', '3', '12'),
    ('2020-01-25', '4', '12'),
    ('2020-01-26', '5', '13'),
    ('2020-01-27', '1', '9'),
    ('2020-01-28', '1', '8'),
    ('2020-01-28', '2', '6'),
    ('2020-01-29', '5', '2'),
    ('2020-01-30', '5', '11'),
    ('2020-01-10', '5', '7'),
    ('2020-02-01', '1', '1'),
	('2020-02-03', '2', '2'),
	('2020-02-05', '3', '3'),
	('2020-02-07', '4', '4'),
	('2020-02-09', '5', '5'),
    ('2020-02-11', '5', '6'),
    ('2020-02-13', '5', '7'),
    ('2020-02-15', '4', '8'),
    ('2020-02-17', '1', '9'),
    ('2020-02-19', '2', '10'),
    ('2020-02-21', '3', '11'),
    ('2020-02-23', '3', '12'),
    ('2020-02-25', '4', '12'),
    ('2020-02-27', '5', '13'),
    ('2020-02-26', '1', '9'),
    ('2020-02-24', '1', '8'),
    ('2020-02-02', '2', '6'),
    ('2020-02-04', '5', '2'),
    ('2020-02-06', '5', '11'),
    ('2020-02-08', '5', '7'),
    ('2020-03-01', '1', '1'),
	('2020-03-03', '2', '2'),
	('2020-03-05', '3', '3'),
	('2020-03-07', '4', '4'),
	('2020-03-09', '5', '5'),
    ('2020-03-11', '5', '6'),
    ('2020-03-13', '5', '7'),
    ('2020-03-15', '4', '8'),
    ('2020-03-17', '1', '9'),
    ('2020-03-19', '2', '10'),
    ('2020-03-21', '3', '11'),
    ('2020-03-23', '3', '12'),
    ('2020-03-25', '4', '12'),
    ('2020-03-27', '5', '13'),
    ('2020-03-26', '1', '9'),
    ('2020-03-24', '1', '8'),
    ('2020-03-02', '2', '6'),
    ('2020-03-04', '5', '2'),
    ('2020-03-06', '5', '11'),
    ('2020-03-08', '5', '7'),
    ('2020-04-30', '1', '13'),
	('2020-04-29', '2', '12'),
	('2020-04-28', '3', '11'),
	('2020-04-27', '4', '10'),
	('2020-04-26', '5', '9'),
    ('2020-04-25', '5', '8'),
    ('2020-04-24', '5', '7'),
    ('2020-04-23', '4', '6'),
    ('2020-04-22', '1', '5'),
    ('2020-04-21', '2', '4'),
    ('2020-04-20', '3', '3'),
    ('2020-04-02', '3', '2'),
    ('2020-04-03', '4', '1'),
    ('2020-04-04', '5', '13'),
    ('2020-04-05', '1', '11'),
    ('2020-04-06', '1', '9'),
    ('2020-04-07', '2', '7'),
    ('2020-04-08', '5', '5'),
    ('2020-04-09', '5', '3'),
    ('2020-04-13', '5', '1'),
    ('2020-05-30', '5', '13'),
	('2020-05-29', '4', '12'),
	('2020-05-28', '3', '11'),
	('2020-05-27', '2', '10'),
	('2020-05-26', '1', '9'),
    ('2020-05-25', '1', '8'),
    ('2020-05-24', '2', '7'),
    ('2020-05-23', '3', '6'),
    ('2020-05-22', '4', '5'),
    ('2020-05-21', '5', '4'),
    ('2020-05-20', '5', '3'),
    ('2020-05-02', '4', '2'),
    ('2020-05-03', '3', '1'),
    ('2020-05-04', '2', '13'),
    ('2020-05-05', '2', '11'),
    ('2020-05-06', '1', '9'),
    ('2020-05-07', '2', '7'),
    ('2020-05-08', '4', '5'),
    ('2020-05-09', '3', '3'),
    ('2020-05-13', '2', '1'),
    ('2020-06-02', '5', '13'),
	('2020-06-04', '4', '12'),
	('2020-06-06', '3', '11'),
	('2020-06-08', '2', '10'),
	('2020-06-10', '1', '9'),
    ('2020-06-12', '1', '8'),
    ('2020-06-14', '2', '7'),
    ('2020-06-16', '3', '6'),
    ('2020-06-18', '4', '5'),
    ('2020-06-20', '5', '4'),
    ('2020-06-22', '5', '3'),
    ('2020-06-24', '4', '2'),
    ('2020-06-26', '3', '1'),
    ('2020-06-28', '2', '13'),
    ('2020-06-30', '2', '11'),
    ('2020-06-29', '1', '9'),
    ('2020-06-27', '2', '7'),
    ('2020-06-25', '4', '5'),
    ('2020-06-23', '3', '3'),
    ('2020-06-01', '2', '1'),
    ('2020-07-02', '5', '1'),
	('2020-07-04', '4', '2'),
	('2020-07-06', '3', '3'),
	('2020-07-08', '2', '4'),
	('2020-07-10', '1', '5'),
    ('2020-07-12', '1', '6'),
    ('2020-07-14', '2', '7'),
    ('2020-07-16', '3', '8'),
    ('2020-07-18', '4', '9'),
    ('2020-07-20', '5', '10'),
    ('2020-07-22', '5', '11'),
    ('2020-07-24', '4', '12'),
    ('2020-07-26', '3', '13'),
    ('2020-07-28', '2', '10'),
    ('2020-07-30', '2', '9'),
    ('2020-07-29', '1', '8'),
    ('2020-07-27', '2', '7'),
    ('2020-07-25', '4', '6'),
    ('2020-07-23', '3', '2'),
    ('2020-07-01', '2', '3'),
    ('2020-08-02', '1', '1'),
	('2020-08-04', '2', '2'),
	('2020-08-06', '3', '3'),
	('2020-08-08', '3', '4'),
	('2020-08-10', '4', '5'),
    ('2020-08-12', '5', '6'),
    ('2020-08-14', '5', '7'),
    ('2020-08-16', '4', '8'),
    ('2020-08-18', '4', '9'),
    ('2020-08-20', '2', '10'),
    ('2020-08-22', '2', '11'),
    ('2020-08-24', '1', '12'),
    ('2020-08-26', '2', '13'),
    ('2020-08-28', '3', '10'),
    ('2020-08-30', '4', '9'),
    ('2020-08-29', '5', '8'),
    ('2020-08-27', '1', '7'),
    ('2020-08-25', '2', '6'),
    ('2020-08-23', '2', '2'),
    ('2020-08-01', '5', '3'),
    ('2020-09-01', '1', '1'),
	('2020-09-03', '2', '2'),
	('2020-09-05', '3', '3'),
	('2020-09-07', '3', '4'),
	('2020-09-09', '4', '5'),
    ('2020-09-11', '5', '6'),
    ('2020-09-13', '5', '7'),
    ('2020-09-15', '4', '8'),
    ('2020-09-17', '4', '9'),
    ('2020-09-19', '2', '10'),
    ('2020-09-21', '2', '11'),
    ('2020-09-23', '1', '12'),
    ('2020-09-25', '2', '13'),
    ('2020-09-27', '3', '10'),
    ('2020-09-29', '4', '9'),
    ('2020-09-30', '5', '8'),
    ('2020-09-06', '1', '7'),
    ('2020-09-08', '2', '6'),
    ('2020-09-10', '2', '2'),
    ('2020-09-16', '5', '3'),
    ('2020-10-01', '1', '13'),
	('2020-10-03', '2', '11'),
	('2020-10-05', '3', '9'),
	('2020-10-07', '3', '7'),
	('2020-10-09', '4', '5'),
    ('2020-10-11', '5', '3'),
    ('2020-10-13', '5', '2'),
    ('2020-10-15', '4', '1'),
    ('2020-10-17', '4', '4'),
    ('2020-10-19', '2', '6'),
    ('2020-10-21', '2', '8'),
    ('2020-10-23', '1', '10'),
    ('2020-10-25', '2', '12'),
    ('2020-10-27', '3', '13'),
    ('2020-10-29', '4', '1'),
    ('2020-10-30', '5', '5'),
    ('2020-10-06', '1', '7'),
    ('2020-10-08', '2', '9'),
    ('2020-10-10', '2', '11'),
    ('2020-10-16', '5', '10'),
    ('2020-11-01', '5', '13'),
	('2020-11-03', '4', '11'),
	('2020-11-05', '3', '9'),
	('2020-11-07', '2', '7'),
	('2020-11-09', '1', '5'),
    ('2020-11-11', '1', '3'),
    ('2020-11-13', '2', '2'),
    ('2020-11-15', '3', '1'),
    ('2020-11-17', '4', '4'),
    ('2020-11-19', '5', '6'),
    ('2020-11-21', '1', '8'),
    ('2020-11-23', '2', '10'),
    ('2020-11-25', '3', '12'),
    ('2020-11-27', '4', '13'),
    ('2020-11-29', '5', '1'),
    ('2020-11-30', '3', '5'),
    ('2020-11-06', '2', '7'),
    ('2020-11-08', '1', '9'),
    ('2020-11-10', '5', '11'),
    ('2020-11-16', '4', '10'),
    ('2020-12-30', '4', '13'),
	('2020-12-29', '2', '11'),
	('2020-12-23', '1', '9'),
	('2020-12-21', '2', '7'),
	('2020-12-20', '1', '7'),
    ('2020-12-19', '1', '3'),
    ('2020-12-17', '2', '2'),
    ('2020-12-15', '4', '1'),
    ('2020-12-13', '4', '4'),
    ('2020-12-11', '2', '6'),
    ('2020-12-09', '5', '8'),
    ('2020-12-08', '5', '9'),
    ('2020-12-07', '3', '13'),
    ('2020-12-06', '4', '1'),
    ('2020-12-05', '5', '1'),
    ('2020-12-04', '3', '5'),
    ('2020-12-03', '2', '7'),
    ('2020-12-02', '1', '5'),
    ('2020-12-11', '5', '2'),
    ('2020-12-01', '4', '10');
    
INSERT INTO product(product_name, price) VALUES
	('Jumbotron XXL', 799.99),
    ('OLED Jumbotron 392', 1099.99),
    ('Jumbotron 4K Ultra HD', 999.99),
    ('Jumbotron 4K UHD with LTE', 1299.99),
    ('Jumbotron Smart 4K XL', 899.99);
    
INSERT INTO crm_order(date_submitted, date_installed, date_completed, order_total, order_status, order_comments, client_id) VALUES
	('2020-01-02', '2020-01-13', '2020-01-13', 9599.91, 'Complete', null, 1),
    ('2020-02-24', '2020-02-25', '2020-02-25', 9099.99, 'Complete', null, 2),
    ('2020-03-17', '2020-03-19', '2020-03-19', 899.99, 'Complete', null, 3),
    ('2020-04-02', '2020-04-04', '2020-04-04', 5499.95, 'Complete', null, 4),
    ('2020-05-05', '2020-05-08', '2020-05-10', 14499.86, 'Complete', null, 5),
    ('2020-06-25', '2020-06-29', '2020-06-29', 3799.96, 'Complete', null, 6),
    ('2020-07-14', '2020-07-18', '2020-07-19', 6899.94, 'Complete', null, 7),
    ('2020-08-28', '2020-08-30', '2020-08-30', 3999.96, 'Complete', null, 8),
    ('2020-09-20', '2020-09-21', '2020-09-22', 3899.97, 'Complete', null, 9),
    ('2020-10-12', '2020-10-13', '2020-10-15', 6399.94, 'Complete', null, 10),
    ('2020-11-16', '2020-11-17', '2020-11-19', 1999.98, 'Complete', null, 11),
    ('2020-12-04', '2020-12-05', '2020-12-05', 7199.94, 'Complete', null, 12),
    ('2020-12-09', '2020-12-11', '2020-12-11', 1699.98, 'Complete', null, 13);

INSERT INTO crm_order_product(order_id, product_id, quantity) VALUES
	(1, 4, 1),
    (1, 3, 5),
    (1, 2, 3),
    (2, 2, 1),
    (2, 5, 4),
    (2, 1, 3),
    (2, 3, 2),
    (3, 5, 1),
    (4, 3, 1),
    (4, 1, 2),
    (4, 2, 5),
    (5, 1, 2),
    (5, 2, 4),
    (5, 3, 1),
    (5, 4, 3),
    (5, 5, 4),
    (6, 1, 2),
    (6, 2, 2),
    (7, 3, 3),
    (7, 4, 3),
    (8, 2, 2),
    (8, 5, 2),
    (9, 4, 3),
    (10, 1, 2),
    (10, 2, 2),
    (10, 4, 2),
    (11, 3, 2),
    (12, 2, 3),
    (12, 4, 3),
    (13, 1, 1),
    (13, 5, 1);
