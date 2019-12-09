package Database;

import static Enumeration.Enumeration.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lasted Updated: 11/21/19
 * Loads user data from text file and separates it into individual parts.
 * @authors Stephen Hampson
 */
public class StoreData {
    /**
     * Writes output to a file.
     *
     * @param _name The name of the file (usually a users name).
     * @param _output The string to be written.
     * @throws FileNotFoundException
     * @throws IOException
     */
    static void writeFile (String _name, String _output) throws FileNotFoundException, IOException{
        //Create file path and use it to open the users file.
        String filepath = USER_FILEPATH + _name + TXT;
        FileWriter writer = new FileWriter(filepath);

        //Write the content recieved to the file then close.
        writer.write(_output);
        writer.close();
    }
}