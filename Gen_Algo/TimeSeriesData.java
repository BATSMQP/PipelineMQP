package Gen_Algo; 

import lib.jdsp.filter.ButterworthJDSP;

public class TimeSeriesData {
    private double[][] signal;
    // double samplingFreq;
    private double[][] output;

    /**
     * This constructor initialises the prerequisites
     * required to use Butterworth filter.
     * @param s Signal to be filtered
     * @param Fs Sampling frequency of input signal
     */
    public TimeSeriesData(double[][] data, double Fs) {
        this.signal = data;
        //this.samplingFreq = Fs;
    }

    public TimeSeriesData(double[][] data) {
        this.signal = data;
        //this.samplingFreq = Fs;
    }


  
     /**
     * This method .
     * @param startT is the beginning of the range that will be analysed
     * @param endT is the end of the range that will be analysed
     * @return double[][] shortened signal
     */
    public double[][] AnalyseRange (int startT, int endT, int Fs) {
            this.output= new double[endT-startT*Fs][2];
            int j= 0; 
           for ( int i = 0; i < this.signal.length; i++) {
                   if ( this.signal[i][0]< startT | this.signal[i][0]>endT ) { //this is assuming that col 1 = time 2= data
                        this.output[j][0] =this.signal[i][0];
                        this.output[j][1] = this.signal[i][1];
                        j++;
                   }
            return this.output;
            }
        return this.output;
        }

    public ButterworthJDSP ConvertBWJDSP(double Fs){
        ButterworthJDSP Data= new ButterworthJDSP (GetSignal(),Fs);
        return Data;
    }
       

     /**
     * This method takes the given data by the system and makes it processible for the JDSP library.
     * @return double[] which is just the signal
     */
    public double[] GetSignal( ){
        int numRows = this.signal.length;
        double[] signal = this.signal[numRows];
        for(int row = 0; row < numRows; row++) {
            signal[row] = this.signal[row][1];
        }
        return signal;
    }

       /**
     * This method takes the given data by the system and returns the associated time.
     * @return double[] which is just the timestamps/sample numbers
     */
    public double[] GetTime( ){
        int numRows = this.signal.length;
        double[] time = this.signal[numRows];
        for(int row = 0; row < numRows; row++) {
            time[row] = this.signal[row][0];
        }
        return time;
    }

    public static double[][] Refill2dArray(double[] Time, double[] Signal){
        double[][] Data = new double[Time.length][2];  
     for (int row = 0; row < Data.length; row++){
             Data[row][0]= Time[row];
             Data[row][1]= Signal[row];
     }
     return Data;
    }


}