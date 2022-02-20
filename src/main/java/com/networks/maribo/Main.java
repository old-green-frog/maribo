package com.networks.maribo;

import com.networks.maribo.service.migrations.Migrate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;


@SpringBootApplication
public class Main {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public Main(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		Migrate.run(jdbcTemplate);
		LocalContext.runner = new JdbcTemplate(
				Objects.requireNonNull(jdbcTemplate.getDataSource())
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
