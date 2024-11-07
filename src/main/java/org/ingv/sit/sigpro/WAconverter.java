
package org.ingv.sit.sigpro;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.math3.complex.Complex;

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
public class WAconverter { 
    private int N_PUNTI_USATI=0;
    
    double[] fft_combined_result;
    Complex[] fft_complex_samples;
//--------------------------------------------------------------------------------    
    public void makeWA(String sta, String comp, String net, String loc, double sampRate){
        
        
        
        
        
    }
//--------------------------------------------------------------------------------    
   _SAC_ResponseStruct readPZ(String sta, String comp, String net, String loc) {
       try {                         
           _SAC_ResponseStruct res;
           res = new _SAC_ResponseStruct();
            
            res.dGain = 116426.7364; 
                   
           res.iNumPoles=4;
           res.iNumZeroes=3;
                  
           res.Poles = new _SAC_PZNum[res.iNumPoles];
           res.Zeroes = new _SAC_PZNum[res.iNumZeroes];
           
           res.Zeroes[0] = new _SAC_PZNum();
           res.Zeroes[0].dReal=0.0;
           res.Zeroes[0].dImag=0.0;
           res.Zeroes[1] = new _SAC_PZNum();
           res.Zeroes[1].dReal=0.0;
           res.Zeroes[1].dImag=0.0;
           res.Zeroes[2] = new _SAC_PZNum();
           res.Zeroes[2].dReal=0.0;
           res.Zeroes[2].dImag=0.0;
//   
           res.Poles[0] = new _SAC_PZNum();
           res.Poles[0].dReal = -0.1111;
           res.Poles[0].dImag = 0.1111;
           res.Poles[1] = new _SAC_PZNum();
           res.Poles[1].dReal = -0.1111;
           res.Poles[1].dImag = -0.1111;
           res.Poles[2] = new _SAC_PZNum();
           res.Poles[2].dReal = -172.79;
           res.Poles[2].dImag = 262.37;
           res.Poles[3] = new _SAC_PZNum();
           res.Poles[3].dReal = -172.79;
           res.Poles[3].dImag = -262.37;
//
           return res;
       } catch (Exception ex) {
           return null;
       }
       
   }    
   
   
     _SAC_ResponseStruct readPZ_WA() {
       try {
           _SAC_ResponseStruct res;
           res = new _SAC_ResponseStruct();
          
           res.Poles = new _SAC_PZNum[2];
           res.Zeroes = new _SAC_PZNum[2];
           
           res.iNumPoles=2;
           res.iNumZeroes=2;
           
            //-6.283185307179586 4.712388980384689
            //-6.283185307179586 -4.712388980384689
           
            //double CONSTANT=2080.0;
            double CONSTANT=0.0028;
              
            res.dGain=CONSTANT;
            
            double t0 = 0.8;
            double h = 0.8;           
          
            double om0 = Math.PI * 2.0 / t0;
            double rad = Math.sqrt(1.0 - (h*h));

            res.Zeroes[0] = new _SAC_PZNum();
            res.Zeroes[0].dReal=0.0;
            res.Zeroes[0].dImag=0.0;
            res.Zeroes[1] = new _SAC_PZNum();
            res.Zeroes[1].dReal=0.0;
            res.Zeroes[1].dImag=0.0;
           
            res.Poles[0] = new _SAC_PZNum();
            res.Poles[0].dReal = -om0 * h;
            res.Poles[0].dImag = om0 * rad;
            res.Poles[1] = new _SAC_PZNum();
            res.Poles[1].dReal = -om0 * h;
            res.Poles[1].dImag =-om0 * rad;
                
           return res;
       } catch (Exception ex) {
           return null;
       }
       
   }    
//-------------------------------------------------------------------------------- 
    public void convert(double delta_t){
        _SAC_ResponseStruct rs;       
        int nfft, nz, np, i;
        double delfreq;
        double taperFreqs[] = {1.0, 1.0, 10.0, 10.0};
//        
        /* Get the instrument response */
        
//        
        _SAC_ResponseStruct origRS = this.readPZ("","","","");
        if (origRS==null) return ;
//        
        _SAC_ResponseStruct finalRS = this.readPZ_WA();
        if (finalRS==null) return ;
//              
        //------------------------------------------------------------------------
        /* Combine the response functions into one: finalRS / origRS */
        //------------------------------------------------------------------------
        rs = new _SAC_ResponseStruct();
        //
        rs.dGain = finalRS.dGain / origRS.dGain;
        rs.iNumPoles = finalRS.iNumPoles + origRS.iNumZeroes;
        rs.iNumZeroes = finalRS.iNumZeroes + origRS.iNumPoles;
//      
        rs.Poles = new _SAC_PZNum[rs.iNumPoles];
        rs.Zeroes = new _SAC_PZNum[rs.iNumZeroes];
        //
        nz=0;
        np=0;
        int k=0;
        //     
        for (i = 0; i < origRS.iNumPoles; i++) {
            k=nz++;
            rs.Zeroes[k] = new _SAC_PZNum();
            rs.Zeroes[k] = origRS.Poles[i];
        }
        for (i = 0; i < origRS.iNumZeroes; i++) {
            k=np++;
            rs.Poles[k] = new _SAC_PZNum();
            rs.Poles[k] = origRS.Zeroes[i];
        }
        for (i = 0; i < finalRS.iNumPoles; i++) {
            k=np++;
            rs.Poles[k] = new _SAC_PZNum();
            rs.Poles[k] = finalRS.Poles[i];
        }
        for (i = 0; i < finalRS.iNumZeroes; i++) {
            k=nz++;
            rs.Zeroes[k] = new _SAC_PZNum();
            rs.Zeroes[k] = finalRS.Zeroes[i];
        }
//        
        nfft = fft_complex_samples.length;
//
        double fre[] = new double[N_PUNTI_USATI];
        double fim[] = new double[N_PUNTI_USATI];
//  
        response(N_PUNTI_USATI, delta_t, rs, fre, fim);
//                    
        //chart_response.setChart(MakeTimeChart(fre.length, fre,  "response"));
        //this.txtNpunti_response.setText(String.valueOf(fre.length));
//     
        /* Convolve the tapered frequency response with the data. Since we  *
         * are in the frequency domain, convolution becomes `multiply'.     *
         * We skip the zero-frequency part; this only affects the mean      *
         * of the data, which should have been removed long ago.            */        
//
        int nfreq = N_PUNTI_USATI/2 +1;
//               
        int ii;
        double f, tpr, dre, dim;
//        
        taperFreqs[3] = 0.5/delta_t;  
        taperFreqs[2] = 0.45/delta_t;   
//
        delfreq = 1.0 / (N_PUNTI_USATI * delta_t);       
        //delfreq = 1.0 / (nfft * DELTA_T);
        fft_combined_result[0] = 0.0;   /* Remove the mean, if there is any */
        for (i = 1; i < nfreq - 1; i++)
        {
          ii = i+i;
          f = i * delfreq;
          tpr =  ftaper(f, taperFreqs[1], taperFreqs[0]) * ftaper(f, taperFreqs[2], taperFreqs[3]);
          dre = fft_combined_result[ii];   /* Real part of transformed data */
          dim = fft_combined_result[ii+1]; /* Imaginary part of transformed data */
          fft_combined_result[ii] = (dre * fre[i] - dim * fim[i]) * tpr;
          fft_combined_result[ii+1] = (dre * fim[i] + dim * fre[i]) * tpr;
        } 
        f = i * delfreq;
        tpr = ftaper(f, taperFreqs[1], taperFreqs[0]) * ftaper(f, taperFreqs[2], taperFreqs[3]);
        dre = fft_combined_result[N_PUNTI_USATI-1];  /* Real part of transformed data; imaginary part is 0 */
        fft_combined_result[N_PUNTI_USATI-1] = dre * fre[i] * tpr;  
//
        double real_part_of_dconvolution[] = new double[N_PUNTI_USATI/2];
        Complex deconvolved_signal[] = new Complex[N_PUNTI_USATI/2];
        int nrefSamps=0;
        int nS;
        for (nS=0; nS < (N_PUNTI_USATI/2)-1 ; nS++) {
            real_part_of_dconvolution[nS] = fft_combined_result[nrefSamps];             
            deconvolved_signal[nS] = new Complex(fft_combined_result[nrefSamps],  fft_combined_result[nrefSamps+1]);
            //
            nrefSamps = nrefSamps+2;    
        }
        deconvolved_signal[ N_PUNTI_USATI/2-1] = new Complex(fft_combined_result[N_PUNTI_USATI/2-1], 0.0);
//        
        fftMaker FFT = new fftMaker();       
//        
        Complex FFTres_rev[];       
        FFTres_rev = FFT.fft_commons(deconvolved_signal, false);
//
        double res[];  
        res = new double[FFTres_rev.length];
        double res_i[];  
        res_i = new double[FFTres_rev.length];

        for (int kk=0; kk<FFTres_rev.length; kk++) {            
            res[kk] = FFTres_rev[kk].getReal() ; 
            res_i[kk] = FFTres_rev[kk].getImaginary();
            
        }
    }
     

//--------------------------------------------------------------------------------        
   /*
     * ftaper: produce a cosine taper between unity (beyond fon) and zero
     *        (beyond foff). The cosine taper is between fon and foff.
     * Arguments: freq: the frequency at which the taper value is desired
     *             fon: the unity end of the taper
     *            foff: the zero end of the taper
     *    if fon and foff are equal, then taper returns 1.0, the all-pass filter.
     * returns: the value of the taper
     */
    double ftaper(double freq, double fon, double foff){    
        double t, pi = Math.PI;
        if (fon > foff)
        {   /* high-pass taper */
          if (freq < foff)
            t = 0.0;
          else if (freq > fon)
            t = 1.0;
          else
            t = 0.5 * (1.0 - Math.cos(pi * (freq - foff) / (fon - foff)));
        }
        else if (fon < foff)
        {   /* low-pass case */
          if (freq < fon)
            t = 1.0;
          else if (freq > foff)
            t = 0.0;
          else
            t = 0.5 * (1.0 + Math.cos(pi * (freq - fon) / (foff - fon)));
        }
        else
          t = 1.0;

        return t;
    }    
    
//--------------------------------------------------------------------------------    
    /*
     * response: compute frequency response from the pole-zero-gain information.
     *  arguments:  nfft: the number of points that will be used in the FFT
     *            deltat: the time interval between data points in the time-domain
     *               pRS: pointer to the Response Structure holding the poles,
     *                    zeros and gain information for the desired function
     *               tfr: pointer to the real part of the frequency response
     *               tfi: pointer to the imaginary part of the frequency 
     *                    response. Both tfr and tfi must be allocated
     *                    by the caller to contain at least nfft/2+1 values.
     */
    void response(long nfft, double deltat, _SAC_ResponseStruct pRS, double tfr[], double tfi[]){
         double delomg, omega, mag2;
        double sr, si, srn, sin, srd, sid, sr0, si0;
        int i, j, ntr;
        

        ntr = (int)nfft / 2 + 1;
        delomg = 2.0 * Math.PI / (nfft * deltat);

        /* The (almost) zero frequency term */
        /* The zeros, in the numerator */
        srn = 1.0;
        sin = 0.0;
        omega = delomg * 0.001;
        for (j = 0; j < pRS.iNumZeroes; j++)
        {
          sr = -pRS.Zeroes[j].dReal;
          si = omega - pRS.Zeroes[j].dImag;
          sr0 = srn * sr - sin * si;
          si0 = srn * si + sin * sr;
          srn = sr0;
          sin = si0;
        }

        /* The poles; in the denominator */
        srd = 1.0;
        sid = 0.0;

        for (j = 0; j < pRS.iNumPoles; j++)
        {
          sr = - pRS.Poles[j].dReal;
          si = omega - pRS.Poles[j].dImag;
          sr0 = srd * sr - sid * si;
          si0 = srd * si + sid * sr;
          srd = sr0;
          sid = si0;
        }

        /* Combine numerator, denominator and gain using complex arithemetic */
        mag2 = pRS.dGain / (srd * srd + sid * sid);
        tfr[0] = mag2 * (srn * srd + sin * sid);
        tfi[0] = 0.0; /* Actually the Nyqust part; we don't want it */

        /* The non-zero frequency parts */
        for (i = 1; i < ntr; i++)
        {
          /* The zeros, in the numerator */
          srn = 1.0;
          sin = 0.0;
          omega = delomg * i;
          for (j = 0; j < pRS.iNumZeroes; j++)
          {
            sr = - pRS.Zeroes[j].dReal;
            si = omega - pRS.Zeroes[j].dImag;
            sr0 = srn * sr - sin * si;
            si0 = srn * si + sin * sr;
            srn = sr0;
            sin = si0;
          }

          /* The poles; in the denominator */
          srd = 1.0;
          sid = 0.0;

          for (j = 0; j < pRS.iNumPoles; j++)
          {
            sr = - pRS.Poles[j].dReal;
            si = omega - pRS.Poles[j].dImag;
            sr0 = srd * sr - sid * si;
            si0 = srd * si + sid * sr;
            srd = sr0;
            sid = si0;
          }

          /* Combine numerator, denominator and gain using complex arithemetic */
          mag2 = pRS.dGain / (srd * srd + sid * sid);
          tfr[i] = mag2 * (srn * srd + sin * sid);
          tfi[i] = mag2 * (sin * srd - srn * sid);
        }
        return;
    }
    
    
//--------------------------------------------------------------------------------    
    private void Make_FFT(double data_samples[]) {

        fftMaker FFT = new fftMaker();
//         
        int n_zero_samples = FFT.findNPadding(data_samples.length);     
//
        double[] fft_candidate = new double[data_samples.length + n_zero_samples];
        Complex[] fft_candidate_complex = new Complex[data_samples.length + n_zero_samples];
        for (int jj=0; jj< data_samples.length; jj++) {
            fft_candidate[jj]=data_samples[jj];
            fft_candidate_complex[jj]= new Complex(data_samples[jj], 0.0);
        }
        
        for (int jj=data_samples.length; jj< fft_candidate.length; jj++) {
            fft_candidate[jj]=0.0;
            fft_candidate_complex[jj]= new Complex(0.0, 0.0);
        }
//
        fft_complex_samples = FFT.fft_commons(fft_candidate, true);     
//     
        N_PUNTI_USATI = fft_complex_samples.length/2;       
//
        fft_combined_result = new double[2 * N_PUNTI_USATI];
        
        int jj=1;
        fft_combined_result[0]=0.0;
        fft_combined_result[1]=0.0;
        for (int kk=0; kk<N_PUNTI_USATI-1; kk=kk+1) {
            jj+=1;
            fft_combined_result[jj] = fft_complex_samples[kk].getReal(); 
            jj+=1;
            fft_combined_result[jj] = fft_complex_samples[kk].getImaginary(); 
        }
   
    }
}
