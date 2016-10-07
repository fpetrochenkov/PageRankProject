package com.roxoft.algorithm;

import java.util.Arrays;
import java.util.List;
import com.roxoft.exceptions.ConvergenceRateException;
import com.roxoft.models.Site;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PageRank {

	private static final Logger LOG = LogManager.getLogger(PageRank.class);
	public int adjacencyMatrix[][];
	public double dampingFactor = 0.85;

	public List<Site> algotihmPageRank(List<Site> sites) throws ConvergenceRateException {
		try {
			int i, j, k;
			int vertices = sites.size();
			adjacencyMatrix = new int[vertices][vertices];
			for (i = 0; i < vertices; i++)
				for (j = 0; j < vertices; j++) {
					if (sites.get(i).getLinksOut().contains(j))
						adjacencyMatrix[i][j] = 1;
					if (i == j)
						adjacencyMatrix[i][j] = 0;
				}
			for (int[] array : adjacencyMatrix) {
				LOG.info(Arrays.toString(array));
			}

			double pageRank[] = new double[vertices];
			double current[] = new double[vertices];
			int edges = 0;

			for (i = 0; i < vertices; i++) {
				pageRank[i] = sites.get(i).getPageRank();
				LOG.info("startRank of site(vertex) " + i + " = " + pageRank[i] + "\n");
			}

			for (int iterationNumber = 1; iterationNumber < 25; iterationNumber++) {
				for (i = 0; i < vertices; i++) {
					current[i] = pageRank[i];
					pageRank[i] = 0;
				}

				for (j = 0; j < vertices; j++) {
					for (i = 0; i < vertices; i++)
						if (adjacencyMatrix[i][j] == 1) {
							k = 0;
							edges = 0;
							while (k < vertices) {
								if (adjacencyMatrix[i][k] == 1) {
									edges = edges + 1;
								}
								k = k + 1;
							}
							if (j != i)
								pageRank[j] = pageRank[j] + dampingFactor * (current[i] / edges);
						}
					pageRank[j] = pageRank[j] + (1 - dampingFactor);

					/*
					 * alternative version of pagerank formula, where pagerank
					 * sum=1; pageRank[j] = pageRank[j] + (1 -
					 * dampingFactor)/vertices;
					 */
				}

				for (i = 0; i < vertices; i++) {
					sites.get(i).setPageRank(pageRank[i]);
				}
			}

			for (i = 0; i < vertices; i++) {
				LOG.info("pagerank of site " + i + " - " + sites.get(i).getPageRank() + "\n");
			}

			double convergenceRate = 0;

			for (i = 0; i < vertices; i++) {
				convergenceRate = convergenceRate + Math.pow(pageRank[i] - current[i], 2);
			}
			if (convergenceRate > 0.001)
				throw new ConvergenceRateException("Error, convergenceRate = " + convergenceRate + " is big enough");
			else
				LOG.info("convergenceRate = " + convergenceRate + ", results are accurate enough");
		} catch (ConvergenceRateException e) {
			LOG.error("ConvergenceRateException e");
		}
		return sites;
	}

}
