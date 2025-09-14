package mx.sauap.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.facade.SistemaAcademicoFacade;

import java.io.Serializable;
import java.util.List;
@Named("unidadControl")
@RequestScoped
public class UnidadBeanUI implements Serializable {

    private List<UnidadAprendizaje> listaUnidades;
    private UnidadAprendizaje unidadAEliminar;
    private final SistemaAcademicoFacade facade = new SistemaAcademicoFacade();

    @PostConstruct
    public void init() {
        listaUnidades = facade.consultarUA();
    }

    public List<UnidadAprendizaje> getListaUnidades() {
        return listaUnidades;
    }

    public void prepararEliminacion(UnidadAprendizaje ua) {
        this.unidadAEliminar = ua;
    }

    public void eliminarUAConfirmada() {
        if (unidadAEliminar != null) {
            facade.eliminarUA(unidadAEliminar);
            listaUnidades = facade.consultarUA();
            unidadAEliminar = null;
        }
    }
}
