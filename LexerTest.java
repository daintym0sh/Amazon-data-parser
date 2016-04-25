package contents;

import java.io.IOException;
import java.util.*;
/**
 * LexerTest tests the functionality of the Lexer class
 * 
 * @author John
 */
public class LexerTest {
    public static void main (String[] args) throws IOException{
        List<List<String>> List = new Lexer("amazon-meta-head.txt").search();
        System.out.println(List.toString());
    }
}
