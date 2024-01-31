package org.fedon.wipro.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import org.fedon.wipro.model.InstrumentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Dmytro Fedonin
 *
 */
@Service
public class FileProcessingUnit {
    private static Logger logger = LoggerFactory.getLogger(FileProcessingUnit.class);

    @Autowired
    private EnginService service;
    public void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();

            while (line != null) {
                InstrumentRecord record = parseRecord(line);
                if (record == null) {
                    continue;
                }
                service.process(record);
                line = br.readLine();
            }
            service.publishResult();
        } catch (FileNotFoundException e) {
            logger.error("Please check the file location.", e);
        } catch (IOException e) {
            logger.error("The input data is corrupted.", e);
        }
    }

    private InstrumentRecord parseRecord(String line) {
        InstrumentRecord result = new InstrumentRecord();
        StringTokenizer st = new StringTokenizer(line, ",");
        if (st.hasMoreElements()) {
            String token = st.nextToken().trim();
            result.setInstrument(token);
        } else {
            return null;
        }
        if (st.hasMoreElements()) {
            String token = st.nextToken().trim();
            LocalDate date = LocalDate.parse(token, DateTimeFormatter.ofPattern("dd-MMM-uuuu"));
            result.setDate(date);
        } else {
            return null;
        }
        if (st.hasMoreElements()) {
            String token = st.nextToken().trim();
            BigDecimal value = new BigDecimal(token);
            result.setValue(value);
        } else {
            return null;
        }

        return result;
    }
}
