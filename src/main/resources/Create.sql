-- Database: webnotes

-- DROP DATABASE webnotes;

CREATE DATABASE webnotes
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;

       -- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  login character varying(50) NOT NULL,
  password character varying(50) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (login),
  CONSTRAINT unique_login UNIQUE (login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;

  -- Table: notes

-- DROP TABLE notes;

CREATE TABLE notes
(
  id serial NOT NULL,
  login character varying(50) NOT NULL,
  note character varying(255) NOT NULL,
  datetimecreate timestamp without time zone NOT NULL,
  CONSTRAINT notes_pkey PRIMARY KEY (id),
  CONSTRAINT notes_login_fkey FOREIGN KEY (login)
      REFERENCES users (login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT unique_id UNIQUE (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE notes
  OWNER TO postgres;
