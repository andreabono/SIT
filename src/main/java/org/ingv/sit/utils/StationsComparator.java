
package org.ingv.sit.utils;

import java.util.Comparator;
import java.util.Objects;
import org.ingv.sit.datamodel.Station;

/**
 *
 * @author andreabono
 */
public class StationsComparator implements Comparator<Station>{

    @Override
    public int compare(Station o1, Station o2) {
        if ((o1.getPhases()==null) || (o2.getPhases()==null)) return 0;
        if ((o1.getPhases().isEmpty()) || (o2.getPhases().isEmpty())) return 0;
        
        if (Objects.equals(o1.getPhase(0).getEpDistanceKm(), o2.getPhase(0).getEpDistanceKm()))
            return 0;
        else if (o1.getPhase(0).getEpDistanceKm()>o2.getPhase(0).getEpDistanceKm())
            return 1;
        else
            return -1;
    }
    
}
