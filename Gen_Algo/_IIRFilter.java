package Gen_Algo;

public interface _IIRFilter {
 /**
     * This method implements a low pass filter with given parameters, filters the signal and returns it.
     * @param order Order of the filter
     * @param cutoffFreq The cutoff frequency for the filter
     * @return double[] Filtered signal
     */
    public double[][] lowPassFilter(int order, double cutoffFreq);

    /**
     * This method implements a high pass filter with given parameters, filters the signal and returns it.
     * @param order Order of the filter
     * @param cutoffFreq The cutoff frequency for the filter
     * @return double[] Filtered signal
     */
    public double[][] highPassFilter(int order, double cutoffFreq);

    /**
     * This method implements a band pass filter with given parameters, filters the signal and returns it.
     * @param order Order of the filter
     * @param lowCutoff The lower cutoff frequency for the filter
     * @param highCutoff The upper cutoff frequency for the filter
     * @return double[] Filtered signal
     */
    public double[][] bandPassFilter(int order, double lowCutoff, double highCutoff);

    /**
     * This method implements a band stop filter with given parameters, filters the signal and returns it.
     * @param order Order of the filter
     * @param lowCutoff The lower cutoff frequency for the filter
     * @param highCutoff The upper cutoff frequency for the filter
     * @return double[] Filtered signal
     */
    public double[][] bandStopFilter(int order, double lowCutoff, double highCutoff);

}
