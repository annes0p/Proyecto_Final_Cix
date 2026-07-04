-- ================================================================
-- Migracion (MySQL / Aiven, base `defaultdb`)
-- Agrega coordenadas reales a los lugares existentes, usadas solo
-- para simular en el mapa el avance de una entrega (sin depender de
-- GPS real del vendedor). Ejecutar UNA sola vez.
-- ================================================================

ALTER TABLE location ADD COLUMN latitude DOUBLE NULL;
ALTER TABLE location ADD COLUMN longitude DOUBLE NULL;

UPDATE location SET latitude = -6.7714, longitude = -79.8409 WHERE id_location = 4;  -- Almacen CIXOIL (Chiclayo)
UPDATE location SET latitude = -6.7511, longitude = -79.8367 WHERE id_location = 5;  -- Jose Leonardo Ortiz (JLO)
UPDATE location SET latitude = -6.6167, longitude = -79.3833 WHERE id_location = 6;  -- Chongoyape
UPDATE location SET latitude = -6.6389, longitude = -79.7944 WHERE id_location = 7;  -- Ferrenafe
UPDATE location SET latitude = -7.2333, longitude = -79.4333 WHERE id_location = 8;  -- Chepen
