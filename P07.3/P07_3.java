import java.util.*;
import java.io.*;

public class P07_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        while (opcion != 4) {
            System.out.println("==== MENÚ ====");
            System.out.println("\t1) Mostrar notas.");
            System.out.println("\t2) Leer notas y escribir en fichero objeto.");
            System.out.println("\t3) Leer fichero objetos y mostrar datos.");
            System.out.println("\t4) Salir.");
            System.out.print("> ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("La opcion debe de ser un numero.");
                continue;
            }

            try {
                switch (opcion) {
                    case 1:
                        mostrarDatos("datos.txt");
                        break;
                    case 2:
                        escribirObjetos("datos.txt", "obj.txt");
                        break;
                    case 3:
                        lecturaObjetos("obj.txt");
                        break;
                    case 4:
                        // no hacer nada
                        break;
                    default:
                        System.out.println("La opcion " + opcion + " no se reconoce.");
                        break;
                }
            } catch (FileNotFoundException e) {
                System.out.println("No se ha encontrado el archivo.");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String[] leerDatos(String ruta) throws FileNotFoundException, IOException {
        ArrayList<String> arr = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(ruta));
        try {
            String linea = "";
            while ((linea = in.readLine()) != null) arr.add(linea);
        } finally {
            in.close();
        }

        String[] ret = new String[arr.size()];
        ret = arr.toArray(ret);
        return ret;
    }

    public static Boletin crearBoletin(String[] datos) {
        String nombre = datos[0] + " " + datos[1] + " " + datos[2];
        return new Boletin(nombre, datos[3], datos[4], datos[5], datos[6], datos[7], datos[8]);
    }

    public static void mostrarDatos(String ruta) throws FileNotFoundException, IOException {
        for (String dato : leerDatos(ruta)) {
            Boletin bol = crearBoletin(dato.split(" "));
            System.out.println(bol.datos());
        }
    }

    public static void escribirObjetos(String entrada, String salida) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(salida, true));
        try {
            for (String dato : leerDatos(entrada)) {
                Boletin bol = crearBoletin(dato.split(" "));
                System.out.println(bol.datos());
                out.writeObject(bol);
            }
        } finally {
            out.close();
        }
    }

    public static void lecturaObjetos(String ruta) throws FileNotFoundException, ClassNotFoundException, IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta));
        try {
            while (true) {
                Boletin bol = (Boletin)in.readObject();
                System.out.println(bol.datos());
            }
        } catch (EOFException e) {
            // no hacer nada, readObject() ha llegado al final del archivo
        }
    } 
}
