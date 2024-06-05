package controlTPV;

import java.util.*;
import persistencia.Persistencia;
import persistencia.Producto;

/**
 * Clase que gestiona el catálogo de productos.
 * Permite mostrar, vender, añadir, quitar, cambiar precio y rellenar stock de productos.
 * Además, carga y guarda los productos en la persistencia.
 */
public class Catalogo {
    List<Producto> productos = new ArrayList<>();

    /**
     * Constructor que carga los productos al iniciar.
     */
    public Catalogo() {
        cargarProductos();
    }

    /**
     * Muestra el catálogo de productos en la consola.
     */
    public void mostrarCatalogo() {
        System.out.println("\t\t--- CATALOGO PRODUCTOS ---\n"
                + "\tProducto\tPrecio\t\tStock");

        for (int i = 0; i < productos.size(); i++) {
            System.out.println(" " + (i + 1) + "  -> " + productos.get(i).getNombre() + "\t\t" + productos.get(i).getPrecio() + "€\t\t" + productos.get(i).getStock() + "ud.");
        }
    }

    /**
     * Realiza la venta de un producto y actualiza el stock.
     *
     * @param id El ID del producto a vender
     * @return El precio del producto vendido
     */
    public double vender(int id) {
        if (id != 0 && id <= productos.size() && id > 0) {
            productos.get(id - 1).setStock(productos.get(id - 1).getStock() - 1);
            return productos.get(id - 1).getPrecio();
        } else {
            return 0;
        }
    }

    /**
     * Permite añadir un nuevo producto al catálogo.
     */
    public void aniadirProducto() {
        boolean existe = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de producto: ");
        String nombre = sc.nextLine();
        System.out.println("Precio de producto: ");
        double precio = sc.nextDouble();
        System.out.println("Cantidad de producto: ");
        int cantidad = sc.nextInt();
        for (Producto p : productos) {
            if (p.getNombre().equals(nombre)) {
                existe = true;
            }

            if (!existe) {
                System.out.println(nombre + ", " + precio + ", " + cantidad);
                System.out.println("¿El producto es correcto?\nS/N");
                char confirmarProducto = sc.next().charAt(0);
                if (confirmarProducto == 'S' || confirmarProducto == 's') {
                    productos.add(new Producto(nombre, precio, cantidad));
                    guardarProductos();
                } else {
                    System.out.println("Inserción de producto cancelada.");
                }
            } else {
                System.out.println("El producto " + nombre + " ya existe, prueba con otro nombre.");
            }
        }
    }

    /**
     * Permite quitar un producto del catálogo.
     */
    public void quitarProducto() {
        mostrarCatalogo();
        System.out.println("\nIntroduce el ID del producto que quieres eliminar: ");
        int id = new Scanner(System.in).nextInt();
        id--;
        if (id >= 0 && id < productos.size()) {
            Producto productoEliminado = productos.remove(id);
            System.out.println("Producto eliminado: " + productoEliminado.getNombre() + " " + productoEliminado.getPrecio() + "€ " + productoEliminado.getStock() + "ud.");
        }
        guardarProductos();
    }

    /**
     * Permite cambiar el precio de un producto.
     */
    public void cambiarPrecio() {
        mostrarCatalogo();
        System.out.println("\nIntroduce el ID del producto al cual quieres modificar su precio: ");
        int id = new Scanner(System.in).nextInt();
        id--;
        System.out.println("Precio actual: " + productos.get(id).getPrecio() + "€");
        System.out.println("Introduce el nuevo precio: ");
        double nuevoPrecio = new Scanner(System.in).nextDouble();
        productos.get(id).setPrecio(nuevoPrecio);
        System.out.println("Precio cambiado a " + productos.get(id).getPrecio() + "€");
        guardarProductos();
    }

    /**
     * Permite rellenar el stock de un producto.
     */
    public void rellenarStock() {
        mostrarCatalogo();
        System.out.println("\nIntroduce el ID del producto al cual quieres rellenar el stock: ");
        int id = new Scanner(System.in).nextInt();
        id--;
        System.out.println("Stock actual: " + productos.get(id).getStock() + "ud.");
        System.out.println("Introduce el stock a rellenar: ");
        int nuevoStock = new Scanner(System.in).nextInt();
        productos.get(id).setStock(productos.get(id).getStock() + nuevoStock);
        System.out.println("Stock actualizado a " + productos.get(id).getStock() + "ud.");
        guardarProductos();
    }

    /**
     * Carga los productos desde la persistencia.
     * Si no hay productos cargados, se añaden productos por defecto y se guardan.
     */
    public void cargarProductos() {
        List<Producto> productosCargados = Persistencia.leerStock();
        if (productosCargados != null) {
            productos = productosCargados;
            if (productos.isEmpty()) {
                productos.add(new Producto("Cocacola", 1, 10));
                productos.add(new Producto("Cerveza", 1.6, 20));
                productos.add(new Producto("Tortilla", 2, 2));
                guardarProductos();
            }
        }
    }

    /**
     * Guarda los productos en la persistencia.
     */
    public void guardarProductos() {
        Persistencia.guardarStock(productos);
    }
}
