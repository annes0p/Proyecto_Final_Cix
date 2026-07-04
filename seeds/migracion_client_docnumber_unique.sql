-- ================================================================
-- Migracion (MySQL / Aiven, base `defaultdb`)
-- Limpia los 2 clientes duplicados detectados con doc_number 17453291
-- (id_client 10011 y 10012, sin ventas ni incidencias asociadas,
-- se conserva el id_client 5) y blinda la base para que nunca mas se
-- pueda repetir un doc_number. Ejecutar UNA sola vez.
-- ================================================================

DELETE FROM client WHERE id_client IN (10011, 10012);

ALTER TABLE client ADD CONSTRAINT uq_client_doc_number UNIQUE (doc_number);
