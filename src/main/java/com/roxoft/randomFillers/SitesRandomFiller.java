package com.roxoft.randomFillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import com.roxoft.data.Sites;

public class SitesRandomFiller {

	public Set<String> getSitesURLs(int number) {

		ArrayList<String> allSites = new ArrayList<String>(Arrays.asList((new Sites()).getSites()));
		Set<String> sites = new TreeSet<String>();
		sites.addAll(allSites);

		return sites;

	}

}
