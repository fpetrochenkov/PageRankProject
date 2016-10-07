package com.roxoft.dao.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.roxoft.algorithm.PageRank;
import com.roxoft.dao.ISiteDao;
import com.roxoft.models.Site;

public class SiteDaoImpl implements ISiteDao {

	PageRank pageRank = new PageRank();

	public List<Site> getAllSites() {
		List<Site> list;
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			list = session.selectList("mappers.getAllSites");
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	public void delete(int id) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			session.delete("mappers.deleteSiteById", id);
		} finally {
			session.close();
		}
	}

	public void insertSiteHaveLinks(Site site) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			for (String linkOut : site.getLinksOutStr()) {
				int siteId = 0;
				int linkId = 0;
				siteId = getSiteIdBySiteUrl(site.getUrl());
				linkId = getSiteIdBySiteUrl(linkOut);
				if ((siteId != 0) & (linkId != 0)) {
					Map<String, Integer> hm = new HashMap<String, Integer>();
					hm.put("id_out", siteId);
					hm.put("id_in", linkId);
					session.insert("mappers.insertSiteHaveLinks", hm);
					session.commit();
				}
			}
		} finally {
			session.close();
		}
	}

	public int getSiteIdBySiteUrl(String siteUrl) {
		Integer siteId = null;
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			siteId = session.selectOne("mappers.getSiteIdBySiteUrl", siteUrl);
			session.commit();
		} finally {
			session.close();
		}
		if (siteId == null)
			return 0;
		else
			return siteId;
	}

	public void insertSite(Site site) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			session.insert("mappers.insertSite", site);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void insertSites(ArrayList<Site> sites) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			session.insert("mappers.insertSites", sites);
			session.commit();
		} finally {
			session.close();
		}
	}

}
