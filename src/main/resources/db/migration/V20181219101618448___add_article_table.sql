CREATE TABLE articles (
  id BIGSERIAL PRIMARY KEY,
  header VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  text TEXT NOT NULL,
  keywords VARCHAR(255) DEFAULT '',
  authors VARCHAR(255) DEFAULT '',
  created_date timestamp without time zone
);