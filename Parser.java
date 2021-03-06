package contents;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Parser outputs CSV files via one of its methods.
 *The CSV file is uses data from the text file designated in the constructor parameter {@code file}.
 * 
 * @author John Meier
 */
public class Parser {
    private String file;
    private List<List<String>> List;
    private Iterator<List<String>> it;
    private Iterator<String> prodData;
    private Product product;
    private List<Product> products = new ArrayList<Product>();
    private Iterator<Product> prod;
    private Customer customer;
    private List<Customer> customers = new ArrayList<Customer>();
    private Iterator<Customer> cust;
    private Category category;
    private List<Category> categories = new ArrayList<Category>();
    private Iterator<Category> cat;
    private ParentCategory categoryP;
    private List<ParentCategory> categoriesP = new ArrayList<ParentCategory>();
    private Iterator<ParentCategory> pat;
    private String id;
    private String asin;
    private String title;
    private String group;
    private String rank;
    private String date;
    private String custnum;
    private String rating;
    private String votes;
    private String helpful;
    private String c;
    private String cid;
    private String p;
    private String pid;
    private Pattern pattern;
    private Matcher matcher;
    private FileWriter writer;
    /**
     * Constructs and initializes a Parser objects.
     * 
     * @param piece - The name of the text file to be parsed by the Parser objects
     */
    public Parser(String file){
        this.file = file;
    }
    
    public void parseProduct(String csvFileName) throws IOException{
        List = new Lexer(file).search();
        it = List.iterator();
        while(it.hasNext()){
            id = null;
            asin = null;
            title = null;
            group = null;
            rank = null;
            List<String> data = it.next();
            prodData = data.iterator();
            while(prodData.hasNext()){
                String term = prodData.next();
                if(term.startsWith("Id: ")){
                    pattern=Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(term);
                    while(matcher.find()){
                        id = matcher.group();
                    }
                }
                if(term.startsWith("ASIN: ")){
                    pattern=Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(term);
                    while(matcher.find()){
                        asin = matcher.group();
                    }
                }
                if(term.startsWith("title: ")){
                    pattern=Pattern.compile("(?<=title: )[^\n]+");
                    matcher = pattern.matcher(term);
                    while(matcher.find()){
                        title = matcher.group();
                        title = title.replace(",",";");
                        title = title.replace("\"","'");
                    }
                }
                if(term.startsWith("group: ")){
                    pattern=Pattern.compile("(?<=group: )[^\n]+");
                    matcher = pattern.matcher(term);
                    while(matcher.find()){
                        group = matcher.group();
                    }
                }
                if(term.startsWith("salesrank: ")){
                    pattern=Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(term);
                    while(matcher.find()){
                        rank = matcher.group();
                    }
                }
            }
            if(id != null && asin != null && title != null && group != null && rank != null){
                product = new Product(id,asin,title,group,rank);
                products.add(product);
            }
        }
        writer = new FileWriter(csvFileName);
        writer.append("id,asin,title,group,salerank\n");
        prod = products.iterator();
        while(prod.hasNext()){
            Product next = prod.next();
            writer.append(next.getId() + ',' + 
                          next.getAsin() + ',' + 
                          next.getTitle() + ',' + 
                          next.getGroup() + ',' + 
                          next.getRank() + '\n');
        }
        writer.flush();
        writer.close();
    }
    
