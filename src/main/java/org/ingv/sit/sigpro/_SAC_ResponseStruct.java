
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
public class _SAC_ResponseStruct { 
    public double    dGain;
    public int         iNumPoles;
    public int         iNumZeroes;
    public _SAC_PZNum  Poles[];
    public _SAC_PZNum  Zeroes[];
    
    
    public _SAC_ResponseStruct(){
    };
    
    public _SAC_ResponseStruct(int in_numPoles, int in_numZeroes){
        
        iNumPoles = in_numPoles;
        iNumZeroes = in_numZeroes;
        Poles = new _SAC_PZNum[iNumPoles];
        Zeroes = new _SAC_PZNum[iNumZeroes];
    }
    
    
    public int need_a_zero(){
        try {
            
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }
    
    public _SAC_PZNum[] Add_n_zeroes(int in_n) {
        int oldN = this.iNumZeroes;
        this.iNumZeroes+=in_n;
        _SAC_PZNum newZeros[] = new _SAC_PZNum[iNumZeroes];
        for (int i=0; i < oldN; i++){
            newZeros[i]= this.Zeroes[i];
        }
        for (int i=oldN; i<iNumZeroes; i++) {
            newZeros[i]= new _SAC_PZNum();
            newZeros[i].dImag=0.0;
            newZeros[i].dReal=0.0;
        }
        
        return newZeros;
    }
}
