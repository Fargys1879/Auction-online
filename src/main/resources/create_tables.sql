DROP TABLE IF EXISTS users;
CREATE TABLE users
(id BIGINT NOT NULL AUTO_INCREMENT, user_name VARCHAR(255),
adress VARCHAR(255), login VARCHAR(255), password VARCHAR(255),
PRIMARY KEY(id));
INSERT into users (user_name, adress, login, password)
VALUES ('Evgeny','SergievPosad','evg123','123');
INSERT into users (user_name, adress, login, password)
VALUES ('Vasya','Saint Petersburg','vas123','123');
INSERT into users (user_name, adress, login, password)
VALUES ('Petya','Moscow','petro123','123');

DROP Table IF EXISTS products;
CREATE Table products
(uid BIGINT NOT NULL AUTO_INCREMENT,PRIMARY KEY(uid),
product_name VARCHAR(255),description VARCHAR(255),
start_price FLOAT, rate_step FLOAT, current_price FLOAT, time_lot INT,
flag_buy BOOLEAN DEFAULT false, bidder VARCHAR(255) DEFAULT 'NoBidder');
INSERT into products
(product_name, description, start_price, current_price, rate_step, time_lot)
VALUES ('Iphone12','New SmartPhone',1000.20,1000.20,100.50,24);
INSERT into products
(product_name, description, start_price, current_price, rate_step, time_lot)
VALUES ('IpadPro','New Tablet',1500.20,1500.20,150.50,24);
INSERT into products
(product_name, description, start_price, current_price, rate_step, time_lot)
VALUES ('SmartWatch 4','New Watches',500.20,500.20,50.50,24);

CREATE TABLE bids
(bid_id BIGINT NOT NULL AUTO_INCREMENT, product_id BIGINT,user_id BIGINT,price FLOAT,
PRIMARY KEY (bid_id),
FOREIGN KEY (product_id) REFERENCES products(uid),
FOREIGN KEY (user_id) REFERENCES users(id),
UNIQUE (bid_id, product_id, user_id, price));

SELECT product_name,description,user_id,price
from bids
INNER JOIN products
ON products.uid = bids.product_id
WHERE product_id = 2;

SELECT product_id,user_id,price,user_name
from "\bid\"
LEFT JOIN \"users\"
ON \"users\".id = "\bid\".user_id
WHERE user_id = 1;
