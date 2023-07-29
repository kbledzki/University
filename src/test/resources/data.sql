INSERT INTO student (student_id, email, last_name,name) VALUES (134, 'email1@test.com', 'Lastnametest1','Nametest1');
INSERT INTO student (student_id, email, last_name,name) VALUES (222, 'email2@test.com', 'Lastnametest2','Nametest2');
INSERT INTO student (student_id, email, last_name,name) VALUES (333, 'email3@test.com', 'Lastnametest3','Nametest3');
INSERT INTO student (student_id, email, last_name,name) VALUES (445, 'email4@test.com', 'Lastnametest4','Nametest4');

INSERT INTO teacher (teacher_id, email, last_name,name) VALUES (111, 'Teacheremail1@test.com', 'TeacherLastnametest1','TeacherNametest1');
INSERT INTO teacher (teacher_id, email, last_name,name) VALUES (224, 'Teacheremail2@test.com', 'TeacherLastnametest2','TeacherNametest2');
INSERT INTO teacher (teacher_id, email, last_name,name) VALUES (353, 'Teacheremail3@test.com', 'TeacherLastnametest3','TeacherNametest3');
INSERT INTO teacher (teacher_id, email, last_name,name) VALUES (436, 'Teacheremail4@test.com', 'TeacherLastnametest4','TeacherNametest4');

INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (156, 4, 134,111);
INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (274, 5, 134,224);
INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (377, 5, 134,224);
INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (488, 3, 134,353);
INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (547, 4, 222,436);
INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (698, 5, 222,353);
INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (715, 3, 333,111);
INSERT INTO grade (grade_id, grade_value, student_id,teacher_id) VALUES (887, 4, 333,111);