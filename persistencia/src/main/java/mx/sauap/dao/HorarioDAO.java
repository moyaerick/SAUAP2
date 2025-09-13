package mx.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.sauap.entity.Horario;
import mx.sauap.persistence.AbstractDAO;

import java.util.List;

public class HorarioDAO extends AbstractDAO<Horario> {
    private final EntityManager entityManager;

    public HorarioDAO(EntityManager em) {
        super(Horario.class);
        this.entityManager = em;
    }

    public List<Horario> obtenerTodos() {
        return entityManager
                .createQuery("SELECT u FROM Usuario u", Horario.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}