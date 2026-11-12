package com.kari3600.me.statistics_rdf4j.median;

import com.kari3600.me.statistics_rdf4j.TDigestAggregateCollector;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

public class ApproximateMedianCollector extends TDigestAggregateCollector {
    @Override
    protected Value computeValue() {
        return SimpleValueFactory.getInstance().createLiteral(
                tdigest.getQuantile(0.5)
        );
    }
}
