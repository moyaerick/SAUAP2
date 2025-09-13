package mx.sauap.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Alumno.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Alumno_ {

	
	/**
	 * @see mx.sauap.entity.Alumno#apellidos
	 **/
	public static volatile SingularAttribute<Alumno, String> apellidos;
	
	/**
	 * @see mx.sauap.entity.Alumno#matricula
	 **/
	public static volatile SingularAttribute<Alumno, Integer> matricula;
	
	/**
	 * @see mx.sauap.entity.Alumno#id
	 **/
	public static volatile SingularAttribute<Alumno, Integer> id;
	
	/**
	 * @see mx.sauap.entity.Alumno#usuarios
	 **/
	public static volatile SetAttribute<Alumno, Usuario> usuarios;
	
	/**
	 * @see mx.sauap.entity.Alumno
	 **/
	public static volatile EntityType<Alumno> class_;
	
	/**
	 * @see mx.sauap.entity.Alumno#nombre
	 **/
	public static volatile SingularAttribute<Alumno, String> nombre;

	public static final String APELLIDOS = "apellidos";
	public static final String MATRICULA = "matricula";
	public static final String ID = "id";
	public static final String USUARIOS = "usuarios";
	public static final String NOMBRE = "nombre";

}

