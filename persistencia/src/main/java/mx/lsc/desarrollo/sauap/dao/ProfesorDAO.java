package mx.lsc.desarrollo.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.lsc.desarrollo.sauap.persistence.AbstractDAO;
import mx.desarrollo.sauap.entity.Profesor;

import java.util.List;

public class ProfesorDAO extends AbstractDAO<Profesor> {
    private final EntityManager entityManager;

    public ProfesorDAO(EntityManager em) {
        super(Profesor.class);
        this.entityManager = em;
    }

    public List<Profesor> obtenerTodos() {
        return entityManager
                .createQuery("SELECT u FROM Usuario u", Profesor.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}