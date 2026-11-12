package com.kari3600.me.statistics_rdf4j.chi_squared;

import com.kari3600.me.statistics_rdf4j.SimpleAggregateNAryCollector;
import com.kari3600.me.utils.collections.CollectionUtil;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

import java.util.*;

public class ChiSquaredCollector extends SimpleAggregateNAryCollector {
    private final int variableCount;
    private int total = 0;
    private final Map<List<Value>, Long> map = new HashMap<>();
    private final List<Map<Value, Long>> groupedMap = new ArrayList<>();

    public ChiSquaredCollector(int variableCount) {
        this.variableCount = variableCount;
        for (int i = 0; i < variableCount; i++) {
            groupedMap.add(new HashMap<>());
        }
    }

    @Override
    public void addValue(Value[] vals) throws ValueExprEvaluationException {
        List<Value> values = Arrays.asList(vals);
        map.compute(values, (_, v) -> v == null ? 1L : v + 1);
        for (int i = 0; i < variableCount; i++) {
            Map<Value, Long> group = groupedMap.get(i);
            Value value = values.get(i);
            group.compute(value, (_, v) -> v == null ? 1L : v + 1);
        }
        total++;
    }

    @Override
    protected Value computeValue() {
        double val = CollectionUtil.combinations(groupedMap, Map::entrySet).asStream()
                .mapToDouble(combination -> {
                    List<Value> key = combination.stream()
                            .map(Map.Entry::getKey)
                            .toList();
                    double expectedCount = combination.stream()
                            .mapToDouble(Map.Entry::getValue)
                            .reduce(total, (l1, l2) -> l1 / total * l2);
                    long actualCount = map.getOrDefault(key, 0L);

                    return (actualCount - expectedCount) * (actualCount - expectedCount) / expectedCount;
                }).sum();
        return SimpleValueFactory.getInstance().createLiteral(val);
    }
}
