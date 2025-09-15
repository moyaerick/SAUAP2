package mx.sauap.facade;

import mx.sauap.entity.Profesor;
import mx.sauap.gestores.GestorProfesor;
import java.util.List;

import mx.sauap.delegate.UnidadAprendizajeDelegate;
import mx.sauap.entity.UnidadAprendizaje;

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

    private final UnidadAprendizajeDelegate delegate;

    public SistemaAcademicoFacade() {
        this.delegate = new UnidadAprendizajeDelegate();
    }

    public List<UnidadAprendizaje> consultarUA() {
        return delegate.consultarUA();
    }

    public void guardarUA(UnidadAprendizaje ua) {
        delegate.insertarUA(ua);
    }

    public void actualizarUA(UnidadAprendizaje ua) {
        delegate.actualizarUA(ua);
    }

    public void eliminarUA(UnidadAprendizaje ua) {
        delegate.eliminarUA(ua);
    }
}
