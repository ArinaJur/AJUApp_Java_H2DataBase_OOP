package ajuapp;

import static ajuapp.utils.ProjectConstants.*;

@Author
public interface IExit {

    default void printQForExit() {
        System.out.println();
        System.out.println(PRINT_Q_FOR_EXIT);
        System.out.println();
    }

    default void exitIfQ(String input) {
        if(input.equalsIgnoreCase("q")) {
            System.out.println(GOODBYE);
            System.exit(0);
        }
    }

    default void exitIfUnauthorizedUser() {
        System.out.println(CANT_RECOGNIZE);
        System.out.println(GOODBYE);
        System.exit(0);
    }

    void runIfQ(String input);

    default void printHeader(String header) {
        System.out.println();
        System.out.println();
        System.out.println(header);
    }

}
