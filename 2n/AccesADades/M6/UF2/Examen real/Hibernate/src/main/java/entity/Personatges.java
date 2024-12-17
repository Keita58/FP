package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
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

    @Column(name = "Genere")
    private Genere genere;

    @Column(name = "Viu", nullable = false)
    private boolean viu;

    @ManyToMany(mappedBy = "personatgeDelJugador", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Jugadors> personatgeAmbJugador;

    public Personatges() {
        super();
    }

    public Personatges(String nom, Genere genere, boolean viu) {
        this.nomPersonatge = nom;
        this.genere = genere;
        this.viu = viu;
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

    public Genere getGenere() {
        return this.genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public boolean isViu() {
        return viu;
    }

    public void setViu(boolean viu) {
        this.viu = viu;
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
