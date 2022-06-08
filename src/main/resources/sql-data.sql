INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');

INSERT INTO `ims`.`items` (`value`, `item_name`) VALUES ('1.20', 'car');

INSERT INTO `ims`.`orders`(`fk_customers_id`) VALUES ('1');

INSERT INTO `ims`.`orders_items`(`fk_orders_id`,`fk_items_id`,`quantity`)
VALUES ('1','1','5')




