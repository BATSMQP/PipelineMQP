package Interpreter;
//import java.io.*;

import org.python.util.PythonInterpreter;
import org.python.core.*;
import java.io.*;
import java.util.Properties;

import org.python.core.*; 
//import com.mathworks.engine.*;

public class PyInterpreter{
     //static PythonInterpreter python = new PythonInterpreter();
    public static void main(String[] args){
        PythonInterpreter python = new PythonInterpreter();
        //pyInterp.exec("print('Hello Python World!')");
        //make a main in python and then run the sub function that asks for user input and calls the function
        
        //python.exec("from Interpreter import mainPython");

        python.execfile("Interpreter/main.py");
        

        //python.execfile("Interpreter/import_test.py");
        python.close();
    }

    private Properties setDefaultPythonPath(Properties props) {
        String pythonPathProp = props.getProperty("python.path");
        String new_value;
        if (pythonPathProp==null)
        {
            new_value  = System.getProperty("user.dir");
        } else {
            new_value = pythonPathProp +java.io.File.pathSeparator + System.getProperty("user.dir") + java.io.File.pathSeparator;
        }
        props.setProperty("python.path",new_value);
        return props;
    }
    
    public static void graphWav(){
        
        //props = setDefaultPythonPath(p);
        //PySystemState.initialize( System.getProperties(), props, null );
        PythonInterpreter python = new PythonInterpreter();
        

        python.execfile("Interpreter/graphwav.py");
        

        python.close();
    }


    public static void graphWavT(){
        PythonInterpreter python = new PythonInterpreter();
        

        python.execfile("import sys\n#from lib.pyAudioAnalysisMaster import pyAudioAnalysis\nsys.path.insert(1,'lib/pyAudioAnalysisMaster/pyAudioAnalysis')\nimport numpy as np \nimport \nShortTermFeatures as aF\nimport audioBasicIO as aIO \nimport plotly\nimport plotly.graph_objs as go \nimport IPython\n fs, s = aIO.read_audio_file(\"lib/pyAudioAnalysisMaster/pyAudioAnalysis/data/3WORDS.wav\")\nduration = len(s) / float(fs)\nwin, step = 0.050, 0.050\n[f, fn] = aF.feature_extraction(s, fs, int(fs * win), \n                                int(fs * step))\n# plot short-term energy\n# create time axis in seconds\n time = np.arange(0, duration - step, win) \n # get the feature whose name is 'energy'\n energy = f[fn.index('energy'), :]\n mylayout = go.Layout(yaxis=dict(title=\"frame energy value\"),\n                     xaxis=dict(title=\"time (sec)\"))\nplotly.offline.iplot(go.Figure(data=[go.Scatter(x=time, \n                                                y=energy)], \n                              layout=mylayout))");
        

        python.close();
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