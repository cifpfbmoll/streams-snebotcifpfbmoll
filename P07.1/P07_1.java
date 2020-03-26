import java.util.*;
import java.io.*;

public class P07_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
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
                    try {
                        leerConByteStreams();
                    } catch (InputFilePathException e) {
                        System.out.println(e.getError());
                        e.log("errores.txt");
                    } catch (IOException ioe) {
                        System.out.println(ioe.getMessage());
                    }
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

    // preferiria utilizar string.split() pero el caracter '{' es un caracter del regex y no se puede escapar
    public static String[] separarString(String str, char c) {
        // no se la mida del array asi que no puedo instanciar un String[] sin saber la mida
        // con ArrayList<String> puede hacerlo
        ArrayList<String> lista_str = new ArrayList<String>();
        String tmp_str = "";
        for (int i = 0; i < str.length(); i ++) {
            if (str.charAt(i) == c) {
                lista_str.add(tmp_str);
                tmp_str = "";
            } else {
                tmp_str += str.charAt(i);
            }
        }

        lista_str.add(tmp_str);

        return lista_str.toArray(new String[0]);
    }

    public static void analizarDatos(String datos) {
        System.out.println("====================================");
        System.out.println("===== CARTELERA DE CINE FBMOLL =====");
        System.out.println("====================================");
        // primero separamos todo el string por '{' (por pelicula)
        // por cada substring, lo separamos por '#' (por dato)
        for (String str : separarString(datos, '{')) {
            Pelicula p = new Pelicula();
            int i = 0;
            for (String dato : separarString(str, '#')) {
                switch (i) {
                    case 0:
                        p.setTitulo(dato);
                        break;
                    case 1:
                        p.setAno(dato);
                        break;
                    case 2:
                        p.setDirector(dato);
                        break;
                    case 3:
                        p.setDuracion(dato + " minutos");
                        break;
                    case 4:
                        p.setSinopsis(dato);
                        break;
                    case 5:
                        p.setReparto(dato);
                        break;
                    case 6:
                        p.setSesion(dato + " horas");
                        break;
                    default:
                        // hay mas datos de los esperados
                        System.out.println("El archivo contiene mas datos de los esperados. Puede que este corrupto.");
                        break;
                }

                i ++;
            } 
            p.datos();
        }
    }

    public static void leerConByteStreams() throws InputFilePathException, IOException {
        FileInputStream fin = null;
        try {
            System.out.print("Ruta del fichero: ");
            String ruta = pedirEntrada();
            //System.out.print("Ruta de salida: ");
            //String salida = pedirEntrada();

            fin = new FileInputStream(ruta);
            String buff = "";
            int c = 0;

            while ((c = fin.read()) != -1) buff += (char)c;

            analizarDatos(buff);
        } catch (InputFilePathException e) {
            e.log("errores.txt");
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if (fin != null) fin.close();
        }
    }

    public static void leerConCharacterStreams() {
    }

    public static void leerConBufferStreams() {
    }
}
