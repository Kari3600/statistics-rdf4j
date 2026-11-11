package com.kari3600.me.statistics_rdf4j;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateFunction;

import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleAggregateFunction<T extends SimpleAggregateCollector, D> extends AggregateFunction<T,D> {
    public SimpleAggregateFunction(Function<BindingSet, Value> evaluationStep) {
        super(evaluationStep);
    }

    @Override
    public void processAggregate(BindingSet bindingSet, Predicate<D> predicate, T t) throws QueryEvaluationException {
        if (t.hasError()) return;
        try {
            t.addValue(evaluate(bindingSet));
        } catch (ValueExprEvaluationException e) {
            t.setTypeError(e);
        }
    }
}
