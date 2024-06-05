package persistencia;

/**
 * Clase que representa a un empleado.
 */
public class Empleado {
    /** variable que almacena el nombre del empleado */
    private String nombre;
    /** Varibale que almacena el beneficio del empleado */
    private double beneficio;

    /**
     * Constructor que inicializa el nombre y el beneficio del empleado.
     * @param nombre El nombre del empleado
     * @param beneficio El beneficio del empleado
     */
    public Empleado(String nombre, double beneficio) {
        this.nombre = nombre;
        this.beneficio = beneficio;
    }

    /**
     * Constructor que inicializa solo el nombre del empleado.
     * @param nombre El nombre del empleado
     */
    public Empleado(String nombre) {
        this.nombre = nombre;
        this.beneficio = 0;
    }

    /**
     * Obtiene el nombre del empleado.
     * @return El nombre del empleado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     * @param nombre El nuevo nombre del empleado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el beneficio del empleado.
     * @return El beneficio del empleado
     */
    public double getBeneficio() {
        return beneficio;
    }

    /**
     * Establece el beneficio del empleado, verificando que sea mayor que cero.
     * @param beneficio El nuevo beneficio del empleado
     */
    public void setBeneficio(double beneficio) {
        if (beneficio > 0) {
            this.beneficio = beneficio;
        } else {
            this.beneficio = 0;
        }
    }
}
