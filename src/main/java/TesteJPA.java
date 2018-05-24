
import repository.CursoDAO;
import repository.ProfessorDAO;
import model.Curso;
import model.Professor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class TesteJPA {

    public static void main(String[] args) {
        
        Professor professor = new Professor("Marco");
        ProfessorDAO dao1 = ProfessorDAO.getInstance();
        dao1.salvar(professor);
        
        Curso curso = new Curso("Eng Computacao", 3000, "Graduacao", 10, professor);
        CursoDAO dao2 = CursoDAO.getInstance();
        dao2.salvar(curso);
     }

}
