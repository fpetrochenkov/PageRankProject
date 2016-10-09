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

	public List<Site> getListSitesByKeyword(String keyword) {
		List<Site> sitesByKeyword = new ArrayList<Site>();
		SiteDaoImpl siteDao = new SiteDaoImpl();
		PageRank pageRank = new PageRank();
		try {
			for (Site site : pageRank.algotihmPageRank(siteDao.getAllSites())) {
				if (site.getHtml().toString().contains(keyword))
					sitesByKeyword.add(site);
				Collections.sort(sitesByKeyword, new ComparePageRanks());
				throw new ConvergenceRateException("Error, convergenceRate is big enough");
			}
		} catch (ConvergenceRateException e) {
			LOG.error("ConvergenceRateException e");
		}
		LOG.info(sitesByKeyword.toString());
		return sitesByKeyword;

	}

}
