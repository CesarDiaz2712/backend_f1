INSERT INTO `role` (`name`) VALUES ('SUPERADMIN');
INSERT INTO `role` (`name`) VALUES ('USER');
INSERT INTO `role` (`name`) VALUES ('ADMIN');

INSERT INTO `permission_system` (`code`, `entity_name`, `action_name`) VALUES ('CREATE_USER', 'USER', 'CREATE');

INSERT INTO `user_app` (`firstname`, `lastname`, `username`, `password`, `date_created`) VALUES ('Cesar', 'Alejo', 'calejo', '1234', '2024-10-12 00:00:00');

INSERT INTO `role_permission_system` (`permission_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `role_user_app` (`user_id`, `role_id`) VALUES ('1', '1');
