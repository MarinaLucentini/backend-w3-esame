package marinalucentini.entities;

import jakarta.persistence.*;
import marinalucentini.enums.Periodicità;

@Entity
@DiscriminatorValue("rivista")
public class Rivista extends Catalogo {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicità periodicità;

    public Rivista() {
    }

    public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicità periodicità) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicità = periodicità;
    }

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "titolo=" + getTitolo() +
                "periodicità=" + periodicità +
                '}';
    }
}
