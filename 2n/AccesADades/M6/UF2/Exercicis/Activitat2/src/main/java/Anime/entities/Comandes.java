package Anime.entities;

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
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Comandes")
public class Comandes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComanda", updatable = false)
    private int idComanda;

    @Column(name = "DataComanda", columnDefinition = "datetime default current_timestamp")
    private LocalDateTime dataComanda;

    @Column(name = "PreuFinal", columnDefinition = "Decimal(12, 2) default 0.0")
    private double preuFinal;

    @Column(name = "Pagat")
    private boolean pagat;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Anime", joinColumns = {@JoinColumn(name = "idComanda")}, inverseJoinColumns = {@JoinColumn(name = "idAnime")})
    private Set<Anime> animes;

    public Comandes() {
        super();
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
