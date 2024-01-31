package org.fedon.wipro.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Dmytro Fedonin
 *
 */
@Service
public class DateService {
    private static Logger logger = LoggerFactory.getLogger(DateService.class);

    // TODO make today configurable
    private LocalDate today = LocalDate.now();

    /**
     * Currently checks only weekends and the future.
     * 
     * @param date
     * @return <b>true</b> if the date is a valid business day not in the future, otherwise false. I.e. if the date is a weekend or is in the future
     *         the method returns <b>false</b>.
     */
    public boolean isValidBusinessDay(LocalDate date) {
        // TODO check holidays
        if (date.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0 || date.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) {
            logger.info("Weekend found " + date);
            return false;
        }
        return !today.isBefore(date);
    }
}
