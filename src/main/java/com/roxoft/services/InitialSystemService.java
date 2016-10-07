package com.roxoft.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.roxoft.dao.mybatis.SiteDaoImpl;
import com.roxoft.io.InitialSystemPrinter;
import com.roxoft.models.Site;
import com.roxoft.randomFillers.SitesRandomFiller;

public class InitialSystemService {

	private static final Logger LOGGER = LogManager.getLogger(InitialSystemService.class);

	private ArrayList<Site> fillSitesURLs(int numberOfSites) {
		ArrayList<Site> sites = new ArrayList<Site>();
		SitesRandomFiller srf = new SitesRandomFiller();
		ArrayList<String> sitesURLs = new ArrayList<String>();
		sitesURLs.addAll(srf.getSitesURLs(numberOfSites));
		for (String url : sitesURLs) {
			Site site = new Site();
			site.setUrl(url);
			sites.add(site);
		}
		return sites;
	}

	private ArrayList<Site> fillSitesWithHtmlsAndLinks(ArrayList<Site> sites) {
		try {
			for (Site site : sites) {
				String url = site.getUrl();
				Set<String> linksSet = new HashSet<String>();
				Document doc;
				doc = Jsoup.connect(url).get();
				site.setHtml(doc.html());
				Elements links = doc.select("a[href]");
				for (Element link : links) {
					linksSet.add(link.attr("abs:href").toString());
				}
				ArrayList<String> linksArayList = new ArrayList<String>();
				linksArayList.addAll(linksSet);
				site.setLinksOutStr(linksArayList);
			}
		} catch (IOException e) {
			LOGGER.error("IOException in InitialSystemService.fillSitesWithHtmlsAndLinks()", e);
		}
		return sites;
	}

	public void recordInitialSystem(int numberOfSites) throws IOException {
		ArrayList<Site> sites = new ArrayList<Site>();
		sites.addAll(fillSitesWithHtmlsAndLinks(fillSitesURLs(numberOfSites)));
		InitialSystemPrinter isp = new InitialSystemPrinter();
		isp.printInitialSystem(sites);
		SiteDaoImpl sdi = new SiteDaoImpl();
		for (Site site : sites)
			sdi.insertSite(site);
		for (Site site : sites)
			sdi.insertSiteHaveLinks(site);
	}

}
