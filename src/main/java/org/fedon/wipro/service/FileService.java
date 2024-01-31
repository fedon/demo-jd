package org.fedon.wipro.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author Dmytro Fedonin
 *
 */
@Service
public class FileService {
    private static Logger logger = LoggerFactory.getLogger(FileService.class);
    private static String instrument1 = "INSTRUMENT1";

    public void readFile(String fileName) {
        // TODO config separator
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();

            while (line != null) {
                // TODO separate processing
                StringTokenizer st = new StringTokenizer(line, ",");
                int i = 0;
                String instrument = null;
                LocalDate date = null;
                BigDecimal value = null;
                while (st.hasMoreElements()) {
                    String token = st.nextToken();
                    i++;
                    if (i == 1 && instrument1.equals(token)) {
                        logger.info(instrument1 + " has been found for date...");
                        instrument = instrument1;
                    }
                    if (i == 2 && instrument == instrument1) {
                        date = LocalDate.parse(token, DateTimeFormatter.ofPattern("dd-MMM-uuuu"));
                        logger.info(date.toString() + " <<===");
                    }
                    if (i == 3 && instrument == instrument1) {
                        value = new BigDecimal(token);
                        logger.info("\nWith value: " + value);
                    }
                    // TODO value
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
