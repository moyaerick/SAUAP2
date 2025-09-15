package mx.sauap.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import mx.sauap.dao.ProfesorDAO;
import mx.sauap.delegate.ProfesorDelegate;
import mx.sauap.entity.Profesor;
import mx.sauap.facade.SistemaAcademicoFacade;
import mx.sauap.gestores.GestorProfesor;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named("profesorBean")
@RequestScoped
public class ProfesorUI {

    private ProfesorDelegate delegate;
    private List<Profesor> profesores;
    private List<Profesor> listaFiltrada;

    private String nombreFiltro = "";
    private String nombre;
    private String apPrimero;
    private String apSegundo;
    private String rfc;

    @PostConstruct
    public void init() {
        try {
            EntityManager em = Persistence
                    .createEntityManagerFactory("persistencePU")
                    .createEntityManager();

            ProfesorDAO dao = new ProfesorDAO(em);
            GestorProfesor gestor = new GestorProfesor(dao);
            SistemaAcademicoFacade facade = new SistemaAcademicoFacade(gestor);
            delegate = new ProfesorDelegate(facade);

            profesores = delegate.obtenerProfesores();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Profesor> getProfesores() {
        List<Profesor> lista = delegate != null ? delegate.obtenerProfesores() : null;
        System.out.println("Profesores cargados: " + (lista != null ? lista.size() : "null"));
        return profesores;
    }

    public String guardarProfesor() {
        System.out.println("Entró a guardarProfesor");
        try {
            Profesor profesor = new Profesor();
            profesor.setNombre(nombre);
            profesor.setApPrimero(apPrimero);
            profesor.setApSegundo(apSegundo);
            profesor.setRfc(rfc);

            delegate.insertarProfesor(profesor);

            // Actualizar la lista de profesores
            profesores = delegate.obtenerProfesores();

            // Limpiar campos del formulario
            nombre = apPrimero = apSegundo = rfc = null;

            // Mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Profesor registrado correctamente"));

            return null; // quedarse en la misma página

        } catch (Exception e) {
            e.printStackTrace();

            // Mensaje de error
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el profesor"));

            return null;
        }
    }

    // ===== GETTERS Y SETTERS =====
    public List<Profesor> getListaProfesores() {
        return profesores;
    }

    public List<Profesor> getListaFiltrada() {
        return listaFiltrada;
    }

    public String getFiltro() {
        return nombreFiltro;
    }

    public void setFiltro(String nombreFiltro) {
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


    // Getter y setter de nombreFiltro
    public String getNombreFiltro() { return nombreFiltro; }
    public void setNombreFiltro(String nombreFiltro) { this.nombreFiltro = nombreFiltro; }

    //filtro simple por busqueda binaria
    public void aplicarFiltro() {
        // Asegúrate de ordenar primero
        profesores.sort(Comparator.comparing(p -> (p.getNombre() == null ? "" : p.getNombre().toLowerCase())));

        if (nombreFiltro == null || nombreFiltro.trim().isEmpty()) {
            listaFiltrada = new ArrayList<>(profesores);
            return;
        }
        String pref = nombreFiltro.toLowerCase().trim();

        int i = lowerBound(profesores, pref);   // primer indice cuyo nombre >= pref
        List<Profesor> out = new ArrayList<>();
        while (i < profesores.size()) {
            String n = (profesores.get(i).getNombre() == null ? "" : profesores.get(i).getNombre().toLowerCase());
            if (n.startsWith(pref)) {
                out.add(profesores.get(i));
                i++;
            } else {
                break; // ya no hay mas coincidencias de ese prefijo
            }
        }
        listaFiltrada = out;
    }

    // busqueda binaria para encontrar el primer indice cuyo nombre >= pref
    private int lowerBound(List<Profesor> list, String pref) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            String n = (list.get(mid).getNombre() == null ? "" : list.get(mid).getNombre().toLowerCase());
            if (n.compareTo(pref) < 0) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

}
