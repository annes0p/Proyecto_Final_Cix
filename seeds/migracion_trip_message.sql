-- ================================================================
-- Migracion (MySQL / Aiven, base `defaultdb`)
-- Tabla nueva para el chat (no chatbot) entre cliente y personal
-- sobre un envio puntual (Trip). ddl-auto=update deberia crearla
-- solo en el proximo deploy, pero se deja el script por si acaso
-- (como paso con columnas anteriores). Si la tabla ya existe, no
-- pasa nada al re-ejecutar el CREATE TABLE IF NOT EXISTS.
-- ================================================================

CREATE TABLE IF NOT EXISTS trip_message (
    id_trip_message BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_trip BIGINT NOT NULL,
    sender VARCHAR(20),
    sender_name VARCHAR(100),
    content VARCHAR(500),
    created_at DATETIME,
    updated_at DATETIME
);
