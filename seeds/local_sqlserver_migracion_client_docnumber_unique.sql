-- ================================================================
-- Migracion local (SQL Server, base `cixoil`)
-- Blinda la base para que nunca se pueda repetir un doc_number en
-- client (el duplicado detectado fue solo en produccion/Aiven, pero
-- aqui tambien se agrega la misma restriccion por consistencia).
-- Si esta base local ya tuviera duplicados, este script fallaria: en
-- ese caso avisar antes de forzarlo. Ejecutar UNA sola vez.
-- ================================================================

USE cixoil;

ALTER TABLE client ADD CONSTRAINT uq_client_doc_number UNIQUE (doc_number);
