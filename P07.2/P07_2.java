import java.util.*;
import java.io.*;

public class P07_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("==== MENU ====");
            System.out.println("\t1) Lectura y escritura del fichero de cartelera byte a byte (byte Streams).");
            System.out.println("\t2) Lectura y escritura de fichero de cartelera carácter a carácter (character Streams).");
            System.out.println("\t3) Lectura y escritura de fichero línea a línea con buffers (character Streams).");
            System.out.println("\t4) Tratamiento de objetos.");
            System.out.println("\t5) Salir.");
            System.out.print("\n> ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("La opcion debe de ser un numero.");
                continue;
            }

            TipoStream tipo = null;
            switch (opcion) {
                case 1:
                    tipo = TipoStream.BYTE;
                    break;
                case 2:
                    tipo = TipoStream.CHAR;
                    break;
                case 3:
                    tipo = TipoStream.BUFFER;
                    break;
                case 4:
                    tratamientoObjetos();
                    break;
                case 5:
                    // el bucle va a terminar
                    break;
                default:
                    System.out.println("La opcion " + opcion + " no se reconoce.");
                    break;
            }

            if (tipo != null) {
                try {
                    System.out.print("Ruta del fichero: ");
                    String entrada = pedirEntrada();
                    System.out.print("Ruta de salida: ");
                    String salida = pedirSalida();
        
                    String buff = "";
                    buff = GestorArchivos.leer(entrada, tipo);
                    String datos = analizarDatos(buff);

                    System.out.println("====================================");
                    System.out.println("===== CARTELERA DE CINE FBMOLL =====");
                    System.out.println("====================================");
                    System.out.println(datos);

                    GestorArchivos.escribir(salida, datos, tipo, true);
                } catch (InputFilePathException e) {
                    System.out.println(e.getError());
                    e.log("errores.txt", tipo);
                } catch (OutputFilePathException e) {
                    System.out.println(e.getError());
                    e.log("errores.txt", tipo);
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
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

    public static String pedirSalida() throws OutputFilePathException {
        Scanner sc = new Scanner(System.in);
        String ruta = sc.nextLine();
        if (new File(ruta).exists() == false) {
            throw new OutputFilePathException("La ruta " + ruta + " no existe.");
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

    public static String analizarDatos(String datos) {
        String ret = "";
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
            ret += p.datos();
        }
        return ret;
    }

    public static ArrayList<Pelicula> analizarDatosObjetos(String datos) {
        ArrayList<Pelicula> arr = new ArrayList<Pelicula>();
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
            arr.add(p);
        }
        return arr;
    }

    public static void lecturaFicheroEscrituraObjetos() {
        try {
            System.out.print("Fichero de entrada: ");
            String entrada = pedirEntrada();
            System.out.print("Fichero de salida: ");
            String salida = pedirSalida();

            String datos = "";
            datos = GestorArchivos.leer(entrada, TipoStream.BYTE);
            ArrayList<Pelicula> peliculas = analizarDatosObjetos(datos);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(salida, true));
            for (Pelicula p : peliculas) out.writeObject(p);
            out.close();
        } catch (InputFilePathException e) {
            System.out.println(e.getError());
            e.log("errores.txt");
        } catch (OutputFilePathException e) {
            System.out.println(e.getError());
            e.log("errores.txt");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void lecturaObjetosEscrituraObjetos() {
        try {
            System.out.print("Fichero de entrada: ");
            String entrada = pedirEntrada();
            System.out.print("Fichero de salida: ");
            String salida = pedirSalida();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(entrada));
            ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
            
            try {
                // ObjectInputStream.readObject() va a leer hasta que llegue al final del archivo (EOF)
                // cuando lo haga va a lanzar la excepcion EOFException
                //
                // Rafa, no te enfades. Ya se que un while infinito no es adecuado. Pero el metodo readObject() no me deja otra opcion
                // Preferiria que readObject() devolviese algo como null para poder comprobar que se ha llegado al final del archivo
                while (true) peliculas.add((Pelicula)in.readObject());
            } catch (EOFException e) {
                // El stream ha llegado al final del archivo (EOF)
                // no hay que hacer nada
            }

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(salida, true));
            for (Pelicula p : peliculas) out.writeObject(p);

            in.close();
            out.close();
        } catch (InputFilePathException e) {
            System.out.println(e.getError());
            e.log("errores.txt");
        } catch (OutputFilePathException e) {
            System.out.println(e.getError());
            e.log("errores.txt");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void lecturaObjetosEscrituraPantalla() {
        try {
            System.out.print("Fichero de entrada: ");
            String entrada = pedirEntrada();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(entrada));
            ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
            
            try {
                // leer comentarios en el metodo lecturaObjetosEscrituraObjetos()
                while (true) peliculas.add((Pelicula)in.readObject());
            } catch (EOFException e) {
                // no hay que hacer nada
            }

            String buff = "";
            for (Pelicula p : peliculas) buff += p.datos();
            System.out.println(buff);

            in.close();
        } catch (InputFilePathException e) {
            System.out.println(e.getError());
            e.log("errores.txt");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void lecturaPantallaEscrituraObjetos() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Fichero de salida: ");
            String salida = pedirSalida();

            System.out.print("Titulo: ");
            String titulo = sc.nextLine();
            System.out.print("Año: ");
            String ano = sc.nextLine();
            System.out.print("Director: ");
            String director = sc.nextLine();
            System.out.print("Duracion: ");
            String duracion = sc.nextLine();
            System.out.print("Sinopsis: ");
            String sinopsis = sc.nextLine();
            System.out.print("Reparto: ");
            String reparto = sc.nextLine();
            System.out.print("Sesion: ");
            String sesion = sc.nextLine();

            Pelicula p = new Pelicula(titulo, ano, director, duracion, sinopsis, reparto, sesion);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(salida, true));
            out.writeObject(p);
        } catch (OutputFilePathException e) {
            System.out.println(e.getError());
            e.log("errores.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void tratamientoObjetos() {
        Scanner sc = new Scanner(System.in);
        char opcion = 0;
        while (opcion != 'e') {
            System.out.println("==== OBJETOS ====");
            System.out.println("\ta) Lectura línea a línea y escritura con objetos.");
            System.out.println("\tb) Lectura de objetos y escritura de objetos.");
            System.out.println("\tc) Lectura de objetos y escritura por consola.");
            System.out.println("\td) Lectura por consola y escritura de objetos.");
            System.out.println("\te) Volver al menú principal.");
            System.out.print("\n> ");

            opcion = sc.nextLine().charAt(0);

            switch (opcion) {
                case 'a':
                    lecturaFicheroEscrituraObjetos();
                    break;
                case 'b':
                    lecturaObjetosEscrituraObjetos();
                    break;
                case 'c':
                    lecturaObjetosEscrituraPantalla();
                    break;
                case 'd':
                    lecturaPantallaEscrituraObjetos();
                    break;
                case 'e':
                    // se va a volver al menu principal
                    break;
            }
        }
    }
}
