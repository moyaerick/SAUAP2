package mx.sauap.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Profesor.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Profesor_ {

	
	/**
	 * @see mx.sauap.entity.Profesor#apSegundo
	 **/
	public static volatile SingularAttribute<Profesor, String> apSegundo;
	
	/**
	 * @see mx.sauap.entity.Profesor#apPrimero
	 **/
	public static volatile SingularAttribute<Profesor, String> apPrimero;
	
	/**
	 * @see mx.sauap.entity.Profesor#id
	 **/
	public static volatile SingularAttribute<Profesor, Integer> id;
	
	/**
	 * @see mx.sauap.entity.Profesor
	 **/
	public static volatile EntityType<Profesor> class_;
	
	/**
	 * @see mx.sauap.entity.Profesor#nombre
	 **/
	public static volatile SingularAttribute<Profesor, String> nombre;
	
	/**
	 * @see mx.sauap.entity.Profesor#rfc
	 **/
	public static volatile SingularAttribute<Profesor, String> rfc;

	public static final String AP_SEGUNDO = "apSegundo";
	public static final String AP_PRIMERO = "apPrimero";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String RFC = "rfc";

}

