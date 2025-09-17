package mx.sauap.ui;

import java.io.Serializable;
import java.time.LocalTime;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.sauap.entity.Asignacion;
import mx.sauap.entity.Horario;
import mx.sauap.facade.SistemaAcademicoFacade;

@Named("unidadHorario")
@ViewScoped
public class HorarioBeanUI implements Serializable {
    private String dia;
    private LocalTime hrIn;
    private LocalTime hrFin;
    private Asignacion asignacion;
    private final SistemaAcademicoFacade facade=new  SistemaAcademicoFacade();

    public String guardarHorario(){
        Horario h=new Horario();
        h.setDia(dia);
        h.setHrIn(hrIn);
        h.setHrFin(hrFin);
        h.setIdAsignacion(asignacion);
        facade.guardarH(h);
        return "asignaciones.xhtml?faces-redirect=true";
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getHrIn() {
        return hrIn;
    }
    public void setHrIn(LocalTime hrIn) {
        this.hrIn = hrIn;
    }

    public LocalTime getHrFin() {
        return hrFin;
    }
    public void setHrFin(LocalTime hrFin) {
        this.hrFin = hrFin;
    }

    public Asignacion getAsignacion() {
        return asignacion;
    }
    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }
}