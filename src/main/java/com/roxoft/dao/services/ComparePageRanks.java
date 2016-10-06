package com.roxoft.dao.services;

import java.util.Comparator;

import com.roxoft.models.Site;

public class ComparePageRanks implements Comparator<Site> {

	@Override
	public int compare(Site s1, Site s2) {

		if (s1.getPageRank() > s2.getPageRank())
			return -1;
		else if (s1.getPageRank() < s2.getPageRank())
			return 1;
		else
			return 0;
	}

}
