
CREATE TABLE IF NOT EXISTS  student (
                           student_id bigint NOT NULL PRIMARY KEY,
                           email varchar(255) DEFAULT NULL,
                           last_name varchar(30) NOT NULL,
                           name varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS  teacher (
                           teacher_id bigint NOT NULL PRIMARY KEY ,
                           email varchar(255) DEFAULT NULL,
                           last_name varchar(30) NOT NULL,
                           name varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS grade (
                         grade_id bigint NOT NULL PRIMARY KEY ,
                         grade_value double NOT NULL,
                         student_id bigint DEFAULT NULL,
                         teacher_id bigint DEFAULT NULL,
                          FOREIGN KEY (student_id) REFERENCES student (student_id),
                          FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
