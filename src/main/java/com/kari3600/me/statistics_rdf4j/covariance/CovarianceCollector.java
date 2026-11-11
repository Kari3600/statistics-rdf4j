package com.kari3600.me.statistics_rdf4j.covariance;

import com.kari3600.me.statistics_rdf4j.MultivariateStatisticCollector;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

public class CovarianceCollector extends MultivariateStatisticCollector {

	public CovarianceCollector(boolean population, int k) {
		super(population, k);
	}

	@Override
	protected Literal computeValue() {
		return SimpleValueFactory.getInstance()
				.createLiteral(
						statistics.getCovariance().getEntry(0, 1)
				);
	}
}
