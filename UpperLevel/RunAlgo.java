package UpperLevel;

import java.io.File;
//import java.util.Scanner;

import Gen_Algo.Butterworth;
//import Interpreter.PyInterpreter;
import Graphing.Graphing_Simp;


//script that takes the algorithms themselfs (the programs that are run on the data) and feeds them instructions. Intended to be most of the connection between the front end and the algorithm branch.


public class RunAlgo {
    public static File[] run(File f,String algo){
        //TODO: Make accedental overwriting less of an issue
        
        File[] arF=new File[2];
        if (algo=="lowpass"){
            //Scanner keyboard = new Scanner(System.in); 
            Gen_Algo.TimeSeriesData Data= new Gen_Algo.TimeSeriesData(Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1));
            //System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            //double cutOff= keyboard.nextDouble();
            double cutOff= .3;
            double[][] LowPassed= Butterworth.LowPass(Data,0, Data.GetTime().length, 1, 1,  cutOff); //fs and order are bs just for this version
            //Graphing.Graphing_Simp.printThisD2(LowPassed,j2);
            arF[0]=Gen_Algo.Table2CSV.Doub(f.getName()+"_lowpassed", LowPassed,2,f);
            arF[1] = Graphing_Simp.printThisD2(LowPassed, f.getPath()+"_lowpassGraph.png","Time (s)","Wavelength\n (m)");
            
        }else if(algo=="highpass"){
            //Scanner keyboard = new Scanner(System.in); 
            Gen_Algo.TimeSeriesData Data= new Gen_Algo.TimeSeriesData(Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1));
            //System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            //double cutOff= keyboard.nextDouble();
            double cutOff= .3;
            double[][] LowPassed= Butterworth.HighPass(Data,0, Data.GetTime().length, 1, 1,  cutOff); //fs and order are bs just for this version
            //Graphing.Graphing_Simp.printThisD2(LowPassed,j2);
            arF[0]=Gen_Algo.Table2CSV.Doub(f.getName()+"_lowpassed", LowPassed,2,f);
            arF[1] = Graphing_Simp.printThisD2(LowPassed, f.getPath()+"_lowpassGraph.png","Time (s)","Wavelength\n (m)");

        }else if(algo=="ttest"){
            double[][] ar=Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1);
            double[] m= new double[3];
            String[][] m2= new String[3][2];
            m= Stats_Func.tTest.sameSizeAll(ar,0,1);
            m2[0][1]=""+m[0];
            m2[1][1]=""+m[1];
            m2[2][1]=""+m[2];
            m2[0][0]="T-Value:";
            m2[1][0]="Degree(s) of Freedom:";
            m2[2][0]="P-Value:";
            arF[0]=Gen_Algo.Table2CSV.Stri(f.getName()+"_ttested",m2,2,f);
        //}else if(algo=="graphwav"){
        //    PyInterpreter.graphWav();
        //    return;
        }else if(algo=="graphcsv"){

            arF[0]=f;
            arF[1] = Graphing_Simp.printThisD2(Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0,1), f.getPath()+"_Graph.png","Time (s)","Wavelength\n (m)");
        }else{
            System.out.println("This algorithm is not curently available");
            return arF;
        }
        System.out.println("File "+f.getName()+" had the "+algo+ " algorithm run on it.\nThe result is in the Data folder.");
        
        
        return arF;
    }

    public static void main(String[] args) {
        String name="test_d";
        File file = new File("./Data2/",name+".csv" );
        File[] fileout=run(file, "ttest");
        System.out.println(fileout[0].getName());
    }
}