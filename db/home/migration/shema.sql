CREATE SCHEMA IF NOT EXISTS `main` DEFAULT CHARACTER SET utf8 ;

USE `main` ;

-- Создание таблицы "customers"
CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` SMALLINT(5) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(255) NOT NULL,
    `district` VARCHAR(255) NOT NULL,
    `street` VARCHAR(255) NOT NULL,
    `house` VARCHAR(255) NOT NULL,
    `apartment` VARCHAR(255) NOT NULL,
    `payment_method` VARCHAR(255) NOT NULL,
    `credit` TINYINT(1) NOT NULL,
    `car_brand` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`customer_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Создание таблицы "car_info"
CREATE TABLE IF NOT EXISTS `car_info` (
    `car_id` SMALLINT(5) NOT NULL AUTO_INCREMENT,
    `brand` VARCHAR(255) NOT NULL,
    `model` VARCHAR(255) NOT NULL,
    `year` INT(11) NOT NULL,
    `color` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`car_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Создание таблицы "orders_products"
CREATE TABLE IF NOT EXISTS `orders_products` (
    `order_id` SMALLINT(5) NOT NULL,
    `product_id` SMALLINT(5) NOT NULL,
    PRIMARY KEY (`order_id`, `product_id`),
    CONSTRAINT `fk_orders_products_orders`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_orders_products_products`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;