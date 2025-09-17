package mx.sauap.gestores;

import mx.sauap.dao.ProfesorDAO;
import mx.sauap.entity.Profesor;
import java.util.List;

public class GestorProfesor {

    private ProfesorDAO profesorDAO;

    public GestorProfesor(ProfesorDAO profesorDAO) {

        this.profesorDAO = profesorDAO;
    }

    public List<Profesor> listarProfesores() {

        return profesorDAO.obtenerTodos();
    }

    public void agregarProfesor(Profesor profesor) {

        profesorDAO.insertarProfesor(profesor);
    }
    public Profesor buscarPorId(Integer id) {
        return profesorDAO.obtenerPorId(id);
    }
}
