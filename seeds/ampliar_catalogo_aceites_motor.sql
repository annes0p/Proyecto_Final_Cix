-- ================================================================
-- Migracion produccion (MySQL / Aiven, base `defaultdb`)
-- Ejecutar UNA sola vez.
--
-- Causa raiz de "el Recomendador siempre sugiere el mismo producto":
-- en la categoria 1 (Aceites de motor) solo existia UN producto
-- (EDGE 10W-40, id 1). Sin importar que tan variado sea el catalogo
-- de vehiculos, la IA no tenia otro aceite de motor real entre el
-- cual elegir, asi que siempre terminaba recomendando el mismo.
-- Aqui se agregan mas aceites de motor reales, con distintas
-- viscosidades, tipo de combustible y uso, usando las marcas que
-- ya existen en el catalogo.
-- ================================================================

USE defaultdb;

INSERT INTO product (name, description, price, viscosity, id_product_brand, id_category, status, created_at, updated_at)
SELECT v.name, v.description, v.price, v.viscosity, v.id_product_brand, 1, 1, NOW(), NOW()
FROM (
  SELECT 'Mobil 1 5W-30 Full Synthetic' AS name, 'Aceite 100% sintetico para motores a gasolina modernos, alto rendimiento en ciudad y carretera' AS description, 165.00 AS price, '5W-30' AS viscosity, 1 AS id_product_brand
  UNION ALL SELECT 'Mobil 1 0W-20 Advanced Fuel Economy', 'Aceite sintetico de baja viscosidad para motores a gasolina pequenos, mejora el ahorro de combustible', 170.00, '0W-20', 1
  UNION ALL SELECT 'Mobil Delvac 15W-40', 'Aceite mineral para motores diesel de trabajo pesado, camiones y pickups de carga', 130.00, '15W-40', 1
  UNION ALL SELECT 'Mobil 1 Racing 4T 10W-40', 'Aceite sintetico para motocicletas de 4 tiempos de alto rendimiento', 95.00, '10W-40', 1
  UNION ALL SELECT 'Castrol GTX 20W-50', 'Aceite mineral para motores a gasolina de alto kilometraje, ideal en climas calidos', 95.00, '20W-50', 2
  UNION ALL SELECT 'Castrol Edge 5W-40 Diesel', 'Aceite sintetico para motores diesel turboalimentados de alta exigencia', 175.00, '5W-40', 2
  UNION ALL SELECT 'Castrol Power 1 4T 20W-50', 'Aceite mineral para motocicletas de 4 tiempos, uso urbano', 60.00, '20W-50', 2
  UNION ALL SELECT 'Shell Helix Ultra 5W-30', 'Aceite sintetico premium para motores a gasolina de ultima generacion', 160.00, '5W-30', 3
  UNION ALL SELECT 'Shell Rimula 15W-40 Heavy Duty', 'Aceite para motores diesel de camiones y maquinaria de carga pesada', 140.00, '15W-40', 3
  UNION ALL SELECT 'Valvoline MaxLife 10W-30 High Mileage', 'Aceite para motores a gasolina con mas de 120,000 km de recorrido', 110.00, '10W-30', 4
  UNION ALL SELECT 'TotalEnergies Quartz 9000 5W-40', 'Aceite sintetico compatible con motores a gasolina y diesel modernos', 150.00, '5W-40', 5
  UNION ALL SELECT 'Liqui Moly Top Tec 4200 5W-30', 'Aceite sintetico de bajas cenizas (low SAPS) para motores diesel con filtro de particulas (DPF)', 185.00, '5W-30', 6
) v
WHERE NOT EXISTS (
  SELECT 1 FROM product p WHERE p.name = v.name
);
