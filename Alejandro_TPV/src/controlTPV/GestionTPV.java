package controlTPV;

import java.util.*;

import interfaz.Interfaz;
import persistencia.Empleado;
import persistencia.Persistencia;

/**
 * Clase que gestiona todas las operaciones relacionadas con el TPV (Terminal Punto de Venta).
 * Permite la interacción con el sistema tanto para camareros como para administradores.
 */
public class GestionTPV {
    /** Lista que almacena empleados, su estructura interna es de un ArrayList */
    List<Empleado> camareros = new ArrayList<>();
    /** Instancia de la clase Interfaz para acceder a sus métodos */
    Interfaz i = new Interfaz();
    /** Instancia de la clase Descuentos para acceder a sus métodos */
    Descuentos d = new Descuentos();
    /** Instancia de la clase Catalogo para acceder a sus métodos */
    private Catalogo catalogo = new Catalogo();
    /** Contraseña del modo administrador */
    private String contrasenia = "Admin1234";
    /** Beneficio total de la caja desde que se inicia el programa */
    private double beneficioTotalCaja = 0;

    /**
     * Constructor que carga los camareros al iniciar.
     */
    public GestionTPV() {
        cargarCamareros();
    }

    /**
     * Método que muestra el menú de inicio y gestiona las acciones según la elección del usuario.
     */
    public void menuInicio() {
        i.inicio();
        String eleccion = new Scanner(System.in).nextLine();
        switch (eleccion) {
            case "" -> seleccionCamarero();
            case "admin" -> validarContrasenia();
            case "exit" -> System.out.println("Sistema cerrado.");
            default -> {
                System.out.println("Elección no válida, por favor prueba de nuevo.");
                menuInicio();
            }
        }
    }

    /**
     * Método que valida la contraseña del modo administrador.
     */
    private void validarContrasenia() {
        int intentos = 2;
        System.out.println("Introduce la contraseña: ");
        String contraseniaUsuario = new Scanner(System.in).next();
        while (!contraseniaUsuario.equals(contrasenia) && intentos > 0) {
            System.out.println("Contraseña incorrecta");
            contraseniaUsuario = new Scanner(System.in).next();
            intentos--;
        }
        if (intentos == 0) {
            System.out.println("Sistema bloqueado");
        } else {
            seleccionAdmin();
        }
    }

    /**
     * Método que muestra el menú de administrador y gestiona las acciones según la elección del administrador.
     */
    public void seleccionAdmin() {
        i.asteriscos(45);
        System.out.println("\nMENU ADMINISTRADOR\n");
        System.out.println("--- GESTION PRODUCTOS ---\n"
                + "a.- Ver catálogo\n"
                + "b.- Añadir producto al catálogo\n"
                + "c.- Quitar producto del catálogo\n"
                + "d.- Cambiar precio de producto\n"
                + "e.- Rellenar stock de producto\n"
                + "\n--- GESTION ECONOMICA ---\n"
                + "f.- Ver caja\n"
                + "g.- Ver recaudación de empleados\n"
                + "h.- Consultar descuentos\n"
                + "\n--- ADMINISTRACIÓN DEL SISTEMA ---\n"
                + "x.- Añadir nuevo camarero\n"
                + "y.- Eliminar camarero\n"
                + "z.- Cambiar contraseña\n"
                + "s.- Salir del modo administrador\n");
        i.asteriscos(45);
        char eleccion = new Scanner(System.in).next().charAt(0);
        menuAdmin(eleccion);
    }
    
