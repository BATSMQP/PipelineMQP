package Time_Data_Algo.Voice;
import java.util.Scanner;
import Gen_Algo.TimeSeriesData;
import lib.TarsosDSP.pitch.AMDF;
import lib.TarsosDSP.pitch.PitchDetectionResult;
//import lib.TarsosDSP.pitch.PitchDetector;
import Gen_Algo.ReadFile;

public class PitchAnalysis {
    
    //function that runs an AMDF pitch analysis over a given region
    public static float AMDFpa(TimeSeriesData Data,int startT, int endT, int Fs){
        TimeSeriesData Data2= new TimeSeriesData(Data.AnalyseRange(startT, endT, Fs));
        double[] Signal= Data2.GetSignal();
        float[] SignalF= TimeSeriesData.Double2Float(Signal);
        PitchDetectionResult Pitched=AMDF.getPitch(SignalF);
        float pitchAverage= Pitched.getPitch();
        return pitchAverage;
    }

    //function that uses pitch test w/ different sliding windows=> would help understand how pitch changes over time
    public static float[] AMDFwindow(TimeSeriesData Data,int Wsize){
        double[] Signal= Data.GetSignal();
        float[] SignalF= TimeSeriesData.Double2Float(Signal);
        double[] Time= Data.GetTime();
        float[] TimeF= TimeSeriesData.Double2Float(Time);
        int n=Time.length;
        if (n<Wsize){ //if the Wsize is too big we cant make it
            System.out.println("Invalid");
            float[] Invalid= new float[1];
            return Invalid; 
        }//Calculating the pitch average of the window
        for(int i=Wsize; i<Wsize; i++){
            int startT=i;
            int endT= i+Wsize;
            
        }
        int k= 0;//num of windows
        PitchDetectionResult Pitched=AMDF.getPitch(SignalF);
        float pitchAverage= Pitched.getPitch();
        float[] pitchAverages= new float[k];

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
        Graphing.Graphing_Simp.printThisD2(ReadFile.fromCSVtoD2(path,Time, Signal));
        System.out.print("Would you like to determine the total pitch average of the file(opt 1), or view the change in pitch averges throughout the document(Opt 2) ");  
        int ChooseFilter= keyboard.nextInt(); 
        if (ChooseFilter==1){
            System.out.print("What time would you like the analysis to start: ");  
            int startT= keyboard.nextInt();
            System.out.print("What time would you like the analysis to end: ");  
            int endT= keyboard.nextInt();
            float Averaged = AMDFpa(Data,startT,endT,Fs);
            System.out.println("The Signal's average pitch from "+startT+" to "+endT+" seconds is "+Averaged);
        }else if(ChooseFilter==2){
            System.out.print("How big would you like the windows to be ");  
            int Wsize= keyboard.nextInt();
            float[] Averaged= AMDFwindow(Data,Wsize);
            System.out.println("The Signal's average pitches for a window size "+Wsize+" is "+Averaged);
        }
    }
}
