package mx.sauap.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.sauap.entity.Asignacion;
import mx.sauap.entity.Profesor;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.facade.SistemaAcademicoFacade;

@Named("unidadAsignacion")
@ViewScoped
public class AsignacionBeanUI implements Serializable {
    private final HorarioBeanUI horarioBeanUI;
    private Integer idProfesor;
    private Integer idUA;
    private String tipoAsignacion;
    private List<Profesor> listaProfesores;
    private List<UnidadAprendizaje> listaUnidades;
    private final SistemaAcademicoFacade facade = new SistemaAcademicoFacade();
    private List<Asignacion> listaAsignaciones;   // lista completa (ordenada)
    private Asignacion nuevaA;

    @Inject
    public AsignacionBeanUI(@Named("unidadHorario") HorarioBeanUI horarioBeanUI) {
        this.horarioBeanUI = horarioBeanUI;
    }

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

    public String guardarAAlta(){
        Profesor prof=facade.buscarProfesorPorId(this.idProfesor);
        UnidadAprendizaje ua=facade.buscarUnidadPorId(this.idUA);

        nuevaA=new Asignacion();
        nuevaA.setIdProfesor(prof);
        nuevaA.setIdUa(ua);
        nuevaA.setTipoAsignacion(tipoAsignacion);
        facade.guardarA(nuevaA);
        horarioBeanUI.setAsignacion(nuevaA);
        nuevaA=null;
        return "/asignaciones.xhtml?faces-redirect=true";
    }

    // getters/setters
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

    public String cancelarAAlta() {
        nuevaA = null;
        return "/asignaciones.xhtml?faces-redirect=true";
    }
}