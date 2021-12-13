
import java.io.*;

import org.python.util.PythonInterpreter;
import org.python.util.*; 
import org.python.core.*; 
//import com.mathworks.engine.*;

public class PyInterpreter{
     //static PythonInterpreter python = new PythonInterpreter();
    public static void main(String[] args){
        PythonInterpreter python = new PythonInterpreter();
        //pyInterp.exec("print('Hello Python World!')");
        //make a main in python and then run the sub function that asks for user input and calls the function
        python.execfile("Interpreter\\Main.py");
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