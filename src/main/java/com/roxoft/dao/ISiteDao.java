package com.roxoft.dao;

import java.util.List;
import com.roxoft.models.Site;

public interface ISiteDao {
	
	public List<Site> getAllSites ();
	public void insert (Site entity);
	public void delete (int id);
	public void update ();

}
