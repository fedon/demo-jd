package org.fedon.wipro.service.processor;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;

/**
 * @author Dmytro Fedonin
 *
 */
public interface Processor<T> {

    public void process(InstrumentRecord record, InstrumentStat stat);

    public T calculate(InstrumentStat stat);

    public String action();
}
