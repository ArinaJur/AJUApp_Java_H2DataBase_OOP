package ajuapp.database;

import ajuapp.Author;

@Author
public final class Table {
    public enum TBL_PERSON {PERSON_ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD}
    public enum TBL_ADMIN {ADMIN_ID, PERSON_ID, ROLE_ID}
    public enum TBL_STUDENT {STUDENT_ID, PERSON_ID, ACADEMIC_ID, ROLE_ID}
    public enum TBL_PROFESSOR {PROFESSOR_ID, PERSON_ID, ACADEMIC_ID, ROLE_ID}
    public enum TBL_ACADEMIC {ACADEMIC_ID, COURSE1_ID, COURSE2_ID, COURSE3_ID, COURSE4_ID, COURSE5_ID, COURSE6_ID}
    public enum TBL_COURSE {COURSE_ID, COURSE_NAME, PRICE}
    public enum NAME {TBL_PERSON, TBL_ADMIN, TBL_STUDENT, TBL_PROFESSOR, TBL_ACADEMIC, TBL_COURSE}

    public static String getId(Table.NAME name) {
        final String  tableName = name.toString();
        String id = "";
        switch (tableName) {
            case "TBL_PERSON" -> id = TBL_PERSON.PERSON_ID.toString();
            case "TBL_ADMIN" -> id = TBL_ADMIN.ADMIN_ID.toString();
            case "TBL_STUDENT" -> id = TBL_STUDENT.STUDENT_ID.toString();
            case "TBL_PROFESSOR" -> id = TBL_PROFESSOR.PROFESSOR_ID.toString();
            case "TBL_ACADEMIC" -> id = TBL_ACADEMIC.ACADEMIC_ID.toString();
            case "TBL_COURSE" -> id = TBL_COURSE.COURSE_ID.toString();
        }
        return id;
    }

    public static String person_id() {return TBL_PERSON.PERSON_ID.toString();}
    public static String firstName() {return TBL_PERSON.FIRSTNAME.toString();}
    public static String lastName() {return TBL_PERSON.LASTNAME.toString();}
    public static String username() {return TBL_PERSON.USERNAME.toString();}
    public static String password() {return TBL_PERSON.PASSWORD.toString();}

    public static String admin_id() {return TBL_ADMIN.ADMIN_ID.toString();}
    public static String admin_person_id() {return TBL_ADMIN.PERSON_ID.toString();}
    public static String admin_role_id() {return TBL_ADMIN.ROLE_ID.toString();}

    public static String student_id() {return TBL_STUDENT.STUDENT_ID.toString();}
    public static String student_person_id() {return TBL_STUDENT.PERSON_ID.toString();}
    public static String student_academic_id() {return TBL_STUDENT.ACADEMIC_ID.toString();}
    public static String student_role_id() {return TBL_STUDENT.ROLE_ID.toString();}

    public static String professor_id() {return TBL_PROFESSOR.PROFESSOR_ID.toString();}
    public static String professor_person_id() {return TBL_PROFESSOR.PERSON_ID.toString();}
    public static String professor_academic_id() {return TBL_PROFESSOR.ACADEMIC_ID.toString();}
    public static String professor_role_id() {return TBL_PROFESSOR.ROLE_ID.toString();}

    public static String academic_id() {return TBL_ACADEMIC.ACADEMIC_ID.toString();}
    public static String course1_id() {return TBL_ACADEMIC.COURSE1_ID.toString();}
    public static String course2_id() {return TBL_ACADEMIC.COURSE2_ID.toString();}
    public static String course3_id() {return TBL_ACADEMIC.COURSE3_ID.toString();}
    public static String course4_id() {return TBL_ACADEMIC.COURSE4_ID.toString();}
    public static String course5_id() {return TBL_ACADEMIC.COURSE5_ID.toString();}
    public static String course6_id() {return TBL_ACADEMIC.COURSE6_ID.toString();}

    public static String course_id() {return TBL_COURSE.COURSE_ID.toString();}
    public static String course_name() {return TBL_COURSE.COURSE_NAME.toString();}
    public static String price() {return TBL_COURSE.PRICE.toString();}
}


