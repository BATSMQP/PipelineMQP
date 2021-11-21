 package Gen_Algo;
 import java.util.Scanner; // Claire Nicolas Edit
 import java.util.*;  //Claire Nicolas Edit
 import lib.jdsp.filter.ButterworthJDSP;
 import Graphing.Graphing_Simp;
 import Gen_Algo.TimeSeriesData;
 import Gen_Algo.ReadFile;

public class Butterworth{

    public static double[][] LowPass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double cutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.lowPassFilter(order, cutOff);
        double[][] LowPassed = TimeSeriesData.Refill2dArray(Time, result);
        return LowPassed;
    }

    public static double[][] HighPass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double cutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.highPassFilter(order, cutOff);
        double[][] HighPassed = TimeSeriesData.Refill2dArray(Time, result);
        return HighPassed;
    }

    public static double[][] BandPass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double lowCutOff, double highCutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.bandPassFilter(order, lowCutOff, highCutOff);
        double[][] BandPassed = TimeSeriesData.Refill2dArray(Time, result);
        return BandPassed;
    }

    public static double[][] BandStop(TimeSeriesData Data, int startT, int endT,int Fs, int order, double lowCutOff, double highCutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.bandPassFilter(order, lowCutOff, highCutOff);
        double[][] BandStopped = TimeSeriesData.Refill2dArray(Time, result);
        return BandStopped;
    }
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);  
        System.out.print("Enter path to csv: ");  
        String path= keyboard.nextLine();    
        System.out.print("What column represents the time of collection: ");  
        int Time= keyboard.nextInt();
        System.out.print("What is the Sampling frequency: ");  
        int Fs= keyboard.nextInt(); 
        System.out.print("What column number has the data we wish to analyse: ");  
        int Signal= keyboard.nextInt();
        TimeSeriesData Data= new TimeSeriesData(ReadFile.fromCSVtoD2(path,Time, Signal));
        System.out.print("What time would you like the analysis to start: ");  
        int startT= keyboard.nextInt();
        System.out.print("What time would you like the analysis to end: ");  
        int endT= keyboard.nextInt();
        System.out.print("What is the order of the filter: ");  
        int order= keyboard.nextInt();
        System.out.print("What filter would you like to use between Highpass, Lowpass, Bandpass, and Bandstop: ");  
        String ChooseFilter= keyboard.nextLine(); 
        if (ChooseFilter =="Lowpass"){
            System.out.print("What is the cutoff signal you wish to use for the lowpass: ");  
            double cutOff= keyboard.nextDouble();
            double[][] LowPassed= LowPass(Data,startT, endT, Fs, order,  cutOff);
            Graphing.Graphing_Simp.printThisD2(LowPassed);
        } else if(ChooseFilter =="Highpass" ){
            System.out.print("What is the cutoff signal you wish to use for the filter: ");  
            double cutOff= keyboard.nextDouble();
            double[][] HighPassed= HighPass(Data,startT, endT, Fs, order,  cutOff);
            Graphing.Graphing_Simp.printThisD2(HighPassed);
        } else if(ChooseFilter =="Bandpass" ){
            System.out.print("What is the lower cutoff signal you wish to use for the filter: ");  
            double lowcutOff= keyboard.nextDouble();
            System.out.print("What is the higher cutoff signal you wish to use for the filter: ");  
            double highcutOff= keyboard.nextDouble();
            double[][] BandPassed= BandPass(Data,startT, endT, Fs, order,  lowcutOff, highcutOff);
            Graphing.Graphing_Simp.printThisD2(BandPassed);
        } else if(ChooseFilter =="Bandstop" ){
            System.out.print("What is the lower cutoff signal you wish to use for the filter: ");  
            double lowcutOff= keyboard.nextDouble();
            System.out.print("What is the higher cutoff signal you wish to use for the filter: ");  
            double highcutOff= keyboard.nextDouble();
            double[][] BandStopped= BandStop(Data,startT, endT, Fs, order,  lowcutOff, highcutOff);
            Graphing.Graphing_Simp.printThisD2(BandStopped);
        }
   
    }
}
