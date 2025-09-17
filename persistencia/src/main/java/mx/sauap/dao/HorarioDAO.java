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
                .createQuery("SELECT u FROM Horario u", Horario.class)
                .getResultList();
    }

    public long getHorasAsignadas(Integer idAsignacion) {
        List<Horario> horarios = obtenerTodos();
        // Calcula la cantidad total de horas asignadas a una asignación específica.
        // 1. Obtenemos todos los horarios existentes.
        // 2. Filtramos solo aquellos que pertenecen a la asignación indicada.
        // 3. Para cada horario, calculamos la duración en horas entre hrIn y hrFin.
        // 4. Sumamos todas las horas para obtener el total.
        // Se usa un Stream con lambdas para hacerlo de forma concisa y legible.
        return horarios.stream()
                .filter(h -> h.getIdAsignacion().getId().equals(idAsignacion))
                .mapToLong(h -> java.time.Duration.between(h.getHrIn(), h.getHrFin()).toHours())
                .sum();

    }

    public void delete(Horario horario) {
        try {
            entityManager.getTransaction().begin();

            Horario h = entityManager.find(Horario.class, horario.getId());
            if (h != null) {
                entityManager.remove(h);
                System.out.println("Horario eliminado: " + horario.getId());
            } else {
                System.out.println("Horario no encontrado: " + horario.getId());
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }



    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}