package Gen_Algo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Table2CSV {
    public static void main(String[] args) {
        String[][] a=new String[4][2];
        a[0][0]="0.2";
        a[1][1]="1.2";
        col2("test", a);
        
    }

    public static void col2(String name, String[][] a) {
        //TODO: make work for all col numbers, just a string apend thing
        //TODO: naming conventions improve
        try{
            File file = new File("./Data/",name+".csv" ); //KEY IS DIR ex."./local-storage/" and fileName='comp.html'

            // if file doesnt exists, then create it 
            if ( ! file.exists( ) )
            {
                file.createNewFile( );
            }
    
            FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
            BufferedWriter fileWriter = new BufferedWriter(fw);
            int l=0;
            while(a.length>l){
                String line = String.format("%s,%s", a[l][0],a[l][1]);
                    
                fileWriter.newLine();
                fileWriter.write(line); 
                l++;
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println("File IO error:");
            e.printStackTrace();
        }
        return;
    }
}
