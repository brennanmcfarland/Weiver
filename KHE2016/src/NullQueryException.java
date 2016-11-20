/**
 *
 * @author Brennan McFarland
 */

/**
 * thrown when a query to a site can't find a page on the given product
 */

public class NullQueryException extends Exception {
    //an exception to throw when a product cannot be found on a given site
    public NullQueryException(String message) {
        super(message);
    }
}