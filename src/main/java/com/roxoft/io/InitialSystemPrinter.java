package com.roxoft.io;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roxoft.models.Site;

public class InitialSystemPrinter {

	private static final Logger INITIALSYSTEMLOGGER = LogManager.getLogger(InitialSystemPrinter.class);

	public void printInitialSystem(ArrayList<Site> sites) {
		int numberOfSites = 1;
		INITIALSYSTEMLOGGER.info("Initial system");
		INITIALSYSTEMLOGGER.info("\nNumber of sites:" + sites.size());
		for (Site site : sites) {
			INITIALSYSTEMLOGGER.info("Site " + numberOfSites + ": " + 
					site.getUrl() + "");
			numberOfSites++;
		}
		for (Site site : sites) {
			INITIALSYSTEMLOGGER.info("\nSite: " + site.getUrl());
			INITIALSYSTEMLOGGER.info("Number of outgoing links: " + 
					site.getLinksOutStr().size() + ".");
			int numberOfLinks = 1;
			for (String link : site.getLinksOutStr()){
				INITIALSYSTEMLOGGER.info("Outgoing link " + numberOfLinks + " : " + link + "");
				numberOfLinks++;
			}
		}
	}

}
