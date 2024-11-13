-- Inserta datos en la tabla de usuarios

INSERT IGNORE INTO crud_user (reference, password, first_name, last_name,
nickname, email, is_active, created_at, updated_at, version)
VALUES
('17334fcc-8b65-11ed-a1eb-0242ac120002', 'SA34', 'Leonardo', 'da Vinci',
'LeoVinci', 'leonardo.davinci@example.com', true, NOW(), NOW(), 0),
('17335260-8b65-11ed-a1eb-0242ac120002', 'SA12', 'Galileo', 'Galilei',
'StarGazer', 'galileo.galilei@example.com', true, NOW(), NOW(), 0),
('17335396-8b65-11ed-a1eb-0242ac120002', 'SA56', 'Dante', 'Alighieri',
'PoetDante', 'dante.alighieri@example.com', true, NOW(), NOW(), 0),
('173354c2-8b65-11ed-a1eb-0242ac120002', 'SA87', 'Michelangelo', 'Buonarroti',
'ArtMaster', 'michelangelo.buonarroti@example.com', true, NOW(), NOW(), 0);