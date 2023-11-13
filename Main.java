package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        cargarUsuariosDesdeCSV();
        Scanner scanner = new Scanner(System.in);

        // Menú principal
        int opcion;
        do {
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    Usuario usuarioIngresado = ingresarUsuario(scanner);
                    if (usuarioIngresado != null){
                        Metodos(usuarioIngresado, usuarioIngresado, scanner);
                    }
                    break;
                
            }

        } while (opcion != 3);

        scanner.close();
    }

    private static void registrarUsuario(Scanner scanner) {
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.next();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.next();
        System.out.print("Seleccione plan (Base/Premium): ");
        String tipoPlan = scanner.next();

        Usuario nuevoUsuario = new Usuario(nombre, contraseña, tipoPlan);
        usuarios.add(nuevoUsuario);
        nuevoUsuario.guardarInfoUsuarioCSV(); // Guardar información del usuario en CSV

        System.out.println("Usuario registrado con éxito.");

       
    }

    private static Usuario ingresarUsuario(Scanner scanner) {
        System.out.println("\n");
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.next();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.next();

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContrasena().equals(contraseña)) {
                usuarioActual = usuario;
                System.out.println("Inicio de sesión exitoso.");
                return usuario;
            }
        }
        System.out.println("\n");
        System.out.println("Nombre de usuario o contraseña incorrectos.");
        return usuarioActual;
    }

    private static void Metodos(Usuario usuario, Metodos metodos, Scanner scanner) {
        int opcion;
        do {
            System.out.println("1. Modo selección");
            System.out.println("2. Modo préstamo");
            System.out.println("3. Modo perfil");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    metodos.modoseleccion(scanner);
                    break;
                case 2:
                    metodos.ejecutar(scanner);
                    break;
                case 3:
                    metodos.modoPerfil(scanner);
                    break;
    
            }

        } while (opcion != 4);
    }

     private static void cargarUsuariosDesdeCSV() {
        try (BufferedReader csvReader = new BufferedReader(new FileReader("usuarios.csv"))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                String nombre = data[0];
                String contraseña = data[1];
                String tipoPlan = data[2];

                Usuario usuario = new Usuario(nombre, contraseña, tipoPlan);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   

    
}

