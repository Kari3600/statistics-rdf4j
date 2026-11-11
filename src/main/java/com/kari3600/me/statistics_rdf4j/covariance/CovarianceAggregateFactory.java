package com.kari3600.me.statistics_rdf4j.covariance;

import com.kari3600.me.statistics_rdf4j.SimpleAggregateNAryFunction;
import com.kari3600.me.statistics_rdf4j.StatisticsRDF4J;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.*;

import java.util.function.BiFunction;

public class CovarianceAggregateFactory implements AggregateNAryFunctionFactory {

	@Override
	public String getIri() {
		return StatisticsRDF4J.IRI+"covariance";
	}

	@Override
	public AggregateNAryFunction buildFunction(int argsCount,
			BiFunction<Integer, BindingSet, Value> evaluationStepByIndex) {
		return new SimpleAggregateNAryFunction(evaluationStepByIndex, argsCount);
	}

	@Override
	public AggregateCollector getCollector(int argsCount) {
		return new CovarianceCollector(false, argsCount);
	}
}
