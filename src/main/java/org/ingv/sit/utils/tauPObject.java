
package org.ingv.sit.utils;

import java.util.ArrayList;



/**
 *
 * @author andreabono
 */
public class tauPObject {
    public class tauParrival{

        /**
         * @return the distdeg
         */
        public double getDistdeg() {
            return distdeg;
        }

        /**
         * @param distdeg the distdeg to set
         */
        public void setDistdeg(double distdeg) {
            this.distdeg = distdeg;
        }

        /**
         * @return the phase
         */
        public String getPhase() {
            return phase;
        }

        /**
         * @param phase the phase to set
         */
        public void setPhase(String phase) {
            this.phase = phase;
        }

        /**
         * @return the time
         */
        public Double getTime() {
            return time;
        }

        /**
         * @param time the time to set
         */
        public void setTime(Double time) {
            this.time = time;
        }

        /**
         * @return the rayparam
         */
        public Double getRayparam() {
            return rayparam;
        }

        /**
         * @param rayparam the rayparam to set
         */
        public void setRayparam(Double rayparam) {
            this.rayparam = rayparam;
        }

        /**
         * @return the takeoff
         */
        public Double getTakeoff() {
            return takeoff;
        }

        /**
         * @param takeoff the takeoff to set
         */
        public void setTakeoff(Double takeoff) {
            this.takeoff = takeoff;
        }

        /**
         * @return the incident
         */
        public Double getIncident() {
            return incident;
        }

        /**
         * @param incident the incident to set
         */
        public void setIncident(Double incident) {
            this.incident = incident;
        }

        /**
         * @return the puristdist
         */
        public Double getPuristdist() {
            return puristdist;
        }

        /**
         * @param puristdist the puristdist to set
         */
        public void setPuristdist(Double puristdist) {
            this.puristdist = puristdist;
        }

        /**
         * @return the puristname
         */
        public String getPuristname() {
            return puristname;
        }

        /**
         * @param puristname the puristname to set
         */
        public void setPuristname(String puristname) {
            this.puristname = puristname;
        }
        private double distdeg;
        private String phase;
        private Double time;
        private Double rayparam;
        private Double takeoff;
        private Double incident;
        private Double puristdist;
        private String puristname;
    }
    
    
    private String model;
    private Double sourceDepth;
    private Double receiverDepth;
    ArrayList<String> phases = new ArrayList();
    private ArrayList<tauParrival> arrivals = new ArrayList();

    /**
     * @return the arrivals
     */
    public ArrayList<tauParrival> getArrivals() {
        return arrivals;
    }

    /**
     * @param arrivals the arrivals to set
     */
    public void setArrivals(ArrayList<tauParrival> arrivals) {
        this.arrivals = arrivals;
    }
}
