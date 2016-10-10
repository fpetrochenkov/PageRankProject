package com.roxoft.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.roxoft.algorithm.PageRank;
import com.roxoft.dao.mybatis.SiteDaoImpl;
import com.roxoft.exceptions.ConvergenceRateException;
import com.roxoft.models.Site;

public class SiteService {

	private static final Logger LOG = LogManager.getLogger(SiteService.class);

	public List<Site> getListSitesByKeyword(String keyword) throws ConvergenceRateException {
		List<Site> sitesByKeyword = new ArrayList<Site>();
		SiteDaoImpl siteDao = new SiteDaoImpl();
		PageRank pageRank = new PageRank();
		for (Site site : pageRank.algotihmPageRank(siteDao.getAllSites())) {
			if (site.getHtml().toString().contains(keyword))
				sitesByKeyword.add(site);
			Collections.sort(sitesByKeyword, new ComparePageRanks());				
		}
		LOG.info(sitesByKeyword.toString());
		return sitesByKeyword;

	}

}
