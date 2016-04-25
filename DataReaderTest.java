package contents;
import java.io.IOException;
/**
 * DataReaderTest tests the functionality of the DataReader class.
 * 
 * @author John
 */
public class DataReaderTest{
    public static void main (String args[]) throws IOException {
        DataReader sample = new DataReader("amazon-meta-head.txt");
        System.out.println(sample.read());
    }
}