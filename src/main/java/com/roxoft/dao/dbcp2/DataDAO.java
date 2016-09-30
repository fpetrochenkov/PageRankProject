package com.roxoft.dao.dbcp2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.roxoft.dao.IDataDAO;
import com.roxoft.models.Site;

public class DataDAO extends GeneralDataDAO implements IDataDAO {

	private static final Logger LOG = Logger.getLogger(DataDAO.class);

	public DataDAO(Connection connection) {
		this.connection = connection;
	}

	public void fillAllSites(ArrayList<Site> sites) {

		final String SQL_INSERT = "INSERT INTO `sites`(url, html, pagerank) VALUES(?, ?, 1)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);

		try {
			for (Site site : sites) {
				preparedStatement.setString(1, site.getUrl());
				preparedStatement.setString(2, site.getHtml());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}

	}

	public void fillSite(Site site) {

		final String SQL_INSERT = "INSERT INTO `sites`(url, html, pagerank) VALUES(?, ?, 1)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);

		try {
			preparedStatement.setString(2, site.getUrl());
			preparedStatement.setString(3, site.getHtml());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error("SQLException e");
		} finally {
			closePreparedStatement(preparedStatement);
		}

	}

	public void fillSiteHaveLinks(Site site) {

		final String SQL_INSERT = "INSERT INTO `sites_have_links`(id_out, id_in) " + "VALUES(?, ?)";
		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_INSERT);

		try {
			for (String linkOut : site.getLinksOutStr()) {

				int siteId = 0;
				siteId = getSiteIdBySiteUrl(linkOut);

				if (siteId != 0) {

					preparedStatement.setInt(1, getSiteIdBySiteUrl(site.getUrl()));
					preparedStatement.setInt(2, getSiteIdBySiteUrl(linkOut));
					preparedStatement.executeUpdate();

				}
			}
		} catch (SQLException e) {
			LOG.error("SQLException e");
		} finally {
			closePreparedStatement(preparedStatement);
		}

	}

	public int getSiteIdBySiteUrl(String siteUrl) {

		int siteId = 0;

		final String SQL_SELECT = "SELECT site_id FROM sites " + "WHERE url = ?";

		PreparedStatement preparedStatement = null;
		preparedStatement = getPreparedStatement(SQL_SELECT);
		ResultSet resultSet = null;

		try {

			preparedStatement.setString(1, siteUrl);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				siteId = resultSet.getInt("site_id");

		} catch (SQLException e) {
			LOG.error("SQLException e");
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException e) {
					LOG.error("SQLException e");
				}
			closePreparedStatement(preparedStatement);
		}

		return siteId;

	}

}
