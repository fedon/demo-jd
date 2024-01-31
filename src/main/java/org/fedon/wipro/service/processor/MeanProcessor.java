package org.fedon.wipro.service.processor;

import java.math.BigDecimal;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro Fedonin
 *
 */
@Component
public class MeanProcessor {

    public void process(InstrumentRecord record, InstrumentStat stat) {
        stat.setNumberOfRecords(stat.getNumberOfRecords() + 1);
        // TODO add value to a list
    }

    /**
     * Calculate average of the stat with Sum scale rounded up.
     * 
     * @param stat
     * @return sum/numberOfRecords of the InstrumentStat
     */
    public BigDecimal calculate(InstrumentStat stat) {
        // TODO implement
        return null;
    }
}
