import java.util.*;
import java.io.*;

public class Boletin implements Serializable {
    String nombre = "";
    String llm = "";
    String prg = "";
    String ed = "";
    String bd = "";
    String si = "";
    String fol = "";

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setLlm(String llm) {
        this.llm = llm;
    }

    public String getLlm() {
        return this.llm;
    }

    public void setPrg(String prg) {
        this.prg = prg;
    }

    public String getPrg() {
        return this.prg;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getEd() {
        return this.ed;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getBd() {
        return this.bd;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getSi() {
        return this.si;
    }

    public void setFol(String fol) {
        this.fol = fol;
    }

    public String getFol() {
        return this.fol;
    }

    public int[] getResultados() throws NotaException {
        String[] modulos = { getLlm(), getPrg(), getEd(), getBd(), getSi(), getFol() };

        int[] res = new int[2];
        for (String modulo : modulos) {
            if (modulo.charAt(0) == 'c') {
                // tiene el modulo convalidado
                res[0]++;
            } else {
                int nota = Integer.parseInt(modulo);
                if (nota >= 5) {
                    res[0]++;
                } else {
                    if (nota == 0) throw new NotaException("El alumno " + getNombre() + " no puede tener un 0. Debes poner una nota.");
                    res[1]++;
                }
            }
        }

        return res;
    }

    public Boletin() {}

    public Boletin(String nombre, String llm, String prg, String ed, String bd, String si, String fol) {
        this.nombre = nombre;
        this.llm = llm;
        this.prg = prg;
        this.ed = ed;
        this.bd = bd;
        this.si = si;
        this.fol = fol;
    }

    public String datos() throws NotaException {
        String datos = "---------------------------------------------\n";
        datos += "Boletín de notas CIFP FBMOLL\n";
        datos += "---------------------------------------------\n";
        datos += "Alumno: " + getNombre() + "\n";
        datos += "--------------------------------------  -----\n";
        datos += "Módulo                                  Nota\n";
        datos += "--------------------------------------  -----\n";
        datos += "Lenguaje de marcas:                     " + getLlm() + "\n";
        datos += "Programación:                           " + getPrg() + "\n";
        datos += "Entornos de desarollo:                  " + getEd() + "\n";
        datos += "Base de datos:                          " + getBd() + "\n";
        datos += "Sistemas informáticos:                  " + getSi() + "\n";
        datos += "FOL:                                    " + getFol() + "\n";
        datos += "---------------------------------------------\n";
        
        int[] res = getResultados();
        datos += "Nº de módulos aprobados: \t" + res[0] + "\n";
        datos += "Nº de módulos suspendidos: \t" + res[1] + "\n";

        return datos;
    }
}
