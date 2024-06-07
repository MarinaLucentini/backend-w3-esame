package marinalucentini.entities;

import jakarta.persistence.*;
import marinalucentini.enums.Periodicità;

@Entity
@DiscriminatorValue("rivista")
public class Rivista extends Catalogo {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicità periodicità;
}
