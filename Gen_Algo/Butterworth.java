/*
 * Copyright (c) 2020 Sambit Paul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.psambit9791.jdsp.filter; //

import static easyjcckit.QuickPlot.*; //downloaded to make plots

/**
 * <h1>Butterworth Filter</h1>
 * The Butterworth class implements low-pass, high-pass, band-pass and band-stop filter using the Butterworth polynomials.
 * Has the flattest pass-band but a poor roll-off rate.
 * Reference <a href="https://en.wikipedia.org/wiki/Butterworth_filter">article</a> for more information on Buttterworth Filters.
 * <p>
 *
 * @author  Sambit Paul
 * @version 1.1
 */

 import java.util.Scanner; // Claire Nicolas Edit
 import java.util.*;  //Claire Nicolas Edit

public class Butterworth implements _IIRFilter {
    private double[][] signal; /** Here, the 1st column is  */
    private double samplingFreq;
    private double[] output;

    /**
     * This constructor initialises the prerequisites
     * required to use Butterworth filter.
     * @param s Signal to be filtered
     * @param Fs Sampling frequency of input signal
     */
    public Butterworth(double[][] s, double Fs) {
        this.signal = s;
        this.samplingFreq = Fs;
    }

    /** This method selects the specific range and removed all unneeded 
    @param startT start time of range we want to analyse
    @param endT end time of the range we wanna analyse  
    Created by Claire Nicolas
    */
    public double[][] SpecificSection(double startT, double endT){
        this.output= new double[endT-startT*this.samplingFreq][1]
        int j= 0; 
       for ( int i = 0; i < this.signal.length; i++) {
               if ( this.signal[i][0]< startT | this.signal[i][0]>endT ) { //this is assuming that col 1 = time 2= data
                    this.output[j][0] =this.signal[i][0];
                    this.output[j][1] = this.signal[i][1];
                    j++;
        return this.output;
    }

    /**This method prints the this.output of a butterworth() 
    
    Created by Claire Nicolas
    */
    public void PrintGraph( ){
       scatter(this.output[][0], this.output[][1] ); // create a plot using xaxis and yvalues
       System.out.println("Press enter to exit");
       System.in.read();  
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
        SpecificSection(startTime, endTime)
        lp = new Butterworth();
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
        this.output = new double[this.signal.length];
        hp = new Butterworth();
        highPass(order, this.samplingFreq, cutoffFreq);
        for (int i =0; i<this.output.length; i++) {
            this.output[i] = hp.filter(this.signal[i]);
        }
        print
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
    public double[] bandPassFilter(int order, double lowCutoff, double highCutoff) throws IllegalArgumentException{
        if (lowCutoff >= highCutoff) {
            throw new IllegalArgumentException("Lower Cutoff Frequency cannot be more than the Higher Cutoff Frequency");
        }
        double centreFreq = (highCutoff + lowCutoff)/2.0;
        double width = Math.abs(highCutoff - lowCutoff);
        this.output = new double[this.signal.length];
         bp = new Butterworth();
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
        this.output = new double[this.signal.length];
        Butterworth bs = new Butterworth();
        bs.bandStop(order, this.samplingFreq, centreFreq, width);
        for (int i=0; i<this.output.length; i++) {
            this.output[i] = bs.filter(this.signal[i]);
        }
        return this.output;
    }

    public static void main(String[] args){
        
        Scanner keyboard = new Scanner(System.in);  
        System.out.print("Enter path to csv: ");  
        String str= keyboard.nextLine();    
        Scanner sc = new Scanner(new File(str))
        double[][] trial1= csv

    }
}