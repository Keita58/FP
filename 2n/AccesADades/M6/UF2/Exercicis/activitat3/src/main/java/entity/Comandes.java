package entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comandes")
public class Comandes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComanda", updatable = false, insertable = false)
    private int idComanda;

    @Column(name = "DataComanda")
    private LocalDateTime dataComanda = LocalDateTime.now();

    @Column(name = "PreuFinal", columnDefinition = "decimal(12, 2) default 0")
    private double preuFinal;

    @Column(name = "Pagat")
    private boolean pagat;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "AnimeComprat", joinColumns = {@JoinColumn(name = "idComanda")}, inverseJoinColumns = {@JoinColumn(name = "idAnime")})
    private Set<Anime> animesComprats;

    public Comandes() {
        super();
    }

    public Comandes(boolean pagat) {
        this();
        this.pagat = pagat;
    }

    public Comandes(int idComanda, LocalDateTime dataComanda, double preuFinal, boolean pagat) {
        this();
        this.idComanda = idComanda;
        this.dataComanda = dataComanda;
        this.preuFinal = preuFinal;
        this.pagat = pagat;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public LocalDateTime getDataComanda() {
        return dataComanda;
    }

    public void setDataComanda(LocalDateTime dataComanda) {
        this.dataComanda = dataComanda;
    }

    public double getPreuFinal() {
        return preuFinal;
    }

    public void setPreuFinal(double preuFinal) {
        this.preuFinal = preuFinal;
    }

    public boolean isPagat() {
        return pagat;
    }

    public void setPagat(boolean pagat) {
        this.pagat = pagat;
    }

    public Set<Anime> getAnimesComprats() {
        return animesComprats;
    }

    public void setAnimesComprats(Set<Anime> animesComprats) {
        this.animesComprats = animesComprats;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idComanda;
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
        Comandes other = (Comandes) obj;
        if (idComanda != other.idComanda)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comandes [idComanda=" + idComanda + ", dataComanda=" + dataComanda + ", preuFinal=" + preuFinal
                + ", pagat=" + pagat + "]";
    }
}
