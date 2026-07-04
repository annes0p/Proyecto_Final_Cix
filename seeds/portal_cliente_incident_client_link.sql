-- ================================================================
-- Migracion produccion (MySQL / Aiven, base `defaultdb`)
-- Ejecutar UNA sola vez.
--
-- Vincula incident -> client para poder armar la seccion
-- "Mis incidencias" del portal publico del cliente (buscando por DNI).
-- Solo se llenara para incidencias nuevas (reportadas por el cliente
-- desde el portal, o vinculadas manualmente); las incidencias ya
-- existentes quedaran con id_client NULL y no apareceran en el portal.
-- ================================================================

USE defaultdb;

ALTER TABLE incident ADD COLUMN id_client BIGINT NULL;
ALTER TABLE incident ADD CONSTRAINT FK_incident_client FOREIGN KEY (id_client) REFERENCES client (id_client);
