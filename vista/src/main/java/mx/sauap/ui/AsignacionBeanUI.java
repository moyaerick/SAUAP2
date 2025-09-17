package mx.sauap.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import mx.sauap.entity.Asignacion;
import mx.sauap.entity.Profesor;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.facade.SistemaAcademicoFacade;

@Named("unidadAsignacion")
@ViewScoped
public class AsignacionBeanUI implements Serializable {

    @Inject
    private HorarioBeanUI horarioBeanUI;

    private final SistemaAcademicoFacade facade = new SistemaAcademicoFacade();

    private Integer idProfesor;
    private Integer idUA;
    private String tipoAsignacion;

    private List<Profesor> listaProfesores;
    private List<UnidadAprendizaje> listaUnidades;
    private List<Asignacion> listaAsignaciones;

    private Asignacion nuevaA;

    @PostConstruct
    public void init() {
        listaAsignaciones = facade.consultarA();
        listaUnidades = facade.consultarUA();
        listaProfesores = facade.obtenerProfesores();
    }

    public String asignacion_alta() {
        nuevaA = new Asignacion();
        return "/asignacion_alta.xhtml?faces-redirect=true";
    }

    public String guardarAAlta() {
        Profesor prof = facade.buscarProfesorPorId(idProfesor);
        UnidadAprendizaje ua = facade.buscarUnidadPorId(idUA);

        nuevaA = new Asignacion();
        nuevaA.setIdProfesor(prof);
        nuevaA.setIdUa(ua);
        nuevaA.setTipoAsignacion(tipoAsignacion);

        facade.guardarA(nuevaA);
        horarioBeanUI.setAsignacion(nuevaA);

        nuevaA = null;
        return "/asignaciones.xhtml?faces-redirect=true";
    }

    public String cancelarAAlta() {
        nuevaA = null;
        return "/asignaciones.xhtml?faces-redirect=true";
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getIdUA() {
        return idUA;
    }

    public void setIdUA(Integer idUA) {
        this.idUA = idUA;
    }

    public String getTipoAsignacion() {
        return tipoAsignacion;
    }

    public void setTipoAsignacion(String tipoAsignacion) {
        this.tipoAsignacion = tipoAsignacion;
    }

    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public List<UnidadAprendizaje> getListaUnidades() {
        return listaUnidades;
    }

    public List<Asignacion> getListaAsignaciones() {
        return listaAsignaciones;
    }
}
