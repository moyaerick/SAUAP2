package mx.sauap.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Asignacion.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Asignacion_ {

	
	/**
	 * @see mx.sauap.entity.Asignacion#idProfesor
	 **/
	public static volatile SingularAttribute<Asignacion, Profesor> idProfesor;
	
	/**
	 * @see mx.sauap.entity.Asignacion#idUa
	 **/
	public static volatile SingularAttribute<Asignacion, UnidadAprendizaje> idUa;
	
	/**
	 * @see mx.sauap.entity.Asignacion#tipoAsignacion
	 **/
	public static volatile SingularAttribute<Asignacion, String> tipoAsignacion;
	
	/**
	 * @see mx.sauap.entity.Asignacion#id
	 **/
	public static volatile SingularAttribute<Asignacion, Integer> id;
	
	/**
	 * @see mx.sauap.entity.Asignacion
	 **/
	public static volatile EntityType<Asignacion> class_;

	public static final String ID_PROFESOR = "idProfesor";
	public static final String ID_UA = "idUa";
	public static final String TIPO_ASIGNACION = "tipoAsignacion";
	public static final String ID = "id";

}

