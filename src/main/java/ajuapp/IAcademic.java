package ajuapp;

import ajuapp.database.DBUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ajuapp.Course.courses;
import static ajuapp.utils.ProjectConstants.*;

@Author
public interface IAcademic {

    default String getAvailableCourseName(int courseId) {
        List<Course> courses = DBUtils.getTableCourseData();
        for (Course course : courses) {
            if (course.getTblCourseId() == courseId) {
                return course.getCourseName();
            }
        }
        return "";
    }

    default void printAvailableCoursesList() {
        courses = DBUtils.getTableCourseData();
        if (courses.size() > 0) {
            System.out.println(LINE);
            System.out.println(AVAILABLE_COURSES);
            System.out.println(courses);
            System.out.println(LINE);
        } else {
            System.out.println(NO_COURSES);
        }
    }

    default <T> void printScheduleTableHeaders(Academic<T> person) {
        System.out.println(LINE);
        System.out.println(SCHEDULE_HEADER + "\n" + person.getFirstName() + " " + person.getLastName());
        System.out.println(LINE);
        System.out.println(TABLE_HEADER);
        System.out.println(LINE);
    }

    default void printNoAssignments() {
        System.out.println(LINE);
        System.out.println(STUDENT_NO_COURSE_ASSIGNMENTS);
        System.out.println(LINE);
    }

    default <T> void printSchedule(Academic<T> person) {
        Map<String, Integer> assignedCourses = getAssignedCourses(person);
        if(assignedCourses.size() > 0) {
            printScheduleTableHeaders(person);
            for (Map.Entry<String, Integer> entry : assignedCourses.entrySet()) {
                int l = 28 - entry.getKey().length();

                String space = " ".repeat(Math.max(0, l + 1));
                String courseName = entry.getKey() + space;

                System.out.println(courseName + entry.getValue());
            }
            System.out.println(LINE);
        } else {
            printNoAssignments();
        }
    }

    default <T> Map<String, Integer> getAssignedCourses(Academic<T> person) {
        Map<String, Integer> assignedCourses = new HashMap<>();
        courses = DBUtils.getTableCourseData();
        List<Integer> coursesIds = getAssignedCoursesIds(person.getAcademicData(person));

        if (coursesIds.size() > 0) {
            for(Integer courseId : coursesIds) {
                for(Course course : courses) {
                    if(courseId == course.getTblCourseId()) {
                        assignedCourses.put(course.getCourseName(), course.getPrice());
                    }
                }
            }
        }

        return assignedCourses;
    }

    default <T> List<Integer> getAssignedCoursesIds(Academic<T> personData) {
        List<Integer> coursesIds = new ArrayList<>();

        if (personData.getCourse1() != 0) {
            coursesIds.add(personData.getCourse1());
        }
        if (personData.getCourse2() != 0) {
            coursesIds.add(personData.getCourse2());
        }
        if (personData.getCourse3() != 0) {
            coursesIds.add(personData.getCourse3());
        }
        if (personData.getCourse4() != 0) {
            coursesIds.add(personData.getCourse4());
        }
        if (personData.getCourse5() != 0) {
            coursesIds.add(personData.getCourse5());
        }
        if (personData.getCourse6() != 0) {
            coursesIds.add(personData.getCourse6());
        }

        return coursesIds;
    }

    default <T> int countTotal(Student student, Person<T> currentUser) {
        if(currentUser.getRole() == 'S' || currentUser.getRole() == 'A') {
            int total = 0;
            List<Integer> assignedCourses = student.getAssignedCoursesIds(student);
            courses = DBUtils.getTableCourseData();

            for (int courseId : assignedCourses) {
                for (Course course : courses)
                    if (courseId == course.getTblCourseId()) {
                        total += course.getPrice();
                    }
            }
            return total;
        }

        return 0;
    }

    default <T> void printBalance(Student student, Person<T> currentUser) {
        int total = countTotal(student, currentUser);
        System.out.println(TOTAL_BALANCE + total);
        System.out.println(LINE);
    }
}
