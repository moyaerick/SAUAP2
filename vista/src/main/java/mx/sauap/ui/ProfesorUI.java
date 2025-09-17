package mx.sauap.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mx.sauap.delegate.ProfesorDelegate;
import mx.sauap.entity.Profesor;
import mx.sauap.facade.SistemaAcademicoFacade;

@Named("profesorBean")
@ViewScoped
public class ProfesorUI implements Serializable {

    private ProfesorDelegate delegate;
    private List<Profesor> profesores;
    private List<Profesor> listaFiltrada;

    private String nombreFiltro = "";
    private String nombre;
    private String apPrimero;
    private String apSegundo;
    private String rfc;

    public ProfesorUI() {
    }

    @PostConstruct
    public void init() {
        delegate = new ProfesorDelegate(new SistemaAcademicoFacade());
        profesores = delegate.obtenerProfesores();
        if (profesores == null) profesores = new ArrayList<Profesor>();
        ordenarPorNombre();
        listaFiltrada = new ArrayList<Profesor>(profesores);


        if (delegate == null) {
            delegate = new ProfesorDelegate(new SistemaAcademicoFacade());
        }


        profesores = delegate.obtenerProfesores();
        if (profesores == null) {
            profesores = new ArrayList<Profesor>();
        }

        ordenarPorNombre();
        listaFiltrada = new ArrayList<Profesor>(profesores);
    }

    private void ordenarPorNombre() {
        Collections.sort(profesores, new Comparator<Profesor>() {
            public int compare(Profesor a, Profesor b) {
                String na = (a.getNombre() == null) ? "" : a.getNombre().toLowerCase();
                String nb = (b.getNombre() == null) ? "" : b.getNombre().toLowerCase();
                return na.compareTo(nb);
            }
        });
    }

    public String guardarProfesor() {
        try {
            Profesor profesor = new Profesor();
            profesor.setNombre(nombre);
            profesor.setApPrimero(apPrimero);
            profesor.setApSegundo(apSegundo);
            profesor.setRfc(rfc);

            delegate.insertarProfesor(profesor);

            profesores = delegate.obtenerProfesores();
            ordenarPorNombre();
            aplicarFiltro();

            profesores = delegate.obtenerProfesores();
            if (profesores == null) {
                profesores = new ArrayList<Profesor>();
            }
            ordenarPorNombre();
            listaFiltrada = new ArrayList<Profesor>(profesores);

            nombre = null;
            apPrimero = null;
            apSegundo = null;
            rfc = null;

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Profesor registrado correctamente")
            );
            return null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el profesor")
            );
            return null;
        }
    }

    public void aplicarFiltro() {
        if (profesores == null) {
            profesores = new ArrayList<Profesor>();
        }
        ordenarPorNombre();

        if (nombreFiltro == null || nombreFiltro.trim().isEmpty()) {
            listaFiltrada = new ArrayList<Profesor>(profesores);
            return;
        }

        String pref = nombreFiltro.toLowerCase().trim();

        int i = lowerBound(profesores, pref);
        List<Profesor> out = new ArrayList<Profesor>();
        while (i < profesores.size()) {
            String n = (profesores.get(i).getNombre() == null) ? "" : profesores.get(i).getNombre().toLowerCase();
            if (n.startsWith(pref)) {
                out.add(profesores.get(i));
                i++;
            } else {
                break;
            }
        }
        listaFiltrada = out;
    }

    private int lowerBound(List<Profesor> list, String pref) {
        int lo = 0;
        int hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            String n = (list.get(mid).getNombre() == null) ? "" : list.get(mid).getNombre().toLowerCase();
            if (n.compareTo(pref) < 0) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Profesor> getListaProfesores() {
        return profesores;
    }

    public List<Profesor> getListaFiltrada() {
        if (listaFiltrada == null) {
            listaFiltrada = new ArrayList<Profesor>(profesores);
        }
        return listaFiltrada;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String nombreFiltro) {
        this.nombreFiltro = nombreFiltro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPrimero() {
        return apPrimero;
    }

    public void setApPrimero(String apPrimero) {
        this.apPrimero = apPrimero;
    }

    public String getApSegundo() {
        return apSegundo;
    }

    public void setApSegundo(String apSegundo) {
        this.apSegundo = apSegundo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}