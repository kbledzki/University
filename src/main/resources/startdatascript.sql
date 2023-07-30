USE university;

INSERT INTO student (student_id, email, last_name, name)
VALUES (1, 'email1@test.com', 'Lastnametest1', 'Nametest1'),
       (2, 'email2@test.com', 'Lastnametest2', 'Nametest2'),
       (3, 'email3@test.com', 'Lastnametest3', 'Nametest3'),
       (4, 'email4@test.com', 'Lastnametest4', 'Nametest4');

INSERT INTO teacher (teacher_id, email, last_name, name)
VALUES (1, 'Teacheremail1@test.com', 'TeacherLastnametest1', 'TeacherNametest1'),
       (2, 'Teacheremail2@test.com', 'TeacherLastnametest2', 'TeacherNametest2'),
       (3, 'Teacheremail3@test.com', 'TeacherLastnametest3', 'TeacherNametest3'),
       (4, 'Teacheremail4@test.com', 'TeacherLastnametest4', 'TeacherNametest4');

INSERT INTO grade (grade_id, grade_value, student_id, teacher_id)
VALUES (1, 4, 1, 1),
       (2, 5, 1, 2),
       (3, 5, 1, 2),
       (4, 3, 1, 3),
       (5, 4, 2, 4),
       (6, 5, 2, 3),
       (7, 3, 3, 1),
       (8, 4, 3, 1);