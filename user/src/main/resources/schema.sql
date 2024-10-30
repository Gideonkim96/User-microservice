CREATE TABLE IF NOT EXISTS `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(100) UNIQUE NOT NULL,
  `user_email` VARCHAR(100) NOT NULL,
  `mobile_number` VARCHAR(20) NOT NULL UNIQUE,
  `password` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);
