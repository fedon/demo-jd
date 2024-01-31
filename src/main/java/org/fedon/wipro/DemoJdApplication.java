package org.fedon.wipro;

import org.fedon.wipro.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJdApplication implements CommandLineRunner {

    @Autowired
    private FileService service;

    @Override
    public void run(String... args) throws Exception {
        // TODO usage
        long start = System.currentTimeMillis();
        service.readFile(args[0]);
        System.out.println((System.currentTimeMillis() - start) / 1000 + " <<");
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoJdApplication.class, args);
	}
}
