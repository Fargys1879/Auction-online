CREATE Table "USER"
(ID BIGINT PRIMARY KEY, USER_NAME VARCHAR(255),
 ADRESS VARCHAR(255), LOGIN VARCHAR(255), PASSWORD VARCHAR(255));

INSERT into "USER" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (1,'Evgeny','SergievPosad','evg123','123');
INSERT into "USER" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (2,'Vasya','Saint Petersburg','vas123','123');
INSERT into "USER" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (3,'Petya','Moscow','petro123','123');

CREATE Table "PRODUCT"
(UID BIGINT PRIMARY KEY, PRODUCT_NAME VARCHAR(255),DESCRIPTION VARCHAR(255),
 START_PRICE FLOAT, RATE_STEP FLOAT, TIME_LOT INT, FLAG_BUY BOOLEAN);

INSERT into "PRODUCT"
(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY)
VALUES (1,'Iphone12','New SmartPhone',1000.20,100.50,24,false);
INSERT into "PRODUCT"
(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY)
VALUES (2,'IpadPro','New Tablet',1500.20,150.50,24,false);
INSERT into "PRODUCT"
(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY)
VALUES (3,'SmartWatch 4','New Watches',500.20,50.50,24,false);