public class NullQueryException extends Exception {
    //an exception to throw when a product cannot be found on a given site
    public NullQueryException(String message) {
        super(message);
    }
}