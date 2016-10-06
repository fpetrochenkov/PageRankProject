package com.roxoft.dao;

import java.util.ArrayList;
import java.util.List;
import com.roxoft.models.Site;

public interface ISiteDao {
	
	List<Site> getAllSites ();
	void insertSite(Site site);
	void insertSites(ArrayList<Site> sites);
	void insertSiteHaveLinks(Site site);
	int getSiteIdBySiteUrl(String siteUrl);
	void delete (int id);

}
