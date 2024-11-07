/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ingv.sit.webservices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.ingv.sit.App;
import org.ingv.sit.utils.pfxDialog;

/**
 *
 * @author andreabono
 */
public class Hypo2000_configuration {
    
    private boolean FixDepth=false;

    private String basic_item;  // "200 T 2000 0"
    private String LET;         // "LET 5 2 3 2 2"
    private String H71;         // "H71 1 1 3"
    private String STA;         // "STA './all_stations.hinv'"
    private String CRH;         // "CRH 1 './italy.crh'"
    private String MAG;         // "MAG 1 T 3 1"
    private String DUR;         // "DUR -.81 2.22 0 .0011 0); 5*0); 9999 1"
    private String FC1;         // "FC1 'D' 2 'HHZ' 'EHZ'"
    private String PRE;         // "PRE 7); 3 0 4 9); 5 6 4 9); 1 1 0 9); 2 1 0 9); 4 4 4 9); 3 0 0 9); 4 0 0 9"
    private String RMS;         // "RMS 4 .40 2 4"
    private String ERR;         // "ERR .10"
    private String POS;         // "POS 1.78"
    private String REP;         // "REP T T"
    private String JUN;         // "JUN T"
    private String MIN;         // "MIN 4"
    private String NET;         // "NET 4"
    private String ZTR;         // "ZTR 5 T"
    private String DIS;         // "DIS 6 100 1. 7."
    private String DAM;         // "DAM 7 30 0.5 0.9 0.005 0.02 0.6 100 500"
    private String WET;         // "WET 1. .75 .5 .25"
    private String ERF;         // "ERF T"
    private String TOP;         // "TOP F"
    private String LST;         // "LST 1 1 0"
    private String KPR;         // "KPR 2"
    private String COP;         // "COP 5"
    private String CAR;         // "CAR 3"
    private String PRT;         // "PRT '../output/hypo.prt'"
    private String SUM;         // "SUM '../output/hypo.sum'"
    private String ARC;         // "ARC '../output/hypo.arc'"
    private String APP;         // "APP F T F"
    private String CON;         // "CON 25 0.04 0.001"
    private String PHS;         // "PHS './input.arc'"
    private String LOC;
    
    private String  MODEL_Name;
    private Float  MODEL_H0;
    private Float  MODEL_V0;
    private Float  MODEL_H1;
    private Float  MODEL_V1;
    private Float  MODEL_H2;
    private Float  MODEL_V2;
    

//------------------------------------------------------------------------------    
    public Hypo2000_configuration(String default_settings_file, String model_file){
        if (!this.Read_default_settings(default_settings_file)){
            pfxDialog.ShowErrorMessage("Unable to read Hypo2000 settings file.", null);
        } else {
            if (!this.Read_model_settings(model_file)) {
                pfxDialog.ShowErrorMessage("Unable to read Hypo2000 velocity model file.", null);
            }
        }
    }
    
    
    /**
     * @return the MODEL_Name
     */
    public String getMODEL_Name() {
        return MODEL_Name;
    }

    /**
     * @param MODEL_Name the MODEL_Name to set
     */
    public void setMODEL_Name(String MODEL_Name) {
        this.MODEL_Name = MODEL_Name;
    }

    /**
     * @return the MODEL_H0
     */
    public Float getMODEL_H0() {
        return MODEL_H0;
    }

    /**
     * @param MODEL_H0 the MODEL_H0 to set
     */
    public void setMODEL_H0(Float MODEL_H0) {
        this.MODEL_H0 = MODEL_H0;
    }

    /**
     * @return the MODEL_V0
     */
    public Float getMODEL_V0() {
        return MODEL_V0;
    }

    /**
     * @param MODEL_V0 the MODEL_V0 to set
     */
    public void setMODEL_V0(Float MODEL_V0) {
        this.MODEL_V0 = MODEL_V0;
    }

    /**
     * @return the MODEL_H1
     */
    public Float getMODEL_H1() {
        return MODEL_H1;
    }

    /**
     * @param MODEL_H1 the MODEL_H1 to set
     */
    public void setMODEL_H1(Float MODEL_H1) {
        this.MODEL_H1 = MODEL_H1;
    }

    /**
     * @return the MODEL_V1
     */
    public Float getMODEL_V1() {
        return MODEL_V1;
    }

    /**
     * @param MODEL_V1 the MODEL_V1 to set
     */
    public void setMODEL_V1(Float MODEL_V1) {
        this.MODEL_V1 = MODEL_V1;
    }

