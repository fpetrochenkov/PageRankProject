package com.roxoft.dao.dbcp2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class DataSource {

	private static final Logger LOG = Logger.getLogger(DataSource.class);
	private static DataSource dataSource;
	private BasicDataSource basicDataSource;

	private DataSource() {

		basicDataSource = new BasicDataSource();
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream("src\\main\\resources\\database.properties");
			properties.load(fileInputStream);
			basicDataSource.setDriverClassName(properties.getProperty("driver"));
			basicDataSource.setUsername(properties.getProperty("username"));
			basicDataSource.setPassword(properties.getProperty("password"));
			basicDataSource.setUrl(properties.getProperty("url"));
			basicDataSource.setMinIdle(5);
			basicDataSource.setMaxIdle(20);
			basicDataSource.setMaxOpenPreparedStatements(180);

		} catch (IOException e) {
			LOG.error("IOException e");
		}
	}

	public static DataSource getInstance() {
		if (dataSource == null) {
			dataSource = new DataSource();
			return dataSource;
		} else {
			return dataSource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.basicDataSource.getConnection();
	}

}
