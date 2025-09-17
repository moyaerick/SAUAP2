package mx.sauap.facade;

import java.util.List;

import mx.sauap.delegate.UnidadAprendizajeDelegate;
import mx.sauap.dao.ProfesorDAO;
import mx.sauap.dao.HorarioDAO;
import mx.sauap.dao.AsignacionDAO;
import mx.sauap.entity.Profesor;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.entity.Horario;
import mx.sauap.entity.Asignacion;
import mx.sauap.gestores.GestorHorario;
import mx.sauap.gestores.GestorProfesor;
import mx.sauap.gestores.GestorAsignacion;
import mx.sauap.integration.ServiceLocator;

public class SistemaAcademicoFacade {
    private GestorProfesor gestorProfesor;
    private UnidadAprendizajeDelegate delegate;
    private GestorHorario gestorHorario;
    private GestorAsignacion gestorAsignacion;

    // Constructor usado cuando ya te pasan un GestorProfesor
    public SistemaAcademicoFacade(GestorProfesor gestorProfesor) {
        this.gestorProfesor = gestorProfesor;
        this.delegate = new UnidadAprendizajeDelegate();
        this.gestorHorario = new GestorHorario(new HorarioDAO(ServiceLocator.getEntityManager()));
        this.gestorAsignacion = new GestorAsignacion(new AsignacionDAO(ServiceLocator.getEntityManager()));
    }

    public SistemaAcademicoFacade() {
        ProfesorDAO profesorDAO = new ProfesorDAO(ServiceLocator.getEntityManager());
        this.gestorProfesor = new GestorProfesor(profesorDAO);
        this.delegate = new UnidadAprendizajeDelegate();
        this.gestorHorario = new GestorHorario(new HorarioDAO(ServiceLocator.getEntityManager()));
        this.gestorAsignacion = new GestorAsignacion(new AsignacionDAO(ServiceLocator.getEntityManager()));
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

    // ===== Horarios =====
    public List<Horario> consultarHorarios() {
        return gestorHorario.consultarHorarios();
    }

    public long getHorasAsignadas(Integer idAsignacion) {
        return gestorHorario.getHorasAsignadas(idAsignacion);
    }

    public Asignacion consultarAsignacionPorId(Integer id){
        return gestorAsignacion.consultarAsignacionPorId(id);
    }


    public boolean guardarHorario(Horario horario) {
        return gestorHorario.insertarHorario(horario);
    }


    public void actualizarHorario(Horario horario) {
        gestorHorario.actualizarHorario(horario);
    }

    public void eliminarHorario(Horario horario) {
        gestorHorario.eliminarHorario(horario);
    }

    // ===== Asignaciones =====
    public List<Asignacion> consultarAsignaciones() {
        return gestorAsignacion.consultarAsignaciones();
    }
}
