package mx.sauap.gestores;

import mx.sauap.dao.AsignacionDAO;
import mx.sauap.entity.Asignacion;

import java.util.List;

public class GestorAsignacion {
    private AsignacionDAO asignacionDAO;

    public GestorAsignacion(AsignacionDAO dao) {
        this.asignacionDAO = dao;
    }

    public List<Asignacion> consultarAsignaciones() {
        return asignacionDAO.obtenerTodos();
    }

    public Asignacion consultarAsignacionPorId(Integer id) {
        return asignacionDAO.obtenerPorId(id);
    }

}
