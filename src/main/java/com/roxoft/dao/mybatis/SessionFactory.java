package com.roxoft.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SessionFactory {

	private static final Logger LOG = LogManager.getLogger(SessionFactory.class);

	private static final String resource = "mybatis/mybatis_config.xml";
	private static SqlSessionFactory sessionFactory = null;
	private static InputStream input;

	public static SqlSession getSession() {
		if (sessionFactory == null) {
			try {
				input = Resources.getResourceAsStream(resource);
				sessionFactory = new SqlSessionFactoryBuilder().build(input);
			} catch (IOException e) {
				LOG.error("IOException", e);
			}
		}
		return sessionFactory.openSession();
	}

}
