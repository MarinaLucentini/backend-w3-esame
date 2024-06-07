package marinalucentini.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import marinalucentini.entities.Catalogo;
import marinalucentini.exception.ArchivioException;

import java.util.UUID;

public class ArchivioDao {
    private EntityManager em;

    public ArchivioDao(EntityManager em) {
        this.em = em;
    }

    public void Archiviosave(Catalogo catalogo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(catalogo);
        transaction.commit();
        System.out.println("Elemento" + catalogo.getTitolo() + "inserito correttamente nel catalogo");
    }

    public Catalogo findById(String id) {
        Catalogo elementoCatalogo = em.find(Catalogo.class, UUID.fromString(id));
        if (elementoCatalogo == null) throw new ArchivioException(id);
        return elementoCatalogo;
    }
}
