package com.networks.maribo;

import com.networks.maribo.service.migrations.Migrate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;


@SpringBootApplication
public class Main {

	private JdbcTemplate jdbcTemplate;
	private Environment props;

	@Autowired
	public Main(JdbcTemplate jdbcTemplate, Environment env) {
		this.jdbcTemplate = jdbcTemplate;
		this.props = env;
		LocalContext.env = this.props;
		Migrate.run(jdbcTemplate);
		LocalContext.runner = new JdbcTemplate(
				Objects.requireNonNull(jdbcTemplate.getDataSource())
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
