package mx.sauap.gestores;

import mx.sauap.dao.HorarioDAO;
import mx.sauap.entity.Horario;

import java.util.List;

public class GestorHorario {

    private HorarioDAO horarioDAO;

    public GestorHorario(HorarioDAO horarioDAO) {
        this.horarioDAO = horarioDAO;
    }

    public List<Horario> consultarHorarios() {
        return horarioDAO.obtenerTodos();
    }

    public long getHorasAsignadas(Integer idAsignacion) {
        return horarioDAO.getHorasAsignadas(idAsignacion);
    }

    public boolean hayTraslape(Horario nuevo) {
        List<Horario> existentes = consultarHorarios();
        for (Horario h : existentes) {
            if (h.getIdAsignacion().getId().equals(nuevo.getIdAsignacion().getId())
                    && h.getDia() == nuevo.getDia()) { // comparar enums con ==, pero por que? porque son singletons

                // Verifica traslape
                if (!nuevo.getHrFin().isBefore(h.getHrIn()) && !nuevo.getHrIn().isAfter(h.getHrFin())) {
                    return true;
                }
            }
        }
        return false;
    }



    public boolean insertarHorario(Horario horario) {
        try {
            if(hayTraslape(horario)) {
                System.out.println("No se inserta: hay traslape");
                return false; // no inserta si hay traslape
            }

            horarioDAO.save(horario);
            System.out.println("Horario insertado: " + horario);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            if(horarioDAO.getEntityManager().getTransaction().isActive()) {
                horarioDAO.getEntityManager().getTransaction().rollback();
            }
            return false;
        }
    }


    public void actualizarHorario(Horario horario) {
        try {
            horarioDAO.getEntityManager().getTransaction().begin();
            horarioDAO.update(horario);
            horarioDAO.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (horarioDAO.getEntityManager().getTransaction().isActive()) {
                horarioDAO.getEntityManager().getTransaction().rollback();
            }
        }
    }

    public void eliminarHorario(Horario horario) {
        try {
            horarioDAO.delete(horario);
            System.out.println("Horario eliminado correctamente: " + horario.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
