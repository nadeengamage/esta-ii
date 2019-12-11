INSERT INTO department (identity_no, name, working_days_per_week, working_hours_per_day, status) VALUES
('98b4bb00-6191-46e5-82f3-72588505783c', 'HR', 5 ,'08:00:00', 1),
('98b4bb00-6191-46e5-82f3-72588505783c', 'HR', 5 ,'08:00:00', 1),
('a1052788-f221-4047-84d0-9e62119f302b', 'IT', 5 ,'08:00:00', 1),
('b9725baa-3b22-4bb5-aa37-9ebee5895f29', 'Finance', 6 ,'09:00:00', 1),
('780a4112-9ceb-408e-8b9b-03d8061dd4e9', 'Support', 7 ,'08:00:00', 1),
('0c0780f5-f82e-4c1c-a385-7fb9512e0ef6', 'Delivery', 7 ,'10:00:00', 1);


INSERT INTO employee (identity_no, first_name, last_name, working_hours, date_join, status) VALUES
('82ad7f7c-bd62-4921-ad59-e43e7c8f753b', 'Ty', 'Ayelloribbin', '08:00:00', '2019-12-05', 1),
('2ddf4395-81ba-4a77-8cf7-3a8b432fdb94', 'Hugo', 'First', '08:00:00', '2019-12-05', 1),
('c3f57bdd-aef2-4750-ba93-e533a1db8b49', 'Percy', 'Vere', '08:00:00', '2019-12-05', 1),
('eba83c5c-f6e0-4ab4-ac59-dcccae769f30', 'Jack', 'Aranda', '08:00:00', '2019-12-05', 1),
('ced75f9d-9dc8-42d4-b7c2-cc0eb8f5fc32', 'Olive', 'Tree', '08:00:00', '2019-12-05', 1),
('85143a0d-1750-4661-a374-bd24a89de2b9', 'John', 'Quil', '09:00:00', '2019-12-05', 1),
('fe2e13e7-b06e-41ae-b825-c1c0ea626c75', 'Anne', 'Thurium', '09:00:00', '2019-12-05', 1),
('b9afc251-3309-463a-9940-d615f567df53', 'Cherry', 'Blossom', '09:00:00', '2019-12-05', 1),
('f8aa303e-7909-4c34-8b1f-ffaf800fd6f1', 'Ginger', 'Plant', '09:00:00', '2019-12-05', 1),
('b458880f-45bd-444b-b83f-d6a4523ea05c', 'Perry', 'Scope', '09:00:00', '2019-12-05', 1),
('e4908fe0-21c7-4d65-8459-65ce04d84f48', 'Frank', 'Stein', '10:00:00', '2019-12-05', 1),
('d42f1677-c010-4788-8287-f22738004279', 'Pat', 'Thettick', '10:00:00', '2019-12-05', 1),
('65340f67-3475-4bc8-a248-0c9108881274', 'Percy', 'Kewshun', '10:00:00', '2019-12-05', 1),
('e0b2738d-ba56-4f6b-9c98-53a4ab286a22', 'Rod', 'Knee', '10:00:00', '2019-12-05', 1),
('4ad2376a-fefc-4907-ab22-550de151c657', 'Karen', 'Onnabit', '10:00:00', '2019-12-05', 1),
('7de6abad-1c90-41e3-ab54-54c072f95a6f', 'Col', 'Fays', '09:30:00', '2019-12-05', 1),
('c8eacca5-b3fb-4cab-b2f5-ede4688a3b10', 'Fay', 'Daway', '09:30:00', '2019-12-05', 1),
('00b409cf-56e1-499e-9b52-2c13d5c81be4', 'Colin', 'Sik', '09:30:00', '2019-12-05', 1),
('59c3328f-d35c-4410-990e-8f4e8ee76371', 'Greg', 'Arias', '09:30:00', '2019-12-05', 1),
('a6870f1b-93ef-4ae5-aa7f-386a0962cd23', 'Toi', 'Story', '09:30:00', '2019-12-05', 1);

INSERT INTO assigner (identity_no, working_hours, dep_id, emp_id) VALUES
('5c8a42e6-c51b-44bb-8144-dd7da7386750', '08:00:00', 1, 1),
('da3d0a69-590c-4912-8e4d-81597efbba9e', '05:00:00', 2, 3),
('d963e9d1-db0c-42f4-bd10-9e46577400ae', '03:00:00', 3, 3),
('b9056273-7310-4734-ab07-227dc08d3a0a', '10:00:00', 5, 6);

INSERT INTO assigner_audit_trail (working_hours, history_change_reason, history_type, history_date, assigner_id, dep_id, emp_id) VALUES
('08:00:00', 'Inserted Record', 'CREATED', '2019-12-05 16:32:43.971365', 1, 1, 1),
('09:00:00', 'Inserted Record', 'CREATED', '2019-12-05 16:32:57.833968', 2, 1, 2),
('10:00:00', 'Inserted Record', 'CREATED', '2019-12-05 16:35:46.933780', 3, 1, 3),
('05:00:00', 'Inserted Record', 'CREATED', '2019-12-05 17:07:32.116353', 4, 2, 3),
('03:00:00', 'Inserted Record', 'CREATED', '2019-12-05 17:07:46.970917', 5, 3, 3),
('10:00:00', 'Inserted Record', 'CREATED', '2019-12-07 05:38:37.223594', 6, 5, 6);
