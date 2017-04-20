INSERT INTO `school` (`school_id`, `school_address`, `school_name`, `school_phone`) VALUES
(1, 'Rugjer Boshkovikj, Skopje 1000, Macedonia', 'FINKI', '+389 2 309 9191'),
(2, 'Rugjer Boshkovikj, Skopje 1000, Macedonia', 'FEIT', '+389 2 308 8292');
INSERT INTO `student` (`student_id`, `student_city`, `student_date_of_birth`, `student_firstname`, `student_gender`, `student_index`, `student_lastname`, `student_phone`, `school_id`) VALUES
(1, 'Kocani', '1994-06-05 00:00:00', 'Aleksandra', 'female', '135002', 'Petkova', '+398 78 292 616', 1),
(2, 'Kocani', '1995-01-19 00:00:00', 'Ivan', 'male', '135016', 'Pavlov', '+398 78 263 852', 1),
(3, 'Negotino', '1994-06-01 00:00:00', 'Gjurgjica', 'female', '124002', 'Minova', '+398 78 292 292', 1),
(4, 'Kocani', '1994-04-26 00:00:00', 'Emilija', 'female', '1111', 'Nacova', '+398 78 123 456', 2),
(5, 'Kocani', '1994-06-12 00:00:00', 'Hristina', 'female', '2222', 'Jordanova', '+398 78 456 123', 2);
INSERT INTO `subject` (`subject_id`, `subject_credits`, `subject_name`, `subject_status`) VALUES
(1, 6, 'Strikturirano programiranje', 'Zadolzitelen'),
(2, 6, 'Objektno-Orientirano programiranje', 'Zadolzitelen'),
(3, 6, 'Bazi na podatoci', 'Zadolzitelen'),
(4, 6, 'Napredni bazi na podatoci', 'Izboren'),
(5, 6, 'Polustrukturirani bazi na podatoci i XML', 'Izboren'),
(6, 6, 'Veb bazirnai sistemi', 'Izboren'),
(7, 6, 'Diskrentna matematika 1', 'Zadolzitelen'),
(8, 6, 'Diskrenta matematika 2', 'Zadolzitelen'),
(9, 6, 'Algoritmi i podatocni strukturi', 'Zadolzitelen'),
(10, 6, 'Operativni sistemi', 'Zadolzitelen');
INSERT INTO `subject_student` (`subject_id`, `student_id`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 4),
(9, 5),
(10, 5);
