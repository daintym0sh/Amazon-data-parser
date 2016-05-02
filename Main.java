package contents;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        Parser parser = new Parser("amazon-meta-head.txt");
        parser.parseCustomer("productCSV.csv");
        parser.parseCustomer("custCSV.csv");
        parser.parseCat("catCSV.csv","patCSV.csv");
    }
}
