DROP TABLE IF EXISTS logins CASCADE;
CREATE TABLE logins (
"id" integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
"name" character varying(100),
"pass" character varying(256)
);

REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO twitter_user;

REVOKE ALL ON logins FROM public;
GRANT ALL ON logins TO twitter_user;

