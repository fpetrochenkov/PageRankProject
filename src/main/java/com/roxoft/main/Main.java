package com.roxoft.main;

import java.io.File;
import com.roxoft.dao.services.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.roxoft.algorithm.PageRank;
import com.roxoft.dao.dbcp2.InitialSystemService;
import com.roxoft.dao.mybatis.SiteDaoImpl;
import com.roxoft.data.Sites;
import com.roxoft.exceptions.ConvergenceRateException;
import com.roxoft.models.InitialSystem;
import com.roxoft.models.Site;
import com.roxoft.randomFillers.SitesRandomFiller;

public class Main {

	private static final Logger rootLogger = LogManager.getRootLogger();
	
	public static void main(String[] args) throws IOException, ConvergenceRateException {

		 InitialSystemService iss = new InitialSystemService();
		 iss.recordInitialSystem(78);
		 SiteDaoImpl s = new SiteDaoImpl();		 
		 PageRank pageRank = new PageRank();
		 pageRank.algotihmPageRank(s.getAllSites());
		 rootLogger.info("Enter the keyword: ");
		 Scanner sc = new Scanner(System.in);
		 String keyword = sc.nextLine();
		 rootLogger.info("");
		 SiteService service = new SiteService();
		 service.getListSitesByKeyword(keyword);
		






	}



}
