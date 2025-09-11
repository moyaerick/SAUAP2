/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;


import mx.desarrollo.sauap.integration.ServiceFacadeLocator;
import mx.desarrollo.sauap.entity.Usuario;

import java.io.Serializable;

public class LoginHelper implements Serializable {


    /**
     * Metodo para hacer login llamara a la instancia de usuarioFacade
     *
     * @param nombre
     * @param password
     * @return
     */
    public Usuario Login(String nombre, String password) {
        return ServiceFacadeLocator.getInstanceFacadeUsuario().login(password, nombre);
    }


}
