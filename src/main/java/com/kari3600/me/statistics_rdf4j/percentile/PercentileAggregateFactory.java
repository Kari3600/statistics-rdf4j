package com.kari3600.me.statistics_rdf4j.percentile;

import com.kari3600.me.statistics_rdf4j.SimpleAggregateNAryFunction;
import com.kari3600.me.statistics_rdf4j.StatisticsRDF4J;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateCollector;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateNAryFunction;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateNAryFunctionFactory;

import java.util.function.BiFunction;

public class PercentileAggregateFactory implements AggregateNAryFunctionFactory {
    @Override
    public String getIri() {
        return StatisticsRDF4J.IRI + "percentile";
    }

    @Override
    public AggregateNAryFunction buildFunction(int i, BiFunction<Integer, BindingSet, Value> biFunction) {
        return new SimpleAggregateNAryFunction(biFunction, i);
    }

    @Override
    public AggregateCollector getCollector(int i) {
        return new PercentileAggregateCollector();
    }
}
