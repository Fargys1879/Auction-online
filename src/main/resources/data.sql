ALTER TABLE users
ALTER COLUMN active BOOLEAN DEFAULT true;

INSERT into roles
(role_name )
VALUES ('ROLE_USER');
INSERT into roles
(role_name )
VALUES ('ROLE_ADMIN');

ALTER TABLE products
ALTER COLUMN buy_flag BOOLEAN DEFAULT false;
ALTER TABLE products
ALTER COLUMN bidder VARCHAR(255) DEFAULT 'NoBidder';
ALTER TABLE products
ALTER COLUMN add_time DATETIME DEFAULT NOW();

INSERT into products
(product_name, description, start_price, current_price, rate_step, time_lot)
VALUES ('Iphone12','New SmartPhone',1000.20,1000.20,100.50,30);
INSERT into products
(product_name, description, start_price, current_price, rate_step, time_lot)
VALUES ('IpadPro','New Tablet',1500.20,1500.20,150.50,30);
INSERT into products
(product_name, description, start_price, current_price, rate_step, time_lot)
VALUES ('SmartWatch 4','New Watches',500.20,500.20,50.50,30);
