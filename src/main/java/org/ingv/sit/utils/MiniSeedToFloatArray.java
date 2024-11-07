
package org.ingv.sit.utils;

import edu.iris.dmc.seedcodec.CodecException;
import edu.iris.dmc.seedcodec.DecompressedData;
import edu.iris.dmc.seedcodec.UnsupportedCompressionType;
import edu.sc.seis.seisFile.mseed.DataRecord;
import edu.sc.seis.seisFile.mseed.SeedFormatException;
import edu.sc.seis.seisFile.mseed.SeedRecord;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MiniSeedToFloatArray {

   public ArrayList<List<DataRecord>> readFile_manyWaves(String filename) throws IOException, SeedFormatException {
        ArrayList<List<DataRecord>> out = new ArrayList<List<DataRecord>>();
        List<DataRecord> drList = new ArrayList<DataRecord>();
        DataInput dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
//
        String STA_CHA_NET="", tmpSTA_CHA_NET;
        int counter = 0;
        try {
            while (true) {
                SeedRecord sr = SeedRecord.read(dis, 4096);
                //sr.writeASCII(out);
                if (sr instanceof DataRecord) {   
                    DataRecord dr = (DataRecord)sr;
                    tmpSTA_CHA_NET = dr.getHeader().getStationIdentifier() + dr.getHeader().getChannelIdentifier() +
                            dr.getHeader().getNetworkCode();
                    if (!(tmpSTA_CHA_NET.equalsIgnoreCase(STA_CHA_NET))) {
                        // Abbiamo cambiato segnale----> nuova forma d'onda!!
                        counter +=1; 
                        STA_CHA_NET = tmpSTA_CHA_NET; 
                        //                 
                        if (drList.size()>0) {
                            // Aggiunge il segnale precedente all'arraylist di output...
                            out.add(drList);
                        }
                        //... e passa ad inizializzare il segnale successivo
                        drList = new ArrayList<>();
                    }
                    // aggiunge il data record alla lista
                    drList.add(dr);
                }
            } 
        } catch(EOFException e) {
            if (!drList.isEmpty()) out.add(drList);
        } finally {
            try {
                ((DataInputStream)dis).close();
            } catch (Exception ex) {}
        }
        
        return out;
    }

    
    
    public ArrayList<List<DataRecord>> readFile_singleWave(String filename) throws IOException, SeedFormatException {
        ArrayList<List<DataRecord>> out = new ArrayList<List<DataRecord>>();
        List<DataRecord> drList = new ArrayList<DataRecord>();
        DataInput dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
//  
        try {
            drList = new ArrayList<>();
            while (true) {
            SeedRecord sr = SeedRecord.read(dis, 4096);
                if (sr instanceof DataRecord) {   
                    DataRecord dr = (DataRecord)sr; 
                    drList.add(dr);
                }                 
          }  
        } catch(EOFException e) {
            //System.out.println (e.getMessage());
            //Logger.getLogger("org.ingv.pfx").log(java.util.logging.Level.SEVERE,e.getMessage());
        } finally {
            try {
                ((DataInputStream)dis).close();
            } catch (Exception ex) {}
        }
//        
        if (!drList.isEmpty()) {
            out.add(drList);
        }
//                
        return out;
        
    }

    
    public float[] extract(List<DataRecord> drList) throws UnsupportedCompressionType, CodecException,
            SeedFormatException {
//
        ArrayList<Float> realsamples = new ArrayList<>();

        try {
            Float lgs=Float.valueOf("0");
            DataRecord prevRecord = null;
            int packetduration;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,DDD,HH:mm:ss.SSSS");
            for (DataRecord currentRecord : drList) {
                Instant currentEndTime = LocalDateTime.parse(currentRecord.getEndTime(), formatter).toInstant(ZoneOffset.UTC);
                if (prevRecord != null) {
                    packetduration = currentRecord.getHeader().getNumSamples() / Math.abs(currentRecord.getHeader().getSampleRateFactor()/currentRecord.getHeader().getSampleRateMultiplier());
                    Instant prevEndTime = LocalDateTime.parse(prevRecord.getEndTime(), formatter).toInstant(ZoneOffset.UTC);
                    if (ChronoUnit.SECONDS.between(currentEndTime, prevEndTime.plusSeconds(packetduration)) < 0) {
                        //-----------------------------------
                        // There's a GAP in the time series
                        //-----------------------------------
                        long differenza =ChronoUnit.SECONDS.between(currentEndTime, prevEndTime.plusSeconds(packetduration));
                        long missingSamples = Math.abs(differenza) * Math.abs(currentRecord.getHeader().getSampleRateFactor()/currentRecord.getHeader().getSampleRateMultiplier());
                        for (int i =0; i< missingSamples; i++) realsamples.add(Float.valueOf(lgs));
                    }
                }

                //--------------------------------------------
                // Reading good samples from a good blockette
                //--------------------------------------------
                DecompressedData decompData = currentRecord.decompress();
                float[] temp = decompData.getAsFloat();
                for (int i =0; i< temp.length; i++) realsamples.add((float)temp[i]);
                lgs=temp[temp.length-1];
                prevRecord = currentRecord;
            }
            
            if (!realsamples.isEmpty()){
                //--------------------------------------------
                // ArrayList<Float> ----> float[]
                //--------------------------------------------
                float[] data = new float[realsamples.size()];
                for (int i = 0; i < realsamples.size(); i++) {
                    data[i] = realsamples.get(i);
                }

                return data;
            } else return null;
            
        } catch (Exception ex) {
            
            return null;
        }
        
    }

    public ArrayList<List<DataRecord>> readseedfile(String args, boolean single_wave) { //throws SeedFormatException, IOException, UnsupportedCompressionType, CodecException {
        try {
            MiniSeedToFloatArray msfa = new MiniSeedToFloatArray();
            
            if (!single_wave) {
                return msfa.readFile_manyWaves(args);
            } else {
                return msfa.readFile_singleWave(args);
            }
           
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx").log(Level.SEVERE, 
                             ex.getMessage());
            return null;
        }
     
    }
    }  
