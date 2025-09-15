package mx.sauap.facade;

import mx.sauap.entity.Profesor;
import mx.sauap.gestores.GestorProfesor;
import java.util.List;

public class SistemaAcademicoFacade {
    private GestorProfesor gestorProfesor;

    public SistemaAcademicoFacade(GestorProfesor gestorProfesor) {
        this.gestorProfesor = gestorProfesor;
    }

    public List<Profesor> obtenerProfesores() {
        return gestorProfesor.listarProfesores();
    }

    public void insertarProfesor(Profesor profesor) {
        gestorProfesor.agregarProfesor(profesor);
    }
}
