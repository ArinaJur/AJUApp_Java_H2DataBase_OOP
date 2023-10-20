package ajuapp;

import ajuapp.database.DBUtils;

import static ajuapp.Admin.admins;
import static ajuapp.Professor.professors;
import static ajuapp.Student.students;
import static ajuapp.utils.ProjectConstants.WELCOME;
import static ajuapp.utils.ProjectConstants.WELCOME_TO_AJU;

@Author
public final class SignUp implements IExit, IScanInput {

    private void printWelcomeMessage() {
        System.out.println(WELCOME_TO_AJU);
        System.out.println();
    }

    private void signUp() {
        admins = DBUtils.getTableAdminData();
        students = DBUtils.getTableStudentData();
        professors = DBUtils.getTableProfessorData();

        if (admins.size() == 0) {
            Admin.addFirstAdmin();
        }

        final String username = scanUserName();
        final String password = scanPassword();
        checkCredentials(username, password);
    }

    private void checkCredentials(String userName, String password) {
        for(Admin admin: admins) {
            if(admin.getUserName().equals(userName) && admin.getPassword().equals(password) && admin.getRole() == 'A') {
                admin.setCurrentAdmin(admin);
                Admin currentAdmin = admin.getCurrentAdmin();
                welcome(currentAdmin);
                currentAdmin.runAdmin();
            }
        }
        for (Student student : students) {
            if(student.getUserName().equals(userName) && student.getPassword().equals(password) && student.getRole() == 'S') {
                student.setCurrentStudent(student);
                welcome(student.getCurrentStudent());
                student.getCurrentStudent().runStudent();
            }
        }
        for (Professor professor : professors) {
            if(professor.getUserName().equals(userName) && professor.getPassword().equals(password) && professor.getRole() == 'P') {
                professor.setCurrentProfessor(professor);
                welcome(professor.getCurrentProfessor());
                professor.runProfessor();
            }
        }

        new Admin().setCurrentUserNull();
        exitIfUnauthorizedUser();
    }

    private <T> void welcome(Person<T> person) {
        System.out.println(WELCOME + person.getFirstName() + " " + person.getLastName() + "!\n");
    }

    public void runAJUApp() {
        printWelcomeMessage();
        signUp();
    }

    @Override
    public void runIfQ(String input) {}
}
