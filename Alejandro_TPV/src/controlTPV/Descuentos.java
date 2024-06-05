package controlTPV;

/**
 * Clase que maneja los descuentos para diferentes tipos de empleados.
 */
public class Descuentos {
    /** Código para el tipo de empleado Enfermero */
    public static final String ENFERMERO = "e020";
    /** Código para el tipo de empleado Doctor */
    public static final String DOCTOR = "d022";
    /** Código para el tipo de empleado Auxiliar de Enfermería */
    public static final String AUXILIAR_ENFERMERIA = "a020";
    /** Código para el tipo de empleado Administrativo */
    public static final String ADMINISTRATIVO = "a015";
    /** Código para el tipo de empleado Celador */
    public static final String CELADOR = "c018";
    
    /** Descuento para el tipo de empleado Enfermero */
    public static final double DESCUENTO_ENFERMERO = 0.2;
    /** Descuento para el tipo de empleado Doctor */
    public static final double DESCUENTO_DOCTOR = 0.22;
    /** Descuento para el tipo de empleado Auxiliar de Enfermería */
    public static final double DESCUENTO_AUXILIAR_ENFERMERIA = 0.2;
    /** Descuento para el tipo de empleado Administrativo */
    public static final double DESCUENTO_ADMINISTRATIVO = 0.15;
    /** Descuento para el tipo de empleado Celador */
    public static final double DESCUENTO_CELADOR = 0.18;
    
    /**
     * Aplica el descuento correspondiente según el tipo de empleado.
     * @param tipoEmpleado El tipo de empleado (código)
     * @param totalCompra El total de la compra antes del descuento
     * @return El total de la compra después de aplicar el descuento
     */
    public static double aplicarDescuento(String tipoEmpleado, double totalCompra) {
        switch (tipoEmpleado) {
            case ENFERMERO:
                return totalCompra * (1 - DESCUENTO_ENFERMERO);
            case DOCTOR:
                return totalCompra * (1 - DESCUENTO_DOCTOR);
            case AUXILIAR_ENFERMERIA:
                return totalCompra * (1 - DESCUENTO_AUXILIAR_ENFERMERIA);
            case ADMINISTRATIVO:
                return totalCompra * (1 - DESCUENTO_ADMINISTRATIVO);
            case CELADOR:
                return totalCompra * (1 - DESCUENTO_CELADOR);
            default:
                return totalCompra; 
        }
    }
}