    public void parseCustomer(String csvFileName) throws IOException{
        List = new Lexer(file).search();
        it = List.iterator();
        while(it.hasNext()){
            asin=null;
            date=null;
            custnum=null;
            rating=null;
            votes=null;
            helpful=null;
            List<String> data = it.next();
            prodData = data.iterator();
            int count = 0;
            while(prodData.hasNext()){
                String term = prodData.next();
                if(term.startsWith("ASIN: ")){
                    pattern=Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(term);
                    while(matcher.find()){
                        id = matcher.group();
                    }
                }
                if(term.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}") && 
                   (data.size() - count) > 4){
                    //Date
                    String[] pieces = data.get(count).split("-");
                    if(pieces[1].length()<2){
                        pieces[1] = "0" + pieces[1];
                    }
                    if(pieces[2].length()<2){
                        pieces[2] = "0" + pieces[2];
                    }
                    date = pieces[0] + pieces[1] + pieces[2];
                    //Customer number
                    pattern=Pattern.compile("(?<=cutomer: )[^\n]+");
                    matcher = pattern.matcher(data.get(count+1));
                    while(matcher.find()){
                        custnum = matcher.group();
                    }
                    //Rating
                    pattern=Pattern.compile("[0-9]");
                    matcher = pattern.matcher(data.get(count+2));
                    while(matcher.find()){
                        rating = matcher.group();
                    }
                    //Votes
                    pattern=Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(data.get(count+3));
                    while(matcher.find()){
                        votes = matcher.group();
                    }
                    //Helpful
                    pattern=Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(data.get(count+4));
                    while(matcher.find()){
                        helpful = matcher.group();
                    }
                }
                count++;
                if(id != null && date != null && custnum != null && rating != null && votes != null && helpful != null){
                    customer = new Customer(id,date,custnum,rating,votes,helpful);
                    if(customers.size() == 0){
                        customers.add(customer);
                    }
                    else if(customer.getCustomer() != customers.get(customers.size()-1).getCustomer() &&
                            customer.getDate() != customers.get(customers.size()-1).getDate()){
                        customers.add(customer);
                    }
                }
            }
        }
        writer = new FileWriter(csvFileName);
        writer.append("asin,date,custnum,rating,votes,helpful\n");
        cust = customers.iterator();
        while(cust.hasNext()){
            Customer next = cust.next();
            writer.append(next.getAsin() + ',' + 
                          next.getDate() + ',' + 
                          next.getCustomer() + ',' + 
                          next.getRating() + ',' + 
                          next.getVotes() + ',' +  
                          next.getHelpful() + '\n');
        }
        writer.flush();
        writer.close();
    }
    public void parseCat(String csvFileNameCAT,String csvFileNamePAT) throws IOException{
        List = new Lexer(file).search();
        it = List.iterator();
        while(it.hasNext()){
            asin = null;
            c = null;
            cid = null;
            List<String> data = it.next();
            prodData = data.iterator();
            while(prodData.hasNext()){
                String term = prodData.next();
                if(term.startsWith("ASIN: ")){
                    pattern=Pattern.compile("[0-9]+");
                    matcher = pattern.matcher(term);
                    while(matcher.find()){
                        id = matcher.group();
                    }
                }
                if(term.startsWith("|")){
                    String[] pieces = term.split("\\|");
                    pattern=Pattern.compile("[^\n]+(?=\\[\\d+\\])");
                    matcher = pattern.matcher(pieces[pieces.length-1]);
                    while(matcher.find()){
                        c = matcher.group();
                        c = c.replace(",",";");
                    }
                    pattern=Pattern.compile("(?<=\\[)(\\d+)(?=\\])");
                    matcher = pattern.matcher(pieces[pieces.length-1]);
                    while(matcher.find()){
                        cid = matcher.group();
                    }
                    int i;
                    String child=cid;
                    for(i=pieces.length-2; i > 0; i--){
                        pattern=Pattern.compile("[^\n]+(?=\\[\\d+\\])");
                        matcher = pattern.matcher(pieces[i]);
                        while(matcher.find()){
                            p = matcher.group();
                            p = p.replace(",",";");
                        }
                        pattern=Pattern.compile("(?<=\\[)(\\d+)(?=\\])");
                        matcher = pattern.matcher(pieces[i]);
                        while(matcher.find()){
                            pid = matcher.group();
                        }
                        categoryP = new ParentCategory(child,p,pid);
                        categoriesP.add(categoryP);
                        child=pid;
                    }
                }
                if(id != null && c !=null && cid != null){
                    category = new Category(id,c,cid);
                    if(categories.size() == 0){
                        categories.add(category);
                    }
                    else if(category.getCid() != categories.get(categories.size()-1).getCid()){
                        categories.add(category);
                    }
                }
            }
        }
        writer = new FileWriter(csvFileNameCAT);
        writer.append("asin,c,cid\n");
        cat = categories.iterator();
        while(cat.hasNext()){
            Category next = cat.next();
            writer.append(next.getAsin() + ',' + 
                          next.getC() + ',' + 
                          next.getCid() + '\n');
        }
        writer.flush();
        writer = new FileWriter(csvFileNamePAT);
        writer.append("cid,p,pid\n");
        pat = categoriesP.iterator();
        while(pat.hasNext()){
            ParentCategory next = pat.next();
            writer.append(next.getCid() + ',' + 
                          next.getP() + ',' + 
                          next.getPid() + '\n');
        }
        writer.close();
    }
}
