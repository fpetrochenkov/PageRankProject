package com.roxoft.io;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.models.Site;

public class InitialSystemPrinter {

	private static final Logger INITIAL_SYSTEM_LOGGER = LogManager.getLogger(InitialSystemPrinter.class);

	public void printInitialSystem(ArrayList<Site> sites) {
		int numberOfSites = 1;
		INITIAL_SYSTEM_LOGGER.info("Initial system");
		INITIAL_SYSTEM_LOGGER.info("\nNumber of sites:" + sites.size());
		for (Site site : sites) {
			INITIAL_SYSTEM_LOGGER.info("Site " + numberOfSites + ": " + site.getUrl() + "");
			numberOfSites++;
		}
		for (Site site : sites) {
			INITIAL_SYSTEM_LOGGER.info("\nSite: " + site.getUrl());
			INITIAL_SYSTEM_LOGGER.info("Number of outgoing links: " + site.getLinksOutStr().size() + ".");
			int numberOfLinks = 1;
			for (String link : site.getLinksOutStr()) {
				INITIAL_SYSTEM_LOGGER.info("Outgoing link " + numberOfLinks + " : " + link + "");
				numberOfLinks++;
			}
		}
	}

}
