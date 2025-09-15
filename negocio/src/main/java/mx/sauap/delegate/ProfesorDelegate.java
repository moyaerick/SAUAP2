package mx.sauap.delegate;

import mx.sauap.entity.Profesor;
import mx.sauap.facade.SistemaAcademicoFacade;

import java.util.ArrayList;
import java.util.List;

public class ProfesorDelegate {

    private SistemaAcademicoFacade facade;

    public ProfesorDelegate(SistemaAcademicoFacade facade) {
        this.facade = facade;
    }

    public List<Profesor> obtenerProfesores() {
        return facade.obtenerProfesores();
    }

    public List<Profesor> buscarProfesoresPorNombre(String nombre) {
        if(nombre == null || nombre.isEmpty()) {
            return obtenerProfesores();
        }
        List<Profesor> lista = new ArrayList<>();
        for(Profesor p : obtenerProfesores()) {
            if(p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                lista.add(p);
            }
        }
        return lista;
    }

    public void insertarProfesor(Profesor profesor) {
        facade.insertarProfesor(profesor);
    }

}

