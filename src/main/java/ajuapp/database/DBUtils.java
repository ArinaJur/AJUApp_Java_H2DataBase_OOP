package ajuapp.database;

import ajuapp.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ajuapp.database.DBConstants.*;
import static ajuapp.database.Table.*;
import static ajuapp.database.Table.NAME.*;

@Author
public final class DBUtils {

    public static void dropTable(Table.NAME tableName) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(DROP_TBL + tableName)
        ) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLastId(Table.NAME tableName) {
        final String tableId = Table.getId(tableName);
        final String selectLastId = SELECT_MAX_ID.replace("id", tableId) + tableName;
        int lastId = 0;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectLastId)
        ) {
            ResultSet resultQuery = statement.executeQuery();
            while (resultQuery.next()) {
                lastId = resultQuery.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastId;
    }

    public static List<Admin> getTablePersonData() {
        List<Admin> dbUsers = new ArrayList<>();
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM + TBL_PERSON)
        ) {
            ResultSet resultPerson = statement.executeQuery();
            while (resultPerson.next()) {
                dbUsers.add(new Admin(
                        resultPerson.getInt(person_id()),
                        resultPerson.getString(firstName()),
                        resultPerson.getString(lastName()),
                        resultPerson.getString(username()),
                        resultPerson.getString(password()))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbUsers;
    }

    public static List<Admin> getTableAdminData() {
        List<Admin> dbAdmins = new ArrayList<>();
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementPerson = connection.prepareStatement(SELECT_ALL_FROM + TBL_PERSON);
             PreparedStatement statementAdmin = connection.prepareStatement(SELECT_ALL_FROM + TBL_ADMIN)
        ) {
            ResultSet resultPerson = statementPerson.executeQuery();
            ResultSet resultAdmin = statementAdmin.executeQuery();
            while (resultAdmin.next()) {
                final int tblAdminPersonId = resultAdmin.getInt(admin_person_id());
                while (resultPerson.next()) {
                    final int tblPersonId = resultPerson.getInt(person_id());

                    if (tblAdminPersonId == tblPersonId) {
                        dbAdmins.add(new Admin(
                                tblPersonId,
                                resultPerson.getString(firstName()),
                                resultPerson.getString(lastName()),
                                resultPerson.getString(username()),
                                resultPerson.getString(password()),
                                resultAdmin.getInt(admin_id()),
                                tblAdminPersonId,
                                resultAdmin.getString(admin_role_id()))
                        );
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbAdmins;
    }

    public static List<Student> getTableStudentData() {
        List<Student> dbStudent = new ArrayList<>();
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementPerson = connection.prepareStatement(SELECT_ALL_FROM + TBL_PERSON);
             PreparedStatement statementStudent = connection.prepareStatement(SELECT_ALL_FROM + TBL_STUDENT);
             PreparedStatement statementAcademic = connection.prepareStatement(SELECT_ALL_FROM + TBL_ACADEMIC)
        ) {
            ResultSet resultPerson = statementPerson.executeQuery();
            ResultSet resultStudent = statementStudent.executeQuery();
            ResultSet resultAcademic = statementAcademic.executeQuery();
            while (resultStudent.next()) {
                final int tblStudentPersonId = resultStudent.getInt(student_person_id());
                final int tblStudentAcademicId = resultStudent.getInt(student_academic_id());
                while (resultPerson.next()) {
                    final int tblPersonId = resultPerson.getInt(person_id());

                    if (tblStudentPersonId == tblPersonId) {
                        while (resultAcademic.next()) {
                            final int tblAcademicId = resultAcademic.getInt(academic_id());

                            if (tblStudentAcademicId == tblAcademicId) {
                                dbStudent.add(new Student(
                                        tblPersonId,
                                        resultPerson.getString(firstName()),
                                        resultPerson.getString(lastName()),
                                        resultPerson.getString(username()),
                                        resultPerson.getString(password()),

                                        tblAcademicId,
                                        resultAcademic.getInt(course1_id()),
                                        resultAcademic.getInt(course2_id()),
                                        resultAcademic.getInt(course3_id()),
                                        resultAcademic.getInt(course4_id()),
                                        resultAcademic.getInt(course5_id()),
                                        resultAcademic.getInt(course6_id()),

                                        resultStudent.getInt(student_id()),
                                        tblStudentPersonId,
                                        tblStudentAcademicId,
                                        resultStudent.getString(student_role_id()))
                                );
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbStudent;
    }

    public static List<Professor> getTableProfessorData() {
        return new ArrayList<>();
    }

    public static Professor getTableProfessorData(int id) {
        return new Professor();
    }

    public static List<Course> getTableCourseData() {
        List<Course> dbCourse = new ArrayList<>();
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementCourse = connection.prepareStatement(SELECT_ALL_FROM + TBL_COURSE)
        ) {
            ResultSet resultCourse = statementCourse.executeQuery();
            while (resultCourse.next()) {
                dbCourse.add(new Course(
                        resultCourse.getInt(course_id()),
                        resultCourse.getString(course_name()),
                        resultCourse.getInt(price()))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbCourse;
    }

    public static <T> Academic<T> getTableAcademicData(Academic<T> person) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementAcademics = connection.prepareStatement(SELECT_ALL_FROM + TBL_ACADEMIC)
        ) {
            ResultSet resultAcademic = statementAcademics.executeQuery();
            while (resultAcademic.next()) {
                final int tblAcademicId = resultAcademic.getInt(academic_id());

                if (person.getAcademicId() == tblAcademicId) {
                    person.setTblAcademicId(tblAcademicId);
                    person.setCourse1(resultAcademic.getInt(course1_id()));
                    person.setCourse2(resultAcademic.getInt(course2_id()));
                    person.setCourse3(resultAcademic.getInt(course3_id()));
                    person.setCourse4(resultAcademic.getInt(course4_id()));
                    person.setCourse5(resultAcademic.getInt(course5_id()));
                    person.setCourse6(resultAcademic.getInt(course6_id()));

                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public static void insertAdmin(Admin admin) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementPerson = connection.prepareStatement(INSERT_PERSON);
             PreparedStatement statementAdmin = connection.prepareStatement(INSERT_ADMIN)
        ) {
            statementPerson.setInt(1, admin.getTblPersonId());
            statementPerson.setString(2, admin.getFirstName());
            statementPerson.setString(3, admin.getLastName());
            statementPerson.setString(4, admin.getUserName());
            statementPerson.setString(5, admin.getPassword());

            statementPerson.executeUpdate();

            statementAdmin.setInt(1, admin.getTblAdminId());
            statementAdmin.setInt(2, admin.getTblAdminPersonId());
            statementAdmin.setString(3, admin.getRoleId());

            statementAdmin.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(Student student) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementPerson = connection.prepareStatement(INSERT_PERSON);
             PreparedStatement statementStudent = connection.prepareStatement(INSERT_STUDENT);
             PreparedStatement statementAcademic = connection.prepareStatement(INSERT_ACADEMIC_EMPTY_ENROLL)
        ) {
            statementPerson.setInt(1, student.getTblPersonId());
            statementPerson.setString(2, student.getFirstName());
            statementPerson.setString(3, student.getLastName());
            statementPerson.setString(4, student.getUserName());
            statementPerson.setString(5, student.getPassword());

            statementPerson.executeUpdate();

            statementAcademic.setInt(1, student.getTblAcademicId());

            statementAcademic.executeUpdate();

            statementStudent.setInt(1, student.getTblStudentId());
            statementStudent.setInt(2, student.getTblStudentPersonId());
            statementStudent.setInt(3, student.getTblStudentAcademicId());
            statementStudent.setString(4, student.getRoleId());

            statementStudent.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCourse(Course course) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementCourse = connection.prepareStatement(INSERT_COURSE)
        ) {
            statementCourse.setInt(1, course.getTblCourseId());
            statementCourse.setString(2, course.getCourseName());
            statementCourse.setInt(3, course.getPrice());

            statementCourse.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> void updateAcademicEnroll(Academic<T> person) {
        try (Connection connection = DBConnect.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            final int idAcademic = person.getTblAcademicId();
            final String query = SELECT_ALL_FROM + TBL_ACADEMIC + WHERE_ID.replace("id", academic_id()) + idAcademic;

            ResultSet resultAcademic = statement.executeQuery(query);

            while (resultAcademic.next()) {
                resultAcademic.updateInt(course1_id(), person.getCourse1());
                resultAcademic.updateInt(course2_id(), person.getCourse2());
                resultAcademic.updateInt(course3_id(), person.getCourse3());
                resultAcademic.updateInt(course4_id(), person.getCourse4());
                resultAcademic.updateInt(course5_id(), person.getCourse5());
                resultAcademic.updateInt(course6_id(), person.getCourse6());

                resultAcademic.updateRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
