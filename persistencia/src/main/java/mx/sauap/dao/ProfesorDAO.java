package mx.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.sauap.persistence.AbstractDAO;
import mx.sauap.entity.Profesor;

import java.util.List;

public class ProfesorDAO extends AbstractDAO<Profesor> {
    private final EntityManager entityManager;

    public ProfesorDAO(EntityManager em) {
        super(Profesor.class);
        this.entityManager = em;
    }

    public List<Profesor> obtenerTodos() {
        return entityManager
                .createQuery("SELECT p FROM Profesor p", Profesor.class)
                .getResultList();
    }

    public Profesor obtenerPorNombre(String nombre) {
        List<Profesor> resultado = entityManager
                .createQuery("SELECT p FROM Profesor p WHERE p.nombre = :nombre", Profesor.class)
                .setParameter("nombre", nombre)
                .getResultList();

        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public void insertarProfesor(Profesor profesor) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(profesor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }


    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
