package Examen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Personatges")
public class Personatges implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonatge")
    private int idPersonatge;

    @Column(name = "Nom", length = 20, nullable = false)
    private String nomPersonatge;

    @Column(name = "Viu", nullable = false)
    private boolean viu;

    @Column(name = "Genere")
    private Genere genere;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "personatgeDelJugador")
    @JsonBackReference
    private Set<Jugadors> personatgeAmbJugador;

    public Personatges() {
        super();
    }

    public Personatges(String nom, boolean viu, Genere genere) {
        this.nomPersonatge = nom;
        this.viu = viu;
        this.genere = genere;
    }

    public int getIdPersonatge() {
        return idPersonatge;
    }

    public void setIdPersonatge(int idPersonatge) {
        this.idPersonatge = idPersonatge;
    }

    public String getNom() {
        return nomPersonatge;
    }

    public void setNom(String nom) {
        this.nomPersonatge = nom;
    }

    public boolean getViu() {
        return viu;
    }

    public void setViu(boolean viu) {
        this.viu = viu;
    }

    public Genere getGenere() {
        return this.genere;
    }

    public void setGenere(Genere bales) {
        this.genere = genere;
    }

    public Set<Jugadors> getPersonatgeAmbJugador() {
        return personatgeAmbJugador;
    }

    public void setPersonatgeAmbJugador(Set<Jugadors> personatgeAmbJugador) {
        this.personatgeAmbJugador = personatgeAmbJugador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personatges that = (Personatges) o;
        return idPersonatge == that.idPersonatge;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPersonatge);
    }

    @Override
    public String toString() {
        return "Personatges{" +
                "idPersonatge=" + idPersonatge +
                ", nom='" + nomPersonatge + '\'' +
                ", genere=" + genere +
                '}';
    }
}
