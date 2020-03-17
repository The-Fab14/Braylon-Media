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
    user_password VARCHAR(15) NOT NULL,
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

