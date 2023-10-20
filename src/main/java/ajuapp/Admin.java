package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Table;

import java.util.ArrayList;
import java.util.List;

import static ajuapp.Course.courses;
import static ajuapp.Student.students;
import static ajuapp.utils.ProjectConstants.*;

@Author
public final class Admin extends Person<Admin> implements IAdmin, IExit, IAcademic, IScanInput {
    private int tblAdminId;
    private int tblAdminPersonId;
    private String roleId = "A";
    public static List<Admin> admins = new ArrayList<>();
    private static Admin currentAdmin;

    Admin() {
    }

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
        final int lastTblAdminId = DBUtils.getLastId(Table.NAME.TBL_ADMIN);
        this.tblAdminId = lastTblAdminId + 1;
        this.tblAdminPersonId = getTblPersonId();
        this.roleId = roleId + 10000 + tblAdminId;
    }

    public Admin(
            int tblPersonId, String firstName, String lastName, String userName, String password,
            int tblAdminId, int tblAdminPersonId, String roleId
    ) {
        super(tblPersonId, firstName, lastName, userName, password);
        this.tblAdminId = tblAdminId;
        this.tblAdminPersonId = tblAdminPersonId;
        this.roleId = roleId;
    }

    public Admin(int tblPersonId, String firstName, String lastName, String userName, String password) {
        super(tblPersonId, firstName, lastName, userName, password);
    }

    public int getTblAdminId() {
        return tblAdminId;
    }

    public int getTblAdminPersonId() {
        return tblAdminPersonId;
    }

    public String getRoleId() {
        return roleId;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(Admin admin) {
        currentAdmin = admin;
    }

    private Student getLastStudent() {
        return students.get(students.size() - 1);
    }

    private Professor getLastProfessor() {
        return new Professor();
    }

    @Override
    public String toString() {
        return "\nAdmin {\n" +
                "tblAdminId = " + getTblAdminId() + ",\n" +
                "tblPersonId = " + getTblPersonId() + ",\n" +
                "tblAdminPersonId = " + getTblAdminPersonId() + ",\n" +
                "roleId = " + getRoleId() + ",\n" +
                "firstName = '" + getFirstName() + "',\n" +
                "lastName = '" + getLastName() + "',\n" +
                "username = '" + getUserName() + "',\n" +
                "password = '" + getPassword() + "',\n" +
                "}";
    }

    @Override
    public char getRole() {
        return roleId.charAt(0);
    }

    @Override
    public Admin getTableData(int id) {
        if (currentAdmin != null) {
            admins = DBUtils.getTableAdminData();
            for (Admin admin : admins) {
                if (admin.getTblAdminId() == id) {
                    return admin;
                }
            }
        }
        return null;
    }

    public static void addFirstAdmin() {
        if (new Admin().getCurrentAdmin() == null) {
            Admin admin = new Admin(FIRST_NAME, LAST_NAME);
            DBUtils.insertAdmin(admin);
            admins = DBUtils.getTableAdminData();
        }
    }

    private void addAdmin(String firstName, String lastName) {
        if (getCurrentAdmin() != null) {
            Admin admin = new Admin(firstName, lastName);
            DBUtils.insertAdmin(admin);
            admins = DBUtils.getTableAdminData();
        }
    }

    private void addAdmin(Admin admin) {
        if (admin.getCurrentAdmin() != null) {
            DBUtils.insertAdmin(admin);
            admins = DBUtils.getTableAdminData();
        }
    }

    private void addStudent(String firstName, String lastName) {
        if (currentAdmin != null) {
            Student student = new Student(firstName, lastName);
            DBUtils.insertStudent(student);
            students = DBUtils.getTableStudentData();
        }
    }

    private void addStudent(Student student) {
        if (currentAdmin != null) {
            DBUtils.insertStudent(student);
            students = DBUtils.getTableStudentData();
        }
    }

    //TODO Add Professor Functionality
    private void addProfessor(String firstName, String lastName) {
        if (currentAdmin != null) {
            System.out.println("TO DO: addProfessor(String firstName, String lastName)");
        }
    }

    private void addCourse(Course course) {
        if (currentAdmin != null) {
            DBUtils.insertCourse(course);
            courses = DBUtils.getTableCourseData();
        }
    }

    public void runAdmin() {
        if (currentAdmin != null) {
            printHeader(RUNNING_SYSTEM_ADMINISTRATION);

            String input = scanRunAdminChoice();

            switch (input) {
                case "Q", "q" -> {
                    new Admin().setCurrentUserNull();
                    new SignUp().runAJUApp();
                }
                case "1" -> runRegistration();
                case "2" -> runAddCourse();
                case "3" -> runRegisterForCourse();
                case "4" -> runPrintInformation();
            }
        }
    }

    @Override
    public void runIfQ(String input) {
        if (currentAdmin != null && input.equalsIgnoreCase("Q")) {
            runAdmin();
        }
    }

    private void runRegistration() {
        if (currentAdmin != null) {
            printHeader(RUNNING_REGISTRATION);

            String input = scanRegistrationChoice();

            switch (input) {
                case "Q", "q" -> runAdmin();
                case "1" -> runRegisterNewStudent();
                case "2" -> runRegisterNewProfessor();
                case "3" -> runRegisterNewAdmin();
            }
        }
    }

    private void runAddCourse() {
        if (currentAdmin != null) {
            printAvailableCoursesList();
            printHeader(ADD_NEW_COURSE);

            final String courseName = scanCourseName();
            final int price = Integer.parseInt(scanCoursePrice());

            addCourse(new Course(courseName, price));

            String input = scanAddAnotherCourse();
            switch (input) {
                case "Q", "q", "2" -> runAdmin();
                case "1" -> runAddCourse();
            }
        }
    }

    private void runPrintInformation() {
        if (currentAdmin != null) {
            printHeader(RUNNING_PRINT_INFORMATION);

            String input = scanPrintInformationChoice();
            switch (input) {
                case "Q", "q" -> runIfQ(input);
                case "1" -> runPrintAdminsList();
                case "2" -> runPrintStudentsList();
                case "3" -> runPrintProfessorsList();
                case "4" -> runPrintCoursesList();
                case "5" -> runPrintBalanceForStudent();
            }
        }
    }

    private void runPrintAdminsList() {
        if (currentAdmin != null) {
            printHeader(ADMINS_LIST);
            printAdminsList();
            runAdmin();
        }
    }

    private void runPrintStudentsList() {
        if (currentAdmin != null) {
            printHeader(STUDENTS_LIST);
            printStudentsList();
            runAdmin();
        }
    }

    private void runPrintProfessorsList() {
        if (currentAdmin != null) {
            printHeader(PROFESSORS_LIST);
            printProfessorsList();
            runAdmin();
        }
    }

    private void runPrintCoursesList() {
        if (currentAdmin != null) {
            printHeader(COURSES_LIST);
            printAvailableCoursesList();
            runAdmin();
        }
    }

    private void runRegisterNewStudent() {
        if (currentAdmin != null) {
            printHeader(REGISTER_NEW_STUDENT);

            final String firstName = scanFirsName();
            final String lastName = scanLastName();

            addStudent(firstName, lastName);

            printHeader(NEW_STUDENT_REGISTERED);
            printLastStudent();
            getCourseRegistrationChoice(getLastStudent());
        }
    }

    private void runRegisterNewProfessor() {
        if (currentAdmin != null) {
            printHeader(REGISTER_NEW_PROFESSOR);

            final String firstName = scanFirsName();
            final String lastName = scanLastName();

            addProfessor(firstName, lastName);

            printHeader(NEW_PROFESSOR_REGISTERED);
            printLastProfessor();
            getCourseRegistrationChoice(getLastProfessor());
        }
    }

    private void runRegisterNewAdmin() {
        if (currentAdmin != null) {
            printHeader(REGISTER_NEW_ADMIN);

            final String firstName = scanFirsName();
            final String lastName = scanLastName();

            addAdmin(firstName, lastName);

            printHeader(NEW_ADMIN_REGISTERED);
            printLastAdmin();
            runRegistration();
        }
    }

    private <T> void getCourseRegistrationChoice(Academic<T> person) {
        if (currentAdmin != null) {
            String input = scanCourseRegistrationChoice(person);
            switch (input) {
                case "Q", "q", "2" -> runRegistration();
                case "1" -> runCourseRegistration(person);
            }
        }
    }

    private void runRegisterForCourse() {
        if (currentAdmin != null) {
            printHeader(REGISTER_FOR_COURSES);

            String input = scanPersonChoice();
            switch (input) {
                case "Q", "q" -> runIfQ(input);
                case "1" -> runStudentCourseRegistration();
                case "2" -> runProfessorCourseAssignment();
            }
        }
    }

    private void runStudentCourseRegistration() {
        if (currentAdmin != null) {
            System.out.println("\n\n\n ");

            String input = scanPrintListChoice();
            switch (input) {
                case "Q", "q" -> runRegisterForCourse();
                case "1" -> {
                    printStudentsList();
                    runConfirmStudent(true);
                }
                case "2" -> runConfirmStudent(true);
            }
        }
    }

    private void runConfirmStudent(boolean isRegistration) {
        if (currentAdmin != null) {
            printHeader(FIND_STUDENT);
            System.out.println();
            int id = scanStudentId();

            Student foundStudent = new Student().getTableData(id);
            System.out.println(foundStudent);
            System.out.println();

            String input = scanStudentConfirmation();
            switch (input) {
                case "Q", "q" -> {
                    if (isRegistration) {
                        runRegisterForCourse();
                    } else {
                        runPrintBalanceForStudent();
                    }
                }
                case "1" -> {
                    assert foundStudent != null;
                    if (isRegistration) {
                        runCourseRegistration(foundStudent);
                    } else {
                        printSchedule(foundStudent);
                        printBalance(foundStudent, getCurrentAdmin());
                        runPrintInformation();
                    }
                }
                case "2" -> {
                    System.out.println("\n");
                    printStudentsList();
                    runConfirmStudent(true);
                }
            }
        }
    }

    //TODO Add Professor Functionality
    private void runProfessorCourseAssignment() {
        if (currentAdmin != null) {
            System.out.println("TO DO: runProfessorCourseAssignment()");
        }
    }

    private <T> void runCourseRegistration(Academic<T> person) {
        if (currentAdmin != null) {
            int count = person.getRole() == 'S' ? 6 : 3;

            List<Integer> coursesIds = new ArrayList<>();

            printAvailableCoursesList();
            printQForExit();
            do {
                String input = scanCourseId();
                if (input.equalsIgnoreCase("Q") && coursesIds.size() == 0) {
                    runAdmin();
                } else if (input.equalsIgnoreCase("Q") && coursesIds.size() != 0) {
                    updatePersonCourses(person, coursesIds);
                    count = 0;
                    runRegistration();
                } else {
                    courses = DBUtils.getTableCourseData();
                    for (Course course : courses) {
                        if (Integer.parseInt(input) == course.getTblCourseId()) {
                            coursesIds.add(course.getTblCourseId());
                            count--;
                        }
                    }
                }

            } while (count != 0);
            updatePersonCourses(person, coursesIds);
            runRegistration();
        }
    }

    public <T> void updatePersonCourses(Academic<T> person, List<Integer> coursesIds) {
        if (currentAdmin != null) {
            final int[] dbIds = {person.getCourse1(), person.getCourse2(), person.getCourse3(),
                    person.getCourse4(), person.getCourse5(), person.getCourse6()};

            for (int courseId : coursesIds) {
                for (int i = 0; i <= dbIds.length - 1; i++) {
                    if (dbIds[i] == 0) {
                        dbIds[i] = courseId;
                        switch (i) {
                            case 0 -> person.setCourse1(courseId);
                            case 1 -> person.setCourse2(courseId);
                            case 2 -> person.setCourse3(courseId);
                            case 3 -> person.setCourse4(courseId);
                            case 4 -> person.setCourse5(courseId);
                            case 5 -> person.setCourse6(courseId);
                        }
                        break;
                    }
                }
            }
            DBUtils.updateAcademicEnroll(person);
            Student.students = DBUtils.getTableStudentData();
            Professor.professors = DBUtils.getTableProfessorData();
        }
    }

    private void runPrintBalanceForStudent() {
        String input = scanPrintListChoice();
        switch (input) {
            case "Q", "q" -> runPrintInformation();
            case "1" -> {
                printStudentsList();
                runConfirmStudent(false);
            }
            case "2" -> runConfirmStudent(false);
        }

    };
}
