package ajuapp.database;

import ajuapp.Author;

@Author
public class DBConstants {
    public static final String SELECT_ALL_FROM = "SELECT * FROM ";
    public static final String SELECT_MAX_ID = "SELECT max(id) FROM ";
    public static final String DROP_TBL = "DROP TABLE IF EXISTS ";
    public static final String WHERE_ID = " WHERE id = ";

    public static final String INSERT_PERSON =
            "INSERT INTO tbl_person(person_id, firstName, lastName, userName, password) VALUES (?, ?, ?, ?, ?);";
    public static final String INSERT_ADMIN =
            "INSERT INTO tbl_admin(admin_id, person_id, role_id) VALUES (?, ?, ?);";
    public static final String INSERT_STUDENT =
            "INSERT INTO tbl_student(student_id, person_id, academic_id, role_id) VALUES (?, ?, ?, ?);";
    public static final String INSERT_ACADEMIC_EMPTY_ENROLL =
            "INSERT INTO tbl_academic(academic_id) VALUES (?);";
    public static final String INSERT_COURSE =
            "INSERT INTO tbl_course(course_id, course_name, price) VALUES (?, ?, ?);";
}
