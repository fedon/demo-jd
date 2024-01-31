package org.fedon.wipro.service.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;
import org.junit.jupiter.api.Test;

/**
 * @author Dmytro Fedonin
 *
 */
public class AverageProcessorTest {

    @Test
    public void process() {
        AverageProcessor processor = new AverageProcessor();
        InstrumentRecord record = new InstrumentRecord();
        record.setInstrument("test1");
        record.setDate(LocalDate.now());
        record.setValue(BigDecimal.ONE);
        InstrumentStat stat = new InstrumentStat();
        processor.process(record, stat);

        assertEquals(BigDecimal.ONE, stat.getSum());
        assertEquals(1, stat.getNumberOfRecords());
    }
}
