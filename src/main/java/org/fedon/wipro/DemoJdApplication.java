package org.fedon.wipro;

import org.fedon.wipro.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJdApplication implements CommandLineRunner {

    @Autowired
    private HelloService service;

    @Override
    public void run(String... args) throws Exception {
        service.hell();
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoJdApplication.class, args);
	}
}
