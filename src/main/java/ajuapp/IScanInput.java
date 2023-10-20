package ajuapp;

import java.util.Scanner;

@Author
public interface IScanInput extends IExit {
    Scanner in = new Scanner(System.in);

    default String scanRunAdminChoice() {
        printQForExit();
        System.out.println("Would you like" );
        System.out.println("1 - Register new user" );
        System.out.println("2 - Add new course" );
        System.out.println("3 - Register Student/Professor for Course" );
        System.out.println("4 - Print existing data from DB" );
        return in.nextLine();
    }

    default String scanRegistrationChoice() {
        printQForExit();
        System.out.println("Would you like" );
        System.out.println("1 - Register new Student" );
        System.out.println("2 - Register new Professor" );
        System.out.println("3 - Register new Admin" );
        return in.nextLine();
    }

    default String scanCourseName() {
        printQForExit();
        System.out.print("Enter course name: " );
        String input = in.nextLine();
        runIfQ(input);
        return input;
    }

    default String scanCoursePrice() {
        System.out.print("Enter course price (whole number): " );
        String input = in.nextLine();
        runIfQ(input);
        return input;
    }

    default String scanAddAnotherCourse() {
        printQForExit();
        System.out.println("Would you like to add another course?" );
        System.out.println("1 - yes" );
        System.out.println("2 - no" );
        return in.nextLine();
    }

    default String scanPrintInformationChoice() {
        printQForExit();
        System.out.println("Would you like" );
        System.out.println("1 - Print Admins" );
        System.out.println("2 - Print Students" );
        System.out.println("3 - Print Professors" );
        System.out.println("4 - Print Courses" );
        System.out.println("5 - Print Balance for the Student" );
        return in.nextLine();
    }

    default String scanFirsName() {
        printQForExit();
        System.out.print("Enter first name: " );
        String input = in.nextLine();
        runIfQ(input);
        return input;
    }

    default String scanLastName() {
        System.out.print("Enter last name: " );
        String input = in.nextLine();
        runIfQ(input);
        return input;
    }

    default String scanUserName() {
        printQForExit();
        System.out.print("Enter username: ");
        String input = in.nextLine();
        exitIfQ(input);
        return input;
    }

    default String scanPassword() {
        System.out.print("Enter password: ");
        String input = in.nextLine();
        exitIfQ(input);
        return input;
    }

    default <T> String scanCourseRegistrationChoice(Academic<T> person) {
        printQForExit();
        char role = person.getRole();
        switch (role) {
            case 'S' -> System.out.println("\n\nWould you like to register Student "
                            + person.getFirstName() + " " + person.getLastName() + " for courses?"
            );
            case 'P' -> System.out.println("\n\nWould you like to assign Professor "
                            + person.getFirstName() + " " + person.getLastName() + " to teach courses?"
            );
        }
        System.out.println("1 - yes");
        System.out.println("2 - no");
        return in.nextLine();
    }

    default String scanPersonChoice() {
        printQForExit();
        System.out.println("Would you like to:");
        System.out.println("1 - Register Student for courses?");
        System.out.println("2 - Assign Professor to teach courses?");
        return in.nextLine();
    }

    default String scanPrintListChoice() {
        printQForExit();
        System.out.println("Would you like to Print Students List?");
        System.out.println("1 - yes");
        System.out.println("2 - no");
        return in.nextLine();
    }

    default int scanStudentId() {
        printQForExit();
        System.out.println();
        System.out.print("Enter Student Id: ");

        String input = in.nextLine();
        return Integer.parseInt(input);
    }

    default String scanStudentConfirmation() {
        printQForExit();
        System.out.println("Is this information about the Student correct?");
        System.out.println("1 - Confirm Student");
        System.out.println("2 - Wrong Student");
        return in.nextLine();
    }

    default String scanCourseId() {
        System.out.println();
        System.out.print("Enter course id: ");
        return in.nextLine();
    }

    default String scanRunStudentChoice() {
        printQForExit();
        System.out.println("Would you like" );
        System.out.println("1 - Print your Schedule" );
        System.out.println("2 - Print Available Courses" );
        System.out.println("3 - Print your balance");
        return in.nextLine();
    }
}
