package com.kari3600.me.statistics_rdf4j.percentile;

import com.kari3600.me.statistics_rdf4j.CollectorUtil;
import com.kari3600.me.statistics_rdf4j.SimpleAggregateNAryCollector;
import com.kari3600.me.statistics_rdf4j.TDigestAggregateCollector;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

public class ApproximatePercentileCollector extends SimpleAggregateNAryCollector {
    private final TDigestAggregateCollector delegate = new TDigestAggregateCollector() {
        @Override
        protected Value computeValue() {
            return SimpleValueFactory.getInstance().createLiteral(
                    tdigest.getQuantile(percentile)
            );
        }
    };

    private Double percentile;

    @Override
    protected Value computeValue() {
        return delegate.getFinalValue();
    }

    @Override
    public void addValue(Value[] vals) throws ValueExprEvaluationException {
        Value val = vals[0];
        double newPercentile = CollectorUtil.asDouble(vals[1]);
        if (percentile == null) {
            percentile = newPercentile;
        } else if (percentile != newPercentile) {
            throw new ValueExprEvaluationException("percentile changed during aggregation");
        }
        delegate.addValue(val);
    }
}
