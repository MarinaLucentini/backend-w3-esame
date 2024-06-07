package marinalucentini.entities;

import com.github.javafaker.Faker;
import marinalucentini.enums.Periodicità;

import java.util.Random;

public class Archivio {
    public static Libro createBook(int annoPubblicazione) {
        Faker faker = new Faker();
        Random random = new Random();
        int numeroPagine = random.nextInt(50, 500);
        Libro libro = new Libro(faker.book().title(), annoPubblicazione, numeroPagine, faker.book().author(), faker.book().genre());
        return libro;
    }

    public static Rivista createRivista(int annoPubblicazione, Periodicità periodicità) {
        Faker faker = new Faker();
        Random random = new Random();
        int numeroPagine = random.nextInt(50, 100);
        Rivista rivista = new Rivista(faker.book().title(), annoPubblicazione, numeroPagine, periodicità);
        return rivista;
    }


}
