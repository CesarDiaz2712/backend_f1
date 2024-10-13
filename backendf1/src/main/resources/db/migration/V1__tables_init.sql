CREATE TABLE `permission_system` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `entity_name` VARCHAR(45) NULL,
  `action_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC));

CREATE TABLE `role` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

  CREATE TABLE `role_permission_system` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `permission_id` BIGINT(22) NOT NULL,
  `role_id` BIGINT(22) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_role_permission_system_permission_id_idx` (`permission_id` ASC),
  INDEX `fk_role_permission_system_role_id_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_permission_system_permission_id`
    FOREIGN KEY (`permission_id`)
    REFERENCES `permission_system` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_permission_system_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `user_app` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

  CREATE TABLE `role_user_app` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(22) NOT NULL,
  `role_id` BIGINT(22) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_role_user_app_role_id_idx` (`role_id` ASC),
  INDEX `fk_role_user_app_user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_role_user_app_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_user_app_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_app` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `code` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));


CREATE TABLE `code_value` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code_id` INT NOT NULL,
  `code_value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_code_value_code_id_idx` (`code_id` ASC),
  CONSTRAINT `fk_code_value_code_id`
    FOREIGN KEY (`code_id`)
    REFERENCES `code` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `f1_driver` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `gamertag` VARCHAR(45) NOT NULL,
  `number_driver` VARCHAR(3) NOT NULL,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL,
  `user_id` BIGINT(22) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `gamertag_UNIQUE` (`gamertag` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_f1_driver_user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_f1_driver_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_app` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `team_scuderia` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `alias` VARCHAR(20) NOT NULL,
  `year_register` VARCHAR(5) NOT NULL,
  `team_chief` VARCHAR(45) NOT NULL,
  `technical_chief` VARCHAR(45) NOT NULL,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

  CREATE TABLE `contract_driver` (
  `driver_id` BIGINT(22) NOT NULL,
  `team_id` BIGINT(22) NOT NULL,
  `date_initial_contract` DATE NOT NULL,
  `date_end_contract` DATE NOT NULL,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL,
  `is_activated` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`driver_id`, `team_id`),
  INDEX `fk_contract_driver_team_id_idx` (`team_id` ASC),
  CONSTRAINT `fk_contract_driver_driver_id`
    FOREIGN KEY (`driver_id`)
    REFERENCES `f1_driver` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contract_driver_team_id`
    FOREIGN KEY (`team_id`)
    REFERENCES `team_scuderia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `circuit` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cv_country_id` INT NULL DEFAULT NULL,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_circuit_cv_country_id_idx` (`cv_country_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_circuit_cv_country_id`
    FOREIGN KEY (`cv_country_id`)
    REFERENCES `code_value` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `schedule_circuit` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `date_scheduled` DATETIME NOT NULL,
  `reversed` TINYINT(1) NULL DEFAULT 0,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL DEFAULT NULL,
  `schedule_replaced_id` BIGINT(22) NULL DEFAULT NULL,
  `circuit_id` BIGINT(22) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_schedule_circuit_circuit_id_idx` (`circuit_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_schedule_circuit_circuit_id`
    FOREIGN KEY (`circuit_id`)
    REFERENCES `circuit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `record_fastlap` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `record` VARCHAR(10) NOT NULL,
  `circuit_id` BIGINT(22) NULL DEFAULT NULL,
  `cv_seasson_id` INT NULL DEFAULT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_record_fastlap_circuit_id_idx` (`circuit_id` ASC),
  INDEX `fk_record_fastlap_cv_seasson_id_idx` (`cv_seasson_id` ASC),
  CONSTRAINT `fk_record_fastlap_circuit_id`
    FOREIGN KEY (`circuit_id`)
    REFERENCES `circuit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_record_fastlap_cv_seasson_id`
    FOREIGN KEY (`cv_seasson_id`)
    REFERENCES `code_value` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `result_race` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `points` DOUBLE NOT NULL,
  `dnf` TINYINT(1) NULL DEFAULT 0,
  `dns` TINYINT(1) NULL DEFAULT 0,
  `dnq` TINYINT(1) NULL DEFAULT 0,
  `record_fastlap_id` BIGINT(22) NOT NULL,
  `driver_id` BIGINT(22) NOT NULL,
  `schedule_circuit_id` BIGINT(22) NOT NULL,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_result_race_record_fastlap_id_idx` (`record_fastlap_id` ASC),
  INDEX `fk_result_race_driver_id_idx` (`driver_id` ASC),
  INDEX `fk_result_race_schedule_circuit_id_idx` (`schedule_circuit_id` ASC),
  CONSTRAINT `fk_result_race_record_fastlap_id`
    FOREIGN KEY (`record_fastlap_id`)
    REFERENCES `record_fastlap` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_result_race_driver_id`
    FOREIGN KEY (`driver_id`)
    REFERENCES `f1_driver` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_result_race_schedule_circuit_id`
    FOREIGN KEY (`schedule_circuit_id`)
    REFERENCES `schedule_circuit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `reset_password` (
  `id` BIGINT(22) NOT NULL AUTO_INCREMENT,
  `updated_by` VARCHAR(45) NOT NULL,
  `user_app_id` BIGINT(22) NOT NULL,
  `created_on` DATETIME NOT NULL,
  `old_password` VARCHAR(255) NOT NULL,
  `new_password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_reset_password_user_id_idx` (`user_app_id` ASC),
  CONSTRAINT `fk_reset_password_user_id`
    FOREIGN KEY (`user_app_id`)
    REFERENCES `user_app` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);









