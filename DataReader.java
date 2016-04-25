package contents;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * DataReader reads in a String corresponding to a file, reads the file, and builds a String out of it
 * 
 * @author John
 */
public class DataReader {
    private String file;
    private String line = null;
    private StringBuilder total = new StringBuilder();
    /**
     * Constructs and initializes DataReader objects.
     * 
     * @param file - a String that corresponds to the name of a file, including the file extension
     */
    public DataReader(String file){
        this.file = file;
    }
    /**
     * Reads in a String corresponding to a file and returns a String built from this file.
     * 
     * @return a String built from the file with the same name as the String file
     * @throws IOException
     */
    public String read() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            total.append(line+"\n");
        }
        reader.close();
        return total.toString();
    }
}
