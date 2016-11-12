CREATE SCHEMA `users` ;
CREATE SCHEMA `posts` ;

CREATE TABLE `users`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
  ALTER TABLE `users`.`users`
  ADD COLUMN `profile_pic` BLOB NULL AFTER `username`;
ALTER TABLE `users`.`users`
  DROP PRIMARY KEY,
  ADD PRIMARY KEY (`email`);


  INSERT INTO `users`.`users` (`id`, `name`, `lastname`, `email`, `password`, `username`) VALUES ('1', 'Elena', 'Georgievskaya', 'ellenageor@gmail.com', 'qwerty123', 'le5n');
  INSERT INTO `users`.`users` (`id`, `name`, `lastname`, `email`, `password`, `username`) VALUES ('2', 'Anna', 'Petrova', 'example@mail.ru', '321ytrewq', 'annap');



CREATE TABLE `posts`.`posts` (
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `post_date` VARCHAR(100) NOT NULL,
  `post_text` VARCHAR(140) NOT NULL,
  PRIMARY KEY (`post_id`),
  UNIQUE INDEX `post_id_UNIQUE` (`post_id` ASC));
ALTER TABLE `posts`.`posts`
  CHANGE COLUMN `post_id` `post_id` INT(11) NOT NULL AUTO_INCREMENT ;

INSERT INTO `posts`.`posts` (`post_id`, `user_id`, `post_date`, `post_text`) VALUES ('1', '6', '05.05.05', 'hello world');



