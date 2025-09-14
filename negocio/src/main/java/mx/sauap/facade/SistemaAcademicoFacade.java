package mx.sauap.facade;

import mx.sauap.delegate.UnidadAprendizajeDelegate;
import mx.sauap.entity.UnidadAprendizaje;

import java.util.List;

public class SistemaAcademicoFacade {

    private final UnidadAprendizajeDelegate delegate;

    public SistemaAcademicoFacade() {
        this.delegate = new UnidadAprendizajeDelegate();
    }

    public List<UnidadAprendizaje> consultarUA() {
        return delegate.consultarUA();
    }

    public void guardarUA(UnidadAprendizaje ua) {
        delegate.insertarUA(ua);
    }

    public void actualizarUA(UnidadAprendizaje ua) {
        delegate.actualizarUA(ua);
    }

    public void eliminarUA(UnidadAprendizaje ua) {
        delegate.eliminarUA(ua);
    }
}
