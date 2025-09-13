package mx.sauap.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Usuario.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Usuario_ {

	
	/**
	 * @see mx.sauap.entity.Usuario#idAlumno
	 **/
	public static volatile SingularAttribute<Usuario, Alumno> idAlumno;
	
	/**
	 * @see mx.sauap.entity.Usuario#correo
	 **/
	public static volatile SingularAttribute<Usuario, String> correo;
	
	/**
	 * @see mx.sauap.entity.Usuario#contrasena
	 **/
	public static volatile SingularAttribute<Usuario, String> contrasena;
	
	/**
	 * @see mx.sauap.entity.Usuario#id
	 **/
	public static volatile SingularAttribute<Usuario, Integer> id;
	
	/**
	 * @see mx.sauap.entity.Usuario
	 **/
	public static volatile EntityType<Usuario> class_;

	public static final String ID_ALUMNO = "idAlumno";
	public static final String CORREO = "correo";
	public static final String CONTRASENA = "contrasena";
	public static final String ID = "id";

}

