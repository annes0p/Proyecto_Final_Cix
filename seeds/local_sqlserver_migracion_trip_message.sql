-- ================================================================
-- Migracion local (SQL Server, base `cixoil`)
-- Mismo contenido que migracion_trip_message.sql pero en sintaxis
-- SQL Server. Ejecutar UNA sola vez.
-- ================================================================

USE cixoil;

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='trip_message' AND xtype='U')
BEGIN
    CREATE TABLE trip_message (
        id_trip_message BIGINT IDENTITY(1,1) PRIMARY KEY,
        id_trip BIGINT NOT NULL,
        sender VARCHAR(20),
        sender_name VARCHAR(100),
        content VARCHAR(500),
        created_at DATETIME,
        updated_at DATETIME
    );
END
