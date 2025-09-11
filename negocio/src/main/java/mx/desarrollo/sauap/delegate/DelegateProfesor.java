package mx.desarrollo.sauap.delegate;

import mx.desarrollo.sauap.entity.Profesor;
import mx.lsc.desarrollo.sauap.integration.ServiceLocator;

public class DelegateProfesor {
    public void saveProfesor(Profesor profesor) {
        ServiceLocator.getInstanceProfesorDAO().save(profesor);
    }

}
