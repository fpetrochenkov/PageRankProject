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

	private static final Logger ROOTLOGGER = LogManager.getLogger(SiteService.class);
	private static final Logger LOG = LogManager.getLogger(SiteService.class);

	private List<Site> sitesByKeyword = new ArrayList<Site>();
	private SiteDaoImpl siteDao = new SiteDaoImpl();
	private PageRank pageRank = new PageRank();

	public List<Site> getListSitesByKeyword(String keyword) {
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
		ROOTLOGGER.info(sitesByKeyword.toString());
		return sitesByKeyword;

	}

	public List<Site> getSitesByKeyword() {
		return sitesByKeyword;
	}

}
