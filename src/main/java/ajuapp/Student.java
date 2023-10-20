package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Table;

import java.util.ArrayList;
import java.util.List;

import static ajuapp.utils.ProjectConstants.*;

@Author
public final class Student extends Academic<Student> implements IAcademic, IExit, IScanInput {
    private int tblStudentId;
    private int tblStudentPersonId;
    private int tblStudentAcademicId;
    private String roleId = "S";
    public static List<Student> students = new ArrayList<>();
    private static Student currentStudent = null;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        this.tblStudentId = DBUtils.getLastId(Table.NAME.TBL_STUDENT) + 1;
        this.tblStudentPersonId = getTblPersonId();
        this.tblStudentAcademicId = getTblAcademicId();
        this.roleId = roleId + 2000 + tblStudentId;
    }

    public Student(int tblPersonId, String firstName, String lastName, String userName, String password,
                   int tblAcademicId, int course1, int course2, int course3, int course4, int course5, int course6,
                   int tblStudentId, int tblStudentPersonId, int tblStudentAcademicId, String roleId) {
        super(tblPersonId, firstName, lastName, userName, password, tblAcademicId, course1, course2, course3, course4, course5, course6);
        this.tblStudentId = tblStudentId;
        this.tblStudentPersonId = tblStudentPersonId;
        this.tblStudentAcademicId = tblStudentAcademicId;
        this.roleId = roleId;
    }

    public int getTblStudentId() {
        return tblStudentId;
    }

    public int getTblStudentPersonId() {
        return tblStudentPersonId;
    }

    public int getTblStudentAcademicId() {
        return tblStudentAcademicId;
    }

    public String getRoleId() {
        return roleId;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student student) {
        currentStudent = student;
    }

    @Override
    public String toString() {
        return "\nStudent {\n" +
                "tblStudentId = " + getTblStudentId() + ",\n" +
                "tblPersonId = " + getTblPersonId() + ",\n" +
                "tblStudentPersonId = " + getTblStudentPersonId() + ",\n" +
                "tblAcademicId = " + getTblAcademicId() + ",\n" +
                "tblStudentAcademicId = " + getTblStudentAcademicId() + ",\n" +
                "roleId = " + getRoleId() + ",\n" +
                "firstName = '" + getFirstName() + "',\n" +
                "lastName = '" + getLastName() + "',\n" +
                "username = '" + getUserName() + "',\n" +
                "password = '" + getPassword() + "',\n" +
                "Student enrolled to courses: \n" +
                "course1 = '" + getAvailableCourseName(getCourse1()) + "',\n" +
                "course2 = '" + getAvailableCourseName(getCourse2()) + "',\n" +
                "course3 = '" + getAvailableCourseName(getCourse3()) + "',\n" +
                "course4 = '" + getAvailableCourseName(getCourse4()) + "',\n" +
                "course5 = '" + getAvailableCourseName(getCourse5()) + "',\n" +
                "course6 = '" + getAvailableCourseName(getCourse6()) + "',\n" +
                "},\n";
    }

    @Override
    public int getAcademicId() {
        return tblStudentAcademicId;
    }

    @Override
    public char getRole() {
        return roleId.charAt(0);
    }

    @Override
    public Student getTableData(int id) {
        students = DBUtils.getTableStudentData();
        for (Student student : students) {
            if (student.getTblStudentId() == id) {
                return student;
            }
        }
        return null;
    }

    public void runStudent() {
        if (getCurrentStudent() != null) {
            printHeader(RUNNING_STUDENT
                    + getCurrentStudent().getFirstName() + " "
                    + getCurrentStudent().getLastName() + ",  "
                    + getCurrentStudent().getRoleId()
            );

            String input = scanRunStudentChoice();
            switch (input) {
                case "Q", "q" -> {
                    getCurrentStudent().setCurrentUserNull();
                    new SignUp().runAJUApp();
                }
                case "1" -> {
                    printSchedule(getCurrentStudent());
                    runStudent();
                }
                case "2" -> {
                    printAvailableCoursesList();
                    runStudent();
                }
                case "3" -> {
                    printSchedule(getCurrentStudent());
                    printBalance(getCurrentStudent(), getCurrentStudent());
                    runStudent();
                }
            }
        }
    }

    @Override
    public void runIfQ(String input) {
        if (getCurrentStudent() != null) {
            runStudent();
        }
    }
}
