package com.kari3600.me.statistics_rdf4j;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.BooleanLiteral;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;

public class CollectorUtil {
    public static boolean asBoolean(Value val) throws ValueExprEvaluationException {
        if (val instanceof BooleanLiteral nextLiteral) {
            return nextLiteral.booleanValue();
        } else {
            throw new ValueExprEvaluationException("not a boolean literal: " + val);
        }
    }

    public static double asDouble(Value val) throws ValueExprEvaluationException {
        if (val instanceof Literal nextLiteral) {
            if (nextLiteral.getCoreDatatype()
                    .asXSDDatatype()
                    .orElseThrow(() -> new ValueExprEvaluationException(val + " is not an XSD type literal"))
                    .isNumericDatatype()) {
                return nextLiteral.doubleValue();
            } else {
                throw new ValueExprEvaluationException("not a number: " + val);
            }
        } else {
            throw new ValueExprEvaluationException("not a literal: " + val);
        }
    }
}
