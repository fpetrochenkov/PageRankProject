package com.roxoft.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class LinksOutDaoImpl extends SessionFactory  {
	
	public List<String> getLinksOutBySiteId12() {
		List<String> linksOut;
		SqlSession session = SessionFactory.getSession();
		try {
			linksOut = session.selectList("mappers.getLinksOutBySiteId");
		} finally {
			session.close();
		}
		return linksOut;
	}

}
