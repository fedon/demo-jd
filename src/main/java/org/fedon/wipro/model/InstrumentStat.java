package org.fedon.wipro.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Dmytro Fedonin
 *
 */
@Data
public class InstrumentStat {

    private int numberOfRecords = 0;
    private BigDecimal sum = BigDecimal.ZERO;
    List<BigDecimal> values = new ArrayList<>();
    List<InstrumentRecord> lastRecords = new ArrayList<>();
}
