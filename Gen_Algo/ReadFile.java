package Gen_Algo;
import Data.*;

import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; import java.util.ArrayList; import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

//import java.io.*;

//https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html

public class ReadFile {
    
    private static String[][] fromCSV(String fileName) 
    {   
        

        Path pathToFile = Paths.get(fileName); 
        // create an instance of BufferedReader 
        // using try with resource, Java 7 feature to close resources 
        int lsize=0;
        List<String> dat = new ArrayList<String>();

        //TODO: dynamic sizing of array, doesnt need to have the exact size of colomns for the entire time
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            // read the first line from the text file 
            
            String l = br.readLine(); 
            
            //int j = 0;
            // loop until all lines are read 
            while (l != null) { 
                // use string.split to load a string array with the values from 
                // each line of 
                // the file, using a comma as the delimiter 
                String[] line = l.split(",");
                lsize=line.length;
                for(int i=0;i<lsize;i++){
                    dat.add(line[i]);
                }
                //TODO: add data to dat
                

                // read next line before looping 
                // if end of file reached, line would be null 
                l = br.readLine(); 
                //j++;
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 



        String[][] datarray= new String[dat.size()/lsize][lsize]; //this will be determined by the size obvs

        for (int i = 0;i<dat.size();i++){
            datarray[i/lsize][i%lsize]=dat.get(i);
        }

        return datarray; 
    }

    private static double[][] fromCSVtoD2(String fileName, int col0, int col1) 
    {
        String[][] s = fromCSV(fileName);
        List<Double> dat = new ArrayList<Double>();
        
        for(int i=0;i<s.length;i++){
            int j=0;
            for(String source : s[i]){
                Scanner scanner = new Scanner(source);

                if(j==col0 || j==col1){
                    //TODO: Possible error here if there are doubles in the colomns that are not inteded to be data, such as if the colomn header was just a number this would cause an error. Will solve at a latter time
                    if(scanner.hasNextDouble()){
                        dat.add(Double.parseDouble(source));
                    }

                }
                scanner.close();
                j++;
            }
        }


        double[][] d = new double[dat.size()/2][2];
        boolean first = col0<col1;
        for(int i = 0;i<d.length;i++){
            if(first){
            
                d[i][0] =dat.get(i*2);
                d[i][1]=dat.get(i*2+1);

            }else{
                d[i][1] =dat.get(i*2);
                d[i][0]=dat.get(i*2+1);
            }

        }



        return d;
    }

    public static void main(String[] args) {
        double[][] d=fromCSVtoD2("Data\\Test.csv", 0, 1);

        System.out.println(ArrayUtils.toString(d));
    }
}
