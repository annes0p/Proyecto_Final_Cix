USE cixoil;

INSERT INTO supplier_product (id_supplier, id_product)
SELECT s.id_supplier, p.id_product FROM supplier s, product p
WHERE s.legal_name = 'LIQUI MOLY PERU S.A.C.' AND p.name = 'MaxLife ATF'
UNION ALL
SELECT s.id_supplier, p.id_product FROM supplier s, product p
WHERE s.legal_name = 'LIQUI MOLY PERU S.A.C.' AND p.name = 'Engine Flush'
UNION ALL
SELECT s.id_supplier, p.id_product FROM supplier s, product p
WHERE s.legal_name = 'DISTRIBUIDORA DE LUBRICANTES ESSA EIRL' AND p.name = 'DOT 3 Brake Fluid'
UNION ALL
SELECT s.id_supplier, p.id_product FROM supplier s, product p
WHERE s.legal_name = 'NEXO LUBRICANTES S.A.' AND p.name = 'Fuel Injector Cleaner';
