
package org.ingv.sit.sigpro;


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
public class COMP3 {
    public 
            
  double peakWinStart; /* starttime of peak-search window                  */
  double peakWinEnd;  /* end time of peak-search window                    */
  double p2pAmp;      /* Peak-to-peak amplitude of Wood-Anderson trace     */
  double p2pMin;      /* Amplitude on negative side of peak-to-peak        */
  double p2pMax;      /* Amplitude on positive side of peak-to-peak        */
  double p2pMinTime;  /* Time of min side of peak-to-peak amplitude        */
  double p2pMaxTime;  /* Time of max side of peak-to-peak amplitude        */
  double z2pAmp;      /* Zero-to-peak amplitude                            */
  double z2pTime;     /* Zero-to-peak time                                 */
  double mag;         /* Local magnitude for this component/direction      */
  double mag_corr;    /* local magnitude correction, to be added           */
  int priority;       /* priority-level for this trace 1 is high, 3 is low */
  int BadBitmap;      /* bitmap to indicate why this component is bad      */
  
  SCNLPAR pSCNLPar;    /* parameters for this SCNL                        */
  
  String name;       /* The full 3-character null termed component name   */
  String loc;        /* The full 2-character  null termed location code   */


}
