package marinalucentini;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import marinalucentini.dao.ArchivioDao;
import marinalucentini.entities.*;

import java.time.LocalDate;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_bibliografico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Hello World!");
        Libro libro1 = Archivio.createBook(2022);
//        Libro libro2 = Archivio.createBook(2015);
//        Libro libro3 = Archivio.createBook(2012);
//        Rivista rivista1 = Archivio.createRivista(2020, Periodicità.MENSILE);
//        Rivista rivista2 = Archivio.createRivista(2022, Periodicità.SETTIMANALE);
//        Rivista rivista3 = Archivio.createRivista(2022, Periodicità.SEMESTRALE);
        ArchivioDao archivioDao = new ArchivioDao(em);
        //  archivioDao.Archiviosave(libro1);
//        archivioDao.Archiviosave(libro2);
//        archivioDao.Archiviosave(libro3);
//        archivioDao.Archiviosave(rivista1);
//        archivioDao.Archiviosave(rivista2);
//        archivioDao.Archiviosave(rivista3);
        Catalogo elementoTrovato = archivioDao.findById("131f6ec1-e053-48e3-9358-6d76ddfaf340");
        // System.out.println(elementoTrovato);
        // archivioDao.findAndDeleteById("ca97e323-c14a-4511-8227-0307b4c6133d");
        List<Catalogo> elementiCercati = archivioDao.findByYearPubblication(2022);
        // elementiCercati.forEach(elemento -> System.out.println(elemento));
        List<Libro> libriCercati = archivioDao.findByAuthor("Joan Hettinger Jr.");
        // libriCercati.forEach(el -> System.out.println(el));
        List<Catalogo> elementiCercatiByTitle = archivioDao.findByTitle("ad");
        elementiCercatiByTitle.forEach(el -> System.out.println(el));
        Utente utente1 = Archivio.createUtente(LocalDate.of(1983, 5, 31));
        Utente utente2 = Archivio.createUtente(LocalDate.of(1992, 6, 10));
        Utente utente3 = Archivio.createUtente(LocalDate.of(1983, 4, 15));
//        archivioDao.UserSave(utente1);
//        archivioDao.UserSave(utente2);
//        archivioDao.UserSave(utente3);
        Utente utenteTrovato = archivioDao.findByNameAndSurname("y", "w");
        System.out.println(utenteTrovato);
        Prestito prestitoNonrestituito1 = Archivio.prendereInPrestito(utenteTrovato, elementoTrovato, LocalDate.of(2024, 05, 15));
        //  archivioDao.savePrestito(prestitoNonrestituito1);
        Prestito prestitoNonrestituito2 = Archivio.prendereInPrestito(utenteTrovato, elementoTrovato, LocalDate.of(2024, 06, 07));
        archivioDao.savePrestito(prestitoNonrestituito2);
        em.close();
        emf.close();
    }
}
