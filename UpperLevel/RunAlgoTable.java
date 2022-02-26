package UpperLevel;

import java.io.File;
//import java.util.Scanner;

import Gen_Algo.Butterworth;
//import Interpreter.PyInterpreter;
//import Graphing.Graphing_Simp;
//import javax.imageio.ImageIO;
//import javax.swing.JFrame;

//a version of RunAlgo but with the imput being tables.
//cutoff and then order for the

public class RunAlgoTable {
    public static Object[] run(double[][] d,String algo, String Title,double... p){
        //TODO: Make accedental overwriting less of an issue
       
        double cutOff= p.length > 0 ? p[0] : 0.3;
        Integer order = (int)(p.length > 1 ? p[1] : 1);

        Object[] arF=new Object[2];
        if (algo.equals("lowpass")){
            //Scanner keyboard = new Scanner(System.in); 
            Gen_Algo.TimeSeriesData Data= new Gen_Algo.TimeSeriesData(d);
            //System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            //double cutOff= keyboard.nextDouble();
            
            double[][] LowPassed= Butterworth.LowPass(Data,0, Data.GetTime().length, 1, order ,  cutOff); //cutoff and order are bs just for this version
            //Graphing.Graphing_Simp.printThisD2(LowPassed,j2);
            arF[0]=Gen_Algo.Table2CSV.RawDoub(Title+"_lowpassed", LowPassed,2);
            //arF[1] = Graphing_Simp.printThisD2Frame(LowPassed, Title+"_lowpassGraph.png","Time (s)","Wavelength\n (m)");
            
        }else if(algo.equals("highpass")){

            //Scanner keyboard = new Scanner(System.in); 
            Gen_Algo.TimeSeriesData Data= new Gen_Algo.TimeSeriesData(d);
            //System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            //double cutOff= keyboard.nextDouble();
            //double cutOff= .3;
            double[][] LowPassed= Butterworth.HighPass(Data,0, Data.GetTime().length, 1, order ,  cutOff); //fs and order are bs just for this version
            //Graphing.Graphing_Simp.printThisD2(LowPassed,j2);
            arF[0]=Gen_Algo.Table2CSV.RawDoub(Title+"_highpassed", LowPassed,2);
            //arF[1] = Graphing_Simp.printThisD2Frame(LowPassed, Title+"_highpassGraph.png","Time (s)","Wavelength\n (m)");
            
        }else if(algo.equals("ttest")){
            //double[][] ar=Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1);
            double[] m= new double[3];
            String[][] m2= new String[3][2];
            m= Stats_Func.tTest.sameSizeAll(d,0,1);
            m2[0][1]=""+m[0];
            m2[1][1]=""+m[1];
            m2[2][1]=""+m[2];
            m2[0][0]="T-Value:";
            m2[1][0]="Degree(s) of Freedom:";
            m2[2][0]="P-Value:";
            arF[0]=Gen_Algo.Table2CSV.RawStri(Title+"_ttested",m2,2);
        //}else if(algo=="graphwav"){
        //    PyInterpreter.graphWav();
        //    return;
         }else if(algo.equals("graphcsv")){

            //arF[0]=f;
            //arF[1] = Graphing_Simp.printThisD2Frame(d, Title+"_Graph.png","Time (s)","Wavelength\n (m)"); 
            arF[0]=Gen_Algo.Table2CSV.RawDoub(Title,d,2);
        }else{
            System.out.println("This algorithm is not curently available");
            return arF;
        }
        System.out.println("File "+Title+" had the "+algo+ " algorithm run on it.\nThe result is in the Data folder.");
        
        
        return arF;
    }

    public static void main(String[] args) {
        String name="Test_Wave";
        File file = new File("./Data2/",name+".csv" );
        double[][] d=Gen_Algo.ReadFile.fromCSVtoD2(file.getAbsolutePath(),0, 1);
        Object[] fileout=run(d, "lowpass",name,0.3,1);
        System.out.println(fileout[0]);
        //JFrame f=(JFrame) fileout[1];
        //f.setVisible(true);
    }
}
