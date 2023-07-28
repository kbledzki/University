DROP TABLE IF EXISTS student;
CREATE TABLE IF NOT EXISTS  student (
                           student_id bigint NOT NULL AUTO_INCREMENT,
                           email varchar(255) DEFAULT NULL,
                           last_name varchar(30) NOT NULL,
                           name varchar(30) NOT NULL,
                           PRIMARY KEY (student_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS teacher;
CREATE TABLE IF NOT EXISTS  teacher (
                           teacher_id bigint NOT NULL AUTO_INCREMENT,
                           email varchar(255) DEFAULT NULL,
                           last_name varchar(30) NOT NULL,
                           name varchar(30) NOT NULL,
                           PRIMARY KEY (teacher_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS grade;
CREATE TABLE IF NOT EXISTS grade (
                         grade_id bigint NOT NULL AUTO_INCREMENT,
                         grade_value double NOT NULL,
                         student_id bigint DEFAULT NULL,
                         teacher_id bigint DEFAULT NULL,
                         PRIMARY KEY (grade_id),
                         KEY `FK5secqnjjwgh9wxk4h1xwgj1n0` (`student_id`),
                         KEY `FKqb6g3kbua30kxih2f8kfx2giu` (`teacher_id`),
                         CONSTRAINT `FK5secqnjjwgh9wxk4h1xwgj1n0` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
                         CONSTRAINT `FKqb6g3kbua30kxih2f8kfx2giu` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
