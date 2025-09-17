package mx.sauap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 20)
    @NotNull
    @Column(name = "Psswd", nullable = false, length = 20)
    private String psswd;

    @NotNull
    @Column(name = "rol", nullable = false, columnDefinition = "ENUM('administrador','coordinador')")
    private String rol;

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

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}