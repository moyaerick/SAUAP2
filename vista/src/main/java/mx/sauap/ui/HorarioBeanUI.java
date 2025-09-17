package mx.sauap.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.sauap.entity.Asignacion;
import mx.sauap.entity.Dia;
import mx.sauap.entity.Horario;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.facade.SistemaAcademicoFacade;

@Named("horarioControl")
@ViewScoped
public class HorarioBeanUI implements Serializable {

    private SistemaAcademicoFacade facade;

    private List<Horario> listaHorarios;
    private List<Horario> listaFiltrada;
    private List<Asignacion> listaAsignaciones;
    private List<Integer> listaHoras;
    private List<Dia> listaDias;

    private Horario nuevoHorario;
    private Horario horarioAEliminar;

    private Integer hrInSeleccionada;
    private Integer hrFinSeleccionada;
    private Integer asignacionSeleccionada;
    private Dia diaSeleccionado;

    @PostConstruct
    public void init() {
        facade = new SistemaAcademicoFacade();

        listaHorarios = facade.consultarHorarios();
        // Prefetch para evitar lazy en UI
        for (Horario h : listaHorarios) {
            if (h.getIdAsignacion() != null && h.getIdAsignacion().getUnidadAprendizaje() != null) {
                h.getIdAsignacion().getUnidadAprendizaje().getNombre();
            }
        }
        listaFiltrada = new ArrayList<>(listaHorarios);

        listaAsignaciones = facade.consultarAsignaciones();
        listaDias = Arrays.asList(Dia.values());

        nuevoHorario = new Horario();

        listaHoras = new ArrayList<>();
        for (int h = 1; h <= 24; h++) {
            listaHoras.add(h);
        }
    }

    // ===== Navegación / CRUD =====
    public String horario_alta() {
        nuevoHorario = new Horario();
        return "/horarios/horario_alta.xhtml?faces-redirect=true";
    }

    public String cancelarAlta() {
        return "/horarios/horarios.xhtml?faces-redirect=true";
    }

    public void prepararEliminacion(Horario h) {
        this.horarioAEliminar = h;
    }

    public void eliminarHorarioConfirmado() {
        if (horarioAEliminar != null) {
            facade.eliminarHorario(horarioAEliminar);
            listaHorarios = facade.consultarHorarios();
            listaFiltrada = new ArrayList<>(listaHorarios);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Horario eliminado correctamente"));
        }
    }

    public String guardarHorarioAlta() {
        try {
            if (hrInSeleccionada == null || hrFinSeleccionada == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar ambas horas."));
                return null;
            }
            if (asignacionSeleccionada == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una asignación."));
                return null;
            }
            if (diaSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar un día."));
                return null;
            }

            LocalTime horaInicio = LocalTime.of(hrInSeleccionada, 0);
            LocalTime horaFin = LocalTime.of(hrFinSeleccionada, 0);
            if (horaFin.isBefore(horaInicio) || horaFin.equals(horaInicio)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La hora final debe ser mayor que la hora de inicio."));
                return null;
            }

            Asignacion asig = facade.consultarAsignacionPorId(asignacionSeleccionada);
            nuevoHorario.setDia(diaSeleccionado);
            nuevoHorario.setIdAsignacion(asig);
            nuevoHorario.setHrIn(horaInicio);
            nuevoHorario.setHrFin(horaFin);

            UnidadAprendizaje ua = asig.getUnidadAprendizaje();
            int maxPermitidas;
            switch (asig.getTipoAsignacion()) {
                case "clase":
                    maxPermitidas = ua.getHrsClase();
                    break;
                case "taller":
                    maxPermitidas = ua.getHrsTaller();
                    break;
                case "laboratorio":
                    maxPermitidas = ua.getHrsLab();
                    break;
                default:
                    maxPermitidas = 0;
            }

            long horasSolicitadas = Duration.between(horaInicio, horaFin).toHours();
            long horasRegistradas = facade.getHorasAsignadas(asig.getId());

            if (horasRegistradas + horasSolicitadas > maxPermitidas) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Límite excedido",
                                "El máximo de horas para " + asig.getTipoAsignacion() + " es " + maxPermitidas + " horas."));
                return null;
            }

            boolean exito = facade.guardarHorario(nuevoHorario);
            if (!exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                "El horario se traslapa con otro existente o ocurrió un error."));
                return null;
            }

            listaHorarios = facade.consultarHorarios();
            listaFiltrada = new ArrayList<>(listaHorarios);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Horario registrado correctamente"));
            return "/horarios/horarios.xhtml?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el horario"));
            return null;
        }
    }

    // ===== Soporte / consultas =====
    public long getHorasAsignadas(Integer idAsignacion) {
        return facade.getHorasAsignadas(idAsignacion);
    }

    // ===== Getters / Setters =====
    public Horario getNuevoHorario() {
        return nuevoHorario;
    }

    public void setNuevoHorario(Horario nuevoHorario) {
        this.nuevoHorario = nuevoHorario;
    }

    public List<Horario> getListaHorarios() {
        return listaHorarios;
    }

    public List<Horario> getListaFiltrada() {
        return listaFiltrada;
    }

    public List<Asignacion> getListaAsignaciones() {
        return listaAsignaciones;
    }

    public Integer getHrInSeleccionada() {
        return hrInSeleccionada;
    }

    public void setHrInSeleccionada(Integer hrInSeleccionada) {
        this.hrInSeleccionada = hrInSeleccionada;
    }

    public Integer getHrFinSeleccionada() {
        return hrFinSeleccionada;
    }

    public void setHrFinSeleccionada(Integer hrFinSeleccionada) {
        this.hrFinSeleccionada = hrFinSeleccionada;
    }

    public Integer getAsignacionSeleccionada() {
        return asignacionSeleccionada;
    }

    public void setAsignacionSeleccionada(Integer asignacionSeleccionada) {
        this.asignacionSeleccionada = asignacionSeleccionada;
    }

    public Dia getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(Dia diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public List<Dia> getListaDias() {
        return listaDias;
    }

    public List<Integer> getListaHoras() {
        return listaHoras;
    }
    public void setAsignacion(mx.sauap.entity.Asignacion a) {
        this.asignacionSeleccionada = (a != null ? a.getId() : null);
    }


}