
package org.ingv.sit.fdsn;

import edu.sc.seis.seisFile.fdsnws.quakeml.Arrival;
import edu.sc.seis.seisFile.fdsnws.quakeml.Pick;
import java.util.List;



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
 */
public class QuakeML_Utils {
    private edu.sc.seis.seisFile.fdsnws.quakeml.Event myQMLEvent ;
//--------------------------------------------------------------------------------    
    public QuakeML_Utils(edu.sc.seis.seisFile.fdsnws.quakeml.Event in_QML_event) {
        this.myQMLEvent=in_QML_event;
    }
    
    public Pick Arrival_2_Pick(Arrival in_arrival) {
        try {
            if (this.myQMLEvent==null) return null;
            List<Pick> picks = myQMLEvent.getPickList();
            if (picks == null) return null;
            //
            for (Pick tmpPk : picks) { 
                if (tmpPk.getPublicId().equalsIgnoreCase(in_arrival.getPickID())) {
                    return tmpPk;
                }
            }
            //
            return null;
        } catch (Exception ex) {
            
            return null;
        }
    }
}
