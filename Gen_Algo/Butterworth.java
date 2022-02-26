 package Gen_Algo;
 import java.util.Scanner; // Claire Nicolas Edit
//import javax.swing.JFrame;

//import java.util.*;  //Claire Nicolas Edit
 import lib.jdsp.filter.ButterworthJDSP;
 //import Graphing.Graphing_Simp;
 //import Gen_Algo.TimeSeriesData;
 //import Gen_Algo.ReadFile;

public class Butterworth{
    //static JFrame j1 = new JFrame();
    //static JFrame j2 = new JFrame();

    public static double[][] LowPass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double cutOff){
        Data.AnalyseRange(startT, endT,Fs);
        double[] Time= Data.GetTime(); 
        //double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.lowPassFilter(order, cutOff);
        double[][] LowPassed = TimeSeriesData.Refill2dArray(Time, result);
        return LowPassed;
    }

    public static double[][] HighPass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double cutOff){
        Data.AnalyseRange(startT, endT,Fs);
        double[] Time= Data.GetTime(); 
        //double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.highPassFilter(order, cutOff);
        double[][] HighPassed = TimeSeriesData.Refill2dArray(Time, result);
        return HighPassed;
    }

    public static double[][] BandPass(TimeSeriesData Data, int startT, int endT,int Fs, int order, double lowCutOff, double highCutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        //double[] signal= Data.GetSignal(); 
        ButterworthJDSP flt = Data.ConvertBWJDSP(Fs);
        double[] result = flt.bandPassFilter(order, lowCutOff, highCutOff);
        double[][] BandPassed = TimeSeriesData.Refill2dArray(Time, result);
        return BandPassed;
    }

    public static double[][] BandStop(TimeSeriesData Data, int startT, int endT,int Fs, int order, double lowCutOff, double highCutOff){
        Data.AnalyseRange(startT, endT, Fs);
        double[] Time= Data.GetTime(); 
        //double[] signal= Data.GetSignal(); 
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
        Graphing.Graphing_Simp.printThisD2(ReadFile.fromCSVtoD2(path,Time, Signal),"Data\\Low_test_Img.png","Time (s)","Wavelength\n (m)");

        System.out.print("What time would you like the analysis to start: ");  
        int startT= keyboard.nextInt();

        System.out.print("What time would you like the analysis to end: ");  
        int endT= keyboard.nextInt();

        System.out.print("What is the order of the filter: ");  
        int order= keyboard.nextInt();

        System.out.print("What filter would you like to use between Lowpass= 1, Highpass=2, Bandpass=3, and Bandstop=4: ");  
        int ChooseFilter= keyboard.nextInt(); 

        if (ChooseFilter== 1){
            System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            double cutOff= keyboard.nextDouble();
            double[][] LowPassed= LowPass(Data,startT, endT, Fs, order,  cutOff);
            Graphing.Graphing_Simp.printThisD2(LowPassed,"Data\\Low_test_Img.png","Time (s)","Wavelength\n (m)");
        } else if(ChooseFilter == 2){
            System.out.print("What is the cutoff signal you wish to use for the filter (<.50): ");  
            double cutOff= keyboard.nextDouble();
            double[][] HighPassed= HighPass(Data,startT, endT, Fs, order,  cutOff);
            Graphing.Graphing_Simp.printThisD2(HighPassed,"Data\\Low_test_Img.png","Time (s)","Wavelength\n (m)");
        } else if(ChooseFilter == 3 ){
            System.out.print("What is the lower cutoff signal you wish to use for the filter: ");  
            double lowcutOff= keyboard.nextDouble();
            System.out.print("What is the higher cutoff signal you wish to use for the filter: ");  
            double highcutOff= keyboard.nextDouble();
            double[][] BandPassed= BandPass(Data,startT, endT, Fs, order,  lowcutOff, highcutOff);
            Graphing.Graphing_Simp.printThisD2(BandPassed,"Data\\Low_test_Img.png","Time (s)","Wavelength\n (m)");
        } else if(ChooseFilter == 4 ){
            System.out.print("What is the lower cutoff signal you wish to use for the filter: ");  
            double lowcutOff= keyboard.nextDouble();
            System.out.print("What is the higher cutoff signal you wish to use for the filter: ");  
            double highcutOff= keyboard.nextDouble();
            double[][] BandStopped= BandStop(Data,startT, endT, Fs, order,  lowcutOff, highcutOff);
            Graphing.Graphing_Simp.printThisD2(BandStopped,"Data\\Low_test_Img.png","Time (s)","Wavelength\n (m)");
        } 
        keyboard.close();
    }
   

}
