package com.networks.maribo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class LocalContext {
    public final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static JdbcTemplate runner = null;
}
