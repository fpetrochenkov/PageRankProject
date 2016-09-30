package com.roxoft.dao.dbcp2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.roxoft.io.InitialSystemPrinter;
import com.roxoft.models.InitialSystem;
import com.roxoft.models.Site;

public class InitialSystemService {

	private static final Logger LOG = Logger.getLogger(InitialSystemService.class);

	public void recordInitialSystem(int numberOfSites) throws IOException {

		Connection connection = null;

		try {

			connection = DataSource.getInstance().getConnection();

			DataDAO dataDAO = new DataDAO(connection);

			InitialSystem initialSystem = new InitialSystem();
			initialSystem.fillInitialSystem(numberOfSites);

			InitialSystemPrinter initialSystemPrinter = new InitialSystemPrinter();
			initialSystemPrinter.printInitialSystem(initialSystem.getSites());

			dataDAO.fillAllSites(initialSystem.getSites());

			for (Site site : initialSystem.getSites()) {

				dataDAO.fillSiteHaveLinks(site);

			}

		} catch (SQLException e) {
			LOG.error("SQLException e");
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					LOG.error("SQLException e");
				}
		}

	}

}
