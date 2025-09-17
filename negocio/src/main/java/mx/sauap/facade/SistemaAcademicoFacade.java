package mx.sauap.facade;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mx.sauap.dao.AsignacionDAO;
import mx.sauap.delegate.AsignacionDelegate;
import mx.sauap.delegate.UnidadAprendizajeDelegate;
import mx.sauap.dao.ProfesorDAO;
import mx.sauap.entity.Asignacion;
import mx.sauap.entity.Horario;
import mx.sauap.entity.Profesor;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.gestores.GestorProfesor;
import mx.sauap.integration.ServiceLocator;

@Stateless
public class SistemaAcademicoFacade {
    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    private GestorProfesor gestorProfesor;
    private UnidadAprendizajeDelegate delegate;
    private AsignacionDelegate asignacionDelegate;

    // Constructor usado cuando ya te pasan un GestorProfesor
    public SistemaAcademicoFacade(GestorProfesor gestorProfesor) {
        this.gestorProfesor = gestorProfesor;
        this.delegate = new UnidadAprendizajeDelegate();
        this.asignacionDelegate = new AsignacionDelegate();
    }

    public SistemaAcademicoFacade() {
        ProfesorDAO profesorDAO = new ProfesorDAO(ServiceLocator.getEntityManager());
        this.gestorProfesor = new GestorProfesor(profesorDAO);
        this.delegate = new UnidadAprendizajeDelegate();
        this.asignacionDelegate = new AsignacionDelegate();
    }

    // ===== Profesores =====
    public List<Profesor> obtenerProfesores() {
        return gestorProfesor.listarProfesores();
    }

    public Profesor buscarProfesorPorId(Integer id) {
        return gestorProfesor.buscarPorId(id);
    }

    public void insertarProfesor(Profesor profesor) {
        gestorProfesor.agregarProfesor(profesor);
    }

    // ===== Unidades de Aprendizaje =====
    public List<UnidadAprendizaje> consultarUA() {
        return delegate.consultarUA();
    }

    public UnidadAprendizaje buscarUnidadPorId(Integer id) {
        return delegate.buscarUAPorId(id);
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

    // ===== Asignaciones =====
    public List<Asignacion> consultarA() {
        return asignacionDelegate.consultarA();
    }

    public void guardarA(Asignacion a) {
        asignacionDelegate.insertarA(a);
    }


    // ===== Horario =====
    public void guardarH(Horario h){
        EntityManager em=ServiceLocator.getEntityManager();
        em.getTransaction().begin();
        em.persist(h);
        em.getTransaction().commit();
    }
}