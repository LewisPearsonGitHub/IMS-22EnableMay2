drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT(11) DEFAULT NULL,
    `fk_item_id` INT(11) DEFAULT NULL,
    PRIMARY KEY (`orders_id`),
    FOREIGN KEY (`fk_customers_id`) REFERENCES `customers`(`customer_id`),
    FOREIGN KEY (`fk_item_id`) REFERENCES `items`(`items_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `value` decimal(10,2) DEFAULT NULL,
    `item_name` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

