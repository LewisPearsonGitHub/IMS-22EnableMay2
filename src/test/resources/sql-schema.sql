DROP TABLE IF EXISTS `orders_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;


CREATE TABLE IF NOT EXISTS `customers` (
    `customers_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customers_id`)
);

CREATE TABLE IF NOT EXISTS `items` (
    `items_id` INT(11) NOT NULL AUTO_INCREMENT,
    `value` decimal(10,2) DEFAULT NULL,
    `item_name` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`items_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
    `orders_id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_customers_id` INT(11) DEFAULT NULL,
    PRIMARY KEY (`orders_id`),
    FOREIGN KEY (`fk_customers_id`) REFERENCES `customers`(`customers_id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `orders_items` (
`orders_items_id` INT(11) NOT NULL AUTO_INCREMENT,
`fk_items_id` INT(11) DEFAULT NULL,
`fk_orders_id` INT(11) DEFAULT NULL,
`quantity` INT(11) DEFAULT NULL,
PRIMARY KEY (`orders_items_id`),
FOREIGN KEY(`fk_items_id`) REFERENCES `items`(`items_id`) ON DELETE CASCADE,
FOREIGN KEY(`fk_orders_id`) REFERENCES `orders`(`orders_id`) ON DELETE CASCADE
);

