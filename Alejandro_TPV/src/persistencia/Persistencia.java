 package persistencia;

import java.io.*;
import java.util.*;

public class Persistencia {
	/* Varibale privada y estatica de tipo String, que almacena el nombre del archivo */
    private static final String STOCK = "productos.txt";
    /* Varibale privada y estatica de tipo String, que almacena el nombre del archivo */
    private static final String CAMAREROS = "camareros.txt";

    /** 
     * Lee el archivo de stock y devuelve una lista de productos.
     * @return La lista de productos leída desde el archivo.
     */
    public static List<Producto> leerStock() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STOCK))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 3) {
                    try {
                        String nombre = partes[0];
                        double precio = Double.parseDouble(partes[1]);
                        int cantidad = Integer.parseInt(partes[2]);
                        productos.add(new Producto(nombre, precio, cantidad));
                    } catch (NumberFormatException e) {
                        System.out.println("Error en el formato de los datos: " + e.getMessage());
                    }
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de stock: " + e.getMessage());
        }
        return productos;
    }
    
    /** 
     * Guarda la lista de productos en el archivo de stock.
     * @param productos La lista de productos a guardar.
     */
    public static void guardarStock(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STOCK))) {
            for (Producto producto : productos) {
                writer.write(producto.getNombre() + ":" + producto.getPrecio() + ":" + producto.getStock());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de stock: " + e.getMessage());
        }
    }
    
    /** 
     * Lee el archivo de camareros y devuelve una lista de empleados.
     * @return La lista de empleados leída desde el archivo.
     */
    public static List<Empleado> leerCamareros(){
        List<Empleado> camareros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMAREROS))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    try {
                        String nombre = partes[0];
                        double beneficio = Double.parseDouble(partes[1]);
                        camareros.add(new Empleado(nombre, beneficio));
                    } catch (NumberFormatException e) {
                        System.out.println("Error en el formato de los datos: " + e.getMessage());
                    }
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de camareros: " + e.getMessage());
        }
        return camareros;
    }
    
    /** 
     * Guarda la lista de empleados en el archivo de camareros.
     * @param camareros La lista de empleados a guardar.
     */
    public static void guardarCamareros(List<Empleado> camareros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMAREROS))) {
            for (Empleado camarero : camareros) {
                writer.write(camarero.getNombre() + ":" + camarero.getBeneficio());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de camareros: " + e.getMessage());
        }
    }

	
	//METODO ANTERIOR PARA GUARDAR EL BENEFICIO
//    public static Map<String, Double> leerBeneficios() {
//        Map<String, Double> beneficios = new HashMap<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(CAMAREROS))) {
//            String linea;
//            while ((linea = reader.readLine()) != null) {
//                String[] partes = linea.split(":");
//                String nombre = partes[0];
//                double beneficio = Double.parseDouble(partes[1]);
//                beneficios.put(nombre, beneficio);
//            }
//        } catch (IOException e) {
//            System.out.println("Error al leer el archivo de beneficios: " + e.getMessage());
//        }
//        return beneficios;
//    }
//
//    public static void guardarBeneficios(Map<String, Double> beneficios) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMAREROS))) {
//            for (Map.Entry<String, Double> entrada : beneficios.entrySet()) {
//                writer.write(entrada.getKey() + ":" + entrada.getValue());
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Error al guardar el archivo de beneficios: " + e.getMessage());
//        }
//    }
//		
//  	private void cargarBeneficios() {
//  		beneficiosCamareros = Persistencia.leerBeneficios();
//  		for (Empleado camarero : camareros) {
//      		if (beneficiosCamareros.containsKey(camarero.getNombre())) {
//          		camarero.setBeneficio(beneficiosCamareros.get(camarero.getNombre()));
//      		}
//  		}
//		}
//
//		private void guardarBeneficios() {
//  		for (Empleado camarero : camareros) {
//  	    	beneficiosCamareros.put(camarero.getNombre(), camarero.getBeneficio());
//  		}
// 	 		Persistencia.guardarBeneficios(beneficiosCamareros);
//		}
}
