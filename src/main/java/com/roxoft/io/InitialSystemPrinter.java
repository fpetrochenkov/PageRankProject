package com.roxoft.io;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.models.Site;

public class InitialSystemPrinter {

	private static final Logger initialSystemLogger = LogManager.getLogger(InitialSystemPrinter.class);

	public void printInitialSystem(ArrayList<Site> sites) {

		int numberOfSites = 1;

		initialSystemLogger.info("Initial system");
		initialSystemLogger.info("\nNumber of sites:" + sites.size());

		for (Site site : sites) {

			initialSystemLogger.info("Site " + numberOfSites + ": " + site.getUrl() + "");
			numberOfSites++;

		}

		for (Site site : sites) {

			initialSystemLogger.info("\nSite: " + site.getUrl());

			initialSystemLogger.info("Number of outgoing links: " + site.getLinksOutStr().size() + ".");

			for (String link : site.getLinksOutStr())
				initialSystemLogger.info("Outgoing link: " + link + "");

		}

	}

}
