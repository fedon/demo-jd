package org.fedon.wipro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

/**
 * @author Dmytro Fedonin
 *
 */
@Data
public class InstrumentRecord {

    private String instrument;
    private LocalDate date;
    private BigDecimal value;
}
