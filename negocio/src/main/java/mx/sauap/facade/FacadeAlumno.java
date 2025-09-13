package mx.sauap.facade;

import mx.sauap.delegate.DelegateAlumno;
import mx.sauap.entity.Alumno;

public class FacadeAlumno {

    private final DelegateAlumno delegateAlumno;

    public FacadeAlumno() {
        this.delegateAlumno = new DelegateAlumno();
    }

    public void guardarAlumno(Alumno alumno){
        delegateAlumno.saveAlumno(alumno);
    }

}
