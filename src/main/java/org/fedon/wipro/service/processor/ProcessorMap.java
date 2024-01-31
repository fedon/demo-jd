package org.fedon.wipro.service.processor;

import java.util.Map;

/**
 * @author Dmytro Fedonin
 *
 */
public class ProcessorMap {
    private Map<String, Processor<?>> map;
    private Processor<?> defaultProcessor;

    public ProcessorMap(Map<String, Processor<?>> processorMap, Processor<?> defaultProcessor) {
        map = processorMap;
        this.defaultProcessor = defaultProcessor;
    }

    public Processor<?> getProcessor(String instrument) {
        Processor<?> processor = map.get(instrument);
        if (processor == null) {
            processor = defaultProcessor;
        }
        return processor;
    }
}
