package mx.sauap.delegate;

import mx.sauap.entity.Alumno;
import mx.sauap.integration.ServiceLocator;

public class DelegateAlumno {
    public void saveAlumno(Alumno alumno){
        ServiceLocator.getInstanceAlumnoDAO().save(alumno);
    }

}