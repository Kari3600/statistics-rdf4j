package com.kari3600.me.statistics_rdf4j;

import org.apache.datasketches.tdigest.TDigestDouble;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

public abstract class TDigestAggregateCollector extends SimpleAggregateCollector {
    private static final short BINS = 100;

    protected final TDigestDouble tdigest;

    public TDigestAggregateCollector() {
        this(BINS);
    }

    public TDigestAggregateCollector(short bins) {
        this.tdigest = new TDigestDouble(bins);
    }

    @Override
    public void addValue(Value val) throws ValueExprEvaluationException {
        tdigest.update(CollectorUtil.asDouble(val));
    }
}
