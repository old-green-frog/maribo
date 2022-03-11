package com.networks.maribo.service.migrations;

import com.networks.maribo.LocalContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.nio.file.Files;


public class Migrate {
    public static void run(JdbcTemplate runner) {

        // pathname depends on current run platform
        String environ = LocalContext.env.getProperty("mariboenv", "local");
        String pathname = environ.equals("local") ? "./build/resources/main/migrations" : "/app/build/resources/main/migrations";

        File migrationFolder = new File(pathname);
        File[] sqls = migrationFolder.listFiles();

        assert sqls != null;
        for (File sql : sqls) {
            try {
                String source = Files.readString(sql.toPath());
                runner.execute(source);
            } catch(Exception ex) {
                LocalContext.logger.error(ex.getMessage());
                System.exit(1);
            }
        }

        LocalContext.logger.info("Migrations successfully applied");

    }
}
