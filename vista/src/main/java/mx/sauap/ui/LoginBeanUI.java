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
                if (usuario != null) usuario.setPsswd("");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Usuario o contraseña incorrecta", "Intente de nuevo"));
                return null;
            }
            this.usuario = auth;

            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("usuario", auth);

            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            this.usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ocurrió un problema al iniciar sesión", "Intente nuevamente"));
            return null;
        }
    }


    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Invalida toda la sesión
        externalContext.invalidateSession();

        // Redirige al login.xhtml con redirect
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
