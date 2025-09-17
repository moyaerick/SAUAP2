package mx.sauap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;

@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Horario", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_Asignacion", nullable = false)
    private Asignacion idAsignacion;

    @NotNull
    @Column(name = "dia", length=3, nullable = false)
    private Dia dia;

    @NotNull
    @Column(name = "hr_in", nullable = false)
    private LocalTime hrIn;

    @NotNull
    @Column(name = "hr_fin", nullable = false)
    private LocalTime hrFin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Asignacion getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Asignacion idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
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

}