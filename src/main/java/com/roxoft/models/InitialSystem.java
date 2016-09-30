package com.roxoft.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.roxoft.randomFillers.SitesRandomFiller;

public class InitialSystem {

	private static final Logger LOG = Logger.getLogger(InitialSystem.class);

	private ArrayList<Site> sites = new ArrayList<Site>();
	private String htmlContent = new String();

	private void cleanData() {

		if (!(sites.isEmpty()))
			sites.clear();

	}

	public void fillInitialSystem(int numberOfSites) throws IOException {
		cleanData();
		fillSitesURLs(numberOfSites);
		fillSitesWithHtmls();
		fillSitesWithLinks();
	}

	public void fillSitesURLs(int numberOfSites) {

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

			String fileName = site.getUrl();
			Set<String> linksSet = new HashSet<String>();
			ArrayList<String> linksArayList = new ArrayList<String>();
			Document doc;
			
				doc = Jsoup.connect(fileName).get();
				Elements links = doc.select("a[href]");

				for (Element link : links) {
					linksSet.add(link.attr("abs:href").toString());
				}
				

				linksArayList.addAll(linksSet);

				site.setLinksOutStr(linksArayList);
		} }catch (IOException e) {
			e.printStackTrace();
		}
			

		

	}

	private void fillSitesWithHtmls() throws IOException {

		try{
			for (Site site : sites) {

				String fileName = site.getUrl();
				Document doc;
				doc = Jsoup.connect(fileName).get();
				htmlContent = doc.html();
				site.setHtml(htmlContent);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Site> getSites() {
		return sites;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

}
