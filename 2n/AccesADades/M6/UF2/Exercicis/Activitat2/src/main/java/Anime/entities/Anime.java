package Anime.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Anime")
public class Anime implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idAnime", updatable = false)
    private int idAnime;

    @Column(name = "Titol", length = 50, nullable = false)
    private String titol;

    @Column(name = "Sinopsis", length = 50, nullable = false)
    private String sinopsis;

    @Enumerated(EnumType.STRING)
    @Column(name = "Genere")
    private Genere genere;

    @Column(name = "Episodis", columnDefinition = "integer default 0")
    private int episodis;

    @Column(name = "DisponibleStreaming")
    private boolean disponibleStreaming;

    @Column(name = "Puntuacio", columnDefinition = "decimal(12, 2) default 0.0")
    private double puntuacio;

    //@Temporal(TemporalType.DATE)
    @Column(name = "DataCreacio")
    private LocalDateTime dataCreacio = LocalDateTime.now();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "AnimeComprat", joinColumns = {@JoinColumn(name = "idAnime")}, inverseJoinColumns = {@JoinColumn(name = "idComanda")})
    private Set<Comandes> comandes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Plataforma")
    private Plataforma plataforma;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAnime")
    private List<Personatges> personatges;

    public Anime() {
        super();
    }

    public Anime(String titol, String sinopsis) {
        this();
        this.titol = titol;
        this.sinopsis = sinopsis;
    }

    public Anime(int idAnime, String titol, String sinopsis, Genere genere, int episodis, boolean disponibleStreaming, double puntuacio, LocalDateTime dataCreacio) {
        this();
        this.idAnime = idAnime;
        this.titol = titol;
        this.sinopsis = sinopsis;
        this.genere = genere;
        this.episodis = episodis;
        this.disponibleStreaming = disponibleStreaming;
        this.puntuacio = puntuacio;
        this.dataCreacio = dataCreacio;
    }

    public int getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(int idAnime) {
        this.idAnime = idAnime;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public int getEpisodis() {
        return episodis;
    }

    public void setEpisodis(int episodis) {
        this.episodis = episodis;
    }

    public boolean isDisponibleStreaming() {
        return disponibleStreaming;
    }

    public void setDisponibleStreaming(boolean disponibleStreaming) {
        this.disponibleStreaming = disponibleStreaming;
    }

    public double getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(double puntuacio) {
        this.puntuacio = puntuacio;
    }

    public LocalDateTime getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(LocalDateTime dataCreacio) {
        this.dataCreacio = dataCreacio;
    }
    
    public Set<Comandes> getComandes() {
        return comandes;
    }

    public void setComandes(Set<Comandes> comandes) {
        this.comandes = comandes;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public List<Personatges> getPersonatges() {
        return personatges;
    }

    public void setPersonatges(List<Personatges> personatges) {
        this.personatges = personatges;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idAnime;
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
        Anime other = (Anime) obj;
        if (idAnime != other.idAnime)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Anime [idAnime=" + idAnime + ", titol=" + titol + ", sinopsis=" + sinopsis + ", genere=" + genere
                + ", episodis=" + episodis + ", disponibleStreaming=" + disponibleStreaming + ", puntuacio=" + puntuacio
                + ", dataCreacio=" + dataCreacio + "]";
    }
}
