package mx.sauap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Asignacion", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_Profesor", nullable = false)
    private Profesor idProfesor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_UA", nullable = false)
    private UnidadAprendizaje idUa;

    @NotNull
    @Lob
    @Column(name = "tipo_asignacion", nullable = false)
    private String tipoAsignacion;

    // ===== GETTERS/SETTERS =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    public UnidadAprendizaje getIdUa() {
        return idUa;
    }

    public void setIdUa(UnidadAprendizaje idUa) {
        this.idUa = idUa;
    }

    public String getTipoAsignacion() {
        return tipoAsignacion;
    }

    public void setTipoAsignacion(String tipoAsignacion) {
        this.tipoAsignacion = tipoAsignacion;
    }

    // ===== NUEVOS GETTERS PARA JSF =====
    // Esto permite usar #{hor.idAsignacion.unidadAprendizaje.nombre} en el horrario.xhtml
    @Transient
    public UnidadAprendizaje getUnidadAprendizaje() {
        return idUa;
    }
    // Esto permite usar #{hor.idAsignacion.profesor.nombre} en el horrario.xhtml
    @Transient
    public Profesor getProfesor() {
        return idProfesor;
    }

}
