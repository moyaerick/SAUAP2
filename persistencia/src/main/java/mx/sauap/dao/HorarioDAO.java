package mx.sauap.dao;

import jakarta.persistence.EntityManager;
import mx.sauap.entity.Dia;
import mx.sauap.entity.Horario;
import mx.sauap.persistence.AbstractDAO;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class HorarioDAO extends AbstractDAO<Horario> {

    private final EntityManager entityManager;

    public HorarioDAO(EntityManager em) {
        super(Horario.class);
        this.entityManager = em;
    }

    public List<Horario> obtenerTodos() {
        return entityManager
                .createQuery("SELECT h FROM Horario h ORDER BY h.dia, h.hrIn", Horario.class)
                .getResultList();
    }

    public List<Horario> obtenerPorAsignacionId(Integer idAsignacion) {
        return entityManager
                .createQuery(
                        "SELECT h FROM Horario h " +
                                "WHERE h.idAsignacion.id = :id " +
                                "ORDER BY h.dia, h.hrIn", Horario.class)
                .setParameter("id", idAsignacion)
                .getResultList();
    }

    public long getHorasAsignadas(Integer idAsignacion) {
        List<Horario> lista = obtenerPorAsignacionId(idAsignacion);
        long total = 0L;
        for (Horario h : lista) {
            LocalTime ini = h.getHrIn();
            LocalTime fin = h.getHrFin();
            if (ini != null && fin != null) {
                total += Duration.between(ini, fin).toHours();
            }
        }
        return total;
    }

    public boolean existeTraslape(Integer idAsignacion, Dia dia, LocalTime hrIn, LocalTime hrFin) {
        Long count = entityManager
                .createQuery(
                        "SELECT COUNT(h) FROM Horario h " +
                                "WHERE h.idAsignacion.id = :asig " +
                                "AND h.dia = :dia " +
                                "AND h.hrIn < :fin " +
                                "AND h.hrFin > :ini", Long.class)
                .setParameter("asig", idAsignacion)
                .setParameter("dia", dia)
                .setParameter("ini", hrIn)
                .setParameter("fin", hrFin)
                .getSingleResult();
        return count != null && count > 0L;
    }

    public void guardar(Horario h) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(h);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void eliminar(Horario h) {
        try {
            entityManager.getTransaction().begin();
            Horario ref = entityManager.contains(h) ? h : entityManager.merge(h);
            entityManager.remove(ref);
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