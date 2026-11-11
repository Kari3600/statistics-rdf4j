package com.kari3600.me.statistics_rdf4j;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateCollector;

public interface ConsumingAggregateNAryCollector extends AggregateCollector {
    void addValue(Value[] vals) throws ValueExprEvaluationException;
}
