package com.kari3600.me.statistics_rdf4j.probability;

import com.kari3600.me.statistics_rdf4j.CollectorUtil;
import com.kari3600.me.statistics_rdf4j.SimpleAggregateNAryCollector;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

public class ConditionalProbabilityCollector extends SimpleAggregateNAryCollector {
    long matches = 0;
    long total = 0;

    @Override
    public void addValue(Value[] vals) throws ValueExprEvaluationException {
        boolean b = CollectorUtil.asBoolean(vals[1]);
        if (!b) return;
        boolean a = CollectorUtil.asBoolean(vals[0]);
        total++;
        if (!a) return;
        matches++;
    }

    @Override
    protected Value computeValue() {
        return SimpleValueFactory.getInstance().createLiteral(
                (double) matches / total
        );
    }
}
