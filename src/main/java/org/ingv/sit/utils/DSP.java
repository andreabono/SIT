/**
 *
 *
 * Andrea Bono 
 * I.N.G.V. Isituto Nazionale di Geofisica e Vulcanologia
 * C.N.T. Centro Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 * andrea.bono@ingv.it
 *
 */
package org.ingv.sit.utils;

import com.github.psambit9791.jdsp.filter.Bessel;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.github.psambit9791.jdsp.filter.Butterworth;
import com.github.psambit9791.jdsp.filter.Chebyshev;
import com.github.psambit9791.jdsp.filter.FIRWin1;
import com.github.psambit9791.jdsp.signal.Detrend;
import java.util.ArrayList;

public class DSP {
    
    public void AlignToCenter(float[] Y, int iStart, int iEnd) {
        int j;
        float mx = (float)0;
        for (j=iStart; j<iEnd; j++){
            mx += Y[j];
        }
        
        mx = mx / (float)(iEnd - iStart);
        
        for (j=iStart; j<iEnd; j++){
            Y[j]-= mx;
        }
    }
//------------------------------------------------------------------------------    
    public void IIR_BandPass_psambit(String filter_type, float[] Y, float SampleRate, int iStart, int iEnd, float f1, float f2, int Order) {
        double dY[]=new double[Y.length];
               
        try {
            for (int i=0; i< Y.length; i++){
                dY[i]=Y[i];
            }
                       
            Detrend d1 = new Detrend(dY, "linear");
            double[] out = d1.detrendSignal();
            
            switch (filter_type){
                case "BUTTERWORTH":
                    Butterworth F = new Butterworth(SampleRate) ;
                    dY = F.bandPassFilter(out, Order, f1, f2);
                    break;
                case "CHEBYSHEV":
                    Chebyshev F2 = new Chebyshev(SampleRate, 1) ;
                     dY = F2.bandPassFilter(out, Order, f1, f2);
                    break;
                case "BESSEL":
                    Bessel F3 = new Bessel(SampleRate);
                    
                    dY = F3.bandPassFilter(out, Order, f1, f2);
                    break;
            }
            //          
            for (int i=0; i< Y.length; i++){
                Y[i]=(float)dY[i];
            }
            
            dY=null;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(Level.SEVERE, 
                             ex.getMessage());
        }  
    }
//------------------------------------------------------------------------------    
    public void IIR_BandStop_psambit(String filter_type, float[] Y, float SampleRate, int iStart, int iEnd, float f1, float f2, int Order) {
        double dY[]=new double[Y.length];
               
        try {
            for (int i=0; i< Y.length; i++){
                dY[i]=Y[i];
            }
                       
            Detrend d1 = new Detrend(dY, "linear");
            double[] out = d1.detrendSignal();
            
            switch (filter_type){
                case "BUTTERWORTH":
                    Butterworth F = new Butterworth(SampleRate) ;
                    dY = F.bandStopFilter(out, Order, f1, f2);
                    break;
                case "CHEBYSHEV":
                    Chebyshev F2 = new Chebyshev(SampleRate, 1) ;
                     dY = F2.bandStopFilter(out, Order, f1, f2);
                    break;
                case "BESSEL":
                    Bessel F3 = new Bessel(SampleRate);
                    
                    dY = F3.bandStopFilter(out, Order, f1, f2);
                    break;
            }
            //          
            for (int i=0; i< Y.length; i++){
                Y[i]=(float)dY[i];
            }
            
            dY=null;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(Level.SEVERE, 
                             ex.getMessage());
        }  
    }
//------------------------------------------------------------------------------
    public void IIR_HighPass_psambit(String filter_type, float[] Y, float SampleRate, int iStart, int iEnd, float f, int Order) {
        double dY[]=new double[Y.length];
               
        try {
            for (int i=0; i< Y.length; i++){
                dY[i]=Y[i];
            }
                       
            Detrend d1 = new Detrend(dY, "linear");
            double[] out = d1.detrendSignal();
            
            switch (filter_type){
                case "BUTTERWORTH":
                    Butterworth F = new Butterworth(SampleRate) ;
                    dY = F.highPassFilter(out, Order, f);
                    break;
                case "CHEBYSHEV":
                    Chebyshev F2 = new Chebyshev(SampleRate, 1) ;
                     dY = F2.highPassFilter(out, Order, f);
                    break;
                case "BESSEL":
                    Bessel F3 = new Bessel(SampleRate);
                    
                    dY = F3.highPassFilter(out, Order, f);
                    break;
            }
            //          
            for (int i=0; i< Y.length; i++){
                Y[i]=(float)dY[i];
            }
            
            dY=null;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(Level.SEVERE, 
                             ex.getMessage());
        }  
    }
//------------------------------------------------------------------------------        
    public void IIR_LowPass_psambit(String filter_type, float[] Y, float SampleRate, int iStart, int iEnd, float f, int Order) {
        double dY[]=new double[Y.length];
               
        try {
            for (int i=0; i< Y.length; i++){
                dY[i]=Y[i];
            }
                       
            Detrend d1 = new Detrend(dY, "linear");
            double[] out = d1.detrendSignal();
            
            switch (filter_type){
                case "BUTTERWORTH":
                    Butterworth F = new Butterworth(SampleRate) ;
                    dY = F.lowPassFilter(out, Order, f);
                    break;
                case "CHEBYSHEV":
                    Chebyshev F2 = new Chebyshev(SampleRate, 1) ;
                     dY = F2.lowPassFilter(out, Order, f);
                    break;
                case "BESSEL":
                    Bessel F3 = new Bessel(SampleRate);
                    
                    dY = F3.lowPassFilter(out, Order, f);
                    break;
            }
            //          
            for (int i=0; i< Y.length; i++){
                Y[i]=(float)dY[i];
            }
            
            dY=null;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(Level.SEVERE, 
                             ex.getMessage());
        }  
    }
//------------------------------------------------------------------------------        
    public void FIR_psambit_ripple(String filter_type, FIRWin1.FIRfilterType filterCut,float[] Y, float SampleRate, double width, double[] cutoff, float Ripple) {
        double dY[]=new double[Y.length];
               
        try {
            for (int i=0; i< Y.length; i++){
                dY[i]=Y[i];
            }
                       
            Detrend d1 = new Detrend(dY, "linear");
            double[] out = d1.detrendSignal();
            
            switch (filter_type){
                case "WINDOWED":
                    FIRWin1 fw = new FIRWin1(Ripple, width, SampleRate);
                    double[] outCoeffs = fw.computeCoefficients(cutoff, filterCut, true);
                    dY = fw.firfilter(outCoeffs, out);
                    break;
//                case "LEAST-SQUARES OPTIMISED":
//                    
//                    double[] freqs; // = {0.0, 1.0, 2.0, 4.0, 4.5, 5.0};
//                    double[] gains; // = {0.0, 0.0, 1.0, 1.0, 0.0, 0.0};
//                    int taps = 7;
//
//                    FIRLS fwls = new FIRLS(taps, SampleRate);
//                    double[] coefficients = fwls.computeCoefficients(freqs, gains);
//                    dY = fwls.firfilter(coefficients, out);
//                    break;

            }
            //          
            for (int i=0; i< Y.length; i++){
                Y[i]=(float)dY[i];
            }
            
            dY=null;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(Level.SEVERE, 
                             ex.getMessage());
        }  
    }   
//------------------------------------------------------------------------------        
    public void FIR_psambit_taps(String filter_type, FIRWin1.FIRfilterType filterCut, float[] Y, float SampleRate, double width, double[] cutoff, int Ntaps) {
        double dY[]=new double[Y.length];
               
        try {
            for (int i=0; i< Y.length; i++){
                dY[i]=Y[i];
            }
                       
            Detrend d1 = new Detrend(dY, "linear");
            double[] out = d1.detrendSignal();
            
            switch (filter_type){
                case "WINDOWED":
                    
                    FIRWin1 fw = new FIRWin1(Ntaps, width, SampleRate);
                    double[] outCoeffs = fw.computeCoefficients(cutoff, filterCut, true);
                    dY = fw.firfilter(outCoeffs, out);
                    break;


            }
            //          
            for (int i=0; i< Y.length; i++){
                Y[i]=(float)dY[i];
            }
            
            dY=null;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(Level.SEVERE, 
                             ex.getMessage());
        }  
    }     

//------------------------------------------------------------------------------    
    public void BandPass(float[] Y, float SampleRate, int iStart, int iEnd, float f1, float f2, int Order) {
        try {
            AlignToCenter(Y, iStart, iEnd);
            HighPass(Y, SampleRate, iStart, iEnd, Order, f1);
            LowPass(Y, SampleRate, iStart, iEnd, Order, f2);
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(Level.SEVERE, 
                             ex.getMessage());
        }  
    }  
//------------------------------------------------------------------------------    
    public void HighPass(float[] Y, float SampleRate, int iStart, int iEnd, int Order, float cutoff_frequency) {
        int i, L;  
        double n0, n1, n2, d0, d1, d2; 
        double wc, wa, ksi;
        float zzf[] = new float[iEnd-iStart];
        //
        wc = 2.0 * Math.PI * cutoff_frequency;
        wa = Math.tan(wc * SampleRate / 2.0);
        //
        for (L=1; L <= Order/2; L++){
            ksi = Math.cos((2.0 * (double)(L - 1.0)) * Math.PI / (2.0 * (double)(Order)));
            d0 = 1.0 + 2.0 * ksi * wa + wa * wa;
            d2 = (1.0 - 2.0 * ksi * wa + wa * wa) / d0 * (-1.0);
            d1 = (2.0 * wa * wa - 2.0) / d0 * (-1.0);
            n0 = 1.0 / d0;
            n2 = n0;
            n1 = -2.0 * n0;
            //
            //For i = iStart To (iEnd - 2) - 1
            for (i=iStart; i< (iEnd -2); i++){
                zzf[i + 2] = (float)(d1 * zzf[i + 1] + 
                        d2 * zzf[i] + Y[i + 2] 
                        * n2 + Y[i + 1] * n1 + 
                        Y[i] * n0);
            }
            //
            
            for (i=iStart; i< iEnd; i++) {
                Y[i]=zzf[i];
            }
        }
    }
//------------------------------------------------------------------------------
    public void LowPass(float[] Y, float SampleRate, int iStart, int iEnd,  int Order, float cutoff_frequency) {
        int i, L;  
        double n0, n1, n2, d0, d1, d2; 
        double wc, wa, ksi;
        float z_out[] = new float[iEnd-iStart];
        //
        wc = 2.0 * Math.PI * cutoff_frequency;
        wa = Math.tan(wc * SampleRate / 2.0);
        //
        for (L=1; L <= Order/2; L++){
            ksi = Math.cos((2.0 * (double)(L - 1.0)) * Math.PI / (2.0 * (double)(Order)));
            d0 = 1.0 + 2.0 * ksi * wa + wa * wa;
            d2 = (1.0 - 2.0 * ksi * wa + wa * wa) / d0 * (-1.0);
            d1 = (2.0 * wa * wa - 2.0) / d0 * (-1.0);
            n0 = wa * wa / d0;
            n2 = n0;
            n1 = 2.0 * n0;
            //
            //for (i=iStart; i< (iEnd -2 -1); i++){
            for (i=iStart; i< (iEnd -2); i++){
                z_out[i + 2] = (float)(z_out[i + 1] * d1 
                             + z_out[i] * d2 
                             + Y[i + 2] * n2 
                             + Y[i + 1] * n1 
                             + Y[i] * n0);
            }
            //
            for (i=iStart; i< iEnd; i++) {
                Y[i]=z_out[i];
            }
        }
    }    
//------------------------------------------------------------------------------    
    public static ArrayList rotateVectorCC(ArrayList<Double> vec, ArrayList axis, double theta){
    double x, y, z;
    double u, v, w;
//    ArrayList<Double> res=new ArrayList<>();
//    
//    x=vec.getX();
//    y=vec.getY();
//    z=vec.getZ();
//    u=axis.getX();
//    v=axis.getY();
//    w=axis.getZ();
//    double xPrime = u*(u*x + v*y + w*z)*(1d - Math.cos(theta)) 
//            + x*Math.cos(theta)
//            + (-w*y + v*z)*Math.sin(theta);
//    double yPrime = v*(u*x + v*y + w*z)*(1d - Math.cos(theta))
//            + y*Math.cos(theta)
//            + (w*x - u*z)*Math.sin(theta);
//    double zPrime = w*(u*x + v*y + w*z)*(1d - Math.cos(theta))
//            + z*Math.cos(theta)
//            + (-v*x + u*y)*Math.sin(theta);
    //return new ArrayList(xPrime, yPrime, zPrime);
    
    return null;
    
    }
    
    

//    private Vector rotateZ(Vector vector,double angle) { // angle in radians
//
//    //normalize(vector); // No  need to normalize, vector is already ok...
//
//    float x1 = (float)(vector.x * Math.cos(angle) - vector.y * Math.sin(angle));
//
//    float y1 = (float)(vector.x * Math.sin(angle) + vector.y * Math.cos(angle)) ;
//
//    return new Vector(x1, y1);
//
//  }
    
    public boolean Rotate(float[] X, float[] Y, double theta){
        try {
            for (int i=0; i<X.length; i++){
                rotate(X[i], Y[i], theta);
            }
            return true;
        } catch(Exception ex){
            return false;
        }
    }
    
    private void rotate(double x, double y, double theta)
    {
        double rx = (x * Math.cos(theta)) - (y * Math.sin(theta));
        double ry = (x * Math.sin(theta)) + (y * Math.cos(theta));
        x = rx;
        y = ry;
    }

}
