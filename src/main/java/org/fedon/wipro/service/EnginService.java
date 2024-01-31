package org.fedon.wipro.service;

import java.util.HashMap;
import java.util.Map;

import org.fedon.wipro.model.InstrumentRecord;
import org.fedon.wipro.model.InstrumentStat;
import org.fedon.wipro.service.processor.Processor;
import org.fedon.wipro.service.processor.ProcessorConfig;
import org.fedon.wipro.service.processor.ProcessorMap;
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
    public ProcessorMap processorMap = ProcessorConfig.configProcessors();


    private Map<String, InstrumentStat> resultMap = new HashMap<>();

    public void process(InstrumentRecord record) {
        Processor<?> processor = processorMap.getProcessor(record.getInstrument());
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
        for (String instrument : resultMap.keySet()) {
            Processor<?> processor = processorMap.getProcessor(instrument);
            System.out.println("\n\n" + instrument + " " + processor.action() + ": " + processor.calculate(resultMap.get(instrument)));
        }
    }
}
