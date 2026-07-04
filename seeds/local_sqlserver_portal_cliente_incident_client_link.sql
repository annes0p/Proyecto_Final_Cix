-- ================================================================
-- Migracion local (SQL Server, base `cixoil`)
-- Mismo contenido que portal_cliente_incident_client_link.sql en sintaxis SQL Server.
-- ================================================================

USE cixoil;

ALTER TABLE incident ADD id_client BIGINT NULL;
ALTER TABLE incident ADD CONSTRAINT FK_incident_client FOREIGN KEY (id_client) REFERENCES client(id_client);
