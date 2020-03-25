import java.util.*;
import java.io.*;

public class P07_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // test
        try {
            String test = pedirEntrada();
        } catch (InputFilePathException e) {
            System.out.println(e.getError());
            try {
                e.log("errores.txt");
            } catch (IOException ioe) {
                System.out.println("error");
            }
        }

        int opcion = 4;
        while (opcion != 4) {
            System.out.println("==== MENU ====");
            System.out.println("\t1) Lectura y escritura del fichero de cartelera byte a byte (byte Streams).");
            System.out.println("\t2) Lectura y escritura de fichero de cartelera carácter a carácter (character Streams).");
            System.out.println("\t3) Lectura y escritura de fichero línea a línea con buffers (character Streams).");
            System.out.println("\t4) Salir.");
            System.out.print("\n> ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("La opcion debe de ser un numero.");
                continue;
            }

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    // el bucle va a terminar
                    break;
                default:
                    System.out.println("La opcion " + opcion + " no se reconoce.");
                    break;
            }
        }

        sc.close();
    }

    public static String pedirEntrada() throws InputFilePathException {
        Scanner sc = new Scanner(System.in);

        String ruta = sc.nextLine();
        if (new File(ruta).exists() == false) {
            throw new InputFilePathException("La ruta " + ruta + " no existe.");
        }

        return ruta;
    }

    public static void leerConByteStreams() {
    }
}
