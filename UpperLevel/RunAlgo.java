package UpperLevel;

import java.io.File;
import java.util.Scanner;

import Gen_Algo.Butterworth;
//import Interpreter.PyInterpreter;

public class RunAlgo {
    public static File run(File f,String algo){
        //TODO: Make accedental overwriting less of an issue
        File file;
        if (algo=="lowpass"){
            //Scanner keyboard = new Scanner(System.in); 
            Gen_Algo.TimeSeriesData Data= new Gen_Algo.TimeSeriesData(Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1));
            //System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            //double cutOff= keyboard.nextDouble();
            double cutOff= .3;
            double[][] LowPassed= Butterworth.LowPass(Data,0, Data.GetTime().length, 1, 1,  cutOff); //fs and order are bs just for this version
            //Graphing.Graphing_Simp.printThisD2(LowPassed,j2);
            file=Gen_Algo.Table2CSV.Doub(f.getName()+"_lowpassed", LowPassed,2,f);
            
        }else if(algo=="ttest"){
            double[][] ar=Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1);
            double[] m= new double[3];
            double[][] m2= new double[3][1];
            m= Stats_Func.tTest.sameSizeAll(ar,0,1);
            m2[0][0]=m[0];
            m2[1][0]=m[1];
            m2[2][0]=m[2];
            file=Gen_Algo.Table2CSV.Doub(f.getName()+"_ttested",m2,1,f);

        //}else if(algo=="graphwav"){
        //    PyInterpreter.graphWav();
        //    return;
        }else{
            System.out.println("This algorithm is not curently available");
            return f;
        }
        System.out.println("File "+f.getName()+" had the "+algo+ " algorithm run on it.\nThe result is in the Data folder.");
        return file;
    }

    public static void main(String[] args) {
        String name="test_d";
        File file = new File("./Data2/",name+".csv" );
        File fileout=run(file, "lowpass");
        System.out.println(fileout.getName());
    }
}