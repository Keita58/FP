package Classes;
public class Missio implements Comparable<Missio>{

    String nomEnemic;
    String descripcio;
    int urgencia;
    
    public Missio(String nomEnemic, String descripcio, int urgencia) {
        this.nomEnemic = nomEnemic;
        this.descripcio = descripcio;
        this.urgencia = urgencia;
    }

    public String getNomEnemic() {
        return nomEnemic;
    }

    public void setNomEnemic(String nomEnemic) {
        this.nomEnemic = nomEnemic;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    @Override
    public int compareTo(Missio o) {
        return this.getUrgencia() - o.getUrgencia();
    }

    @Override
    public String toString() {
        return "Missio [nomEnemic=" + nomEnemic + ", descripcio=" + descripcio + ", urgencia=" + urgencia + "]";
    }
}
