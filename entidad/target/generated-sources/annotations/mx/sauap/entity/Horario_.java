package mx.sauap.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalTime;

@StaticMetamodel(Horario.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Horario_ {

	
	/**
	 * @see mx.sauap.entity.Horario#id
	 **/
	public static volatile SingularAttribute<Horario, Integer> id;
	
	/**
	 * @see mx.sauap.entity.Horario
	 **/
	public static volatile EntityType<Horario> class_;
	
	/**
	 * @see mx.sauap.entity.Horario#idAsignacion
	 **/
	public static volatile SingularAttribute<Horario, Asignacion> idAsignacion;
	
	/**
	 * @see mx.sauap.entity.Horario#dia
	 **/
	public static volatile SingularAttribute<Horario, String> dia;
	
	/**
	 * @see mx.sauap.entity.Horario#hrIn
	 **/
	public static volatile SingularAttribute<Horario, LocalTime> hrIn;
	
	/**
	 * @see mx.sauap.entity.Horario#hrFin
	 **/
	public static volatile SingularAttribute<Horario, LocalTime> hrFin;

	public static final String ID = "id";
	public static final String ID_ASIGNACION = "idAsignacion";
	public static final String DIA = "dia";
	public static final String HR_IN = "hrIn";
	public static final String HR_FIN = "hrFin";

}

