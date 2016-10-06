package com.roxoft.randomFillers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.roxoft.data.Sites;

public class SitesRandomFiller {

	public Set<String> getSitesURLs(int number) {
		ArrayList<String> allSites = new ArrayList<String>(Arrays.asList((new Sites()).getSites()));
		int maxNumber = allSites.size();
		Set<String> sites = new TreeSet<String>();
		Random rand = new Random();
		if (number < allSites.size()) {
			while (number > sites.size())
				sites.add(allSites.get(rand.nextInt(maxNumber)));
		} else {
			sites.addAll(allSites);
		}
		return sites;
	}

}
