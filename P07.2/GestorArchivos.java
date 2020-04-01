import java.util.*;
import java.io.*;
import java.nio.charset.UnsupportedCharsetException;

// No quiero que se hagan instancias de esta clase
// Solo se van a usar metodos static
public abstract class GestorArchivos {
    // todos los metodos excepto leerArchivo() van a ser privados
    private static String pedirEntrada() throws InputFilePathException {
        Scanner sc = new Scanner(System.in);

        String ruta = sc.nextLine();
        if (new File(ruta).exists() == false) {
            throw new InputFilePathException("La ruta " + ruta + " no existe.");
        }

        return ruta;
    }

    public static String leer(String ruta, TipoStream tipo) throws IOException {
        String ret = "";
        switch (tipo) {
            case BYTE:
                {
                    FileInputStream fin = null;
                    try {
                        fin = new FileInputStream(ruta);
                        int c = 0;
                        while ((c = fin.read()) != -1) ret += (char)c;
                    } finally {
                        if (fin != null) fin.close();
                    }
                    break;
                }
            case CHAR:
                {
                    InputStreamReader fin = null;
                    try {
                        fin = new InputStreamReader(new FileInputStream(ruta), "UTF-8");
                        int c = 0;
                        while ((c = fin.read()) != -1) ret += (char)c;
                    } catch (FileNotFoundException e) {
                        System.out.println("No se encuentra el archivo.");
                    } catch (UnsupportedCharsetException uce) {
                        System.out.println("No se soporta el charset del archivo.");
                    } finally {
                        if (fin != null) fin.close();
                    }
                    break;
                }
            case BUFFER:
                {
                    BufferedInputStream fin = null;
                    try {
                        fin = new BufferedInputStream(new FileInputStream(ruta));
                        int c = 0;
                        while ((c = fin.read()) != -1) ret += (char)c;
                    } finally {
                        if (fin != null) fin.close();
                    }
                    break;
                }
            default:
                System.out.println("No se reconoce el tipo de stream.");
                break;
        }

        return ret;
    }

    public static void escribir(String ruta, String str, TipoStream tipo, boolean anadir) throws IOException {
        switch (tipo) {
            case BYTE:
                {
                    FileOutputStream fout = null;
                    try {
                        fout = new FileOutputStream(ruta, anadir);
                        for (char c : str.toCharArray()) {
                            fout.write(c);
                        }
                    } finally {
                        if (fout != null) fout.close();
                    }
                    break;
                }
            case CHAR:
                {
                    OutputStreamWriter fout = null;
                    try {
                        fout = new OutputStreamWriter(new FileOutputStream(ruta, anadir), "UTF-8");
                        for (char c : str.toCharArray()) {
                            fout.write(c);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("No se encuentra el archivo.");
                    } catch (UnsupportedCharsetException uce) {
                        System.out.println("No se soporta el charset del archivo.");
                    } finally {
                        if (fout != null) fout.close();
                    }
                    break;
                }
            case BUFFER:
                {
                    BufferedOutputStream fout = null;
                    try {
                        fout = new BufferedOutputStream(new FileOutputStream(ruta, anadir));
                        for (char c : str.toCharArray()) {
                            fout.write(c);
                        }
                    } finally {
                        if (fout != null) fout.close();
                    }
                    break;
                }
            default:
                System.out.println("No se reconoce el tipo de stream.");
                break;
        }
    }
}
