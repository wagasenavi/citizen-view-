--make sure user already exist or not
CREATE USER citizen_view WITH PASSWORD 'citizen_view123';
 
CREATE TABLESPACE citizen_view
  OWNER citizen_view
  LOCATION 'C:\Software\DataFiles\pg\citizen_view';

ALTER TABLESPACE citizen_view
  OWNER TO citizen_view;

COMMENT ON TABLESPACE citizen_view
  IS 'citizen_view';
  
CREATE DATABASE citizen_view
  WITH 
  OWNER = citizen_view
  ENCODING = 'UTF8'
  TABLESPACE = citizen_view
  CONNECTION LIMIT = -1;

COMMENT ON DATABASE citizen_view
  IS 'citizen_view Database.';  

