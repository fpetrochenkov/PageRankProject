package com.roxoft.main;

import com.roxoft.dao.services.*;
import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.roxoft.algorithm.PageRank;
import com.roxoft.dao.mybatis.SiteDaoImpl;
import com.roxoft.exceptions.ConvergenceRateException;
import com.roxoft.io.InitialSystemPrinter;

public class Main {

	//private static final Logger rootLogger = LogManager.getRootLogger();
	
	public static void main(String[] args) throws IOException, ConvergenceRateException {
		 InitialSystemService iss = new InitialSystemService();
		 iss.recordInitialSystem(15);
		 
		 /*SiteDaoImpl s = new SiteDaoImpl();		 
		 PageRank pageRank = new PageRank();
		 pageRank.algotihmPageRank(s.getAllSites());
		 rootLogger.info("Enter the keyword: ");
		 Scanner sc = new Scanner(System.in);
		 String keyword = sc.nextLine();
		 rootLogger.info("");
		 SiteService service = new SiteService();
		 service.getListSitesByKeyword(keyword);*/
	}

}
