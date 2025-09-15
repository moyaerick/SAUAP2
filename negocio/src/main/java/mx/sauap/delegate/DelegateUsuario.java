package mx.sauap.delegate;

import jakarta.persistence.EntityManager;
import mx.sauap.entity.Usuario;
import mx.sauap.integration.ServiceLocator;

import javax.swing.text.html.parser.Entity;
import java.security.Provider;
import java.util.List;

public class DelegateUsuario {


    public Usuario login(String psswd, String nombre) {
        // limpia para no reventar la consulta
        String n = (nombre == null) ? "" : nombre.trim();
        String p = (psswd  == null) ? "" : psswd;

        if (n.isEmpty() || p.isEmpty()) {
            return null; // nada que buscar → permite reintentar
        }

        var em = mx.sauap.integration.ServiceLocator.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT u FROM Usuario u " +
                                    "WHERE u.nombre = :n AND u.psswd = :p",
                            Usuario.class)
                    .setParameter("n", n.toLowerCase())
                    .setParameter("p", p)
                    .setMaxResults(1)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } catch (Exception ex) {
            // cualquier problema (tipos, formato, etc.) no tumba la app
            return null;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }


    public void saveUsario(Usuario usuario){
        ServiceLocator.getInstanceUsuarioDAO().save(usuario);
    }

}