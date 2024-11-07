
package org.ingv.sit.sigpro;

import edu.sc.seis.seisFile.sac.SacHeader;
import edu.sc.seis.seisFile.sac.SacTimeSeries;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.complex.Complex;
import org.ingv.dante.model.ObjectAmplitude;
import org.ingv.dante.model.ObjectStationmagnitude;
import org.ingv.sit.App;

import org.ingv.sit.datamodel.Event;
import org.ingv.sit.datamodel.Station;
import org.ingv.sit.datamodel.Waveform;
import org.ingv.sit.utils.sitDialog;



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
 * 
 * This class handles metadata recovery and NS-EW waves recovery for local
 * signal conversion into Wood Anderson waves and interactive amplitude re-pick.
 * 
 */
public class WoodAndersonHandler {
   // private String FDSNstationXMLNodeURL;
    private String ChannelsList;
   // private String level = "response";
    
//    private Double lat, lon, delta_min, delta_max;
//    private String date_start, date_end;
//    
    
    //private _StationResponses RESPONSES;
//    private String waves_path;
    
    private int N_PUNTI_USATI=0;
    
    private double data_samples[]=null;
       
    double[] fft_real_samples;
    
    Complex[] fft_complex_samples;
    double[] fft_combined_result;
    
    double DELTA_T=0.01;
    
//--------------------------------------------------------------------------------      
    public WoodAndersonHandler(){
        
    }
//--------------------------------------------------------------------------------     
    public boolean Make_a_WoodAnderson(Waveform inWave){
        try
        {          
            data_samples = new double[(int)inWave.getnSamples()];
            double sm=0d;
            double md;
            for (int i=0; i< inWave.getnSamples(); i++){
                data_samples[i]= inWave.getY(i);
                sm+=data_samples[i];
            }
            // Rimuove la media SUBITO
            md= sm/inWave.getnSamples();
            for (int i=0; i< inWave.getnSamples(); i++){
                data_samples[i]-= md;
            }
                               
            // 2. Fa la FFT    
            if (!Make_FFT(data_samples)) {
                sitDialog.ShowErrorMessage("Error in FFT\n" + 
                        inWave.getFilename() +"\nOperation aborted.", null);
                return false;
            }
            
            // 3. Procede con la conversione           
            if (!Convert(inWave)) {
                sitDialog.ShowErrorMessage("Error during conversion\n" + 
                        inWave.getFilename() +"\nOperation aborted.", null);
                return false;
            } 
                        
            return true;
        } catch (Exception ex) {
            
            return false;
        }
    }
//--------------------------------------------------------------------------------
    private boolean Read_Original(String file_name) {
        /*
        This is only used when input waves are in SAC format!!
        */
        try {
            float sum = 0;
            float avg = 0;
            SacHeader h = new SacHeader(file_name);
            SacTimeSeries sac = new SacTimeSeries(h);
            for (int i=0; i < sac.getY().length; i++) {
                sum+=sac.getY()[i];
            }
            avg = sum / sac.getY().length;
            
            data_samples = new double[sac.getY().length];
            for (int i=0; i < sac.getY().length; i++) {
                 data_samples[i]= (double)sac.getY()[i];
            }
            //            
            return true;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
 
    }    
//--------------------------------------------------------------------------------         
    private boolean Make_FFT(double[] data_samples) {
        try {
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

    //        fft_complex_half = new Complex[fft_complex_samples.length/2];
    //        for (int jj=0; jj< (fft_complex_samples.length / 2); jj++) {
    //            fft_complex_half[jj] = this.fft_complex_samples[jj]; 
    //        }    
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

            fft_real_samples = new double[N_PUNTI_USATI];

            fft_real_samples[0]=0;
            for (int j=1; j<N_PUNTI_USATI; j++) {         
                fft_real_samples[j] = fft_complex_samples[j].getReal();  
                
            }
            return true;
        } catch (Exception ex){
            return false;
        }
        
        
        //chart_fft.setChart(MakeTimeChart(N_PUNTI_USATI, fft_real_samples, "fft " + N_PUNTI_USATI));
        //this.txtNpunti_fft.setText(String.valueOf(N_PUNTI_USATI));
    }
    
//--------------------------------------------------------------------------------
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
_SAC_ResponseStruct readPZ(String STAZ, String CHAN) {
       try {
           int idStazInNet = App.G.SeismicNet.StationCodeToStationId(STAZ);
           int idChanInStaz = ((Station)App.G.SeismicNet.getStations().get(idStazInNet)).getChannelID(CHAN);
           
           if (idChanInStaz==-1) return null;
           
           return ((Station)App.G.SeismicNet.getStations().get(idStazInNet)).getChannels().get(idChanInStaz).getSAC_Response_Structure();
               
       } catch (Exception ex) {
           Logger.getLogger("org.ingv.sit").log(Level.SEVERE, ex.getMessage());
           return null;
       } 
} 
//--------------------------------------------------------------------------------
    
    private boolean Convert(Waveform inWave) {
        try {
            int nfft, nz, np, i;
            double delfreq;
            double taperFreqs[] = {1.0, 1.0, 10.0, 10.0};
            _SAC_ResponseStruct rs;  /* the combined response function */
    //        
            /* Get the instrument response */       
            _SAC_ResponseStruct origRS = readPZ(inWave.getStationCode(), inWave.getChannelCode());
                        
            if (origRS==null) {
                Logger.getLogger("org.ingv.sit").log(Level.SEVERE, "Response not found for " + inWave.getStationCode() + " " + inWave.getChannelCode());
                return  false;
            }
    //        
            _SAC_ResponseStruct finalRS = readPZ_WA();
            if (finalRS==null) return false;
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
            
            float new_duration = nfft / inWave.getSamplingRate();
    //
            double fre[] = new double[N_PUNTI_USATI];
            double fim[] = new double[N_PUNTI_USATI];
    //  
            response(N_PUNTI_USATI, DELTA_T, rs, fre, fim);
    //                    
           // chart_response.setChart(MakeTimeChart(fre.length, fre,  "response"));
           // this.txtNpunti_response.setText(String.valueOf(fre.length));
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
            taperFreqs[3] = 0.5/DELTA_T;  
            taperFreqs[2] = 0.45/DELTA_T;   
    //
            delfreq = 1.0 / (N_PUNTI_USATI * DELTA_T);       
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

         //   chart_deconv.setChart(MakeTimeChart(real_part_of_dconvolution.length,  real_part_of_dconvolution,   "Deconvolved (real samps)"));

         //   this.txtNpunti_deconv.setText(String.valueOf(real_part_of_dconvolution.length));
    //        
            fftMaker FFT = new fftMaker();       
    //        
            Complex FFTres_rev[];
           
            FFTres_rev = FFT.fft_commons(deconvolved_signal, false);
    //
            double res[];  
            float res_float[];
            res = new double[FFTres_rev.length];
            res_float = new float[FFTres_rev.length];
            double res_i[];  
            res_i = new double[FFTres_rev.length];

            ArrayList<Double> WA=new ArrayList<Double>();
            for (int kk=0; kk<FFTres_rev.length; kk++) {            
                res[kk] = FFTres_rev[kk].getReal() ;                
                WA.add(res[kk]);
                res_float[kk] = (float)FFTres_rev[kk].getReal() ;             
                res_i[kk] = FFTres_rev[kk].getImaginary();

            }
            
            inWave.setSamplingRate(WA.size()/new_duration);
            inWave.setnSamples(WA.size());
            //inWave.setY(new float[(int)inWave.getnSamples()]);
            inWave.setY(res_float);
        
            return true;            
        } catch (Exception ex) {
            return false;
        }
        
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
    public ObjectStationmagnitude RecalcAmplitude(Event E, int WAID, int algorithmID){
        try{
            double timemin, timemax;
            double tauP, tauS;
            LocalDateTime PTIME=null, STIME=null ;
            
            double K1 = 40.0;
            double K2 = 30.0;
            int i, nPhases;
//
            timemin = 0.0;
            timemax = 0.0;              
//
            Waveform tmpW = E.SubplotIndex_to_Waveform_WA(WAID);
           
//            STA = tmpW.getStationCode();
//            CHAN = tmpW.getChannelCode();
            
            int idStaz = E.StationCode_to_StationId(tmpW.getStationCode());
            nPhases = E.getStation(idStaz).getNPhases();
//  
            if (nPhases==0) return null;
//           
            for(i = 0; i < nPhases; i++){
                if (E.getStation(idStaz).getPhase(i).getIscCode().toUpperCase().contains("P")) {
                    PTIME = E.getStation(idStaz).getPhase(i).getPick().getArrivalTime().toLocalDateTime(); //  .getDateTime_formatted("HH:mm:ss.SS");
                }else {
                    if (E.getStation(idStaz).getPhase(i).getIscCode().toUpperCase().contains("S"))
                        STIME = E.getStation(idStaz).getPhase(i).getPick().getArrivalTime().toLocalDateTime(); 
                }                 
            }         
//
            if (PTIME==null) return null; // qui si può andare a cercare un tempo P teorico invece di uscire
//               
            tauP = PTIME.toEpochSecond(ZoneOffset.UTC) - E.getInnerObjectEvent().getOrigins().get(0).getOt().toEpochSecond(); 
            tauS = tauP * 1.73;
//

            timemin = PTIME.toEpochSecond(ZoneOffset.UTC) - tmpW.getStartTime().toEpochSecond(ZoneOffset.UTC) - 1.0;
//            
            if (STIME != null) 
                tauS = STIME.toEpochSecond(ZoneOffset.UTC) - E.getInnerObjectEvent().getOrigins().get(0).getOt().toEpochSecond();  
            else
                STIME = E.getInnerObjectEvent().getOrigins().get(0).getOt().plusSeconds((long)tauS).toLocalDateTime(); 
           
//                
            double numero = K1 * (1 - Math.exp(-tauS / K2));
            timemax = STIME.toEpochSecond(ZoneOffset.UTC) + numero - tmpW.getStartTime().toEpochSecond(ZoneOffset.UTC); 
// 
            if ((tauP < 0) || (tauS < 0)){
                String msgText =  "Please, check P and/or S times they look inconsistent when compared to event OT: " + 
                        E.getInnerObjectEvent().getOrigins().get(0).getOt().toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.of("UTC")));
               
                sitDialog.ShowErrorMessage(msgText, null);
                
                return null;
            }
         
            ObjectStationmagnitude outA=null;
            
            if (algorithmID == 1) {
                outA = this.autoAmplPer_1(tmpW, timemin, timemax);      
            } else {
                    if (algorithmID == 2)  {
                       // outA = this.autoAmplPer_2(tmpW, timemin, timemax, out_Pickmin, out_Tmin, out_Pickmax, out_Tmax);
                    }
            }
                    
            return outA;
        } catch (Exception ex) {          
            return null;
        }
    }
//--------------------------------------------------------------------------------   
    private java.time.LocalDateTime LocalDateTimeConversion(LocalDateTime in_datetime){
        java.time.LocalDateTime res=null;

        res = java.time.LocalDateTime.of(in_datetime.getYear(), in_datetime.getMonthValue(), in_datetime.getDayOfMonth(), 
                in_datetime.getHour(), in_datetime.getMinute(), in_datetime.getSecond(), in_datetime.getNano());
        return res;
        
    }
    private LocalDateTime LocalDateTimeConversion_inverse(java.time.LocalDateTime in_datetime){
        LocalDateTime res=null;

        res = LocalDateTime.of(in_datetime.getYear(), in_datetime.getMonthValue(), in_datetime.getDayOfMonth(), 
                in_datetime.getHour(), in_datetime.getMinute(), in_datetime.getSecond(), in_datetime.getNano());
        return res;
        
    }
//------------------------------------------------------------------------------    
    private ObjectStationmagnitude autoAmplPer_1 (Waveform W, double TimeMin, double TimeMax)           
//           computes maximum amplitude and period of the digital signal
//           pointed to by the class member curDigSign, in the time window
//           (timeMin,timeMax) or for the whole signal if timeMin and timeMax
//           are negative;
             {
        double maxAmpl, period;
        double timeOnMin=0.0;
        double timeOnMax=0.0;
        float minVal, maxVal, tOfMinVal, tOfMaxVal;
        int firstSmpl = 0;
        long nSmpls  = W.getnSamples();
        int i;
        
        try{
            maxAmpl = 0.0;
            period = 0.0;
//
            float sampRate_sec = (float)1.0/W.getSamplingRate();
//           
            if ((TimeMin >= 0.0) && (TimeMax > (TimeMin + 1.5 * sampRate_sec))) {               
                firstSmpl = (int)(TimeMin /  sampRate_sec);
                nSmpls =    (int)((TimeMax - TimeMin) / sampRate_sec) + 1;
                if (firstSmpl >= (W.getnSamples() - 2)) {                 
                    firstSmpl = 0;
                    nSmpls = W.getnSamples();
                }
               if ((firstSmpl + nSmpls) > (W.getnSamples())) {
                    nSmpls = (W.getnSamples() - firstSmpl);
                }
            }
//            
            minVal = W.getY(firstSmpl);
            maxVal = W.getY(firstSmpl);
            tOfMinVal = (float)0.0;
            tOfMaxVal = (float)0.0;
//
            for (i=firstSmpl+1; i < (firstSmpl + nSmpls)-1; i++){
                if ((W.getY(i + 1) >= W.getY(i)) && (W.getY(i) <= W.getY(i - 1))) {
                    if  (W.getY(i)<minVal)      // this line should not exist
                        minVal = W.getY(i);
                    tOfMinVal = i * sampRate_sec;
                    if ((maxVal - minVal) > maxAmpl) {
                        maxAmpl = maxVal - minVal;
                        period = 2.0 * (tOfMinVal - tOfMaxVal);
                        timeOnMin = tOfMinVal;
                        timeOnMax = tOfMaxVal;
                    }
                }
                if ((W.getY(i + 1) <= W.getY(i)) && (W.getY(i) >= W.getY(i - 1))) {
                    if  (W.getY(i)>maxVal)       // this line should not exist
                        maxVal = W.getY(i);
                    tOfMaxVal = i * sampRate_sec;
                    if ((maxVal - minVal) > maxAmpl) {
                        maxAmpl = maxVal - minVal;
                        period = 2.0 * (tOfMaxVal - tOfMinVal);
                        timeOnMin = tOfMinVal;
                        timeOnMax = tOfMaxVal;
                    }
                }
            }
//         
            maxAmpl /= 2.0;
//                  
            LocalDateTime out_Tmin, out_Tmax;
            out_Tmin = W.getStartTime().plus((long)(timeOnMin * 1000.0),ChronoUnit.MILLIS);
            out_Tmax = W.getStartTime().plus((long)(timeOnMax * 1000.0),ChronoUnit.MILLIS);

            ObjectStationmagnitude A = new ObjectStationmagnitude();
            A.setAmplitude(new ObjectAmplitude());
            A.getAmplitude().setSta(W.getStationCode());
            A.getAmplitude().setCha(W.getChannelCode());
            A.getAmplitude().setNet(W.getNetworkCode());
            A.getAmplitude().setLoc(W.getLocationCode());
            A.getAmplitude().setAmp1(Double.valueOf(minVal));
            A.getAmplitude().setAmp2(Double.valueOf(maxVal));
            A.getAmplitude().setTime1(OffsetDateTime.of( out_Tmin, ZoneOffset.UTC));
            A.getAmplitude().setTime2(OffsetDateTime.of( out_Tmax, ZoneOffset.UTC));
            A.setIsUsed(Boolean.TRUE);
                                         
            return A;
        }
        catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return null;
        }
        
        }
    //----------------------------------------------------------------------------
}