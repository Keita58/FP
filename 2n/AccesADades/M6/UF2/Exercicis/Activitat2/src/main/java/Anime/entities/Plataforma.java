package Anime.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Plataforma")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlataforma", updatable = false)
    private int idPlataforma;

    @Column(name = "NomLlocWeb", length = 200, nullable = false)
    private String nomLlocWeb;

    @Column(name = "IP", length = 15, nullable = false)
    private String ip;

    @Column(name = "URL", length = 200, nullable = false)
    private String url;

    @OneToOne(mappedBy = "plataforma", cascade = CascadeType.ALL) 
    //? El mappedBy és el nom de la variable de la classe amb la que fa el join, en aquest cas de la classe Anime
    private Anime anime;

    public Plataforma() {
        super();
    }
    
    public Plataforma(String nomLlocWeb, String ip, String url) {
        this();
        this.nomLlocWeb = nomLlocWeb;
        this.ip = ip;
        this.url = url;
    }

    public Plataforma(int idPlataforma, String nomLlocWeb, String ip, String url) {
        this();
        this.idPlataforma = idPlataforma;
        this.nomLlocWeb = nomLlocWeb;
        this.ip = ip;
        this.url = url;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getNomLlocWeb() {
        return nomLlocWeb;
    }

    public void setNomLlocWeb(String nomLlocWeb) {
        this.nomLlocWeb = nomLlocWeb;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idPlataforma;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Plataforma other = (Plataforma) obj;
        if (idPlataforma != other.idPlataforma)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Plataforma [idPlataforma=" + idPlataforma + ", nomLlocWeb=" + nomLlocWeb + ", ip=" + ip + ", url=" + url
                + "]";
    }
}
