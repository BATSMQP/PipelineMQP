package UpperLevel;

import java.io.File;
import java.util.Scanner;

import Gen_Algo.Butterworth;

public class RunAlgo {
    public static void run(File f,String algo){
        if (algo=="lowpass"){
            Scanner keyboard = new Scanner(System.in); 
            Gen_Algo.TimeSeriesData Data= new Gen_Algo.TimeSeriesData(Gen_Algo.ReadFile.fromCSVtoD2(f.getAbsolutePath(),0, 1));
            System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            double cutOff= keyboard.nextDouble();
            double[][] LowPassed= Butterworth.LowPass(Data,0, Data.GetTime().length, 1, 1,  cutOff); //fs and order are bs just for this version
            //Graphing.Graphing_Simp.printThisD2(LowPassed,j2);
            Gen_Algo.Table2CSV.Doub(f.getName()+"_lowpassed", LowPassed,2);
        }else{
            System.out.println("This algorithm is not curently available");
        }

    }

    public static void main(String[] args) {
        String name="hw_200";
        File file = new File("./Data/",name+".csv" );
        run(file, "lowpass");
    }
}
