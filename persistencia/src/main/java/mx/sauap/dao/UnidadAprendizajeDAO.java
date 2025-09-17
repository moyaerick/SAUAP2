package mx.sauap.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import mx.sauap.entity.UnidadAprendizaje;

import java.util.List;

import static mx.sauap.persistence.HibernateUtil.getEntityManager;

public class UnidadAprendizajeDAO {

    private final EntityManager entityManager;

    public UnidadAprendizajeDAO(EntityManager em) {
        this.entityManager = em;
    }

    // Obtener todas las Unidades
    public List<UnidadAprendizaje> obtenerTodos() {
        TypedQuery<UnidadAprendizaje> query =
                entityManager.createQuery("SELECT u FROM UnidadAprendizaje u", UnidadAprendizaje.class);
        return query.getResultList();
    }

    // CRUD
    public void guardar(UnidadAprendizaje ua) {
        entityManager.getTransaction().begin();
        entityManager.persist(ua);
        entityManager.getTransaction().commit();
    }

    public void actualizar(UnidadAprendizaje ua) {
        entityManager.getTransaction().begin();
        entityManager.merge(ua);
        entityManager.getTransaction().commit();
    }

    public void eliminar(UnidadAprendizaje ua) {
        entityManager.getTransaction().begin();
        UnidadAprendizaje managed = entityManager.merge(ua);
        entityManager.remove(managed);
        entityManager.getTransaction().commit();
    }

    public UnidadAprendizaje obtenerPorId(Integer id) {
        return getEntityManager().find(UnidadAprendizaje.class, id);
    }

}


