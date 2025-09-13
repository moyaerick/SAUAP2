package mx.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.persistence.AbstractDAO;

import java.util.List;

public class UnidadAprendizajeDAO extends AbstractDAO<UnidadAprendizaje> {
    private final EntityManager entityManager;

    public UnidadAprendizajeDAO(EntityManager em) {
        super(UnidadAprendizaje.class);
        this.entityManager = em;
    }

    public List<UnidadAprendizaje> obtenerTodos() {
        return entityManager
                .createQuery("SELECT u FROM Usuario u", UnidadAprendizaje.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}