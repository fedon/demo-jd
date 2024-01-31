package org.fedon.wipro.service.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;

/**
 * @author Dmytro Fedonin
 *
 */
public class MovingSumProcessor implements Processor<BigDecimal> {

    // Number of the last records to calculate
    private int number;

    public MovingSumProcessor(int movingNumber) {
        number = movingNumber;
    }

    public void process(InstrumentRecord record, InstrumentStat stat) {
        if (stat.getLastRecords() == null) {
            stat.setLastRecords(new ArrayList<>());
            stat.getLastRecords().add(record);
            return;
        }
        List<InstrumentRecord> list = stat.getLastRecords();
        if (list.size() < number) {
            list.add(record);
        } else if (list.get(0).getDate().isBefore(record.getDate())) {
            list.add(record);
        }
        if (list.size() > 2 * number) {
            list.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));

            stat.setLastRecords(copyLast(number, list));
        }
    }

    private List<InstrumentRecord> copyLast(int number, List<InstrumentRecord> list) {
        List<InstrumentRecord> newList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            newList.add(list.get(i + list.size() - number));
        }
        return newList;
    }

    /**
     * Calculate average of the InstrumentStat with Sum scale rounded up.
     * 
     * @param stat
     * @return sum/numberOfRecords of the InstrumentStat
     */
    public BigDecimal calculate(InstrumentStat stat) {
        stat.getLastRecords().sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
        BigDecimal result = BigDecimal.ZERO;
        for (InstrumentRecord record : copyLast(10, stat.getLastRecords())) {
            result = result.add(record.getValue());
        }
        return result;
    }

    @Override
    public String action() {
        return "moving sum of " + number + " last values";
    }
}
