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
package org.ingv.sit.datamodel;

import java.time.LocalDateTime;


public class Waveform {
    private String StationCode;
    private String ChannelCode;
    private String NetworkCode;
    private String LocationCode;
    private float SamplingRate;
    private long nSamples;
    private float[] X;
    public float[] X_backup;
    private float[] Y;
    private float[] Y_backup;
    private float[] Y_original;
    private LocalDateTime StartTime;
    private LocalDateTime StartTime_Box;
    private LocalDateTime EndTime;
    private LocalDateTime EndTime_Box;
    private String filename;
    private int plot_box_id_WA=-1;
    private int plot_box_id=-1;
    public int filters=0;
    public String DataProvider; // in [EW,FDSN, LOCALHOST] e SL?
    
    private Signal Signal;
    
    
    public void UpdateYBounds(){
        Signal.ymin = Signal.findMin(Y);
        Signal.ymax = Signal.findMax(Y);
    }
    /**
     * @return the SamplingRate
     */
    public float getSamplingRate() {
        return SamplingRate;
    }

    /**
     * @param SamplingRate the SamplingRate to set
     */
    public void setSamplingRate(float SamplingRate) {
        this.SamplingRate = SamplingRate;
    }

    /**
     * @return the ChannelCode
     */
    public String getChannelCode() {
        return ChannelCode;
    }

    /**
     * @param ChannelCode the ChannelCode to set
     */
    public void setChannelCode(String ChannelCode) {
        this.ChannelCode = ChannelCode;
    }

    /**
     * @return the nSamples
     */
    public long getnSamples() {
        return nSamples;
    }

    /**
     * @param nSamples the nSamples to set
     */
    public void setnSamples(long nSamples) {
        this.nSamples = nSamples;
    }

    /**
     * @return the X
     */
    public float[] getX() {
        return X;
    }
    public float getX(int i) {
        return X[i];
    }

    /**
     * @param X the X to set
     */
    public void setX(float[] X) {
        this.X = X;
    }
    public void setX(int index, float value) {
        this.X[index] = value;
    }

    /**
     * @return the Y
     */
    public float[] getY() {
        return Y;
    }
    public float getY(int i) {
        return Y[i];
    }

    /**
     * @param Y the Y to set
     */
    public void setY(float[] Y) {
        this.Y = Y;
    }

    /**
     * @return the StartTime
     */
    public LocalDateTime getStartTime() {
        return StartTime;
    }

    /**
     * @param StartTime the StartTime to set
     */
    public void setStartTime(LocalDateTime StartTime) {
        this.StartTime = StartTime;
    }

//    /**
//     * @return the EndTime
//     */
//    public LocalDateTime getEndTime() {
//        return EndTime;
//    }
//
//    /**
//     * @param EndTime the EndTime to set
//     */
//    public void setEndTime(LocalDateTime EndTime) {
//        this.EndTime = EndTime;
//    }

    /**
     * @return the StationCode
     */
    public String getStationCode() {
        return StationCode.trim();
    }

    /**
     * @param StationCode the StationCode to set
     */
    public void setStationCode(String StationCode) {
        this.StationCode = StationCode.trim();
    }

    /**
     * @return the NetworkCode
     */
    public String getNetworkCode() {
        return NetworkCode;
    }

    /**
     * @param NetworkCode the NetworkCode to set
     */
    public void setNetworkCode(String NetworkCode) {
        this.NetworkCode = NetworkCode;
    }

    /**
     * @return the LocationCode
     */
    public String getLocationCode() {
        return LocationCode;
    }

    /**
     * @param LocationCode the LocationCode to set
     */
    public void setLocationCode(String inLocationCode) {
        this.LocationCode = inLocationCode;
    }
    
//------------------------------------------------------------------------------    
    public void Backup_Samples(){
//        this.X_backup=this.X;
//        this.Y_backup = this.Y;
        
    //   System.arraycopy(X, 0, X_backup, 0, X.length);
        Y_backup = new float[Y.length];
        System.arraycopy(Y, 0, Y_backup, 0, Y.length);
        
        if (Y_original == null) {
            Y_original = new float[Y.length];
            System.arraycopy(Y, 0, Y_original, 0, Y.length);
        }
    }
//------------------------------------------------------------------------------        
    public void Restore_Samples(){
//        this.X = this.X_backup;
//        this.Y = this.Y_backup;
//        
   //     System.arraycopy(X_backup, 0, X, 0, X.length);
        Y = new float[Y_backup.length];
        System.arraycopy(Y_backup, 0, Y, 0, Y.length);
    }
//------------------------------------------------------------------------------        
    public void Restore_Original_Samples(){
//        this.X = this.X_backup;
//        this.Y = this.Y_backup;
//        
   //     System.arraycopy(X_backup, 0, X, 0, X.length);
        Y = new float[Y_original.length];
        System.arraycopy(Y_original, 0, Y, 0, Y.length);
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the plot_box_id_WA
     */
    public int getPlot_box_id_WA() {
        return plot_box_id_WA;
    }

    /**
     * @param WA_plot_box_id the plot_box_id_WA to set
     */
    public void setPlot_box_id_WA(int in_plot_box_id_WA) {
        this.plot_box_id_WA = in_plot_box_id_WA;
    }

    /**
     * @return the X_backup
     */
    public float[] getY_backup() {
        return Y_backup;
    }

    /**
     * @param X_backup the X_backup to set
     */
    public void setY_backup(float[] Y_back) {
        this.Y_backup = Y_back;
    }

    /**
     * @return the filters
     */
    public int getFilters() {
        return filters;
    }

    /**
     * @param filters the filters to set
     */
    public void setFilters(int filters) {
        this.filters = filters;
    }

    /**
     * @return the DataProvider
     */
    public String getDataProvider() {
        return DataProvider;
    }

    /**
     * @param DataProvider the DataProvider to set
     */
    public void setDataProvider(String DataProvider) {
        this.DataProvider = DataProvider;
    }

    /**
     * @return the plot_box_id
     */
    public int getPlot_box_id() {
        return plot_box_id;
    }

    /**
     * @param plot_box_id the plot_box_id to set
     */
    public void setPlot_box_id(int plot_box_id) {
        this.plot_box_id = plot_box_id;
    }

    /**
     * @return the StartTime_Box
     */
    public LocalDateTime getStartTime_Box() {
        return StartTime_Box;
    }

    /**
     * @param StartTime_Box the StartTime_Box to set
     */
    public void setStartTime_Box(LocalDateTime StartTime_Box) {
        this.StartTime_Box = StartTime_Box;
    }

    /**
     * @return the EndTime_Box
     */
    public LocalDateTime getEndTime_Box() {
        return EndTime_Box;
    }

    /**
     * @param EndTime_Box the EndTime_Box to set
     */
    public void setEndTime_Box(LocalDateTime EndTime_Box) {
        this.EndTime_Box = EndTime_Box;
    }

    /**
     * @return the EndTime
     */
    public LocalDateTime getEndTime() {
        return EndTime;
    }

    /**
     * @param EndTime the EndTime to set
     */
    public void setEndTime(LocalDateTime EndTime) {
        this.EndTime = EndTime;
    }

    /**
     * @return the Signal
     */
    public Signal getSignal() {
        return Signal;
    }

    /**
     * @param Signal the Signal to set
     */
    public void setSignal(Signal Signal) {
        this.Signal = Signal;
    }

}
