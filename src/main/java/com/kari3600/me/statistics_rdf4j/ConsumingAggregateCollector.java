package com.kari3600.me.statistics_rdf4j;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

public interface ConsumingAggregateCollector {
    void addValue(Value value) throws ValueExprEvaluationException;
}
