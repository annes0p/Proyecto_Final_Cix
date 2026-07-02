USE cixoil;

SET IDENTITY_INSERT role ON;
INSERT INTO role (id_role, name, description, status)
VALUES (1, 'Administrador', 'Acceso completo a todos los modulos del sistema', 1);
SET IDENTITY_INSERT role OFF;

SET IDENTITY_INSERT [user] ON;
INSERT INTO [user] (id_user, username, email, password, status, id_role)
VALUES (1, 'admin', 'admin@admin.com', '$2a$10$Mt2ltioT746kMKL0v0bprOAKGV2TdoDKSN79uMv/789.Ph0M/HyDO', 1, 1);
SET IDENTITY_INSERT [user] OFF;
