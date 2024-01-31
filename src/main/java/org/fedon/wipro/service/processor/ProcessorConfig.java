package org.fedon.wipro.service.processor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmytro Fedonin
 *
 */
public class ProcessorConfig {

    public static String instrument1 = "INSTRUMENT1";
    public static String instrument2 = "INSTRUMENT2";
    public static String instrument3 = "INSTRUMENT3";

    public static ProcessorMap configProcessors() {

        Map<String, Processor<?>> map = new HashMap<>();
        map.put(instrument1, new MeanProcessor());
        map.put(instrument2, new PeriodMeanProcessor(LocalDate.parse("2014-11-01"), LocalDate.parse("2014-11-30")));
        map.put(instrument3, new AverageProcessor());

        return new ProcessorMap(map, new MovingSumProcessor(10));
    }
}
