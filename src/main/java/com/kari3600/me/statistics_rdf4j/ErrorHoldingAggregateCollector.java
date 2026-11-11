package com.kari3600.me.statistics_rdf4j;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateCollector;

public abstract class ErrorHoldingAggregateCollector implements AggregateCollector {
    private ValueExprEvaluationException typeError;

    public Value getFinalValue() throws ValueExprEvaluationException {
        if (typeError != null) {
            throw typeError;
        }
        return computeValue();
    }

    public void setTypeError(ValueExprEvaluationException typeError) {
        this.typeError = typeError;
    }

    public boolean hasError() {
        return typeError != null;
    }
    protected abstract Value computeValue();
}
