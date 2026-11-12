package com.kari3600.me.statistics_rdf4j.probability;

import com.kari3600.me.statistics_rdf4j.CollectorUtil;
import com.kari3600.me.statistics_rdf4j.SimpleAggregateCollector;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

public class ProbabilityCollector extends SimpleAggregateCollector {
    long matches = 0;
    long total = 0;

    @Override
    public Value computeValue() {
        return SimpleValueFactory.getInstance().createLiteral((double) matches / total);
    }

    @Override
    public void addValue(Value value) throws ValueExprEvaluationException {
        if (CollectorUtil.asBoolean(value)) {
            matches++;
        }
        total++;
    }
}
