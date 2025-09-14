package mx.sauap.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UnidadAprendizaje.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UnidadAprendizaje_ {

	
	/**
	 * @see mx.sauap.entity.UnidadAprendizaje#hrsClase
	 **/
	public static volatile SingularAttribute<UnidadAprendizaje, Integer> hrsClase;
	
	/**
	 * @see mx.sauap.entity.UnidadAprendizaje#hrsTaller
	 **/
	public static volatile SingularAttribute<UnidadAprendizaje, Integer> hrsTaller;
	
	/**
	 * @see mx.sauap.entity.UnidadAprendizaje#hrsLab
	 **/
	public static volatile SingularAttribute<UnidadAprendizaje, Integer> hrsLab;
	
	/**
	 * @see mx.sauap.entity.UnidadAprendizaje#id
	 **/
	public static volatile SingularAttribute<UnidadAprendizaje, Integer> id;
	
	/**
	 * @see mx.sauap.entity.UnidadAprendizaje
	 **/
	public static volatile EntityType<UnidadAprendizaje> class_;
	
	/**
	 * @see mx.sauap.entity.UnidadAprendizaje#nombre
	 **/
	public static volatile SingularAttribute<UnidadAprendizaje, String> nombre;

	public static final String HRS_CLASE = "hrsClase";
	public static final String HRS_TALLER = "hrsTaller";
	public static final String HRS_LAB = "hrsLab";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

