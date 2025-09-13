package mx.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.sauap.persistence.AbstractDAO;
import mx.sauap.entity.Alumno;

import java.util.List;


public class AlumnoDAO extends AbstractDAO<Alumno> {
    private final EntityManager entityManager;

    public AlumnoDAO(EntityManager em) {
        super(Alumno.class);
        this.entityManager = em;
    }

    public List<Alumno> obtenerTodos(){
        return entityManager
                .createQuery("SELECT a FROM Alumno a", Alumno.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
