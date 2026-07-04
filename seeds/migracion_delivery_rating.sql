-- ================================================================
-- Migracion (MySQL / Aiven, base `defaultdb`)
-- Agrega la calificacion de entrega (1-5), separada de la
-- calificacion de incidencias (incident.rating). Ejecutar UNA sola vez.
-- ================================================================

ALTER TABLE trip ADD COLUMN delivery_rating INT NULL;
