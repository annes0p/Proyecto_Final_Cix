-- ================================================================
-- Migracion local (SQL Server, base `cixoil`)
-- Mismo contenido que migracion_location_coordenadas.sql pero en
-- sintaxis SQL Server. Asume los mismos id_location que en Aiven
-- (4=Almacen CIXOIL, 5=JLO, 6=Chongoyape, 7=Ferrenafe, 8=Chepen);
-- si tu base local tiene otros ids, revisa con
-- "SELECT id_location, name FROM location;" antes de correr los UPDATE.
-- Ejecutar UNA sola vez.
-- ================================================================

USE cixoil;

ALTER TABLE location ADD latitude FLOAT NULL;
ALTER TABLE location ADD longitude FLOAT NULL;

UPDATE location SET latitude = -6.7714, longitude = -79.8409 WHERE id_location = 4;  -- Almacen CIXOIL (Chiclayo)
UPDATE location SET latitude = -6.7511, longitude = -79.8367 WHERE id_location = 5;  -- Jose Leonardo Ortiz (JLO)
UPDATE location SET latitude = -6.6167, longitude = -79.3833 WHERE id_location = 6;  -- Chongoyape
UPDATE location SET latitude = -6.6389, longitude = -79.7944 WHERE id_location = 7;  -- Ferrenafe
UPDATE location SET latitude = -7.2333, longitude = -79.4333 WHERE id_location = 8;  -- Chepen
