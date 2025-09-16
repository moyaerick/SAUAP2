package mx.sauap.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import mx.sauap.entity.UnidadAprendizaje;
import mx.sauap.facade.SistemaAcademicoFacade;

@Named("unidadControl")
@SessionScoped
public class UnidadBeanUI implements Serializable {

    private final SistemaAcademicoFacade facade = new SistemaAcademicoFacade();

    private List<UnidadAprendizaje> listaUnidades;   // lista completa (ordenada)
    private List<UnidadAprendizaje> listaFiltrada;   // lo que se muestra
    private String filtro = "";                      // lo que escribe el usuario
    private final Comparator<UnidadAprendizaje> byNombre =
            Comparator.comparing(u -> (u.getNombre() == null ? "" : u.getNombre().toLowerCase()));

    private UnidadAprendizaje unidadAEliminar;
    private UnidadAprendizaje nuevaUA;
    private UnidadAprendizaje unidadAEditar;

    @PostConstruct
    public void init() {
        listaUnidades = facade.consultarUA();
        listaUnidades.sort(byNombre);              // importante: ordenada por nombre
        listaFiltrada = new ArrayList<>(listaUnidades);
    }
    public String ua_alta() {
        nuevaUA = new UnidadAprendizaje();
        nuevaUA.setHrsClase(0);
        nuevaUA.setHrsTaller(0);
        nuevaUA.setHrsLab(0);
        return "/ua_alta.xhtml?faces-redirect=true";
    }

    public String guardarEdicion() {
        if (unidadAEditar != null) {
            facade.actualizarUA(unidadAEditar);
            listaUnidades = facade.consultarUA();
            listaUnidades.sort(byNombre);
            aplicarFiltro(); // mantener filtro aplicado
            unidadAEditar = null;
        }
        return "/unidades.xhtml?faces-redirect=true";
    }



    //filtro simple por prefijo con busqueda binaria
    public void aplicarFiltro() {
        if (filtro == null || filtro.trim().isEmpty()) {
            listaFiltrada = new ArrayList<>(listaUnidades);
            return;
        }
        String pref = filtro.toLowerCase().trim();

        int i = lowerBound(listaUnidades, pref);   // primer indice cuyo nombre >= pref
        List<UnidadAprendizaje> out = new ArrayList<>();
        while (i < listaUnidades.size()) {
            String n = (listaUnidades.get(i).getNombre() == null ? "" : listaUnidades.get(i).getNombre().toLowerCase());
            if (n.startsWith(pref)) {
                out.add(listaUnidades.get(i));
                i++;
            } else {
                break; // ya no hay mas coincidencias de ese prefijo
            }
        }
        listaFiltrada = out;
    }

    // busqueda binaria para encontrar el primer indice cuyo nombre >= pref
    private int lowerBound(List<UnidadAprendizaje> list, String pref) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            String n = (list.get(mid).getNombre() == null ? "" : list.get(mid).getNombre().toLowerCase());
            if (n.compareTo(pref) < 0) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // eliminar
    public void prepararEliminacion(UnidadAprendizaje ua) {
        this.unidadAEliminar = ua;
    }

    public void eliminarUAConfirmada() {
        if (unidadAEliminar != null) {
            facade.eliminarUA(unidadAEliminar);
            listaUnidades = facade.consultarUA();
            listaUnidades.sort(byNombre);   // mantener orden
            aplicarFiltro();                // re-aplicar filtro actual
            unidadAEliminar = null;
        }
    }

    // getters/setters
    public List<UnidadAprendizaje> getListaUnidades() {
        return listaUnidades;
    }
    public List<UnidadAprendizaje> getListaFiltrada() {
        return listaFiltrada;
    }

    public String getFiltro() {
        return filtro;
    }
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    public UnidadAprendizaje getNuevaUA() {
        if (nuevaUA == null) {
            nuevaUA = new UnidadAprendizaje();
            nuevaUA.setHrsClase(0);
            nuevaUA.setHrsTaller(0);
            nuevaUA.setHrsLab(0);
        }
        return nuevaUA;
    }
    public void setNuevaUA(UnidadAprendizaje u) {
        this.nuevaUA = u;
    }

    public String guardarUAAlta() {
        facade.guardarUA(getNuevaUA()); // usa el getter para asegurar no-null
        nuevaUA = null;                 // limpia el buffer
        return "/unidades.xhtml?faces-redirect=true";
    }

    public String cancelarAlta() {
        nuevaUA = null;
        return "/unidades.xhtml?faces-redirect=true";
    }

    public UnidadAprendizaje getUnidadAEditar() {
        return unidadAEditar;
    }
    public void setUnidadAEditar(UnidadAprendizaje unidadAEditar) {
        this.unidadAEditar = unidadAEditar;
    }

    public String irModificarUA(UnidadAprendizaje ua) {
        this.unidadAEditar = ua;
        return "/ua_modificar.xhtml?faces-redirect=true";
    }

}
