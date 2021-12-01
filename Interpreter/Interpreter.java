package Interpreter;
import java.io.*;

import org.python.util.PythonInterpreter; 
import org.python.core.*; 

public class Interpreter{
    public static void main(String[] args){
        PythonInterpreter python = new PythonInterpreter();

        python.execfile("Interpreter/HelloWorld.py");

        // int number1 = 10;
        // int number2 = 32;
        
        // python.set("number1", new PyInteger(number1));
        // python.set("number2", new PyInteger(number2));
        // python.exec("number3 = number1+number2");
        // PyObject number3 = python.get("number3");
        // System.out.println("val : "+number3.toString());
        }
}
       /*  try{
            String prg= "import sys\nprint int(sys.argv[1]+int(sys.argv[2])\n";
            BufferedWriter out= new BufferedWriter(new FileWriter("HelloWorld.py"));
            out.write(prg);
            out.close();
            int number1= 10;
            int number2= 32;

            ProcessBuilder pb= new ProcessBuilder("python","HelloWorld.py","+number1","number2");
            Process p= pb.start();

            BufferedReader in= new BufferedReader(new InputStreamReader(p.getInputStream()));
            int ret = (in.readLine()).intValue();
            System.out.println("value is :"+ret);
            
        }
        catch(Exception e) {System.out.println(e);} */
    //}


//}