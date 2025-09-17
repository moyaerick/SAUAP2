/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sauap.ui;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.sauap.entity.Usuario;
import mx.sauap.helper.LoginHelper;

import java.io.IOException;
import java.io.Serializable;

@Named("loginUI")
@SessionScoped
public class LoginBeanUI implements Serializable {
    private LoginHelper loginHelper;
    private Usuario usuario;

    public LoginBeanUI() {
        loginHelper = new LoginHelper();
    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo
     * sera la primero que haga cuando cargue la pagina
     */
    @PostConstruct
    public void init(){
        usuario= new Usuario();
    }

    public String login() {
        try {
            Usuario auth = loginHelper.Login(usuario.getNombre(), usuario.getPsswd());
            if (auth == null) {
                // intento fallido: no navegar, permitir reintento
                if (usuario != null) usuario.setPsswd(""); // opcional, limpiar pass
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Usuario o contraseña incorrecta", "Intente de nuevo"));
                return null; // se queda en login.xhtml
            }
            this.usuario = auth; // exito
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            // cualquier excepcion NO debe romper el programa
            this.usuario = new Usuario(); // reset limpio para reintentar
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocurrio un problema al iniciar sesion", "Intente nuevamente"));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext();
        usuario = null;
        // navegar al login con redirect (evita volver con el boton atras)
        return "/login.xhtml?faces-redirect=true";
    }

    /* getters y setters*/

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}