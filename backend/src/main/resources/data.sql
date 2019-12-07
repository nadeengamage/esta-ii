INSERT INTO department (name, working_days_per_week, working_hours_per_day, status) VALUES
('HR', 5 ,'08:00:00', 1),
('IT', 5 ,'08:00:00', 1),
('Finance', 6 ,'09:00:00', 1),
('Support', 7 ,'08:00:00', 1),
('Delivery', 7 ,'10:00:00', 1);


INSERT INTO employee (first_name, last_name, working_hours, date_join, status) VALUES 
('Ty', 'Ayelloribbin', '08:00:00', '2019-12-05', 1),
('Hugo', 'First', '08:00:00', '2019-12-05', 1),
('Percy', 'Vere', '08:00:00', '2019-12-05', 1),
('Jack', 'Aranda', '08:00:00', '2019-12-05', 1),
('Olive', 'Tree', '08:00:00', '2019-12-05', 1),
('John', 'Quil', '09:00:00', '2019-12-05', 1),
('Anne', 'Thurium', '09:00:00', '2019-12-05', 1),
('Cherry', 'Blossom', '09:00:00', '2019-12-05', 1),
('Ginger', 'Plant', '09:00:00', '2019-12-05', 1),
('Perry', 'Scope', '09:00:00', '2019-12-05', 1),
('Frank', 'Stein', '10:00:00', '2019-12-05', 1),
('Pat', 'Thettick', '10:00:00', '2019-12-05', 1),
('Percy', 'Kewshun', '10:00:00', '2019-12-05', 1),
('Rod', 'Knee', '10:00:00', '2019-12-05', 1),
('Karen', 'Onnabit', '10:00:00', '2019-12-05', 1),
('Col', 'Fays', '09:30:00', '2019-12-05', 1),
('Fay', 'Daway', '09:30:00', '2019-12-05', 1),
('Colin', 'Sik', '09:30:00', '2019-12-05', 1),
('Greg', 'Arias', '09:30:00', '2019-12-05', 1),
('Toi', 'Story', '09:30:00', '2019-12-05', 1);

INSERT INTO assigner (working_hours, dep_id, emp_id) VALUES
('08:00:00', 1, 1),
('05:00:00', 2, 3),
('03:00:00', 3, 3),
('10:00:00', 5, 6);

INSERT INTO assigner_audit_trail (working_hours, history_change_reason, history_type, history_date, assigner_id, dep_id, emp_id) VALUES
('08:00:00', 'Inserted Record', 'CREATED', '2019-12-05 16:32:43.971365', 1, 1, 1),
('09:00:00', 'Inserted Record', 'CREATED', '2019-12-05 16:32:57.833968', 2, 1, 2),
('10:00:00', 'Inserted Record', 'CREATED', '2019-12-05 16:35:46.933780', 3, 1, 3),
('05:00:00', 'Inserted Record', 'CREATED', '2019-12-05 17:07:32.116353', 4, 2, 3),
('03:00:00', 'Inserted Record', 'CREATED', '2019-12-05 17:07:46.970917', 5, 3, 3),
('10:00:00', 'Inserted Record', 'CREATED', '2019-12-07 05:38:37.223594', 6, 5, 6);
