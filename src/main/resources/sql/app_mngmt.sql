CREATE TABLE `app_release` (
  `release_id` int(11) NOT NULL,
  `env_id` int(11) NOT NULL,
  `executable_path` varchar(500) NOT NULL,
  `executable_size` varchar(45) NOT NULL COMMENT 'in KB',
  PRIMARY KEY (`release_id`,`env_id`),
  KEY `fk_app_rls_idx` (`release_id`),
  KEY `fk_evmt_rls_idx` (`env_id`),
  CONSTRAINT `fk_app_rls` FOREIGN KEY (`release_id`) REFERENCES `release` (`release_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evmt_rls` FOREIGN KEY (`env_id`) REFERENCES `environment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(45) NOT NULL,
  `platform` int(11) NOT NULL,
  `description` varchar(45) NOT NULL,
  `provision_profile_path` varchar(500) DEFAULT NULL,
  `app_icon` longblob NOT NULL,
  PRIMARY KEY (`app_name`,`platform`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_app_platform_idx` (`platform`),
  CONSTRAINT `fk_app_platform` FOREIGN KEY (`platform`) REFERENCES `platform` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COMMENT='This table stores application details';

CREATE TABLE `environment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env_name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `env_name_UNIQUE` (`env_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='This table stores different environment details';

CREATE TABLE `platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(45) NOT NULL,
  `executable_type` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `platform_name_UNIQUE` (`platform_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

CREATE TABLE `release` (
  `release_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL,
  `release_number` varchar(5) NOT NULL,
  `release_date` datetime NOT NULL,
  `release_note` varchar(300) NOT NULL,
  PRIMARY KEY (`app_id`,`release_number`),
  UNIQUE KEY `release_id_UNIQUE` (`release_id`),
  KEY `app_release_idx` (`app_id`),
  CONSTRAINT `fk_app_release` FOREIGN KEY (`app_id`) REFERENCES `application` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='This table saves release entries.';
