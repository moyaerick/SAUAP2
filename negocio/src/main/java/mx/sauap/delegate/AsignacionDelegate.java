package mx.sauap.delegate;

import jakarta.persistence.EntityManager;
import mx.sauap.dao.AsignacionDAO;
import mx.sauap.entity.Asignacion;
import mx.sauap.integration.ServiceLocator;
import java.util.List;

public class AsignacionDelegate {
    private final AsignacionDAO dao;

    public AsignacionDelegate() {
        EntityManager em = ServiceLocator.getEntityManager();
        this.dao = new AsignacionDAO(em);
    }

    public List<Asignacion> consultarA() {
        return dao.obtenerTodos();
    }

    public void insertarA(Asignacion a) {
        dao.guardar(a);
    }
    public Asignacion obtenerPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

}