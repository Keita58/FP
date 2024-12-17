package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Jugador")
public class Jugadors implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idJugador", insertable = false,updatable = false)
    private int idJugador;

    @Column(name="Nom", length = 20, nullable = false)
    private String nomJugador;

    @Column(name="Guanyats", columnDefinition = "INT DEFAULT 0")
    private int guanyats;

    @Column(name="Posicio",columnDefinition = "INT DEFAULT 0")
    private int punts;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "personatgeDelJugador", joinColumns = {@JoinColumn(name = "idJugador")}, inverseJoinColumns = {@JoinColumn(name = "idPersonatge")})
    private Set<Personatges> personatgeDelJugador;

    public Jugadors() {
        super();
    }

    public Jugadors(String nom) {
        this.nomJugador = nom;
    }

    public Jugadors(String nom, int guanyats, int posicio) {
        this.nomJugador = nom;
        this.guanyats = guanyats;
        this.punts = posicio;
    }

    public int getIdJugador() {
        return idJugador;
    }


    public String getNom() {
        return nomJugador;
    }

    public void setNom(String nom) {
        this.nomJugador = nom;
    }

    public int getGuanyats() {
        return guanyats;
    }

    public void setGuanyats(int guanyats) {
        this.guanyats = guanyats;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public Set<Personatges> getPersonatgeDelJugador() {
        return personatgeDelJugador;
    }

    public void setPersonatgeDelJugador(Set<Personatges> personatgeDelJugador) {
        this.personatgeDelJugador = personatgeDelJugador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugadors jugadors = (Jugadors) o;
        return idJugador == jugadors.idJugador;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idJugador);
    }

    @Override
    public String toString() {
        return "Jugadors{" +
                "idJugador=" + idJugador +
                ", nom='" + nomJugador + '\'' +
                ", guanyats=" + guanyats +
                ", punts=" + punts +
                '}';
    }
}
