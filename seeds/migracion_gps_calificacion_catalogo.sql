-- ================================================================
-- Migracion produccion (MySQL / Aiven, base `defaultdb`)
-- Ejecutar UNA sola vez, en este orden.
-- Cubre: calificacion de incidencias, GPS en vivo de rutas,
-- y ampliacion del catalogo de vehiculos para el Recomendador.
-- ================================================================

USE defaultdb;

-- 1) Columnas nuevas en `incident` (fecha real de resolucion + calificacion 1-5)
ALTER TABLE incident ADD COLUMN resolved_at DATETIME NULL;
ALTER TABLE incident ADD COLUMN rating INT NULL;

-- 2) Tabla nueva para la ubicacion GPS en vivo del vendedor durante un viaje
CREATE TABLE IF NOT EXISTS trip_location (
  id_trip BIGINT PRIMARY KEY,
  latitude DOUBLE,
  longitude DOUBLE,
  updated_at DATETIME
);

-- 3) Mas modelos de vehiculo (usa marcas/tipos ya existentes) para que el
--    Recomendador tenga variedad real y no siempre sugiera lo mismo.
INSERT INTO vehicle_model (model, year, fuel_type, horse_power, motor_cc, transmission_type, id_vehicle_brand, id_vehicle_type, status)
SELECT v.model, v.year, v.fuel_type, v.horse_power, v.motor_cc, v.transmission_type, v.id_vehicle_brand, v.id_vehicle_type, 1
FROM (
  SELECT 'Sportage' AS model, 2024 AS year, 'GASOLINE' AS fuel_type, 187 AS horse_power, 2000 AS motor_cc, 'AUTOMATIC' AS transmission_type, 3 AS id_vehicle_brand, 3 AS id_vehicle_type
  UNION ALL SELECT 'Picanto', 2022, 'GASOLINE', 83, 1200, 'MANUAL', 3, 2
  UNION ALL SELECT 'Sentra', 2023, 'GASOLINE', 149, 2000, 'CVT', 5, 1
  UNION ALL SELECT 'Frontier', 2024, 'DIESEL', 190, 2500, 'MANUAL', 5, 4
  UNION ALL SELECT 'Kicks', 2023, 'GASOLINE', 122, 1600, 'CVT', 5, 3
  UNION ALL SELECT 'Swift', 2022, 'GASOLINE', 82, 1200, 'MANUAL', 6, 2
  UNION ALL SELECT 'Vitara', 2023, 'GASOLINE', 138, 1600, 'AUTOMATIC', 6, 3
  UNION ALL SELECT 'Ranger', 2024, 'DIESEL', 200, 2000, 'AUTOMATIC', 9, 4
  UNION ALL SELECT 'EcoSport', 2020, 'GASOLINE', 123, 2000, 'AUTOMATIC', 9, 3
  UNION ALL SELECT 'CR-V', 2023, 'GASOLINE', 190, 1500, 'CVT', 10, 3
  UNION ALL SELECT 'Fit', 2021, 'GASOLINE', 117, 1500, 'CVT', 10, 2
  UNION ALL SELECT 'L200', 2023, 'DIESEL', 178, 2400, 'AUTOMATIC', 11, 4
  UNION ALL SELECT 'Outlander', 2022, 'GASOLINE', 166, 2500, 'CVT', 11, 3
  UNION ALL SELECT 'Duster', 2022, 'GASOLINE', 143, 2000, 'MANUAL', 12, 3
  UNION ALL SELECT 'Logan', 2021, 'GASOLINE', 105, 1600, 'MANUAL', 12, 1
  UNION ALL SELECT 'Tiggo 7 Pro', 2024, 'GASOLINE', 145, 1500, 'CVT', 13, 3
  UNION ALL SELECT 'Arrizo 5', 2022, 'GASOLINE', 113, 1500, 'CVT', 13, 1
  UNION ALL SELECT 'Song Plus', 2024, 'HYBRID', 197, 1500, 'AUTOMATIC', 14, 3
  UNION ALL SELECT 'Yuan Plus', 2024, 'ELECTRIC', 201, 0, 'AUTOMATIC', 14, 3
  UNION ALL SELECT 'X70', 2023, 'GASOLINE', 143, 1500, 'AUTOMATIC', 15, 3
  UNION ALL SELECT 'T6', 2023, 'DIESEL', 150, 2000, 'MANUAL', 16, 4
  UNION ALL SELECT 'Poer', 2023, 'DIESEL', 143, 2000, 'MANUAL', 17, 4
  UNION ALL SELECT 'Haval H6', 2024, 'HYBRID', 187, 1500, 'AUTOMATIC', 17, 3
  UNION ALL SELECT 'Serie 3', 2023, 'GASOLINE', 255, 2000, 'AUTOMATIC', 18, 1
  UNION ALL SELECT 'X3', 2024, 'GASOLINE', 248, 2000, 'AUTOMATIC', 18, 3
  UNION ALL SELECT 'Clase C', 2023, 'GASOLINE', 255, 2000, 'AUTOMATIC', 19, 1
  UNION ALL SELECT 'Sprinter', 2023, 'DIESEL', 163, 2100, 'MANUAL', 19, 8
  UNION ALL SELECT 'A4', 2023, 'GASOLINE', 190, 2000, 'AUTOMATIC', 20, 1
  UNION ALL SELECT 'Q5', 2024, 'GASOLINE', 261, 2000, 'AUTOMATIC', 20, 3
  UNION ALL SELECT 'Yaris', 2022, 'GASOLINE', 107, 1500, 'MANUAL', 1, 2
  UNION ALL SELECT 'Hiace', 2023, 'DIESEL', 136, 2800, 'MANUAL', 1, 7
  UNION ALL SELECT 'Accent', 2021, 'GASOLINE', 132, 1600, 'AUTOMATIC', 2, 1
  UNION ALL SELECT 'Creta', 2023, 'GASOLINE', 130, 1600, 'AUTOMATIC', 2, 3
  UNION ALL SELECT 'Tracker', 2024, 'GASOLINE', 153, 1200, 'AUTOMATIC', 4, 3
  UNION ALL SELECT 'Sail', 2020, 'GASOLINE', 106, 1500, 'MANUAL', 4, 1
  UNION ALL SELECT 'Mazda 3', 2023, 'GASOLINE', 191, 2500, 'AUTOMATIC', 7, 1
  UNION ALL SELECT 'Gol', 2019, 'GASOLINE', 101, 1600, 'MANUAL', 8, 2
  UNION ALL SELECT 'Amarok', 2023, 'DIESEL', 201, 3000, 'AUTOMATIC', 8, 4
) v
WHERE NOT EXISTS (
  SELECT 1 FROM vehicle_model m
  WHERE m.id_vehicle_brand = v.id_vehicle_brand AND m.model = v.model AND m.year = v.year
);
