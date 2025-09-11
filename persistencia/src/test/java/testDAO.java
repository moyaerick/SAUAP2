import mx.lsc.desarrollo.sauap.dao.UsuarioDAO;
import mx.lsc.desarrollo.sauap.persistence.HibernateUtil;
import mx.desarrollo.sauap.entity.Usuario;

import java.util.List;

public class testDAO {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());


        for (Usuario usuario : usuarioDAO.findAll()) {
            System.out.println(usuario + "|| id [" + usuario.getId() + "]");
        }
    }
}