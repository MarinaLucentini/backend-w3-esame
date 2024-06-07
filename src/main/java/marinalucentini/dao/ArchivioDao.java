package marinalucentini.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import marinalucentini.entities.Catalogo;
import marinalucentini.entities.Libro;
import marinalucentini.exception.ArchivioException;

import java.util.List;
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

    public void findAndDeleteById(String id) {
        Catalogo found = this.findById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("L'elemento" + found.getTitolo() + "è stato correttamente eliminato");
    }

    public List<Catalogo> findByYearPubblication(int year) {
        TypedQuery<Catalogo> query = em.createQuery("SELECT e FROM Catalogo e WHERE e.annoPubblicazione = :anno_pubblicazione", Catalogo.class);
        query.setParameter("anno_pubblicazione", year);
        return query.getResultList();
    }

    public List<Libro> findByAuthor(String author) {
        TypedQuery<Libro> query = em.createQuery("SELECT a FROM Libro a WHERE autore = :author", Libro.class);
        query.setParameter("author", author);
        return query.getResultList();

    }
}
