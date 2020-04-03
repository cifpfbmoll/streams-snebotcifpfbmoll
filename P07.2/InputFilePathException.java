import java.util.*;
import java.io.*;
import java.text.*;

public class InputFilePathException extends Throwable {
    private String error = "";

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public InputFilePathException() {}

    public InputFilePathException(String error) {
        this.error = error;
    }

    public void log(String ruta, TipoStream tipo) {
        try {
            // obtener fecha y hora
            String str = "";
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
            Date objFecha = new Date();
            String fecha = df.format(objFecha);
            String error = getError();
            str = fecha + " - " + error + " | " + getStackTrace() + "\n";
    
            GestorArchivos.escribir(ruta, str, tipo, true);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    // escritura a nivel de bytes va a ser la predetermina
    public void log(String ruta) {
        log(ruta, TipoStream.BYTE);
    }
}
