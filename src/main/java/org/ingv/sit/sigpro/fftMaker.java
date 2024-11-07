
package org.ingv.sit.sigpro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.TransformType;

/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 *
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */
public class fftMaker {
        
    public Complex[] fft_commons(double[] inputReal, boolean forward){
        
        org.apache.commons.math3.transform.FastFourierTransformer t = new org.apache.commons.math3.transform.FastFourierTransformer(DftNormalization.STANDARD);

        Complex[] res;
        if (forward)
            res= t.transform(inputReal, TransformType.FORWARD);
        else
            res= t.transform(inputReal, TransformType.INVERSE);
        return res;
    }
     
    public Complex[] fft_commons(Complex[] inputComplex, boolean forward){
        
        org.apache.commons.math3.transform.FastFourierTransformer t = new org.apache.commons.math3.transform.FastFourierTransformer(DftNormalization.STANDARD);
   
        Complex[] res;
        if (forward)
            res= t.transform(inputComplex, TransformType.FORWARD);
        else
            res= t.transform(inputComplex, TransformType.INVERSE);
        return res;
    }
    
    
    public int findNPadding(int in) {
        
        double ld = Math.log(in) / Math.log(2.0);
        
        if (((int) ld) - ld == 0) {
            // in is already a power of 2
            return 0;
        }
        
        int nPow=0;
        while (true) {
            if (Math.pow(2, nPow) > in) 
                return (int)(Math.pow(2, nPow) - in);
            else
                nPow+=1;
           
        }
        
    }
    

    /**
     * The Fast Fourier Transform (generic version, with NO optimizations).
     *
     * @param inputReal
     *            an array of length n, the real part
     * @param inputImag
     *            an array of length n, the imaginary part
     * @param DIRECT
     *            TRUE = direct transform, FALSE = inverse transform
     * @return a new array of length 2n
     */
    public  double[] fft(final double[] inputReal, double[] inputImag,
                               boolean DIRECT) {
        // - n is the dimension of the problem
        // - nu is its logarithm in base e
        int n = inputReal.length;

        // If n is a power of 2, then ld is an integer (_without_ decimals)
        double ld = Math.log(n) / Math.log(2.0);

        // Here I check if n is a power of 2. If exist decimals in ld, I quit
        // from the function returning null.
        if (((int) ld) - ld != 0) {
            Logger.getLogger("org.ingv.pfx").log(Level.SEVERE, 
                             "The number of elements is not a power of 2.");
            return null;
        }

        // Declaration and initialization of the variables
        // ld should be an integer, actually, so I don't lose any information in
        // the cast
        int nu = (int) ld;
        int n2 = n / 2;
        int nu1 = nu - 1;
        double[] xReal = new double[n];
        double[] xImag = new double[n];
        double tReal, tImag, p, arg, c, s;

        // Here I check if I'm going to do the direct transform or the inverse
        // transform.
        double constant;
        if (DIRECT)
            constant = -2 * Math.PI;
        else
            constant = 2 * Math.PI;

        // I don't want to overwrite the input arrays, so here I copy them. This
        // choice adds \Theta(2n) to the complexity.
        for (int i = 0; i < n; i++) {
            xReal[i] = inputReal[i];
            xImag[i] = inputImag[i];
        }

        // First phase - calculation
        int k = 0;
        for (int l = 1; l <= nu; l++) {
            while (k < n) {
                for (int i = 1; i <= n2; i++) {
                    p = bitreverseReference(k >> nu1, nu);
                    // direct FFT or inverse FFT
                    arg = constant * p / n;
                    c = Math.cos(arg);
                    s = Math.sin(arg);
                    tReal = xReal[k + n2] * c + xImag[k + n2] * s;
                    tImag = xImag[k + n2] * c - xReal[k + n2] * s;
                    xReal[k + n2] = xReal[k] - tReal;
                    xImag[k + n2] = xImag[k] - tImag;
                    xReal[k] += tReal;
                    xImag[k] += tImag;
                    k++;
                }
                k += n2;
            }
            k = 0;
            nu1--;
            n2 /= 2;
        }

        // Second phase - recombination
        k = 0;
        int r;
        while (k < n) {
            r = bitreverseReference(k, nu);
            if (r > k) {
                tReal = xReal[k];
                tImag = xImag[k];
                xReal[k] = xReal[r];
                xImag[k] = xImag[r];
                xReal[r] = tReal;
                xImag[r] = tImag;
            }
            k++;
        }

        // Here I have to mix xReal and xImag to have an array (yes, it should
        // be possible to do this stuff in the earlier parts of the code, but
        // it's here to readibility).
        double[] newArray = new double[xReal.length * 2];
        double radice = 1 / Math.sqrt(n);
        for (int i = 0; i < newArray.length; i += 2) {
            int i2 = i / 2;
            // I used Stephen Wolfram's Mathematica as a reference so I'm going
            // to normalize the output while I'm copying the elements.
            newArray[i] = xReal[i2] * radice;
            newArray[i + 1] = xImag[i2] * radice;
        }
        return newArray;
    }

    /**
     * The reference bitreverse function.
     */
    private int bitreverseReference(int j, int nu) {
        int j2;
        int j1 = j;
        int k = 0;
        for (int i = 1; i <= nu; i++) {
            j2 = j1 / 2;
            k = 2 * k + j1 - 2 * j2;
            j1 = j2;
        }
        return k;
      }


    public void write_to_file(String fName ,  double[] arr){
        try (FileWriter writer = new FileWriter(fName, true);
		BufferedWriter bw = new BufferedWriter(writer)) {

            for (int i = 0; i< arr.length; i++)
		bw.write(String.valueOf(arr[i]).replace(".", ",") + "\n");

	} catch (IOException e) {
		System.err.format("IOException: %s%n", e);
	}

    
    }





}


