package com.roxoft.dao;

import java.util.ArrayList;

import com.roxoft.models.Site;

public interface IDataDAO {

	void fillAllSites(ArrayList<Site> sites);

	void fillSite(Site site);

	void fillSiteHaveLinks(Site site);

	int getSiteIdBySiteUrl(String siteName);

}
