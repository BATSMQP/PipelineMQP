/*
 *
 *  * Copyright (c) 2020 Sambit Paul
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.github.psambit9791.jdsp;

import com.github.psambit9791.jdsp.misc.UtilMethods;
import com.github.psambit9791.jdsp.splines.BSpline;
import com.github.psambit9791.jdsp.splines.CubicSpline;
import com.github.psambit9791.jdsp.splines.QuadraticSpline;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestBSpline {

    private double[] x = UtilMethods.linspace(0.0, 10.0, 41, true);
    private double[] y = {1.        ,  0.99997589,  0.99961422,  0.99804751,  0.99383351,
            0.98496741,  0.96891242,  0.94266199,  0.90284967,  0.8459245 ,
            0.76840935,  0.66725595,  0.54030231,  0.38682422,  0.20815202,
            0.00829623, -0.20550672, -0.42245132, -0.62817362, -0.80528032,
            -0.93454613, -0.99687381, -0.97600242, -0.86178492, -0.65364362,
            -0.36358296, -0.01794357,  0.34300209,  0.6683999 ,  0.90350941,
            0.99944942,  0.92474709,  0.67640492,  0.28737168, -0.17292254,
            -0.60754239, -0.91113026, -0.99662623, -0.82363808, -0.41966325,
            0.11527995};

    private double rms_error(double[] tgt, double[] src) {
        double[] err = new double[tgt.length];
        for (int i=0; i<tgt.length; i++) {
            err[i] = Math.pow((tgt[i] - src[i]), 2);
        }
        return new Median().evaluate(err);
    }

    @Test
    public void BSplineTest1_Order5() throws IOException {
        BSpline aks = new BSpline(5);
        aks.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 101, true);

        double[] result = aks.getValue(xnew);
        double[] out = {1.      ,  0.999999,  0.99999 ,  0.99995 ,  0.999842,  0.999614,
                0.9992  ,  0.998518,  0.997473,  0.995953,  0.993834,  0.990976,
                0.987227,  0.982421,  0.97638 ,  0.968912,  0.959818,  0.948885,
                0.935897,  0.920628,  0.90285 ,  0.882333,  0.858849,  0.832175,
                0.802096,  0.768409,  0.730931,  0.689498,  0.643977,  0.594265,
                0.540302,  0.482072,  0.419613,  0.353019,  0.282455,  0.208152,
                0.130424,  0.049665, -0.033642, -0.118922, -0.205507, -0.292635,
                -0.379452, -0.465012, -0.548287, -0.628174, -0.703503, -0.773058,
                -0.835589, -0.889834, -0.934546, -0.968517, -0.99061 , -0.99979 ,
                -0.995162, -0.976002, -0.9418  , -0.892288, -0.827484, -0.747713,
                -0.653644, -0.546303, -0.427095, -0.297802, -0.160579, -0.017944,
                0.127265,  0.271921,  0.412675,  0.546024,  0.6684  ,  0.776267,
                0.866232,  0.935164,  0.980316,  0.999449,  0.990956,  0.953969,
                0.888461,  0.795321,  0.676405,  0.534554,  0.373584,  0.198216,
                0.013982, -0.172923, -0.355827, -0.527811, -0.681974, -0.811736,
                -0.91113 , -0.975102, -0.999804, -0.98288 , -0.92373 , -0.823638,
                -0.685774, -0.515168, -0.318694, -0.105046,  0.11528};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }

    @Test
    public void BSplineTest2_Order5() throws IOException {
        BSpline obs = new BSpline(5);
        obs.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 201, true);

        double[] result = obs.getValue(xnew);
        double[] out = {1.      ,  1.125938,  1.162881,  1.132921,  1.067851,  0.999976,
                0.953025,  0.936188,  0.946052,  0.971645,  0.999614,  1.019325,
                1.026359,  1.021911,  1.01057 ,  0.998048,  0.988922,  0.985097,
                0.986051,  0.989789,  0.993834,  0.996187,  0.996006,  0.993486,
                0.989461,  0.984967,  0.980837,  0.977401,  0.974544,  0.971875,
                0.968912,  0.965264,  0.96075 ,  0.955385,  0.949299,  0.942662,
                0.935603,  0.92816 ,  0.920287,  0.911888,  0.90285 ,  0.893074,
                0.882506,  0.871123,  0.858926,  0.845924,  0.832121,  0.8175  ,
                0.802033,  0.785682,  0.768409,  0.750178,  0.730963,  0.710745,
                0.689513,  0.667256,  0.643967,  0.619636,  0.594254,  0.567811,
                0.540302,  0.511724,  0.482078,  0.451372,  0.419615,  0.386824,
                0.353018,  0.318218,  0.282452,  0.245752,  0.208152,  0.169694,
                0.130425,  0.090396,  0.049665,  0.008296, -0.033642, -0.076075,
                -0.118922, -0.162097, -0.205507, -0.249054, -0.292635, -0.336139,
                -0.379452, -0.422451, -0.465012, -0.507003, -0.548287, -0.588726,
                -0.628174, -0.666483, -0.703503, -0.73908 , -0.773058, -0.80528 ,
                -0.835589, -0.863826, -0.889834, -0.913459, -0.934546, -0.952947,
                -0.968517, -0.981116, -0.99061 , -0.996874, -0.99979 , -0.999252,
                -0.995162, -0.987436, -0.976002, -0.960804, -0.9418  , -0.918964,
                -0.892289, -0.861785, -0.827483, -0.789435, -0.747713, -0.70241 ,
                -0.653644, -0.601554, -0.546304, -0.488081, -0.427096, -0.363583,
                -0.297801, -0.230031, -0.160577, -0.089766, -0.017944,  0.054526,
                0.127261,  0.199862,  0.271918,  0.343002,  0.41268 ,  0.480508,
                0.546036,  0.608815,  0.6684  ,  0.724352,  0.776245,  0.823664,
                0.866213,  0.903509,  0.935191,  0.960918,  0.980377,  0.993297,
                0.999449,  0.998666,  0.990835,  0.975904,  0.953865,  0.924747,
                0.888608,  0.845532,  0.795651,  0.739172,  0.676405,  0.60779 ,
                0.533904,  0.455418,  0.373023,  0.287372,  0.199007,  0.108343,
                0.015762, -0.078242, -0.172923, -0.267141, -0.359333, -0.447755,
                -0.530838, -0.607542, -0.677709, -0.742155, -0.802131, -0.85854 ,
                -0.91113 , -0.957724, -0.994021, -1.014873, -1.016152, -0.996626,
                -0.959796, -0.914386, -0.871464, -0.840208, -0.823638, -0.814445,
                -0.793499, -0.735181, -0.615391, -0.419663, -0.151121,  0.16566 ,
                0.491194,  0.781721,  1.};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }

    @Test
    public void BSplineTest1_Order4() throws IOException {
        BSpline aks = new BSpline(4);
        aks.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 101, true);

        double[] result = aks.getValue(xnew);
        double[] out = {1.      ,  0.999999,  0.99999 ,  0.99995 ,  0.999842,  0.999614,
                0.9992  ,  0.998518,  0.997473,  0.995953,  0.993834,  0.990976,
                0.987227,  0.982421,  0.97638 ,  0.968912,  0.959818,  0.948885,
                0.935897,  0.920628,  0.90285 ,  0.882333,  0.858849,  0.832175,
                0.802096,  0.768409,  0.730931,  0.689499,  0.643977,  0.594265,
                0.540302,  0.482072,  0.419613,  0.353019,  0.282455,  0.208152,
                0.130424,  0.049665, -0.033642, -0.118922, -0.205507, -0.292635,
                -0.379452, -0.465012, -0.548288, -0.628174, -0.703503, -0.773059,
                -0.835588, -0.889835, -0.934546, -0.968517, -0.990611, -0.99979 ,
                -0.995162, -0.976002, -0.941799, -0.892289, -0.827483, -0.747713,
                -0.653644, -0.546303, -0.427095, -0.297802, -0.160578, -0.017944,
                0.127265,  0.271923,  0.412674,  0.546026,  0.6684  ,  0.776266,
                0.866235,  0.935162,  0.980317,  0.999449,  0.990953,  0.95397 ,
                0.88846 ,  0.795319,  0.676405,  0.534553,  0.373581,  0.198218,
                0.013976, -0.172923, -0.355824, -0.527817, -0.681969, -0.811741,
                -0.91113 , -0.975095, -0.999807, -0.982875, -0.92372 , -0.823638,
                -0.685787, -0.515179, -0.318677, -0.105001,  0.11528};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }

    @Test
    public void BSplineTest2_Order4() throws IOException {
        BSpline obs = new BSpline(4);
        obs.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 201, true);

        double[] result = obs.getValue(xnew);
        double[] out = {1.      ,  1.182327,  1.241205,  1.194448,  1.097243,  0.999976,
                0.934286,  0.912888,  0.929635,  0.964621,  0.999614,  1.023159,
                1.030642,  1.024266,  1.011211,  0.998048,  0.9889  ,  0.985418,
                0.986786,  0.99039 ,  0.993834,  0.995604,  0.995081,  0.992535,
                0.988884,  0.984967,  0.981302,  0.978083,  0.975183,  0.972238,
                0.968912,  0.964986,  0.960354,  0.955027,  0.949102,  0.942662,
                0.935751,  0.928368,  0.920472,  0.911989,  0.90285 ,  0.893001,
                0.882403,  0.871033,  0.858877,  0.845924,  0.832156,  0.817549,
                0.802075,  0.785705,  0.768409,  0.750162,  0.73094 ,  0.710726,
                0.689502,  0.667256,  0.643974,  0.619646,  0.594263,  0.567816,
                0.540302,  0.51172 ,  0.482073,  0.451368,  0.419613,  0.386824,
                0.353019,  0.31822 ,  0.282454,  0.245753,  0.208152,  0.169693,
                0.130424,  0.090395,  0.049665,  0.008296, -0.033642, -0.076075,
                -0.118922, -0.162096, -0.205507, -0.249054, -0.292635, -0.33614 ,
                -0.379452, -0.422451, -0.465012, -0.507003, -0.548288, -0.588726,
                -0.628174, -0.666483, -0.703503, -0.739081, -0.773059, -0.80528 ,
                -0.835588, -0.863826, -0.889835, -0.913459, -0.934546, -0.952947,
                -0.968517, -0.981116, -0.990611, -0.996874, -0.99979 , -0.999251,
                -0.995162, -0.987437, -0.976002, -0.960804, -0.941799, -0.918964,
                -0.892289, -0.861785, -0.827483, -0.789435, -0.747713, -0.70241 ,
                -0.653644, -0.601553, -0.546303, -0.48808 , -0.427095, -0.363583,
                -0.297802, -0.230032, -0.160578, -0.089767, -0.017944,  0.054528,
                0.127265,  0.199868,  0.271923,  0.343002,  0.412674,  0.480498,
                0.546028,  0.60881 ,  0.6684  ,  0.72436 ,  0.776263,  0.823689,
                0.866232,  0.903509,  0.935165,  0.960871,  0.980326,  0.993261,
                0.999449,  0.998716,  0.990932,  0.97602 ,  0.95395 ,  0.924747,
                0.888488,  0.845307,  0.79539 ,  0.738983,  0.676405,  0.60805 ,
                0.534393,  0.455982,  0.373431,  0.287372,  0.198439,  0.107275,
                0.014525, -0.079137, -0.172923, -0.265912, -0.357049, -0.445142,
                -0.528962, -0.607542, -0.680273, -0.746904, -0.80754 , -0.862407,
                -0.91113 , -0.952502, -0.984476, -1.004174, -1.00858 , -0.996626,
                -0.969881, -0.932558, -0.891495, -0.854285, -0.823638, -0.795517,
                -0.759112, -0.696886, -0.588123, -0.419663, -0.189447,  0.093438,
                0.408755,  0.725119,  1.};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }

    @Test
    public void CubicSplineTest1() {
        CubicSpline cbs = new CubicSpline();
        cbs.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 101, true);

        double[] result = cbs.getValue(xnew);
        double[] out = {1.      ,  0.999983,  0.999984,  0.999954,  0.999847,  0.999614,
                0.9992  ,  0.998518,  0.997474,  0.995954,  0.993834,  0.990977,
                0.987228,  0.982422,  0.976381,  0.968912,  0.959818,  0.948886,
                0.935897,  0.920628,  0.90285 ,  0.882332,  0.858849,  0.832174,
                0.802094,  0.768409,  0.730928,  0.689498,  0.643975,  0.594261,
                0.540302,  0.482067,  0.41961 ,  0.353016,  0.282447,  0.208152,
                0.130415,  0.049661, -0.033646, -0.118931, -0.205507, -0.292644,
                -0.379456, -0.465015, -0.548294, -0.628174, -0.703506, -0.773061,
                -0.835586, -0.889832, -0.934546, -0.968506, -0.990607, -0.99978 ,
                -0.995144, -0.976002, -0.941771, -0.892278, -0.827467, -0.747679,
                -0.653644, -0.546266, -0.427078, -0.297788, -0.160545, -0.017944,
                0.127285,  0.271935,  0.412671,  0.546028,  0.6684  ,  0.776238,
                0.866226,  0.935133,  0.980261,  0.999449,  0.99087 ,  0.953935,
                0.888416,  0.795223,  0.676405,  0.534467,  0.373539,  0.198198,
                0.01392 , -0.172923, -0.355812, -0.527819, -0.681931, -0.811678,
                -0.91113 , -0.974905, -0.999707, -0.982851, -0.923696, -0.823638,
                -0.685148, -0.514628, -0.31946 , -0.10703 ,  0.11528};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }

    @Test
    public void CubicSplineTest2() {
        CubicSpline cbs = new CubicSpline();
        cbs.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 201, true);

        double[] result = cbs.getValue(xnew);
        double[] out = {1.      ,  0.999986,  0.999983,  0.999984,  0.999984,  0.999976,
                0.999954,  0.999913,  0.999847,  0.999749,  0.999614,  0.999435,
                0.9992  ,  0.998899,  0.998518,  0.998048,  0.997474,  0.996781,
                0.995954,  0.994977,  0.993834,  0.992507,  0.990977,  0.989224,
                0.987228,  0.984967,  0.982422,  0.979568,  0.976381,  0.972837,
                0.968912,  0.964582,  0.959818,  0.954595,  0.948886,  0.942662,
                0.935897,  0.928562,  0.920628,  0.912067,  0.90285 ,  0.892948,
                0.882332,  0.870976,  0.858849,  0.845924,  0.832174,  0.817572,
                0.802094,  0.785715,  0.768409,  0.750153,  0.730928,  0.710716,
                0.689498,  0.667256,  0.643975,  0.619645,  0.594261,  0.567816,
                0.540302,  0.511717,  0.482067,  0.451361,  0.41961 ,  0.386824,
                0.353016,  0.318213,  0.282447,  0.24575 ,  0.208152,  0.169689,
                0.130415,  0.090387,  0.049661,  0.008296, -0.033646, -0.076084,
                -0.118931, -0.162101, -0.205507, -0.249058, -0.292644, -0.336148,
                -0.379456, -0.422451, -0.465015, -0.507009, -0.548294, -0.588729,
                -0.628174, -0.666484, -0.703506, -0.739084, -0.773061, -0.80528 ,
                -0.835586, -0.863823, -0.889832, -0.913459, -0.934546, -0.952941,
                -0.968506, -0.981106, -0.990607, -0.996874, -0.99978 , -0.999232,
                -0.995144, -0.987429, -0.976002, -0.96079 , -0.941771, -0.918937,
                -0.892278, -0.861785, -0.827467, -0.789401, -0.747679, -0.702395,
                -0.653644, -0.601537, -0.546266, -0.488043, -0.427078, -0.363583,
                -0.297788, -0.229999, -0.160545, -0.089751, -0.017944,  0.054536,
                0.127285,  0.19989 ,  0.271935,  0.343002,  0.412671,  0.480497,
                0.546028,  0.608812,  0.6684  ,  0.724346,  0.776238,  0.823666,
                0.866226,  0.903509,  0.935133,  0.960806,  0.980261,  0.993231,
                0.999449,  0.998687,  0.99087 ,  0.975964,  0.953935,  0.924747,
                0.888416,  0.845154,  0.795223,  0.738886,  0.676405,  0.608093,
                0.534467,  0.456093,  0.373539,  0.287372,  0.198198,  0.10678 ,
                0.01392 , -0.079582, -0.172923, -0.265288, -0.355812, -0.443616,
                -0.527819, -0.607542, -0.681931, -0.750222, -0.811678, -0.86556 ,
                -0.91113 , -0.947716, -0.974905, -0.992351, -0.999707, -0.996626,
                -0.982851, -0.958479, -0.923696, -0.878687, -0.823638, -0.758859,
                -0.685148, -0.60343 , -0.514628, -0.419663, -0.31946 , -0.214941,
                -0.10703 ,  0.003352,  0.11528};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }

    @Test
    public void QuadraticSplineTest1() {
        QuadraticSpline qbs = new QuadraticSpline();
        qbs.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 101, true);

        double[] result = qbs.getValue(xnew);
        double[] out = {1.      ,  1.000017,  0.999999,  0.999944,  0.99985 ,  0.999614,
                0.999196,  0.998531,  0.997461,  0.995969,  0.993834,  0.990965,
                0.987247,  0.982402,  0.976401,  0.968912,  0.9598  ,  0.94891 ,
                0.935872,  0.92065 ,  0.90285 ,  0.88231 ,  0.858873,  0.83215 ,
                0.80211 ,  0.768409,  0.730909,  0.689509,  0.643964,  0.594257,
                0.540302,  0.482062,  0.419594,  0.353034,  0.282409,  0.208152,
                0.130441,  0.049599, -0.033583, -0.119014, -0.205507, -0.292575,
                -0.379568, -0.464901, -0.548413, -0.628174, -0.703395, -0.7732  ,
                -0.835447, -0.889942, -0.934546, -0.968385, -0.990708, -0.999681,
                -0.995167, -0.976002, -0.94171 , -0.892246, -0.827504, -0.747538,
                -0.653644, -0.54635 , -0.426844, -0.298027, -0.160235, -0.017944,
                0.127018,  0.272322,  0.412282,  0.546361,  0.6684  ,  0.775881,
                0.866537,  0.934828,  0.980339,  0.999449,  0.990679,  0.953847,
                0.888517,  0.794832,  0.676405,  0.534713,  0.372931,  0.198818,
                0.013223, -0.172923, -0.355149, -0.528561, -0.681194, -0.812049,
                -0.91113 , -0.974347, -0.999791, -0.9828  , -0.923271, -0.823638,
                -0.684896, -0.512881, -0.321855, -0.112468,  0.11528};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }

    @Test
    public void QuadraticSplineTest2() {
        QuadraticSpline qbs = new QuadraticSpline();
        qbs.computeFunction(this.x, this.y);
        double[] xnew = UtilMethods.linspace(0.0, 10.0, 201, true);

        double[] result = qbs.getValue(xnew);
        double[] out = {1.      ,  1.000013,  1.000017,  1.000012,  0.999999,  0.999976,
                0.999944,  0.999904,  0.99985 ,  0.999755,  0.999614,  0.999428,
                0.999196,  0.998911,  0.998531,  0.998048,  0.997461,  0.996771,
                0.995969,  0.994993,  0.993834,  0.992491,  0.990965,  0.989242,
                0.987247,  0.984967,  0.982402,  0.979552,  0.976401,  0.97286 ,
                0.968912,  0.964559,  0.9598  ,  0.954617,  0.94891 ,  0.942662,
                0.935872,  0.928541,  0.92065 ,  0.912092,  0.90285 ,  0.892922,
                0.88231 ,  0.870996,  0.858873,  0.845924,  0.83215 ,  0.817551,
                0.80211 ,  0.785735,  0.768409,  0.750134,  0.730909,  0.710723,
                0.689509,  0.667256,  0.643964,  0.619632,  0.594257,  0.567815,
                0.540302,  0.511718,  0.482062,  0.451342,  0.419594,  0.386824,
                0.353034,  0.318222,  0.282409,  0.245712,  0.208152,  0.169728,
                0.130441,  0.090326,  0.049599,  0.008296, -0.033583, -0.076037,
                -0.119014, -0.162188, -0.205507, -0.248969, -0.292575, -0.336252,
                -0.379568, -0.422451, -0.464901, -0.506917, -0.548413, -0.588861,
                -0.628174, -0.666352, -0.703395, -0.739206, -0.7732  , -0.80528 ,
                -0.835447, -0.8637  , -0.889942, -0.91359 , -0.934546, -0.952811,
                -0.968385, -0.981184, -0.990708, -0.996874, -0.999681, -0.99913 ,
                -0.995167, -0.987476, -0.976002, -0.960747, -0.94171 , -0.918885,
                -0.892246, -0.861785, -0.827504, -0.789402, -0.747538, -0.702266,
                -0.653644, -0.601672, -0.54635 , -0.48781 , -0.426844, -0.363583,
                -0.298027, -0.230177, -0.160235, -0.089423, -0.017944,  0.054203,
                0.127018,  0.200241,  0.272322,  0.343002,  0.412282,  0.480162,
                0.546361,  0.6092  ,  0.6684  ,  0.72396 ,  0.775881,  0.82391 ,
                0.866537,  0.903509,  0.934828,  0.960493,  0.980339,  0.993379,
                0.999449,  0.998549,  0.990679,  0.975818,  0.953847,  0.924747,
                0.888517,  0.845157,  0.794832,  0.738527,  0.676405,  0.608467,
                0.534713,  0.455496,  0.372931,  0.287372,  0.198818,  0.10727 ,
                0.013223, -0.08034 , -0.172923, -0.264526, -0.355149, -0.444248,
                -0.528561, -0.607542, -0.681194, -0.749514, -0.812049, -0.866073,
                -0.91113 , -0.947222, -0.974347, -0.992294, -0.999791, -0.996626,
                -0.9828  , -0.958311, -0.923271, -0.878343, -0.823638, -0.759156,
                -0.684896, -0.601508, -0.512881, -0.419663, -0.321855, -0.219457,
                -0.112468, -0.000889,  0.11528};
        Assertions.assertTrue(this.rms_error(result, out) <= 0.00025);
    }
}
