package org.fedon.wipro.service.processor;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;

/**
 * @author Dmytro Fedonin
 *
 */
public class PeriodMeanProcessor implements Processor<BigDecimal> {
    // inclusive
    private LocalDate periodStartDate;
    // inclusive
    private LocalDate periodEndDate;

    /**
     * @param periodStartDate
     * @param periodEndDate
     */
    public PeriodMeanProcessor(LocalDate periodStartDate, LocalDate periodEndDate) {
        super();
        this.periodStartDate = periodStartDate;
        this.periodEndDate = periodEndDate;
    }

    @Override
    public void process(InstrumentRecord record, InstrumentStat stat) {
        if (record.getDate().isBefore(periodStartDate) || record.getDate().isAfter(periodEndDate)) {
            return;
        }
        stat.setNumberOfRecords(stat.getNumberOfRecords() + 1);
        stat.getValues().add(record.getValue());
    }

    /**
     * Calculate mean of the InstrumentStat values.
     * 
     * @param stat
     * @return sum/numberOfRecords of the InstrumentStat
     */
    @Override
    public BigDecimal calculate(InstrumentStat stat) {
        stat.getValues().sort((o1, o2) -> o1.compareTo(o2));
        return stat.getValues().get(stat.getNumberOfRecords() / 2);
    }

    @Override
    public String action() {
        return "mean from " + periodStartDate + " to " + periodEndDate;
    }
}
