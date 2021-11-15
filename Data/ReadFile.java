package Data;

import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; import java.util.ArrayList; import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;

//https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html

public class ReadFile {
    
    private static String[][] fromCSV(String fileName) 
    {   
        

        Path pathToFile = Paths.get(fileName); 
        // create an instance of BufferedReader 
        // using try with resource, Java 7 feature to close resources 
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            // read the first line from the text file 
            List<String> dat = new ArrayList<String>();
            String l = br.readLine(); 
            
            int j = 0;
            // loop until all lines are read 
            while (l != null) { 
                // use string.split to load a string array with the values from 
                // each line of 
                // the file, using a comma as the delimiter 
                String[] line = l.split(",");

                for(int i=0;i<line.length;i++){

                }
                //TODO: add data to dat
                

                // read next line before looping 
                // if end of file reached, line would be null 
                l = br.readLine(); 
                j++;
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 



        String[][] datarray= new String[0][0]; //this will be determined by the size obvs

        return datarray; 
    }
}
