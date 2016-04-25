package contents;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lexer reads in a String that corresponds to an file, reads in the file, and adds each term to a List<String>
 * 
 * @author John
 */
public class Lexer {
    private String input;
    private String data;
    private String separator = "\\n\\n";  //Separator 
    private String searchValue = 
        "Id\\s*:\\s*([0-9]+)+\n" + "|" + //ID number
        "ASIN\\s*:\\s*([0-9]+)+\n" + "|" + //ASIN number
        "title\\s*:[^\n]+\n" + "|" + //title
        "group\\s*:[^\n]+\n" + "|" + //group
        "salesrank\\s*:\\s*([0-9]+)+\n" + "|" + //Sales rank
        "\\|\\s*[^\n]+\n" + "|" + //Category
        "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}" + "|" + //Review date
        "cutomer\\s*:\\s*[\\w]{1,14}" + "|" +//Customer number
        "(?<!avg )rating\\s*:\\s*([0-9])" + "|" + //Rating
        "votes\\s*:\\s*([0-9])" + "|" + //Votes
        "helpful\\s*:\\s*([0-9]+\n*)" //Helpful
        ;
    private Pattern pattern;
    private Matcher matcher; 
    private String[] separate;
    private List<String> product;
    private List<List<String>> products = new ArrayList<List<String>>();
    /**
     * Constructs and initializes Lexer objects
     * @param input - a String that corresponds to the name of a file, including the file extension
     */
    public Lexer(String input){
        this.input = input;
    }

    /**
     * Makes a List<List<String>>, where each element is a product that contains data elements
     * 
     * @return a List<String> of terms
     * @throws IOException
     */
    public List<List<String>> search() throws IOException{
        data = new DataReader(input).read();
        separate = data.split(separator);
        pattern = Pattern.compile(searchValue);
        for(String s: separate){
            product = new ArrayList<String>();
            matcher = pattern.matcher(s);
            while(matcher.find()){
                product.add(matcher.group());
            }
            products.add(product);
        }
        
        return products;
    }

}
