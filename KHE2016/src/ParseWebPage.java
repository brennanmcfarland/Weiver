
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
    public String websiteTitle()throws IOException;
    public String findArticleBody()throws IOException;
    public String findPros()throws IOException;
    public String findCons()throws IOException;
}
