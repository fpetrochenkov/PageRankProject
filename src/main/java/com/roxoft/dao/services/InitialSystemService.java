package com.roxoft.dao.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.roxoft.dao.mybatis.SiteDaoImpl;
import com.roxoft.io.InitialSystemPrinter;
import com.roxoft.models.Site;
import com.roxoft.randomFillers.SitesRandomFiller;

public class InitialSystemService {

	private static final Logger INITIALSYSTEMSERVICELOGGER = Logger.getLogger(InitialSystemService.class);
	private ArrayList<Site> sites = new ArrayList<Site>();

	private void cleanData() {
		if (!(sites.isEmpty()))
			sites.clear();
	}

	private void fillInitialSystem(int numberOfSites) throws IOException {
		cleanData();
		fillSitesURLs(numberOfSites);
		fillSitesWithHtmls();
		fillSitesWithLinks();
	}

	private void fillSitesURLs(int numberOfSites) {
		SitesRandomFiller srf = new SitesRandomFiller();
		ArrayList<String> sitesURLs = new ArrayList<String>();
		sitesURLs.addAll(srf.getSitesURLs(numberOfSites));
		for (String url : sitesURLs) {
			Site site = new Site();
			site.setUrl(url);
			sites.add(site);
		}
	}

	private void fillSitesWithLinks() throws IOException{
		try{
			for (Site site : sites) {
				String url = site.getUrl();
				Set<String> linksSet = new HashSet<String>();
				Document doc;
					doc = Jsoup.connect(url).get();
					Elements links = doc.select("a[href]");
					for (Element link : links) {
						linksSet.add(link.attr("abs:href").toString());
					}
					ArrayList<String> linksArayList = new ArrayList<String>();
					linksArayList.addAll(linksSet);
					site.setLinksOutStr(linksArayList);
			} 
		} catch (IOException e) {
			INITIALSYSTEMSERVICELOGGER.error(
					"IOException in InitialSystemService.fillSitesWithLinks()",e);
		}	
	}

	private void fillSitesWithHtmls() throws IOException {
		try{
			for (Site site : sites) {
				String url = site.getUrl();
				Document doc;
				doc = Jsoup.connect(url).get();
				site.setHtml(doc.html());
			}
		} catch (IOException e) {
			INITIALSYSTEMSERVICELOGGER.error(
					"IOException in InitialSystemService.fillSitesWithHtmls()",e);
		}
	}

	public ArrayList<Site> getSites() {
		return sites;
	}

	public void recordInitialSystem(int numberOfSites) throws IOException {
		fillInitialSystem(numberOfSites);
		InitialSystemPrinter isp = new InitialSystemPrinter();
		isp.printInitialSystem(sites);
		SiteDaoImpl sdi = new SiteDaoImpl();
		for (Site site : sites)
			sdi.insertSite(site);
		for (Site site : sites)
			sdi.insertSiteHaveLinks(site);
	}

}
