
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
    String websiteTitle()throws IOException;
    String findArticleBody()throws IOException;
    //void findSubtitle()throws IOException;
    String findPros()throws IOException;
    String findCons()throws IOException;
}
