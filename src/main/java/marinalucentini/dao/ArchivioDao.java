package marinalucentini.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import marinalucentini.entities.Catalogo;
import marinalucentini.entities.Libro;
import marinalucentini.entities.Prestito;
import marinalucentini.entities.Utente;
import marinalucentini.exception.ArchivioException;

import java.time.LocalDate;
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
        if (elementoCatalogo == null) throw new ArchivioException();
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

    public List<Catalogo> findByTitle(String title) {
        TypedQuery<Catalogo> query = em.createQuery("SELECT a FROM Catalogo a WHERE  LOWER(titolo) LIKE LOWER(:title) ", Catalogo.class);
        query.setParameter("title", "%" + title + "%");

        return query.getResultList();
    }

    public void UserSave(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("L'utente" + utente.getNome() + utente.getCognome() + "è stato aggiunto correttamente");
    }

    public Utente findByNameAndSurname(String nome, String cognome) {
        TypedQuery<Utente> query = em.createQuery("SELECT a FROM Utente a WHERE LOWER(a.nome) LIKE LOWER(:nome) AND LOWER(a.cognome) LIKE LOWER(:cognome)", Utente.class);
        query.setParameter("nome", "%" + nome + "%");
        query.setParameter("cognome", "%" + cognome + "%");

        return query.getSingleResult();
    }

    public void savePrestito(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(prestito);
        transaction.commit();
        System.out.println("Il prestito" + prestito.getId() + "è stato aggiunto al db");
    }

    public Prestito findByIdPrestito(String id) {
        Prestito prestitoTrovato = em.find(Prestito.class, UUID.fromString(id));
        if (prestitoTrovato == null) throw new ArchivioException();
        return prestitoTrovato;
    }

    public void updateDataRestituzioneEffettiva(LocalDate data, String id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query modificateDate = em.createQuery("UPDATE  Prestito a SET a.dataRestituzioneEffettiva = :data  WHERE a.id = :id");
        modificateDate.setParameter("data", data);
        modificateDate.setParameter("id", UUID.fromString(id));
        modificateDate.executeUpdate();
        transaction.commit();
        System.out.println("L'elemento è stato restituito il giorno" + data);
    }


}
