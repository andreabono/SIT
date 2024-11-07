/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Rome
 * ITALY
 *
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */
package org.ingv.sit.utils;

/**
 *
 * @author andreabono
 */
public class duration_buffer_item {
    public String station_code;
    public float duration;

   
    /**
     * @return the duration
     */
    public float getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * @return the station_code
     */
    public String getStation_code() {
        return station_code;
    }

    /**
     * @param station_code the station_code to set
     */
    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }
    
}
