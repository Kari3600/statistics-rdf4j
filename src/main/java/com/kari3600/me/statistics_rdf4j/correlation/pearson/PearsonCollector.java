package com.kari3600.me.statistics_rdf4j.correlation.pearson;

import com.kari3600.me.statistics_rdf4j.MultivariateStatisticCollector;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

public class PearsonCollector extends MultivariateStatisticCollector {
    public PearsonCollector(boolean population, int k) {
        super(population, k);
    }

    @Override
    protected Literal computeValue() {
        double[] std = statistics.getStandardDeviation();
        return SimpleValueFactory.getInstance().createLiteral(
                statistics.getCovariance().getEntry(0, 1) / (std[0] * std[1])
        );
    }
}
