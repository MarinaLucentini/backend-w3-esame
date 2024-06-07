package marinalucentini.exception;

public class ArchivioException extends RuntimeException {

    public ArchivioException() {
        super("Il record  non è stato trovato!");
    }
}
