import mx.sauap.dao.AlumnoDAO;
import mx.sauap.entity.Alumno;
import mx.sauap.persistence.HibernateUtil;

public class testDAO {

    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO(HibernateUtil.getEntityManager());



        for (Alumno alumno : alumnoDAO.findAll()) {
            System.out.println(alumno + "|| id [" + alumno.getId()+ "]");
        }
    }
}
