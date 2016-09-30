package com.roxoft.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.roxoft.algorithm.PageRank;
import com.roxoft.dao.ISiteDao;
import com.roxoft.models.Site;

public class SiteDaoImpl extends SessionFactory implements ISiteDao {

	PageRank pageRank = new PageRank();
	@Override
	public List<Site> getAllSites() {
		List<Site> list;
		SqlSession session = SessionFactory.getSession();
		try {
			list = session.selectList("mappers.getAllSites");
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void insert(Site entity) {
		SqlSession session = SessionFactory.getSession();
		try {
			session.insert("mappers.insert", entity);
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(int id) {
		SqlSession session = SessionFactory.getSession();
		try {
			session.delete("mappers.deleteSiteById", id);
		} finally {
			session.close();
		}
	}

	@Override
	public void update() {
		SqlSession session = SessionFactory.getSession();		
		try {
			
			session.update("mappers.updatePageRank");
			
		} finally {
			session.close();
		}
	}
	
}
