package com.networks.maribo;

import com.networks.maribo.service.Greeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class DemoApplication {

	private final String template = "Hello, %s!";
	private long counter = 0;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public Greeting hello(@RequestParam(value = "txt", defaultValue = "World") String name) {
		return new Greeting(++counter, String.format(template, name));
	}

}
