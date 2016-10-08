
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joshreichman
 */
public interface ParseWebPage {
    void websiteTitle()throws IOException;
    void findArticleBody()throws IOException;
    void findSubtitle()throws IOException;
    void findPros()throws IOException;
    void findCons()throws IOException;
}
