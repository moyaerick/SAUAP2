package mx.sauap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Profesor", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 50)
    @NotNull
    @Column(name = "Ap_Primero", nullable = false, length = 50)
    private String apPrimero;

    @Size(max = 50)
    @Column(name = "Ap_Segundo", length = 50)
    private String apSegundo;

    @Size(max = 13)
    @NotNull
    @Column(name = "RFC", nullable = false, length = 13)
    private String rfc;

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