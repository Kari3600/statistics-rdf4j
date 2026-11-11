package com.kari3600.me.statistics_rdf4j;

import org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

import java.util.Arrays;

public abstract class MultivariateStatisticCollector extends SimpleAggregateNAryCollector {
	protected final MultivariateSummaryStatistics statistics;

	public MultivariateStatisticCollector(boolean population, int k) {
		this.statistics = new MultivariateSummaryStatistics(k, !population);
	}

	@Override
	public void addValue(Value[] vals) throws ValueExprEvaluationException {
		double[] primitives = Arrays.stream(vals)
				.mapToDouble(CollectorUtil::asDouble)
				.toArray();
		statistics.addValue(primitives);
	}
}
