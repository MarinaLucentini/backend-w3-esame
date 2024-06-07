package marinalucentini.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "elemento_catalogo")
public abstract class Catalogo {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, name = "codice_ISBN")
    private UUID codiceISBN;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false, name = "anno_pubblicazione")
    private int annoPubblicazione;
    @Column(nullable = false, name = "numero_pagine")
    private int numeroPagine;
    @OneToMany(mappedBy = "elementoPrestato")
    private List<Prestito> prestitoList;

    public Catalogo() {

    }

    public Catalogo(String titolo, int annoPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public UUID getCodiceISBN() {
        return codiceISBN;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
