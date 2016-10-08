
/**
 * Abstract class that allows for standardization of fields for the unique
 * structure of each publisher's HTML structure.
 *
 * @author Emilio Lopez *
 */
public abstract class WebPage {

    /**
     * Web site publisher of the review.
     */
    String publisher;

    /**
     * Article title.
     */
    String articleTitle;

    /**
     * The author of the article.
     */
    private String author;

    /**
     * The review article body.
     */
    String articleBody;

    /**
     * The URL of the review.
     */
    private String URL;

    /**
     * An array containing the list of pros for a product.
     */
    private String[] pros;

    /**
     * An array containing the list of cons for a product. 
     */
    private String[] cons;

}
