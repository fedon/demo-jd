package org.fedon.wipro.service;

import java.util.HashMap;
import java.util.Map;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;
import org.fedon.wipro.service.processor.AverageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dmytro Fedonin
 *
 */
@Service
public class EnginService {

    @Autowired
    private DateService dateService;

    @Autowired
    private AverageProcessor processor;

    private Map<String, InstrumentStat> resultMap = new HashMap<>();

    public void process(InstrumentRecord record) {
        if (dateService.isValidBusinessDay(record.getDate())) {
            InstrumentStat result = resultMap.get(record.getInstrument());
            if (result == null) {
                result = new InstrumentStat();
                resultMap.put(record.getInstrument(), result);
            }
            processor.process(record, result);
        }
    }

    public void publishResult() {
        resultMap.entrySet().forEach(i -> System.out.println(i.getKey() + " average: " + processor.calculate(i.getValue())));
    }
}
