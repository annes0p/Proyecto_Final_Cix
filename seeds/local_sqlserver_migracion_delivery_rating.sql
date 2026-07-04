-- ================================================================
-- Migracion local (SQL Server, base `cixoil`)
-- Mismo contenido que migracion_delivery_rating.sql pero en sintaxis
-- SQL Server. Ejecutar UNA sola vez.
-- ================================================================

USE cixoil;

ALTER TABLE trip ADD delivery_rating INT NULL;
