USE cixoil;

-- No forzamos IDs (IDENTITY_INSERT), dejamos que SQL Server los asigne solo,
-- asi evitamos choques con roles/usuarios que ya tengas.

IF NOT EXISTS (SELECT 1 FROM role WHERE name = 'Vendedor')
    INSERT INTO role (name, description, status)
    VALUES ('Vendedor', 'Encargado de ventas y rutas de reparto', 1);

INSERT INTO [user] (username, email, password, status, id_role)
SELECT 'jperez', 'jperez@cixoil.com',
       '$2a$10$Mt2ltioT746kMKL0v0bprOAKGV2TdoDKSN79uMv/789.Ph0M/HyDO',
       1, r.id_role
FROM role r
WHERE r.name = 'Vendedor'
  AND NOT EXISTS (SELECT 1 FROM [user] WHERE username = 'jperez');
