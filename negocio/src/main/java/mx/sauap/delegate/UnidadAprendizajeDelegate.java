package mx.sauap.delegate;

import jakarta.persistence.EntityManager;
import mx.sauap.dao.UnidadAprendizajeDAO;
import mx.sauap.entity.Asignacion;
import mx.sauap.entity.Horario;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.integration.ServiceLocator;

import java.util.List;

public class UnidadAprendizajeDelegate {

    private final UnidadAprendizajeDAO dao;
    private final AsignacionDelegate asignacionDelegate;

    public UnidadAprendizajeDelegate() {
        EntityManager em = ServiceLocator.getEntityManager();
        this.dao = new UnidadAprendizajeDAO(em);
        this.asignacionDelegate = new AsignacionDelegate();
    }

    public List<UnidadAprendizaje> consultarUA() {
        return dao.obtenerTodos();
    }

    public UnidadAprendizaje buscarUAPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

    public void insertarUA(UnidadAprendizaje ua) {
        dao.guardar(ua);
    }

    public void actualizarUA(UnidadAprendizaje ua) {
        dao.actualizar(ua);
    }

    public void eliminarUA(UnidadAprendizaje ua) {
        dao.eliminar(ua);
    }

    public List<Asignacion> consultarA() {
        return asignacionDelegate.consultarA();
    }

    public void guardarA(Asignacion a) {
        asignacionDelegate.insertarA(a);
    }

    public void guardarH(Horario h){
        EntityManager em = ServiceLocator.getEntityManager();
        em.getTransaction().begin();
        em.persist(h);
        em.getTransaction().commit();
    }
}
