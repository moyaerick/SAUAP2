package mx.sauap.facade;

import java.util.List;

import mx.sauap.dao.HorarioDAO;
import mx.sauap.dao.ProfesorDAO;
import mx.sauap.delegate.AsignacionDelegate;
import mx.sauap.delegate.UnidadAprendizajeDelegate;
import mx.sauap.entity.Asignacion;
import mx.sauap.entity.Horario;
import mx.sauap.entity.Profesor;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.gestores.GestorProfesor;
import mx.sauap.integration.ServiceLocator;

public class SistemaAcademicoFacade {

    private GestorProfesor gestorProfesor;
    private UnidadAprendizajeDelegate unidadDelegate;
    private AsignacionDelegate asignacionDelegate;
    private HorarioDAO horarioDAO;

    public SistemaAcademicoFacade() {
        ProfesorDAO profesorDAO = new ProfesorDAO(ServiceLocator.getEntityManager());
        this.gestorProfesor     = new GestorProfesor(profesorDAO);
        this.unidadDelegate     = new UnidadAprendizajeDelegate();
        this.asignacionDelegate = new AsignacionDelegate();
        this.horarioDAO         = new HorarioDAO(ServiceLocator.getEntityManager());
    }

    public SistemaAcademicoFacade(GestorProfesor gestorProfesor) {
        this();
        this.gestorProfesor = gestorProfesor != null ? gestorProfesor : this.gestorProfesor;
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
        return unidadDelegate.consultarUA();
    }

    public UnidadAprendizaje buscarUnidadPorId(Integer id) {
        return unidadDelegate.buscarUAPorId(id);
    }

    public void guardarUA(UnidadAprendizaje ua) {
        unidadDelegate.insertarUA(ua);
    }

    public void actualizarUA(UnidadAprendizaje ua) {
        unidadDelegate.actualizarUA(ua);
    }

    public void eliminarUA(UnidadAprendizaje ua) {
        unidadDelegate.eliminarUA(ua);
    }

    // ===== Asignaciones =====
    // Alias corto usado en AsignacionBeanUI
    public List<Asignacion> consultarA() {
        return asignacionDelegate.consultarA();
    }

    // Nombre largo usado en HorarioBeanUI
    public List<Asignacion> consultarAsignaciones() {
        return asignacionDelegate.consultarA();
    }

    public void guardarA(Asignacion a) {
        asignacionDelegate.insertarA(a);
    }

    public Asignacion consultarAsignacionPorId(Integer id) {
        return asignacionDelegate.obtenerPorId(id);
    }

    // ===== Horarios =====
    public List<Horario> consultarHorarios() {
        return horarioDAO.obtenerTodos();
    }

    public long getHorasAsignadas(Integer idAsignacion) {
        return horarioDAO.getHorasAsignadas(idAsignacion);
    }

    public boolean guardarHorario(Horario h) {
        if (h == null || h.getIdAsignacion() == null) return false;
        Integer idAsig = h.getIdAsignacion().getId();
        boolean traslapa = horarioDAO.existeTraslape(idAsig, h.getDia(), h.getHrIn(), h.getHrFin());
        if (traslapa) return false;
        horarioDAO.guardar(h);
        return true;
    }

    public void eliminarHorario(Horario h) {
        horarioDAO.eliminar(h);
    }
}


