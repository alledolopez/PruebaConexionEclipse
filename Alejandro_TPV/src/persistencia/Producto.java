package persistencia;

public class Producto {
	/* Variable privada que almacena el nombre de un producto */
	private String nombre;
	/* Variable privada que almacena el precio de un producto */
	private double precio;
	/* Variable privada que almacena el stock de un producto */
	private int stock;
	
	/**
	 * Constructor que instancia objetos Producto en otras clases.
	 * @param nombre
	 * @param precio
	 * @param stock
	 */
	public Producto(String nombre, double precio, int stock) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	
	/**
	 * Mediante este get podemos obtener el valor de nombre
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Mediante este set podemos modificar el valor de nombre
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Mediante este get podemos obtener el valor de precio
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}


	/**
	 * Mediante este set podemos modificar el valor de precio
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}


	/**
	 * Mediante este get podemos obtener el valor de stock
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}


	/**
	 * Mediante este set podemos modificar el valor de stock
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

    /* Método para aumentar el stock */
    public void aumentarStock() {
        stock++;
    }
	
}
