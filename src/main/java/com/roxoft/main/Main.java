package com.roxoft.main;

import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.roxoft.algorithm.PageRank;
import com.roxoft.dao.mybatis.SiteDaoImpl;
import com.roxoft.exceptions.ConvergenceRateException;
import com.roxoft.services.InitialSystemService;
import com.roxoft.services.SiteService;

public class Main {

	//private static final Logger LOG = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {
			InitialSystemService iss = new InitialSystemService();
			iss.recordInitialSystem(15);
			/*SiteDaoImpl s = new SiteDaoImpl();
			PageRank pagerank = new PageRank();
			pagerank.algotihmPageRank(s.getAllSites());
			LOG.info("Enter the keyword: ");
			String keyword = sc.nextLine();
			SiteService service = new SiteService();
			service.getListSitesByKeyword(keyword);
			throw new ConvergenceRateException("Error, convergenceRate is big enough");*/
		} catch (IOException e) {
			//LOG.error("IOException e");
		//} catch (ConvergenceRateException e) {
		//	LOG.error("ConvergenceRateException e");
		} finally {
			sc.close();
		}
	}

}
