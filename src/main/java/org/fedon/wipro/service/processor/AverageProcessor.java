package org.fedon.wipro.service.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro Fedonin
 *
 */
@Component
public class AverageProcessor {

    public void process(InstrumentRecord record, InstrumentStat stat) {
        stat.setNumberOfRecords(stat.getNumberOfRecords() + 1);
        stat.setSum(stat.getSum().add(record.getValue()));
    }

    /**
     * Calculate average of the stat with Sum scale rounded up.
     * 
     * @param stat
     * @return sum/numberOfRecords of the InstrumentStat
     */
    public BigDecimal calculate(InstrumentStat stat) {
        return stat.getSum().divide(BigDecimal.valueOf(stat.getNumberOfRecords()), RoundingMode.HALF_UP);
    }
}