    /**
     * Método que gestiona las acciones del administrador según su elección en el menú.
     * @param eleccion
     */
    private void menuAdmin(char eleccion) {
    	switch(eleccion) {
    		case 'a' -> {
    			catalogo.mostrarCatalogo(); 
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'b' -> {
    			catalogo.aniadirProducto();
    			i.enter();
    			seleccionAdmin();
    			
    		}
    		case 'c' ->{
    			catalogo.quitarProducto();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'd' ->{
    			catalogo.cambiarPrecio();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'e' ->{
    			catalogo.rellenarStock();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'f' -> {
    			verCaja();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'g' ->{
    			recaudacionEmpleados();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'h' -> {
    			consultarDescuentos();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'x' -> {
    			aniadirCamarero();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'y' ->{
    			eliminarCamarero();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 'z' ->{
    			cambiarContrasenia();
    			i.enter();
    			seleccionAdmin();
    		}
    		case 's' -> {
    			i.inicio();
    			menuInicio();
    		}
    	}
    }
    
    /**
     * Método que permite al camarero realizar la selección de camarero para acceder al menú correspondiente.
     */
    private void seleccionCamarero() {
    	int eleccion = 0;
        System.out.println("--- SELECCIONAR CAMARERO ---\n");
        for (int i = 0; i < camareros.size(); i++) {
            System.out.println((i + 1) + " -> " + camareros.get(i).getNombre());
        }
        System.out.println("\nIntroduzca su numero de empleado: ");
        eleccion = new Scanner(System.in).nextInt();
        while(eleccion>camareros.size() || eleccion<1) {
        	System.out.println("\nIntroduzca su numero de empleado: ");
            eleccion = new Scanner(System.in).nextInt();
        }
        menuCamareros(eleccion);
    }
    
    /**
     * Método que muestra el menú correspondiente al camarero y gestiona las acciones según su elección.
     * @param eleccion
     */
    private void menuCamareros(int eleccion) {
        
        i.asteriscos(45);
        System.out.println("\n\tMENÚ CAMARERO  (" + camareros.get(eleccion-1).getNombre() + ")\n");
        System.out.println("\ta.-Realizar venta.\n"
                + "\n\tb.-Ver recaudación.\n"
                + "\n\ts.-Cerrar sesion.\n");
        i.asteriscos(45);

        char eleccionLetra = new Scanner(System.in).next().charAt(0);

        switch (eleccionLetra) {
            case 'a' -> {
                catalogo.mostrarCatalogo();  
                System.out.println("\n 0  ->  Finalizar Compra.\n");
                realizarVenta(eleccion);
                
                menuCamareros(eleccion);
            }
            case 'b' -> {
                System.out.println("La facturación generada por " + camareros.get(eleccion-1).getNombre() + " es de: " + camareros.get(eleccion-1).getBeneficio());
                menuCamareros(eleccion);
            }
            case 's' -> {
            	guardarCamareros(); // Guardar los beneficios antes de cerrar la sesión
                catalogo.guardarProductos(); // Guardar el stock antes de cerrar la sesión
                i.inicio();
                menuInicio();
            }
        }
    }
    
    /**
     * Método que carga los camareros al iniciar el programa.
     */
    private void cargarCamareros() {
    	List<Empleado> empleadosCargados = Persistencia.leerCamareros();
    	if (empleadosCargados != null && !empleadosCargados.isEmpty()) {
            camareros = empleadosCargados;
        } else {
            // Agregar empleados predeterminados si la lista está vacía
            camareros.add(new Empleado("Juan", 0));
            camareros.add(new Empleado("Maria", 0));
        }
	    guardarCamareros();
    }
    
    /**
     * Método que guarda los camareros en el archivo de persistencia.
     */
    private void guardarCamareros() {
    	Persistencia.guardarCamareros(camareros);
    }

    /** 
     * Método que realiza la venta de productos, genera el ticket y actualiza el beneficio total.
     * @param eleccion
     */
    private void realizarVenta(int eleccion) {
    	int id = 0;
    	double beneficio = 0;
        double beneficioTotal = 0;
        List<Integer> productosSeleccionados = new LinkedList<>();
        do {
            System.out.print("Seleccione producto a comprar: ");
            id = new Scanner(System.in).nextInt();
            if (id != 0 && catalogo.productos.get(id-1).getStock()>0) {
                beneficio = catalogo.vender(id);
                productosSeleccionados.add(id - 1);
                beneficioTotal += beneficio;
                System.out.println("\t\t\t--- SUBTOTAL: " + beneficioTotal);
            } else {
            	System.out.println("Producto sin stock");
            }
        } while (id != 0);
        
        ticket(productosSeleccionados, beneficioTotal, eleccion, id);
    }
    
    /**
     * Método que genera el ticket de la venta y realiza los cálculos correspondientes.
     * @param productosSeleccionados
     * @param beneficioTotal
     * @param eleccion
     * @param id
     */
    private void ticket(List<Integer> productosSeleccionados, double beneficioTotal, int eleccion, int id) {
        double totalConDescuento = 0;
        String tipoEmpleado;
        boolean codigoValido = false;
        if(beneficioTotal!=0) {
        	do {
                System.out.print("\nIntroduzca el tipo de empleado: ");
                tipoEmpleado = new Scanner(System.in).next();
                totalConDescuento = d.aplicarDescuento(tipoEmpleado, beneficioTotal);
                if (totalConDescuento == beneficioTotal) {
                    System.out.println("Tipo de empleado no válido. Intente de nuevo.");
                } else {
                    codigoValido = true;
                }
            } while (!codigoValido);

            System.out.print("\n\tTICKET DE VENTA\n\t");
            i.guiones(25);
            System.out.println();
            for (int i = 0; i < productosSeleccionados.size(); i++) {
                System.out.println("\t| " + catalogo.productos.get(productosSeleccionados.get(i)).getNombre() + " -> " + catalogo.productos.get(productosSeleccionados.get(i)).getPrecio());
            }
            System.out.print("\t");
            i.guiones(25);
            System.out.println("\n\t\tTOTAL: " + beneficioTotal);
            System.out.println("\n\t\tTOTAL CON DTO: " + totalConDescuento);

            System.out.print("Importe entregado: ");
            double importe = new Scanner(System.in).nextDouble();

            if (importe >= totalConDescuento) {
                System.out.print("\nCantidad a devolver: " + (importe - totalConDescuento + "\n"));
                camareros.get(eleccion - 1).setBeneficio(camareros.get(eleccion - 1).getBeneficio() + totalConDescuento);
                beneficioTotalCaja += totalConDescuento;
                guardarCamareros();
                
            } else {
                System.out.println("\nCantidad no válida.");
                for (int i = 0; i < productosSeleccionados.size(); i++) {
                    id = productosSeleccionados.get(i);
                    catalogo.productos.get(i).aumentarStock(); // Recuperar el stock original del producto
                }
                catalogo.cargarProductos(); // Guardar el stock actualizado
            }
        } else {
        	System.out.println("Venta cancelada");
        }
        
    }
    
    /** 
     * Método que muestra el beneficio total de la caja.
     */
    private void verCaja(){
    	System.out.println("El beneficio de caja es de: " + beneficioTotalCaja);
    }
    
    /** 
     * Método que muestra la recaudación de cada empleado.
     */
    private void recaudacionEmpleados() {
    	int eleccion = 0;
        System.out.println("\n\n--- SELECCIONAR CAMARERO ---\n");
        for (int i = 0; i < camareros.size(); i++) {
            System.out.println((i + 1) + " -> " + camareros.get(i).getNombre());
        }
        System.out.println("\nIntroduzca el numero de empleado para ver su recaudacion: ");
        eleccion = new Scanner(System.in).nextInt();
        eleccion--;
        while(eleccion>camareros.size() || eleccion<0) {
        	System.out.println("\nIntroduzca su numero de empleado: ");
            eleccion = new Scanner(System.in).nextInt();
        }
        System.out.println(camareros.get(eleccion).getBeneficio());
    }
    
    /** 
     * Método que muestra los descuentos disponibles para diferentes tipos de empleados.
     */
	private void consultarDescuentos() {
		System.out.println("--- DESCUENTOS TIPO EMPLEADO ---\n"
				+ "Doctor\t\t" + "dto:" + d.DESCUENTO_DOCTOR + "\tcod:"+d.DOCTOR 
				+ "\nEnfermero\t"+ "dto:" + d.DESCUENTO_ENFERMERO + "\t\tcod:"+d.ENFERMERO
				+ "\nAuxiliar Enf.\t"+ "dto:" + d.DESCUENTO_AUXILIAR_ENFERMERIA + "\t\tcod:"+d.AUXILIAR_ENFERMERIA
				+ "\nAdministrativo\t" + "dto:" + d.DESCUENTO_ADMINISTRATIVO + "\tcod:"+d.ADMINISTRATIVO
				+ "\nCelador\t\t" + "dto:" + d.DESCUENTO_CELADOR + "\tcod:"+d.CELADOR);
	}
	
	/** 
	 * Método que permite añadir un nuevo camarero al sistema.
	 */
	private void aniadirCamarero() {
		System.out.println("Introduce el nombre del nuevo camarero: ");
		String nombre = new Scanner(System.in).nextLine();
		boolean existe = false;
	    for (Empleado camarero : camareros) {
	        if (camarero.getNombre().equals(nombre)) {
	            existe = true;
	        }
	    }
	    
	    if (!existe) {
	        camareros.add(new Empleado(nombre));
	        System.out.println("Camarero " + nombre + " añadido con éxito.");
	        guardarCamareros();
	    } else {
	        System.out.println("El camarero " + nombre + " ya existe, prueba con otro nombre.");
	    }
		
	}
	
	/** 
	 * Método que permite eliminar un camarero del sistema.
	 */
	private void eliminarCamarero() {
		int eleccion=0;
		System.out.println("--- SELECCIONAR CAMARERO ---\n");
        for (int i = 0; i < camareros.size(); i++) {
            System.out.println((i + 1) + " -> " + camareros.get(i).getNombre());
        }
        System.out.println("\nIntroduzca el numero de empleado al que quieres eliminar: ");
        eleccion = new Scanner(System.in).nextInt();
        while(eleccion>camareros.size() || eleccion<1) {
        	System.out.println("\nIntroduzca el numero de empleado al que quieres eliminar: ");
            eleccion = new Scanner(System.in).nextInt();
        }
        System.out.println("Camarero " + camareros.get(eleccion-1).getNombre() + " eliminado.");
        camareros.remove(eleccion-1);
        
        guardarCamareros();
	}
	
	/** 
	 * Método que permite cambiar la contraseña del modo administrador.
	 */
	private void cambiarContrasenia() {
		System.out.println("Introduce la nueva contraseña: ");
		String nuevaContra1 = new Scanner(System.in).next();
		
		if(!nuevaContra1.equals(contrasenia)) {
			System.out.println("Introduce nuevamente la contraseña:");
			String nuevaContra2 = new Scanner(System.in).next();
			
			if(nuevaContra1.equals(nuevaContra2)) {
				if(validarNuevaContrasenia(nuevaContra1)){
					System.out.println("Contraseña actualizada");
					contrasenia = nuevaContra1;
				} else {
					System.out.println("Contrasela no valida, tiene que tener entre 8 y 16 caracteres, al menos un digito, al menos una mayuscula y al menos una minuscula.");
				}
			} else {
				System.out.println("Las contraseñas no coinciden");
			}
			
		} else {
			System.out.println("La nueva contraseña coincide con la contraseña antigua.");
		}
		

	}
	
	/** 
	 * Método que valida una nueva contraseña según ciertos criterios.
	 * @param pass
	 */
	private boolean validarNuevaContrasenia(String pass) {
		boolean valida = false;
		if(pass.length()<16 && pass.length()>8) {
			boolean tieneDigito = false;
	        boolean tieneMayuscula = false;
	        boolean tieneMinuscula = false;
	        
	        for (char c : pass.toCharArray()) {
	            if (Character.isDigit(c)) {
	                tieneDigito = true;
	            } else if (Character.isUpperCase(c)) {
	                tieneMayuscula = true;
	            } else if (Character.isLowerCase(c)) {
	                tieneMinuscula = true;
	            }
	        }
	        if(tieneDigito && tieneMayuscula && tieneMinuscula){
	        	valida = true;
	        }
		}
		return valida;
	}
	
}
