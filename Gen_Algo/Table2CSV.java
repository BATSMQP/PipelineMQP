package Gen_Algo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Table2CSV {
    public static void main(String[] args) {
        /* String[][] a=new String[4][3];
        a[0][0]="0.3";
        a[1][1]="1.3";
        a[2][2]="2.3";
        Stri("test", a,3); */
        String name="test_d";
        File file = new File("./Data/",name+".csv" );
        double[][] b=new double[3][2];
        b[0][0]=0.3;
        b[1][0]=1.3;
        b[2][0]=2.3;
        b[0][1]=0.3;
        b[1][1]=1.3;
        b[2][1]=2.5;
        File fileout=Doub("test_d", b,2,file);
        System.out.println(fileout.getName());
    }

    public static File Stri(String name, String[][] a, int colNum, File f) {
        //TODO: naming conventions improve
        try{
            String path = f.getParent();
            //System.out.println(path);
            File file = new File(path,name+".csv" ); //KEY IS DIR ex."./local-storage/" and fileName='comp.html'

            // if file doesnt exists, then create it 
            if ( ! file.exists( ) )
            {
                file.createNewFile( );
            }
    
            FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
            BufferedWriter fileWriter = new BufferedWriter(fw);
            
            for(int i=0; a.length>i;i++){
                String line =a[i][0];
                for(int j=1;j<colNum;j++){
                    line = line+","+a[i][j];
                }
                fileWriter.newLine();
                fileWriter.write(line); 
                
            }
            fileWriter.close();
            return file;
        } catch (IOException e){
            System.out.println("File IO error:");
            e.printStackTrace();
        }
        return f;
    }

    public static String RawStri(String name, String[][] a, int colNum) {
        //TODO: naming conventions improve
        
        String out = "";
        
        for(int i=0; a.length>i;i++){
            String line =a[i][0];
            for(int j=1;j<colNum;j++){
                line = line+","+a[i][j];
            }
            
            out=out+line+"\n";
        }
        
        
        return out;
    }


    public static File Doub(String name, double[][] a,int colNum, File f) {
        String[][] s=new String[a.length][colNum];
        for(int i=0; i<a.length; i++) {

            for(int j=0; j<colNum;j++){
                s[i][j] = ""+a[i][j];
            }
         
        }
        return Stri(name, s, colNum,f);
    }

    public static String RawDoub(String name, double[][] a, int colNum){
        String[][] s=new String[a.length][colNum];
        for(int i=0; i<a.length; i++) {

            for(int j=0; j<colNum;j++){
                s[i][j] = ""+a[i][j];
            }
         
        }
        return RawStri(name, s, colNum);

    }
}
