package Interpreter;
import java.io.*;

public class Interpreter{
    public static void main(String[] args){
        try{
            String prg= "import sys\nprint int(sys.argv[1]+int(sys.argv[2])\n";
            BufferedWriter out- new BufferedWriter(new FileWriter("HelloWorld.py"));
            out.write(prg);
            out.close();
            int number1= 10;
            int number2= 32;

            ProcessBuilder pb= new ProcessBuilder("python","HelloWorld.py","+number1","number2");
            Process p= pb.start();

            BufferedReader in= new BufferedReader(new InputStreamReader(p.getInputStream()));
            int ret = new Integer(in.readLine()).intValue();
            System.out.println("value is :"+ret);
            
        }Catch(Exception e) {System.out.println(e);}
    }


}