package mx.sauap.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum Dia {
    LUN("Lun"),
    MAR("Mar"),
    MIE("Mie"),
    JUE("Jue"),
    VIE("Vie"),
    SAB("Sab");

    private final String codigo;

    Dia(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Dia fromCodigo(String codigo) {
        for (Dia d : Dia.values()) {
            if (d.codigo.equalsIgnoreCase(codigo)) return d;
        }
        throw new IllegalArgumentException("Código de día inválido: " + codigo);
    }

    // Sobreescribir toString para mostrar el código en vez del nombre literal
    @Override
    public String toString() {
        return codigo;
    }

    // Clase interna en el mismo archivo para realizar la conversion
    @Converter(autoApply = true)
    public static class DiaConverter implements AttributeConverter<Dia, String> {

        @Override
        public String convertToDatabaseColumn(Dia dia) {
            return dia != null ? dia.getCodigo() : null;
        }

        @Override
        public Dia convertToEntityAttribute(String codigo) {
            return codigo != null ? Dia.fromCodigo(codigo) : null;
        }
    }
}
