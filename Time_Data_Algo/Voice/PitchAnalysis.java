package Time_Data_Algo.Voice;
import java.util.Scanner;
import Gen_Algo.TimeSeriesData;
import lib.TarsosDSP.pitch.AMDF;
//import lib.TarsosDSP.pitch.AMDF.getPitch;
import lib.TarsosDSP.pitch.PitchDetectionResult;
//import lib.TarsosDSP.pitch.PitchDetector;
import Gen_Algo.ReadFile;

import javax.swing.JFrame;

public class PitchAnalysis {
    static JFrame j1 = new JFrame();
    static JFrame j2 = new JFrame();
    
    //function that runs an AMDF pitch analysis over a given region
    //be careful cause they assume the frequency char are the same and dont change over a short window (20 ms-100ms) and then it will calculated 1 pitch and DOES AMD ASK FOR THAT MIN VAL AND MAX VAL???????
    public static float AMDFpa(TimeSeriesData Data,int startT, int endT, int Fs){
        TimeSeriesData Data2= new TimeSeriesData(Data.AnalyseRange(startT, endT, Fs));
        double[] Signal= Data2.GetSignal();
        float[] SignalF= TimeSeriesData.Double2Float(Signal);
        float FsS= (float)Fs;
        AMDF test= new AMDF(Fs, Signal.length, 0, 100);

        //AMDF.amd= Signal;
        PitchDetectionResult Pitched= AMDF.AvgPitch(SignalF);
        float pitchAverage= Pitched.getPitch();
       // float pitchAverage= test.getPitch(SignalF);
        return pitchAverage;
    }

    //DO THE SLIDING WINDOW OURSELVES AND TO THE AVERAGE OF THAT

    //function that uses pitch test w/ different sliding windows=> would help understand how pitch changes over time
    public static float[] AMDFwindow(TimeSeriesData Data,int Wsize){
        double[] Signal= Data.GetSignal();
        float[] SignalF= TimeSeriesData.Double2Float(Signal);
        double[] Time= Data.GetTime();
        float[] TimeF= TimeSeriesData.Double2Float(Time);
        int n=Time.length;
        int k= 1;
        float[] pitchAverages= new float[k];
        if (n<Wsize){ //if the Wsize is too big we cant make it
            System.out.println("Invalid");
            float[] Invalid= new float[1];
            return Invalid; 
        }//Calculating the pitch average of the window
        for(int i=Wsize; i<Wsize; i++){
            int startT=i;
            int endT= i+Wsize;
            return pitchAverages;
        }
        //int k= 0;//num of windows
        // PitchDetectionResult Pitched=AMDF.getPitch(SignalF);
        // float pitchAverage= Pitched.getPitch();
        //float[] pitchAverages= new float[k];
        return pitchAverages;
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
        Graphing.Graphing_Simp.printThisD2(ReadFile.fromCSVtoD2(path,Time, Signal),j1);
        System.out.print("What time would you like the analysis to start, please remember that for better accuracy, the sample should be between 25ms and 100ms long: ");  
        int startT= keyboard.nextInt();
        System.out.print("What time would you like the analysis to end: ");  
        int endT= keyboard.nextInt();
        float Averaged = AMDFpa(Data,startT,endT,Fs);
         System.out.println("The Signal's average pitch from "+startT+" to "+endT+" seconds is "+Averaged);
    
    }
}
