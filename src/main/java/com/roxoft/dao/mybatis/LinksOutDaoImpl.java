package com.roxoft.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.roxoft.dao.ILinksOutDao;

public class LinksOutDaoImpl implements ILinksOutDao {

	public List<String> getLinksOutBySiteId12() {
		List<String> linksOut;
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			linksOut = session.selectList("mappers.getLinksOutBySiteId");
		} finally {
			session.close();
		}
		return linksOut;
	}

	@Override
	public List<String> getLinksOutBySiteId(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

}
