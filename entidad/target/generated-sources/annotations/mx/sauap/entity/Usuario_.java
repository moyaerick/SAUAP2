package mx.sauap.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Usuario.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Usuario_ {

	
	/**
	 * @see mx.sauap.entity.Usuario#id
	 **/
	public static volatile SingularAttribute<Usuario, Integer> id;
	
	/**
	 * @see mx.sauap.entity.Usuario
	 **/
	public static volatile EntityType<Usuario> class_;
	
	/**
	 * @see mx.sauap.entity.Usuario#nombre
	 **/
	public static volatile SingularAttribute<Usuario, String> nombre;
	
	/**
	 * @see mx.sauap.entity.Usuario#psswd
	 **/
	public static volatile SingularAttribute<Usuario, String> psswd;
	
	/**
	 * @see mx.sauap.entity.Usuario#rol
	 **/
	public static volatile SingularAttribute<Usuario, String> rol;

	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String PSSWD = "psswd";
	public static final String ROL = "rol";

}

