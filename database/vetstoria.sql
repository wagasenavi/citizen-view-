--make sure user already exist or not
CREATE USER vetstoria WITH PASSWORD 'vetstoria123';
 
CREATE TABLESPACE vetstoria
  OWNER vetstoria
  LOCATION 'C:\Software\DataFiles\pg\vetstoria';

ALTER TABLESPACE vetstoria
  OWNER TO vetstoria;

COMMENT ON TABLESPACE vetstoria
  IS 'vetstoria';
  
CREATE DATABASE vetstoria
  WITH 
  OWNER = vetstoria
  ENCODING = 'UTF8'
  TABLESPACE = vetstoria
  CONNECTION LIMIT = -1;

COMMENT ON DATABASE vetstoria
  IS 'vetstoria Database.';  

