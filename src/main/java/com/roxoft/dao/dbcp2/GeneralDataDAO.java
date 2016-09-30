package com.roxoft.dao.dbcp2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class GeneralDataDAO {

	private static final Logger LOG = Logger.getLogger(GeneralDataDAO.class);

	protected Connection connection;

	protected PreparedStatement getPreparedStatement(String sql) {

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			LOG.error("SQLException e");
		}

		return preparedStatement;

	}

	protected void closePreparedStatement(PreparedStatement preparedStatement) {

		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			LOG.error("SQLException e");
		}

	}

}
