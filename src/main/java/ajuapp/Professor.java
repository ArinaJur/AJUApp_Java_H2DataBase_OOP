package ajuapp;

import ajuapp.database.DBUtils;

import java.util.ArrayList;
import java.util.List;

@Author
public final class Professor extends Academic<Professor> implements IAcademic {
    private String roleId = "P";
    public static List<Professor> professors = new ArrayList<>();
    private Professor currentProfessor = null;

    public Professor getCurrentProfessor() {
        return currentProfessor;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setCurrentProfessor(Professor professor) {
        this.currentProfessor = professor;
    }

    @Override
    public char getRole() {
        return roleId.charAt(0);
    }

    @Override
    public Professor getTableData(int id) {
        return DBUtils.getTableProfessorData(id);
    }

    @Override
    public int getAcademicId() {
        return 0;
    }

    public void runProfessor(){}
}
