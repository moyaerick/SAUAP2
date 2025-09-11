package mx.desarrollo.sauap.facade;

import mx.desarrollo.sauap.delegate.DelegateProfesor;
import mx.desarrollo.sauap.entity.Profesor;

public class FacadeProfesor {

    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor() {
        this.delegateProfesor = new DelegateProfesor();
    }

    public void guardarProfesor(Profesor profesor) {
        delegateProfesor.saveProfesor(profesor);
    }

}
