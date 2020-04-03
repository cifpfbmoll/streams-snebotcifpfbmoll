import java.util.*;
import java.io.Serializable;

public class Pelicula implements Serializable {
    private String titulo = "";
    private String ano = "";
    private String director = "";
    private String duracion = "";
    private String sinopsis = "";
    private String reparto = "";
    private String sesion = "";

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAno() {
        return this.ano;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDuracion() {
        return this.duracion;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getSinopsis() {
        return this.sinopsis;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getReparto() {
        return this.reparto;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getSesion() {
        return this.sesion;
    }

    public Pelicula() {}

    public Pelicula(String titulo, String ano, String director, String duracion, String sinopsis, String reparto, String sesion) {
        this.titulo = titulo;
        this.ano = ano;
        this.director = director;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.sesion = sesion;
    }

    public String datos() {
        String str = "-----" + getTitulo() + "-----\n";
        str += "Año: " + getAno() + "\n";
        str += "Director: " + getDirector() + "\n";
        str += "Duracion: " + getDuracion() + "\n";
        str += "Sinopsis: " + getSinopsis() + "\n";
        str += "Reparto: " + getReparto() + "\n";
        str += "Sesión: " + getSesion() + "\n";
        return str;
    }
}
