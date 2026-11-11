package com.kari3600.me.statistics_rdf4j;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.parser.sparql.aggregate.AggregateNAryFunction;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class SimpleAggregateNAryFunction<T extends SimpleAggregateNAryCollector, D> extends AggregateNAryFunction<T,D> {
    protected final int arity;

    public SimpleAggregateNAryFunction(BiFunction<Integer, BindingSet, Value> evaluationStepByIndex, int arity) {
        super(evaluationStepByIndex);
        this.arity = arity;
    }

    @Override
    public void processAggregate(BindingSet bindingSet, Predicate<List<D>> predicate, T t) throws QueryEvaluationException {
        if (t.hasError()) return;
        try {
            t.addValue(IntStream.range(0, arity).mapToObj(i -> evaluate(i, bindingSet)).toArray(Value[]::new));
        } catch (ValueExprEvaluationException e) {
            t.setTypeError(e);
        }
    }
}
