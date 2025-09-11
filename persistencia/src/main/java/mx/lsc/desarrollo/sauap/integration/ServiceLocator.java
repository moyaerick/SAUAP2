package mx.lsc.desarrollo.sauap.integration;

import jakarta.persistence.EntityManager;
import mx.lsc.desarrollo.sauap.dao.*;
import mx.lsc.desarrollo.sauap.persistence.HibernateUtil;


/**
 *
 * @author total
 */
public class ServiceLocator {

    private static AsignacionDAO asignacionDAO;
    private static HorarioDAO horarioDAO;
    private static ProfesorDAO profesorDAO;
    private static UnidadAprendizajeDAO unidadAprendizajeDAO;
    private static UsuarioDAO usuarioDAO;

    private static EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }

    /**
     * se crea la instancia para AsignacionDAO si esta no existe
     */
    public static AsignacionDAO getInstanceAsignacionDAO() {
        if (asignacionDAO == null) {
            asignacionDAO = new AsignacionDAO(getEntityManager());
            return asignacionDAO;
        } else {
            return asignacionDAO;
        }
    }

    /**
     * se crea la instancia para HorarioDAO si esta no existe
     */
    public static HorarioDAO getInstanceHorarioDAO() {
        if (horarioDAO == null) {
            horarioDAO = new HorarioDAO(getEntityManager());
            return horarioDAO;
        } else {
            return horarioDAO;
        }
    }

    /**
     * se crea la instancia para ProfesorDAO si esta no existe
     */
    public static ProfesorDAO getInstanceProfesorDAO() {
        if (profesorDAO == null) {
            profesorDAO = new ProfesorDAO(getEntityManager());
            return profesorDAO;
        } else {
            return profesorDAO;
        }
    }

    /**
     * se crea la instancia para UnidadAprendizajeDAO si esta no existe
     */
    public static UnidadAprendizajeDAO getInstanceUnidadAprendizajeDAO() {
        if (unidadAprendizajeDAO == null) {
            unidadAprendizajeDAO = new UnidadAprendizajeDAO(getEntityManager());
            return unidadAprendizajeDAO;
        } else {
            return unidadAprendizajeDAO;
        }
    }

    /**
     * se crea la instancia de usuarioDAO si esta no existe
     */
    public static UsuarioDAO getInstanceUsuarioDAO() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else {
            return usuarioDAO;
        }
    }

}