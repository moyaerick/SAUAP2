package mx.sauap.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.sauap.entity.*;
import mx.sauap.dao.HorarioDAO;
import mx.sauap.facade.SistemaAcademicoFacade;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("unidadHorario")
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
    private HorarioDAO horarioDAO;
    private Integer hrInSeleccionada;
    private Integer hrFinSeleccionada;
    private Integer asignacionSeleccionada;
    private Dia diaSeleccionado;

    @PostConstruct
    public void init() {
        facade = new SistemaAcademicoFacade();
        listaHorarios = facade.consultarHorarios();

        for (Horario h : listaHorarios) {
            if (h.getIdAsignacion() != null && h.getIdAsignacion().getIdUa() != null) {
                h.getIdAsignacion().getIdUa().getNombre();
            }
        }

        listaFiltrada = new ArrayList<>(listaHorarios);

        listaAsignaciones = facade.consultarA();

        listaDias = Arrays.asList(Dia.values()); // Java 8 compatible

        nuevoHorario = new Horario();

        listaHoras = new ArrayList<>();
        for (int h = 1; h <= 24; h++) { // de 1 a 24
            listaHoras.add(h);
        }
    }


    // ===== GETTERS/SETTERS =====
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

    public List<Horario> getHorariosPorAsignacion(Integer idAsignacion){
        return listaHorarios.stream().filter(h->h.getIdAsignacion().getId().equals(idAsignacion)).toList();
    }

    // ===== CRUD =====
    public String horario_alta() {
        // Inicializa un nuevo horario vacío
        nuevoHorario = new Horario();
        return "/horarios/horario_alta.xhtml?faces-redirect=true";
    }

    public long getHorasAsignadas(Integer idAsignacion) {
        return facade.getHorasAsignadas(idAsignacion);
    }

    // Getter y Setter para las horas seleccionadas
    public Integer getHrInSeleccionada() { return hrInSeleccionada; }
    public void setHrInSeleccionada(Integer hrInSeleccionada) { this.hrInSeleccionada = hrInSeleccionada; }

    public Integer getHrFinSeleccionada() { return hrFinSeleccionada; }
    public void setHrFinSeleccionada(Integer hrFinSeleccionada) { this.hrFinSeleccionada = hrFinSeleccionada; }

    public Integer getAsignacionSeleccionada() { return asignacionSeleccionada; }
    public void setAsignacionSeleccionada(Integer asignacionSeleccionada) { this.asignacionSeleccionada = asignacionSeleccionada; }

    public Dia getDiaSeleccionado() { return diaSeleccionado; }
    public void setDiaSeleccionado(Dia diaSeleccionado) { this.diaSeleccionado = diaSeleccionado; }

    public List<Dia> getListaDias() { return listaDias; }

    private boolean hayTraslape(Horario nuevo) {
        for (Horario h : listaHorarios) {
            // Revisar solo si es la misma asignación y el mismo día
            if (h.getIdAsignacion().getId().equals(nuevo.getIdAsignacion().getId())
                    && h.getDia().equals(nuevo.getDia())) {

                // Si hay traslape
                if (!nuevo.getHrFin().isBefore(h.getHrIn()) && !nuevo.getHrIn().isAfter(h.getHrFin())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String guardarHorarioAlta() {
        try {
            // Validaciones básicas
            if(hrInSeleccionada == null || hrFinSeleccionada == null){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar ambas horas."));
                return null;
            }

            if(asignacionSeleccionada == null){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una asignación."));
                return null;
            }

            // Convertir a LocalTime
            LocalTime horaInicio = LocalTime.of(hrInSeleccionada, 0);
            LocalTime horaFin = LocalTime.of(hrFinSeleccionada, 0);

            if (horaFin.isBefore(horaInicio) || horaFin.equals(horaInicio)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La hora final debe ser mayor que la hora de inicio."));
                return null;
            }

            if (diaSeleccionado == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una dia."));
            }

            // Obtener la asignación seleccionada
            Asignacion asig = facade.consultarAsignacionPorId(asignacionSeleccionada);
            nuevoHorario.setDia(diaSeleccionado);
            nuevoHorario.setIdAsignacion(asig);
            nuevoHorario.setHrIn(horaInicio);
            nuevoHorario.setHrFin(horaFin);

            // Validar horas máximas permitidas
            UnidadAprendizaje ua = asig.getIdUa();
            int maxPermitidas = switch (asig.getTipoAsignacion()) {
                case "clase" -> ua.getHrsClase();
                case "taller" -> ua.getHrsTaller();
                case "laboratorio" -> ua.getHrsLab();
                default -> 0;
            };

            long horasSolicitadas = Duration.between(horaInicio, horaFin).toHours();
            long horasRegistradas = facade.getHorasAsignadas(asig.getId());

            if(horasRegistradas + horasSolicitadas > maxPermitidas){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Límite excedido",
                                "El máximo de horas para " + asig.getTipoAsignacion() +
                                        " es " + maxPermitidas + " horas."));
                return null;
            }

            // Validación de traslape
            boolean exito = facade.guardarHorario(nuevoHorario); // Debe devolver boolean según GestorHorario
            if(!exito){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El horario se traslapa con otro existente o ocurrió un error."));
                return null;
            }

            // Actualizar listas
            listaHorarios = facade.consultarHorarios();
            listaFiltrada = new ArrayList<>(listaHorarios);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Éxito", "Horario registrado correctamente"));
            return "/horarios/horarios.xhtml?faces-redirect=true";

        } catch(Exception e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo guardar el horario"));
            return null;
        }
    }




    public String cancelarAlta() {
        return "/horarios/horarios?faces-redirect=true";
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
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Éxito","Horario eliminado correctamente"));
        }
    }


    //Getter para tener la lista de horas al hacer las altas
    public List<Integer> getListaHoras() {
        return listaHoras;
    }
}