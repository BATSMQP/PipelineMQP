 package Gen_Algo;
 import java.util.Scanner; // Claire Nicolas Edit
 import java.util.*;  //Claire Nicolas Edit
 import lib.jDSPmaster.src.main.java.com.github.psambit9791.jdsp.filter.ButterworthJDSP;
 import Graphing.Graphing_Simp;
 import Gen_Algo.DataRegion;

public class Butterworth {
    public static void main(String[] args){
        
        Scanner keyboard = new Scanner(System.in);  
        System.out.print("Enter path to csv: ");  
        String str= keyboard.nextLine();    
        Scanner sc = new Scanner(new File(str));
        //double[][] trial1= 
        System.out.print("What is the Sampling frequency: ");  
        double Fs= keyboard.nextDouble(); 
        System.out.print("What is the order of the filter: ");  
        int order= keyboard.nextInt(); 
        System.out.print("What is the cutoff frequency: ");  
        double cutOff= keyboard.nextDouble(); 
        ButterworthJDSP flt = new ButterworthJDSP(signal, Fs); //signal is of type double[]
        double[] result = flt.lowPassFilter(order, cutOff); //get the result after filtering

    }

    @Override
    public double[][] lowPassFilter(int order, double cutoffFreq) {
        // TODO Auto-generated method stub
        return null;
    }
}