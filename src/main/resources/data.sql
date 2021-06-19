DROP Table IF EXISTS products;
CREATE Table products
(u_id BIGINT NOT NULL AUTO_INCREMENT,PRIMARY KEY(u_id),
product_name VARCHAR(255),description VARCHAR(255),
start_price FLOAT, rate_step FLOAT, current_price FLOAT, time_lot INT,
flag_buy BOOLEAN DEFAULT false, bidder VARCHAR(255) DEFAULT 'NoBidder',
 add_time DATETIME DEFAULT NOW());


