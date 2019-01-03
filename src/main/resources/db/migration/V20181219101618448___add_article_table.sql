CREATE TABLE `articles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,

  unique_id VARCHAR(255) NOT NULL,
  requested_id BOOLEAN NOT NULL,
  `version` VARCHAR(255) NOT NULL,
  mttrbit_user VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  metadata TEXT DEFAULT NULL,

  created TIMESTAMP(3) without time zone DEFAULT now() NOT NULL,
  updated TIMESTAMP(3) without time zone DEFAULT now() NOT NULL,
  header VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  text TEXT NOT NULL,
  keywords VARCHAR(255) DEFAULT NULL,
  authors VARCHAR(255) DEFAULT NULL,
  published TIMESTAMP(3) without time zone DEFAULT NULL,

  PRIMARY KEY (`id`),
  CONSTRAINT ARTICLES_UNIQUE_ID_UNIQUE_INDEX UNIQUE (`unique_id`)
);