package mx.sauap.delegate;

import jakarta.persistence.EntityManager;
import mx.sauap.dao.UnidadAprendizajeDAO;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.integration.ServiceLocator;
import java.util.List;

public class UnidadAprendizajeDelegate {

    private final UnidadAprendizajeDAO dao;

    public UnidadAprendizajeDelegate() {
        EntityManager em = ServiceLocator.getEntityManager();
        this.dao = new UnidadAprendizajeDAO(em);
    }

    public UnidadAprendizaje buscarUAPorId(Integer id) {
        return dao.buscarUAPorId(id);
    }

    public List<UnidadAprendizaje> consultarUA() {
        return dao.obtenerTodos();
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
}