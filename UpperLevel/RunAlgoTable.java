package UpperLevel;

import java.io.File;
//import java.util.Scanner;

import Gen_Algo.Butterworth;
//import Interpreter.PyInterpreter;
import Graphing.Graphing_Simp;

public class RunAlgoTable {
    public static File[] run(double[][] d,String algo, String path){
        //TODO: Make accedental overwriting less of an issue
        File f=new File(path);
        File[] arF=new File[2];
        if (algo=="lowpass"){
            //Scanner keyboard = new Scanner(System.in); 
            Gen_Algo.TimeSeriesData Data= new Gen_Algo.TimeSeriesData(d);
            //System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            //double cutOff= keyboard.nextDouble();
            double cutOff= .3;
            double[][] LowPassed= Butterworth.LowPass(Data,0, Data.GetTime().length, 1, 1,  cutOff); //fs and order are bs just for this version
            //Graphing.Graphing_Simp.printThisD2(LowPassed,j2);
            arF[0]=Gen_Algo.Table2CSV.Doub(f.getName()+"_lowpassed", LowPassed,2,f);
            arF[1] = Graphing_Simp.printThisD2(LowPassed, f.getAbsolutePath()+"_lowpassGraph.png","Time (s)","Wavelength\n (m)");
            
        }else if(algo=="ttest"){
            //double[][] ar=Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1);
            double[] m= new double[3];
            double[][] m2= new double[3][1];
            m= Stats_Func.tTest.sameSizeAll(d,0,1);
            m2[0][0]=m[0];
            m2[1][0]=m[1];
            m2[2][0]=m[2];
            arF[0]=Gen_Algo.Table2CSV.Doub(f.getName()+"_ttested",m2,1,f);
        //}else if(algo=="graphwav"){
        //    PyInterpreter.graphWav();
        //    return;
        }else if(algo=="graphcsv"){

            //arF[0]=f;
            arF[1] = Graphing_Simp.printThisD2(d, f.getPath()+"_Graph.png","Time (s)","Wavelength\n (m)");
        }else{
            System.out.println("This algorithm is not curently available");
            return arF;
        }
        System.out.println("File "+f.getName()+" had the "+algo+ " algorithm run on it.\nThe result is in the Data folder.");
        
        
        return arF;
    }

    public static void main(String[] args) {
        String name="Test_Wave";
        File file = new File("./Data2/",name+".csv" );
        double[][] d=Gen_Algo.ReadFile.fromCSVtoD2(file.getAbsolutePath(),0, 1);
        File[] fileout=run(d, "lowpass",file.getAbsolutePath());
        System.out.println(fileout[0].getName());
    }
}
