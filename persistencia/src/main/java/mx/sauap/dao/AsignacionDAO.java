package mx.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.sauap.entity.Asignacion;
import mx.sauap.persistence.AbstractDAO;

import java.util.List;

public class AsignacionDAO extends AbstractDAO<Asignacion> {
    private final EntityManager entityManager;

    public AsignacionDAO(EntityManager em) {
        super(Asignacion.class);
        this.entityManager = em;
    }

    public List<Asignacion> obtenerTodos() {
        return entityManager
                .createQuery("SELECT a FROM Asignacion a", Asignacion.class)
                .getResultList();
    }
    public void guardar(Asignacion a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    public Asignacion obtenerPorId(Integer id) {
        return entityManager.find(Asignacion.class, id); // 'em' es tu EntityManager
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}