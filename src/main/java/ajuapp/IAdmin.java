package ajuapp;

import ajuapp.database.DBUtils;

import static ajuapp.Admin.admins;
import static ajuapp.Student.students;
import static ajuapp.Professor.professors;
import static ajuapp.utils.ProjectConstants.*;

@Author
public interface IAdmin {

    default void printAdminsList() {
        admins = DBUtils.getTableAdminData();
        if (admins.size() > 0) {
            System.out.println(admins);
        } else {
            System.out.println(NO_ADMINS);
        }
    }

    default void printLastAdmin() {
        admins = DBUtils.getTableAdminData();
        if (admins.size() > 0) {
            System.out.println(admins.get(admins.size() - 1));
        } else {
            System.out.println(NO_ADMINS);
        }
    }

    default void printStudentsList() {
        students = DBUtils.getTableStudentData();
        if (students.size() > 0) {
            System.out.println(students);
        } else {
            System.out.println(NO_STUDENTS);
        }
    }

    default void printLastStudent() {
        students = DBUtils.getTableStudentData();
        if (students.size() > 0) {
            System.out.println(students.get(students.size() - 1));
        } else {
            System.out.println(NO_STUDENTS);
        }
    }

    default void printLastProfessor() {
        professors = DBUtils.getTableProfessorData();
        if (professors.size() > 0) {
            System.out.println(professors.get(professors.size() - 1));
        } else {
            System.out.println(NO_PROFESSORS);
        }
    }

    default void printProfessorsList() {
        professors = DBUtils.getTableProfessorData();
        if (professors.size() > 0) {
            System.out.println(professors);
        } else {
            System.out.println(NO_PROFESSORS);
        }
    }
}
