package Stats_Func;

import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; import java.util.ArrayList; import java.util.List;

public class Demo {
    
    public static void main(String[] args) {
        double m[];
        double b;
        double[][] ar=new double [4][5];
        ar[0][0]=2;
        ar[1][0]=3;
        ar[2][0]=4;
        ar[3][0]=5;
        /*
        ar[0][1]=5;
        ar[1][1]=6;
        ar[2][1]=7;
        ar[3][1]=3; 
        */
        ar[0][1]=2;
        ar[1][1]=3;
        ar[2][1]=4;
        ar[3][1]=4;

        /* m= tTest.paired(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        m= tTest.pairedAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b);  */   


        /* m= tTest.sameSize(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        b = m[2];
        System.out.printf("The p value is %f\n",b);

        m= tTest.sameSizeAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b); */  
        
        
        m= tTest.unequal(ar,0,2,0,0,2,1);
        System.out.println(m[0]);
        System.out.println(m[1]);

        b = m[2];
        System.out.printf("The p value is %f\n",b);

        m= tTest.unequalAll(ar,0,1);
        System.out.println(m[0]);
        System.out.println(m[1]); 

        b = m[2];
        System.out.printf("The p value is %f\n",b);
    }

    /* private static double[][] readDataFromCSV(String fileName) 
    { 
        double[][] dat;

        Path pathToFile = Paths.get(fileName); 
        // create an instance of BufferedReader 
        // using try with resource, Java 7 feature to close resources 
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            // read the first line from the text file 
            String l = br.readLine(); 
            dat = new double[2][3];
            // loop until all lines are read 
            while (l != null) { 
                // use string.split to load a string array with the values from 
                // each line of 
                // the file, using a comma as the delimiter 
                String[] attributes = l.split(",");

                //TODO: add data to dat


                // read next line before looping 
                // if end of file reached, line would be null 
                l = br.readLine(); 
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return dat; 
    } */
}
