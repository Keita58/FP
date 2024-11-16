package entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Anime")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnime", updatable = false, insertable = false)
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

    @Column(name = "Puntuacio", columnDefinition = "decimal(12, 2) default 0")
    private double puntuacio;

    @Column(name = "DataCreacio")
    private LocalDateTime dataCreacio = LocalDateTime.now();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "animesComprats", fetch = FetchType.EAGER)
    private Set<Comandes> comandesAnime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPlataforma")
    private Plataforma plataforma;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animeOnApareixo")
    private Set<Personatges> personatgesDeLanime;

    public Anime() {
        super();
    }

    public Anime(String titol, String sinopsis, boolean disponibleStreaming) {
        this();
        this.titol = titol;
        this.sinopsis = sinopsis;
        this.disponibleStreaming = disponibleStreaming;
    }

    public Anime(int idAnime, String titol, String sinopsis, Genere genere, int episodis, boolean disponibleStreaming,
            double puntuacio, LocalDateTime dataCreacio) {
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

    public Set<Comandes> getComandesAnime() {
        return comandesAnime;
    }

    public void setComandesAnime(Set<Comandes> comandesAnime) {
        this.comandesAnime = comandesAnime;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Set<Personatges> getPersonatgesDeLanime() {
        return personatgesDeLanime;
    }

    public void setPersonatgesDeLanime(Set<Personatges> personatgesDeLanime) {
        this.personatgesDeLanime = personatgesDeLanime;
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
