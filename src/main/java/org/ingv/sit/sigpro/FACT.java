
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
public class FACT {
   
   public long nfft;
   public long fact_power[];
   public double trigs[];
   public long ifax[];
   
   FACT next;
 
   
   public FACT(){
       this.nfft=0;
       this.fact_power = new long[10];
       this.trigs = new double[1];
       this.ifax = new long[1];
       
       this.next = null;
   }
   
}
