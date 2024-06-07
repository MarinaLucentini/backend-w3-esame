package marinalucentini;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import marinalucentini.dao.ArchivioDao;
import marinalucentini.entities.Archivio;
import marinalucentini.entities.Libro;
import marinalucentini.entities.Rivista;
import marinalucentini.enums.Periodicità;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_bibliografico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Hello World!");
        Libro libro1 = Archivio.createBook(2022);
        Libro libro2 = Archivio.createBook(2015);
        Libro libro3 = Archivio.createBook(2012);
        Rivista rivista1 = Archivio.createRivista(2020, Periodicità.MENSILE);
        Rivista rivista2 = Archivio.createRivista(2022, Periodicità.SETTIMANALE);
        Rivista rivista3 = Archivio.createRivista(2022, Periodicità.SEMESTRALE);
        ArchivioDao archivioDao = new ArchivioDao(em);
        archivioDao.Archiviosave(libro1);
        archivioDao.Archiviosave(libro2);
        archivioDao.Archiviosave(libro3);
        archivioDao.Archiviosave(rivista1);
        archivioDao.Archiviosave(rivista2);
        archivioDao.Archiviosave(rivista3);
        em.close();
        emf.close();
    }
}
