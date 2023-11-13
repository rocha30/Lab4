package lab4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Usuario implements Metodos{
    private String nombre; 
    private String contrasena; 
    private String tipoPlan; 
    private List<String> librosPrestados;
    private List<String> revistasPrestadas;
    private int diasadicionales = 0;

   
    public void agregarLibro(String libro) {
        if (tipoPlan.equals("Premium") && librosPrestados.size() < 5) {
            librosPrestados.add(libro);
            System.out.println("Libro agregado con éxito.");
        } else if (tipoPlan.equals("Base") && librosPrestados.size() < 3) {
            librosPrestados.add(libro);
            System.out.println("Libro agregado con éxito.");
        } else {
            System.out.println("No puedes agregar más libros. Alcanzaste el límite.");
        }
    }

    public void agregarRevista(String revista) {
        revistasPrestadas.add(revista);
        System.out.println("Revista agregada con éxito.");
    }

    public void vaciarLista() {
        librosPrestados.clear();
        revistasPrestadas.clear();
    }


    public Usuario (String nombrem, String contrasena, String tipoPlan){
        this.nombre = nombrem;
        this.contrasena = contrasena;
        this.tipoPlan = tipoPlan; 
        this.librosPrestados = new ArrayList<>();
        this.revistasPrestadas = new ArrayList<>();
    }
    
    

    @Override
    public void ejecutar(Scanner scanner) {
        int opcion;
        do {
            System.out.println("1. Agregar libro");
            System.out.println("2. Agregar revista");
            System.out.println("3. Vaciar lista");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del libro: ");
                    String libro = scanner.next();
                    agregarLibro(libro);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre de la revista: ");
                    String revista = scanner.next();
                    agregarRevista(revista);
                    break;
                case 3:
                    vaciarLista();
                    System.out.println("Lista vaciada con éxito.");
                    break;
                
            }

        } while (opcion != 4);
    }

    
    @Override
    public void definirDiasEntrega(int dias) {
        if (tipoPlan.equals("Premium") && dias <= 50) {
            System.out.println("Días de entrega definidos: " + dias);
        } else if (tipoPlan.equals("Base") && dias <= 30) {
            System.out.println("Días de entrega definidos: " + dias);
        } else {
            System.out.println("Número de días no válido para tu plan.");
        }
    }

    
    public void modificarTipoCliente(Scanner scanner) {
        if (!tipoPlan.equals("Premium")) {
            System.out.println("¡Felicidades! Has mejorado a la membresía Premium.");
            tipoPlan = "Premium";
        } else {
            System.out.println("Ya eres usuario Premium.");
        }
    }

   
    public void aplicarCupon(Scanner scanner) {
        if (tipoPlan.equals("Premium")) {
            System.out.print("Ingrese el código del cupón: ");
            String codigoCupon = scanner.next();
    
            if (validarCodigoCupon(codigoCupon)) {
                diasadicionales += 15;
                System.out.println("Cupón aplicado con éxito. ¡15 días adicionales!");
            } else {
                System.out.println("Código de cupón inválido.");
            }
        } else {
            System.out.println("Esta opción es solo para usuarios Premium.");
        }
        
    }
    private boolean validarCodigoCupon(String codigoCupon){
        return !codigoCupon.isEmpty();
    }

   
    public void cambiarContraseña(Scanner scanner) {
        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaContraseña = scanner.next();
        contrasena = nuevaContraseña;
        System.out.println("Contraseña cambiada con éxito.");
    }

    @Override
    public void definirHorarioEntrega(String horario) {
        if (tipoPlan.equals("Premium")) {
            System.out.println("Horario de entrega definido: " + horario);
        } else {
            System.out.println("Esta opción es solo para usuarios Premium.");
        }
    }

    @Override
    public void definirSucursalRecoger(String sucursal) {
        if (!tipoPlan.equals("Premium")) {
            System.out.println("Sucursal de recogida definida: " + sucursal);
        } else {
            System.out.println("Esta opción es solo para usuarios no Premium.");
        }
    }

    @Override
    public void imprimirListadoPrestamo() {
        // Implementación específica del préstamo
        // ...
    }


    public void guardarInfoUsuarioCSV() {
        try (FileWriter csvWriter = new FileWriter("lab4\\usuarios.csv", true)) {
            csvWriter.append(nombre);
            csvWriter.append(",");
            csvWriter.append(contrasena);
            csvWriter.append(",");
            csvWriter.append(tipoPlan);
            csvWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarInfoPrestamoCSV() {
        try (FileWriter csvWriter = new FileWriter("lab4\\presatamos.csv", true)) {
            for (String libro : librosPrestados) {
                csvWriter.append(nombre);
                csvWriter.append(",");
                csvWriter.append(libro);
                csvWriter.append(",");
                csvWriter.append("Libro");
                csvWriter.append("\n");
            }

            for (String revista : revistasPrestadas) {
                csvWriter.append(nombre);
                csvWriter.append(",");
                csvWriter.append(revista);
                csvWriter.append(",");
                csvWriter.append("Revista");
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    @Override
public void modoseleccion(Scanner scanner) {
    int opcion;
    do {
        System.out.println("1. Agregar libro");
        System.out.println("2. Agregar Revista");
        System.out.println("3. Vaciar lista");
        System.out.println("4. Volver al menú principal ");
        System.out.println("Seleccione una opción:");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nombre del libro: ");
                String nombreLibro = scanner.nextLine();
                agregarLibro(nombreLibro);
                break;
            case 2:
                System.out.println("Ingrese el nombre de la revista:");
                String nombreRevista = scanner.nextLine();
                agregarRevista(nombreRevista);
                break;
            case 3:
                vaciarLista();
                System.out.println("Lista vaciada con éxito");
                break;
            default:
                System.out.println("Opción no válida");
        }

    } while (opcion != 4);
}


    @Override
    public void modoPerfil(Scanner scanner) {
        int opcion;
        do{
            System.out.println("1. moodificar el tipo de cliente. ");
            System.out.println("2. Aplicar cupon de 15 dias. ");
            System.out.println("3. Cambiar contraseña.");
            System.out.println("4. Regresar al menu pricipal. ");
            System.out.println("Ingrese una opcion ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    modificarTipoCliente(scanner);
                    break;
                case 2: 
                    aplicarCupon(scanner);
                    break;
                case 3:
                cambiarContraseña(scanner);
                    break;
                default:
                System.out.println("Opción no válida");
            }
                }while (opcion !=4);
        }
    


    // getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public List<String> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<String> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public List<String> getRevistasPrestadas() {
        return revistasPrestadas;
    }

    public void setRevistasPrestadas(List<String> revistasPrestadas) {
        this.revistasPrestadas = revistasPrestadas;
    }

}

