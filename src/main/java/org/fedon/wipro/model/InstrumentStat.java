package org.fedon.wipro.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author Dmytro Fedonin
 *
 */
@Data
public class InstrumentStat {

    private int numberOfRecords = 0;
    private BigDecimal sum = BigDecimal.ZERO;
}
