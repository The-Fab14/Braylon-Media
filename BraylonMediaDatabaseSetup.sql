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
    quantity INT NOT NULL,
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
    
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('John', 'Martin','Ted Talks', '285 Young Road', 'Apt #301', 'Stone Mountain', '30385','jmartin@gmail.com','6783611390','GA',1);
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('Doug', 'Smith','Some Company', '285 Young Road', 'Apt #301', 'Stone Mountain', '30385','jmartin@gmail.com','6783611390','GA',1);
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('Jake', 'Porter','Ted Talks', '7816 Monogramm Lane', 'Apt #2200','Mint Hill','28227','cross_sounds@gmail.com','7045521694','NC',1);
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('John', 'Smith','Ted Talks', '7816 Monogramm Lane', 'Apt #2200','Mint Hill','28227','cross_sounds@gmail.com','7045521694','NC',2);

INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('Alex', 'Cross','Live Sound Prodution', '7816 Monogramm Lane', 'Apt #2200','Mint Hill','28227','cross_sounds@gmail.com','7045521694','NC',2);
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('Casey', 'Jones','Stage Audio', '3000 College Drive', 'Apt #305', 'Savannah', '30385','jstagestudios01@yahoo.com','2290052387','GA', 3);
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('Meghan', 'Robinson','Sound Peristance', '25001 Venice Dr.', 'Apt #8759', 'Los Angeles','93736','s.persistance@microsoft.com','8063667834','LA',4);
INSERT INTO crm_client(contact_first_name, contact_last_name, company_name, street_address, apt_unit, city, zip, email_address, phone_number, state_id, user_id) VALUES ('Joey', 'Martin','Creative Sound', '1026 Jay Street', 'Apt# 264', 'Charlotte','28208','creativesound@gmail.com','7048881497','NC', 5);


INSERT INTO visit(visit_id, date_visited, user_id, client_id)
	VALUES
    ('1', '2020-01-13', '1', '1'),
	('2', '2020-01-14', '2', '2'),
	('3', '2020-01-15', '3', '3'),
	('4', '2020-01-16', '4', '4'),
	('5', '2020-01-17', '5', '5');
    
INSERT INTO product(product_name, price) VALUES
	('Jumbotron XXL', 799.99),
    ('OLED Jumbotron 392', 1099.99),
    ('Jumbotron 4K Ultra HD', 999.99),
    ('Jumbotron 4K UHD with LTE', 1299.99),
    ('Jumbotron Smart 4K XL', 899.99);
    
INSERT INTO crm_order(date_submitted, date_installed, date_completed, order_total, order_status, order_comments, client_id) VALUES
	('2020-04-01', '2020-04-05', '2020-04-06', 1299.99, 'Complete', null, 1),
    ('2020-05-12', '2020-05-14', '2020-05-15', 1099.99, 'Complete', null, 2),
    ('2020-05-12', '2020-05-14', '2020-05-16', 899.99, 'Complete', null, 3),
    ('2020-04-15', '2020-04-16', '2020-04-16', 999.99, 'Complete', null, 4),
    ('2020-06-04', '2020-06-08', '2020-06-10', 799.99, 'Complete', null, 5);

INSERT INTO crm_order_product(order_id, product_id, quantity) VALUES
	(1, 4, 1),
    (2, 2, 1),
    (3, 5, 1),
    (4, 3, 1),
    (5, 1, 1);