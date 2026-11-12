package com.kari3600.me.statistics_rdf4j.median;

import com.kari3600.me.statistics_rdf4j.SimpleAggregateFunction;
import com.kari3600.me.statistics_rdf4j.StatisticsRDF4J;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateCollector;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateFunction;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateFunctionFactory;

import java.util.function.Function;

public class ApproximateMedianAggregateFactory implements AggregateFunctionFactory {
    @Override
    public String getIri() {
        return StatisticsRDF4J.IRI + "approx_median";
    }

    @Override
    public AggregateFunction buildFunction(Function<BindingSet, Value> function) {
        return new SimpleAggregateFunction(function);
    }

    @Override
    public AggregateCollector getCollector() {
        return new ApproximateMedianCollector();
    }
}