    /**
     * @return the MODEL_H2
     */
    public Float getMODEL_H2() {
        return MODEL_H2;
    }

    /**
     * @param MODEL_H2 the MODEL_H2 to set
     */
    public void setMODEL_H2(Float MODEL_H2) {
        this.MODEL_H2 = MODEL_H2;
    }

    /**
     * @return the MODEL_V2
     */
    public Float getMODEL_V2() {
        return MODEL_V2;
    }

    /**
     * @param MODEL_V2 the MODEL_V2 to set
     */
    public void setMODEL_V2(Float MODEL_V2) {
        this.MODEL_V2 = MODEL_V2;
    }
//------------------------------------------------------------------------------
    private boolean Read_model_settings(String model_settings_file){
        BufferedReader b=null;
        try{
            File f = new File(model_settings_file);

            b = new BufferedReader(new FileReader(f));

            String readLine = "";
            setMODEL_Name(b.readLine());
            readLine = b.readLine();
            setMODEL_H0(Float.valueOf(readLine.substring(0, readLine.indexOf(" ")+1)));
            setMODEL_V0(Float.valueOf(readLine.substring(readLine.indexOf(" ")+1)));
            
            readLine = b.readLine();
            setMODEL_H1(Float.valueOf(readLine.substring(0, readLine.indexOf(" ")+1)));
            setMODEL_V1(Float.valueOf(readLine.substring(readLine.indexOf(" ")+1)));
            
            readLine = b.readLine();
            setMODEL_H2(Float.valueOf(readLine.substring(0, readLine.indexOf(" ")+1)));
            setMODEL_V2(Float.valueOf(readLine.substring(readLine.indexOf(" ")+1)));
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                b.close();
            } catch (IOException ex) {
                //Logger.getLogger(Hypo2000_configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//------------------------------------------------------------------------------    
    private boolean Read_default_settings(String default_settings_file){
        BufferedReader b=null;
        try {
            File f = new File(default_settings_file);

            b = new BufferedReader(new FileReader(f));

            String readLine = "";
            App.logger.debug("Reading Hypo2000 settings file: " + default_settings_file);
            
            readLine = b.readLine();
            if (readLine.startsWith("200")) this.basic_item = readLine;
            
            String start="";
            while ((readLine = b.readLine()) != null) {
                start = readLine.substring(0,3);
                switch(start) {
                    case "LET":
                        this.LET = readLine.substring(4);
                    break;
                    case "H71":
                        this.H71 = readLine.substring(4);
                    break;
                    case "STA":
                        this.STA  = readLine.substring(4);
                    break;
                    case "CRH":
                        this.CRH = readLine.substring(4);
                    break;
                    case "MAG":
                        this.MAG = readLine.substring(4);
                    break;
                    case "DUR":
                        this.DUR = readLine.substring(4);
                    break;
                    
                    case "FC1":
                        this.FC1 = readLine.substring(4);
                    break;
                    case "PRE":
                        this.PRE = readLine.substring(4);
                    break;
                    case "RMS":
                        this.RMS = readLine.substring(4);
                    break;
                    case "ERR":
                        this.ERR = readLine.substring(4);
                    break;
                    case "POS":
                        this.POS = readLine.substring(4);
                    break;
                    case "REP":
                        this.REP = readLine.substring(4);
                    break;
                    case "JUN":
                        this.JUN = readLine.substring(4);
                    break;
                    case "MIN":
                        this.MIN = readLine.substring(4);
                    break;
                    case "NET":
                        this.NET = readLine.substring(4);
                    break;
                    case "ZTR":
                        this.ZTR = readLine.substring(4);
                        if (ZTR.contains("-"))
                            this.setFixDepth(true);
                        else this.setFixDepth(false);
                        if (ZTR.contains("T"))
                            this.setFixDepth(true);
                        else this.setFixDepth(false);
                        
                        this.ZTR = this.ZTR.replace("T", "").replace("F", "").replace("-", "");
                    break;
                    case "DIS":
                        this.DIS = readLine.substring(4);
                    break;
                    case "DAM":
                        this.DAM = readLine.substring(4);
                    break;
                    case "WET":
                        this.WET = readLine.substring(4);
                    break;
                    case "ERF":
                        this.ERF = readLine.substring(4);
                    break;
                    case "TOP":
                        this.TOP = readLine.substring(4);
                    break;        
                    case "LST":
                        this.LST = readLine.substring(4);
                    break;
                    case "KPR":
                        this.KPR = readLine.substring(4);
                    break;
                    case "COP":
                        this.COP = readLine.substring(4);
                    break;
                    case "CAR":
                        this.CAR = readLine.substring(4);
                    break;
                    case "PRT":
                        this.PRT = readLine.substring(4);
                    break;
                    case "SUM":
                        this.SUM = readLine.substring(4);
                    break;
                    case "ARC":
                        this.ARC = readLine.substring(4);
                    break;
                    case "APP":
                        this.APP = readLine.substring(4);
                    break;
                    case "CON":
                        this.CON = readLine.substring(4);
                    break;
                    case "PHS":
                        this.PHS = readLine.substring(4);
                    break;
                    case "LOC":
                        this.LOC = "LOC";
                    break;
                   
                    }
                }
 
            App.logger.debug("DONE!!");
            
            return true;
        } catch (Exception ex) {
            App.logger.error(ex.getMessage());
            return false;
        } finally {
            try {
                b.close();
            } catch (IOException ex) {
                //Logger.getLogger(Hypo2000_configuration.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }
//------------------------------------------------------------------------------        
    /**
     * @return the basic_item
     */
    public String getBasic_item() {
        return basic_item;
    }

    /**
     * @param basic_item the basic_item to set
     */
    public void setBasic_item(String basic_item) {
        this.basic_item = basic_item;
    }

    /**
     * @return the LET
     */
    public String getLET() {
        return LET;
    }

    /**
     * @param LET the LET to set
     */
    public void setLET(String LET) {
        this.LET = LET;
    }

    /**
     * @return the H71
     */
    public String getH71() {
        return H71;
    }

    /**
     * @param H71 the H71 to set
     */
    public void setH71(String H71) {
        this.H71 = H71;
    }

    /**
     * @return the STA
     */
    public String getSTA() {
        return STA;
    }

    /**
     * @param STA the STA to set
     */
    public void setSTA(String STA) {
        this.STA = STA;
    }

    /**
     * @return the CRH
     */
    public String getCRH() {
        return CRH;
    }

    /**
     * @param CRH the CRH to set
     */
    public void setCRH(String CRH) {
        this.CRH = CRH;
    }

    /**
     * @return the MAG
     */
    public String getMAG() {
        return MAG;
    }

    /**
     * @param MAG the MAG to set
     */
    public void setMAG(String MAG) {
        this.MAG = MAG;
    }

    /**
     * @return the DUR
     */
    public String getDUR() {
        return DUR;
    }

    /**
     * @param DUR the DUR to set
     */
    public void setDUR(String DUR) {
        this.DUR = DUR;
    }

    /**
     * @return the FC1
     */
    public String getFC1() {
        return FC1;
    }

    /**
     * @param FC1 the FC1 to set
     */
    public void setFC1(String FC1) {
        this.FC1 = FC1;
    }

    /**
     * @return the PRE
     */
    public String getPRE() {
        return PRE;
    }

    /**
     * @param PRE the PRE to set
     */
    public void setPRE(String PRE) {
        this.PRE = PRE;
    }

    /**
     * @return the RMS
     */
    public String getRMS() {
        return RMS;
    }

    /**
     * @param RMS the RMS to set
     */
    public void setRMS(String RMS) {
        this.RMS = RMS;
    }

    /**
     * @return the ERR
     */
    public String getERR() {
        return ERR;
    }

    /**
     * @param ERR the ERR to set
     */
    public void setERR(String ERR) {
        this.ERR = ERR;
    }

    /**
     * @return the POS
     */
    public String getPOS() {
        return POS;
    }

    /**
     * @param POS the POS to set
     */
    public void setPOS(String POS) {
        this.POS = POS;
    }

    /**
     * @return the REP
     */
    public String getREP() {
        return REP;
    }

    /**
     * @param REP the REP to set
     */
    public void setREP(String REP) {
        this.REP = REP;
    }

    /**
     * @return the JUN
     */
    public String getJUN() {
        return JUN;
    }

    /**
     * @param JUN the JUN to set
     */
    public void setJUN(String JUN) {
        this.JUN = JUN;
    }

    /**
     * @return the MIN
     */
    public String getMIN() {
        return MIN;
    }

    /**
     * @param MIN the MIN to set
     */
    public void setMIN(String MIN) {
        this.MIN = MIN;
    }

    /**
     * @return the NET
     */
    public String getNET() {
        return NET;
    }

    /**
     * @param NET the NET to set
     */
    public void setNET(String NET) {
        this.NET = NET;
    }

    /**
     * @return the ZTR
     */
    public String getZTR() {
        if (this.FixDepth) 
            return ZTR + " T";
        else
            return ZTR + " F";
    }

    /**
     * @param ZTR the ZTR to set
     */
    public void setZTR(String ztr) {
        //if (this.FixDepth)
//            this.ZTR = ztr + " T";
//        else
            this.ZTR = ztr;
//        if (!this.FixDepth)
//            this.ZTR = ZTR + " T";
//        else
//            this.ZTR = "-" + ZTR + " T";
    }

    /**
     * @return the DIS
     */
    public String getDIS() {
        return DIS;
    }

    /**
     * @param DIS the DIS to set
     */
    public void setDIS(String DIS) {
        this.DIS = DIS;
    }

    /**
     * @return the DAM
     */
    public String getDAM() {
        return DAM;
    }

    /**
     * @param DAM the DAM to set
     */
    public void setDAM(String DAM) {
        this.DAM = DAM;
    }

    /**
     * @return the WET
     */
    public String getWET() {
        return WET;
    }

    /**
     * @param WET the WET to set
     */
    public void setWET(String WET) {
        this.WET = WET;
    }

    /**
     * @return the ERF
     */
    public String getERF() {
        return ERF;
    }

    /**
     * @param ERF the ERF to set
     */
    public void setERF(String ERF) {
        this.ERF = ERF;
    }

    /**
     * @return the TOP
     */
    public String getTOP() {
        return TOP;
    }

    /**
     * @param TOP the TOP to set
     */
    public void setTOP(String TOP) {
        this.TOP = TOP;
    }

    /**
     * @return the LST
     */
    public String getLST() {
        return LST;
    }

    /**
     * @param LST the LST to set
     */
    public void setLST(String LST) {
        this.LST = LST;
    }

    /**
     * @return the KPR
     */
    public String getKPR() {
        return KPR;
    }

    /**
     * @param KPR the KPR to set
     */
    public void setKPR(String KPR) {
        this.KPR = KPR;
    }

    /**
     * @return the COP
     */
    public String getCOP() {
        return COP;
    }

    /**
     * @param COP the COP to set
     */
    public void setCOP(String COP) {
        this.COP = COP;
    }

    /**
     * @return the CAR
     */
    public String getCAR() {
        return CAR;
    }

    /**
     * @param CAR the CAR to set
     */
    public void setCAR(String CAR) {
        this.CAR = CAR;
    }

    /**
     * @return the PRT
     */
    public String getPRT() {
        return PRT;
    }

    /**
     * @param PRT the PRT to set
     */
    public void setPRT(String PRT) {
        this.PRT = PRT;
    }

    /**
     * @return the SUM
     */
    public String getSUM() {
        return SUM;
    }

    /**
     * @param SUM the SUM to set
     */
    public void setSUM(String SUM) {
        this.SUM = SUM;
    }

    /**
     * @return the ARC
     */
    public String getARC() {
        return ARC;
    }

    /**
     * @param ARC the ARC to set
     */
    public void setARC(String ARC) {
        this.ARC = ARC;
    }

    /**
     * @return the APP
     */
    public String getAPP() {
        return APP;
    }

    /**
     * @param APP the APP to set
     */
    public void setAPP(String APP) {
        this.APP = APP;
    }

    /**
     * @return the CON
     */
    public String getCON() {
        return CON;
    }

    /**
     * @param CON the CON to set
     */
    public void setCON(String CON) {
        this.CON = CON;
    }

    /**
     * @return the PHS
     */
    public String getPHS() {
        return PHS;
    }

    /**
     * @param PHS the PHS to set
     */
    public void setPHS(String PHS) {
        this.PHS = PHS;
    }

    /**
     * @return the LOC
     */
    public String getLOC() {
        return LOC;
    }

    /**
     * @param LOC the LOC to set
     */
    public void setLOC(String LOC) {
        this.LOC = LOC;
    }
             // "LOC"

    /**
     * @return the FixDepth
     */
    public boolean isFixDepth() {
        return FixDepth;
    }

    /**
     * @param FixDepth the FixDepth to set
     */
    public void setFixDepth(boolean FixDepth) {
        this.FixDepth = FixDepth;
    }
    
    
    

    
}
