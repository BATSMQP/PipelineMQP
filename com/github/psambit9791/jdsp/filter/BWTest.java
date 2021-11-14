package Gen_Algo;

import java.util.Scanner; // Claire Nicolas Edit
import java.util.*;  //Claire Nicolas Edit
//import static easyjcckit.QuickPlot.*; //downloaded to make plots

public class BWTest implements _IIRFilter  {
    private double[][] signal; /** Here, the 1st column is time */
    private double samplingFreq;
    private double[][] output;

    /**
     * This constructor initialises the prerequisites
     * required to use BWTest filter.
     * @param s Signal to be filtered
     * @param Fs Sampling frequency of input signal
     */
    public BWTest(double[][] s, double Fs) {
        this.signal = s;
        this.samplingFreq = Fs;
    }

    public BWTest() {
    }

    /** This method selects the specific range and removed all unneeded 
    @param startT start time of range we want to analyse
    @param endT end time of the range we wanna analyse  
    Created by Claire Nicolas
    */
    public double[][] SpecificSection(double startT, double endT){
       this.output= new double[endT-startT*this.samplingFreq][1];
        int j= 0; 
       for ( int i = 0; i < this.signal.length; i++) {
               if ( this.signal[i][0]< startT | this.signal[i][0]>endT ) { //this is assuming that col 1 = time 2= data
                    this.output[j][0] =this.signal[i][0];
                    this.output[j][1] = this.signal[i][1];
                    j++;
               }
        return this.output;
        }
    }

    /**This method prints the this.output of a BWTest() 
    
    Created by Claire Nicolas
    */
    public void PrintGraph( ){
      // scatter(this.output[0], this.output[1] ); // create a plot using xaxis and yvalues
       System.out.println("Press enter to exit");
       //System.in.read();  
    }

    /**
     * This method implements a low pass filter with given parameters, filters the signal and returns it.
     * @param order Order of the filter
     * @param cutoffFreq The cutoff frequency for the filter in Hz
     * @return double[] Filtered signal
     */
     /**Modified by Claire Nicolas
     completes a lowpass filter over a desired range and prints the figure */
    public double[][] lowPassFilter(int order, double cutoffFreq, double[][] TSSignal, double startTime, double endTime) {
        SpecificSection(startTime, endTime);
        uk.me.berndporr.iirj.Butterworth lp = new uk.me.berndporr.iirj.Butterworth();
        lp.lowPass(order, this.samplingFreq, cutoffFreq);
        for (int i =0; i<this.output.length; i++) {
            this.output[i] = lp.filter(this.signal[i]);
        }
        return this.output;
    }

    /**
     * This method implements a high pass filter with given parameters, filters the signal, displays a graph and returns it.
     * @param order Order of the filter
     * @param cutoffFreq The cutoff frequency for the filter in Hz
     * @return double[] Filtered signal
     */
     /**Modified by Claire Nicolas */
    public double[] highPassFilter(int order, double cutoffFreq) {
        this.output = new double[this.signal.length][];
        BWTest hp = new BWTest();
        highPass(order, this.samplingFreq, cutoffFreq);
        for (int i =0; i<this.output.length; i++) {
            this.output[i] = hp.filter(this.signal[i]);
        }
        //print
        return this.output;
    }

    /**
     * This method implements a band pass filter with given parameters, filters the signal and returns it.
     * @param order Order of the filter
     * @param lowCutoff The lower cutoff frequency for the filter in Hz
     * @param highCutoff The upper cutoff frequency for the filter in Hz
     * @throws java.lang.IllegalArgumentException The lower cutoff frequency is greater than the higher cutoff frequency
     * @return double[] Filtered signal
     */
    public double[][] bandPassFilter(int order, double lowCutoff, double highCutoff) throws IllegalArgumentException{
        if (lowCutoff >= highCutoff) {
            throw new IllegalArgumentException("Lower Cutoff Frequency cannot be more than the Higher Cutoff Frequency");
        }
        double centreFreq = (highCutoff + lowCutoff)/2.0;
        double width = Math.abs(highCutoff - lowCutoff);
        this.output = new double[this.signal.length][];
        BWTest bp = new BWTest();
        bp.bandPass(order, this.samplingFreq, centreFreq, width);
        for (int i=0; i<this.output.length; i++) {
            this.output[i] = bp.filter(this.signal[i]);
        }
        return this.output;
    }

    /**
     * This method implements a band stop filter with given parameters, filters the signal and returns it.
     * @param order Order of the filter
     * @param lowCutoff The lower cutoff frequency for the filter in Hz
     * @param highCutoff The upper cutoff frequency for the filter in Hz
     * @throws java.lang.IllegalArgumentException The lower cutoff frequency is greater than the higher cutoff frequency
     * @return double[] Filtered signal
     */
    public double[] bandStopFilter(int order, double lowCutoff, double highCutoff) throws IllegalArgumentException{
        if (lowCutoff >= highCutoff) {
            throw new IllegalArgumentException("Lower Cutoff Frequency cannot be more than the Higher Cutoff Frequency");
        }
        double centreFreq = (highCutoff + lowCutoff)/2.0;
        double width = Math.abs(highCutoff - lowCutoff);
        this.output = new double[this.signal.length][];
        BWTest bs = new BWTest();
        bs.bandStop(order, this.samplingFreq, centreFreq, width);
        for (int i=0; i<this.output.length; i++) {
            this.output[i] = bs.filter(this.signal[i]);
        }
        return this.output;
    }

    public static void main(String[] args){
        
        Scanner keyboard = new Scanner(System.in);  
        System.out.print("Enter path to csv: ");  
        //String str= keyboard.nextLine();    
        //Scanner sc = new Scanner(new File(str));
       // double[][] trial1= csv

    }

    @Override
    public double[] lowPassFilter(int order, double cutoffFreq) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
