package org.fedon.wipro;

import org.fedon.wipro.service.FileProcessingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJdApplication implements CommandLineRunner {

    @Autowired
    private FileProcessingUnit processingUnit;

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1) {
            System.out.println("\n\nUsage: demo-jd /path/to/input/file.txt\n\n");
            return;
        }
        long start = System.currentTimeMillis();
        processingUnit.readFile(args[0]);
        System.out.println("Total processor run time: " + (System.currentTimeMillis() - start) / 1000 + " sec\n\n");
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoJdApplication.class, args);
	}
}
