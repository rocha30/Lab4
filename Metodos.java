package lab4;

import java.util.Scanner;


public interface Metodos {
    void ejecutar(Scanner scanner);

    
    
    void definirDiasEntrega(int dias );
    
    void definirHorarioEntrega(String horario);

    void definirSucursalRecoger(String sucursal);

    void imprimirListadoPrestamo();

    void modoseleccion(Scanner scanner);

    void modoPerfil(Scanner scanner);

}
