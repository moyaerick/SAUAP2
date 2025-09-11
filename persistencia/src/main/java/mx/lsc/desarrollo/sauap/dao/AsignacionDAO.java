package mx.lsc.desarrollo.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.lsc.desarrollo.sauap.persistence.AbstractDAO;
import mx.desarrollo.sauap.entity.Asignacion;

import java.util.List;

public class AsignacionDAO extends AbstractDAO<Asignacion> {
    private final EntityManager entityManager;

    public AsignacionDAO(EntityManager em) {
        super(Asignacion.class);
        this.entityManager = em;
    }

    public List<Asignacion> obtenerTodos() {
        return entityManager
                .createQuery("SELECT u FROM Usuario u", Asignacion.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}