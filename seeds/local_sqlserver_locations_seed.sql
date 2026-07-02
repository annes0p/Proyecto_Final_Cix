USE cixoil;

INSERT INTO location (name)
SELECT v.name
FROM (VALUES
    ('Almacen CIXOIL'),
    ('Jose Leonardo Ortiz (JLO)'),
    ('Chongoyape'),
    ('Ferrenafe'),
    ('Chepen')
) AS v(name)
WHERE NOT EXISTS (
    SELECT 1 FROM location l WHERE l.name = v.name
);
