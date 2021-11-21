 package Gen_Algo;
 import java.util.Scanner; // Claire Nicolas Edit
 import java.util.*;  //Claire Nicolas Edit
 import lib.jdsp.filter.ButterworthJDSP;
 import Graphing.Graphing_Simp;
 import Gen_Algo.TimeSeriesData;

public class Butterworth{

    public ButterworthJDSP LowPassnPrint(TimeSeriesData Data, int startT, int endT,int Fs, int order, double cutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.lowPassFilter(order, cutOff);
        Graph
        return flt;
    }

    public ButterworthJDSP HighPass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double cutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.highPassFilter(order, cutOff);
        return flt;
    }

    public ButterworthJDSP Bandpass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double lowCutOff, double highCutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.bandPassFilter(order, lowCutOff, highCutOff);
        return flt;
    }

    public ButterworthJDSP BandStop(TimeSeriesData Data, int startT, int endT,int Fs, int order, double lowCutOff, double highCutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.bandPassFilter(order, lowCutOff, highCutOff);
        return flt;
    }
    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);  
        System.out.print("Enter path to csv: ");  
        String str= keyboard.nextLine();    
        Scanner sc = new Scanner(new File(str));
        double[][] trial1=  (  );
        System.out.print("What is the Sampling frequency: ");  
        double Fs= keyboard.nextDouble(); 
        System.out.print("What filter would you like to use between Highpass, Lowpass, Bandpass, and Bandstop: ");  
        String ChooseFilter= keyboard.nextLine(); 
        if (ChooseFilter =="Lowpass"){
            LowPass()
        }
        System.out.print("What is the Sampling frequency: ");  
        double Fs= keyboard.nextDouble(); 
        
        System.out.print("What is the order of the filter: ");  
        int order= keyboard.nextInt(); 
        System.out.print("What is the cutoff frequency: ");  
        double cutOff= keyboard.nextDouble(); 
        ButterworthJDSP flt = new ButterworthJDSP(signal, Fs); //signal is of type double[]
        double[] result = flt.lowPassFilter(order, cutOff); //get the result after filtering

    }

}