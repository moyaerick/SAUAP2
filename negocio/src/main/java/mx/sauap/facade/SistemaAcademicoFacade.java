package mx.sauap.facade;

import java.util.List;

import mx.sauap.delegate.UnidadAprendizajeDelegate;
import mx.sauap.dao.ProfesorDAO;
import mx.sauap.entity.Profesor;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.gestores.GestorProfesor;
import mx.sauap.integration.ServiceLocator;

public class SistemaAcademicoFacade {

    private GestorProfesor gestorProfesor;
    private UnidadAprendizajeDelegate delegate;

    // Constructor usado cuando ya te pasan un GestorProfesor
    public SistemaAcademicoFacade(GestorProfesor gestorProfesor) {
        this.gestorProfesor = gestorProfesor;
        this.delegate = new UnidadAprendizajeDelegate();
    }

    public SistemaAcademicoFacade() {
        ProfesorDAO profesorDAO = new ProfesorDAO(ServiceLocator.getEntityManager());
        this.gestorProfesor = new GestorProfesor(profesorDAO);
        this.delegate = new UnidadAprendizajeDelegate();
    }

    // ===== Profesores =====
    public List<Profesor> obtenerProfesores() {
        return gestorProfesor.listarProfesores();
    }

    public void insertarProfesor(Profesor profesor) {
        gestorProfesor.agregarProfesor(profesor);
    }

    // ===== Unidades de Aprendizaje =====
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