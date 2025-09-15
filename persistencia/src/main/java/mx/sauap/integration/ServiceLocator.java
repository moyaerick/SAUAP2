/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sauap.integration;


import jakarta.persistence.EntityManager;
import mx.sauap.dao.UsuarioDAO;
import mx.sauap.persistence.HibernateUtil;

/**
 *
 * @author total
 */
public class ServiceLocator {

    private static UsuarioDAO usuarioDAO;

    public static EntityManager getEntityManager(){

        return HibernateUtil.getEntityManager();
    }

    /**
     * se crea la instancia para alumno DAO si esta no existe
     */
    /**
     * se crea la instancia de usuarioDAO si esta no existe
     */
    public static UsuarioDAO getInstanceUsuarioDAO(){
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else{
            return usuarioDAO;
        }
    }
    
}
