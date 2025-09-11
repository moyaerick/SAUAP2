package mx.desarrollo.sauap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "unidad_aprendizaje")
public class UnidadAprendizaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UA", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "hrs_clase", nullable = false)
    private Integer hrsClase;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "hrs_taller", nullable = false)
    private Integer hrsTaller;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "hrs_lab", nullable = false)
    private Integer hrsLab;

    @OneToMany(mappedBy = "idUa")
    private Set<Asignacion> asignacions = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getHrsClase() {
        return hrsClase;
    }

    public void setHrsClase(Integer hrsClase) {
        this.hrsClase = hrsClase;
    }

    public Integer getHrsTaller() {
        return hrsTaller;
    }

    public void setHrsTaller(Integer hrsTaller) {
        this.hrsTaller = hrsTaller;
    }

    public Integer getHrsLab() {
        return hrsLab;
    }

    public void setHrsLab(Integer hrsLab) {
        this.hrsLab = hrsLab;
    }

    public Set<Asignacion> getAsignacions() {
        return asignacions;
    }

    public void setAsignacions(Set<Asignacion> asignacions) {
        this.asignacions = asignacions;
    }

}