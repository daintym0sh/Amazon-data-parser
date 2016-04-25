package contents;

import java.io.IOException;
/**
 * ParserTest tests the functionality of the Parser class
 * 
 * @author John
 */
public class ParserTest {
    public static void main(String args[]) throws IOException{
        Parser parser = new Parser("newaa.txt");
        parser.parseCustomer("custCSV.csv");
        //parser.parseProduct("productCSV.csv");
    }
}
