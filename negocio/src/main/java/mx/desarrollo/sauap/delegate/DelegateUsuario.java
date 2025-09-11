package mx.desarrollo.sauap.delegate;

import mx.desarrollo.sauap.entity.Usuario;
import mx.lsc.desarrollo.sauap.integration.ServiceLocator;

import java.util.List;

public class DelegateUsuario {
    public Usuario login(String password, String correo) {
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = ServiceLocator.getInstanceUsuarioDAO().findAll();

        for (Usuario us : usuarios) {
            if (us.getPsswd().equalsIgnoreCase(password) && us.getNombre().equalsIgnoreCase(correo)) {
                usuario = us;
            }
        }
        return usuario;
    }

    public void saveUsario(Usuario usuario) {
        ServiceLocator.getInstanceUsuarioDAO().save(usuario);
    }

}
