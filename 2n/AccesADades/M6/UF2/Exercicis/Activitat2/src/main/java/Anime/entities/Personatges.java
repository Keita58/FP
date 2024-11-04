package Anime.entities;

import java.io.*;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Personatge")
public class Personatges implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonatge", updatable = false)
    private int idPersonatge;

    @Column(name = "Nom", length = 50, nullable = false)
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rol")
    private Rol rol;

    @Column(name = "Habilitats", length = 30, nullable = false)
    private String habilitats;

    @Column(name = "Popularitat")
    private int popularitat;

    @Column(name = "DataAparicio")
    private LocalDateTime dataAparicio = LocalDateTime.now();

    public Personatges() {
        super();
    }

    public Personatges(String nom, String habilitats) {
        this();
        this.nom = nom;
        this.habilitats = habilitats;
    }

    public Personatges(int idPersonatge, String nom, Rol rol, String habilitats, int popularitat,
            LocalDateTime dataAparicio) {
        this();
        this.idPersonatge = idPersonatge;
        this.nom = nom;
        this.rol = rol;
        this.habilitats = habilitats;
        this.popularitat = popularitat;
        this.dataAparicio = dataAparicio;
    }

    public int getIdPersonatge() {
        return idPersonatge;
    }

    public void setIdPersonatge(int idPersonatge) {
        this.idPersonatge = idPersonatge;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getHabilitats() {
        return habilitats;
    }

    public void setHabilitats(String habilitats) {
        this.habilitats = habilitats;
    }

    public int getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(int popularitat) {
        this.popularitat = popularitat;
    }

    public LocalDateTime getDataAparicio() {
        return dataAparicio;
    }

    public void setDataAparicio(LocalDateTime dataAparicio) {
        this.dataAparicio = dataAparicio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idPersonatge;
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
        Personatges other = (Personatges) obj;
        if (idPersonatge != other.idPersonatge)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Personatges [idPersonatge=" + idPersonatge + ", nom=" + nom + ", rol=" + rol + ", habilitats="
                + habilitats + ", popularitat=" + popularitat + ", dataAparicio=" + dataAparicio + "]";
    }
}
