package mx.desarrollo.sauap.integration;

import mx.desarrollo.sauap.facade.FacadeProfesor;
import mx.desarrollo.sauap.facade.FacadeUsuario;

public class ServiceFacadeLocator {

    private static FacadeProfesor facadeProfesor;
    private static FacadeUsuario facadeUsuario;

    public static FacadeProfesor getInstanceFacadeProfesor() {
        if (facadeProfesor == null) {
            facadeProfesor = new FacadeProfesor();
            return facadeProfesor;
        } else {
            return facadeProfesor;
        }
    }

    public static FacadeUsuario getInstanceFacadeUsuario() {
        if (facadeUsuario == null) {
            facadeUsuario = new FacadeUsuario();
            return facadeUsuario;
        } else {
            return facadeUsuario;
        }
    }
}