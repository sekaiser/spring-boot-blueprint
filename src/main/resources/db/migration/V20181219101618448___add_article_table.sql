CREATE TABLE article (
  id BIGSERIAL PRIMARY KEY,
  identifier VARCHAR(255) NOT NULL,
  header VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  text TEXT NOT NULL,
  keywords VARCHAR(255) NULL,
  authors VARCHAR(255) NULL,
  created_date timestamp without time zone DEFAULT NOW(),
  published_date timestamp without time zone NULL
);