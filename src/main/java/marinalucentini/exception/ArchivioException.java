package marinalucentini.exception;

public class ArchivioException extends RuntimeException {

    public ArchivioException(String id) {
        super("Il record con l'id " + id + " non è stato trovato!");
    }
}
