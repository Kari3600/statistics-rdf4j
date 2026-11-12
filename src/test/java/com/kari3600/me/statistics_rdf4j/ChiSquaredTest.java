package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChiSquaredTest {
    @Test
    public void testGFG() {
        assertEquals(4.0212, query1Group1Var("GFG_example.ttl",
                """
                PREFIX ex: <http://example.org/>
    
                SELECT (stat:chi_sq(?gender, ?order) AS ?chi_sq)
                WHERE {
                    ?person a ex:Person ;
                    ex:gender ?gender ;
                    ex:order ?order .
                }
                """, "chi_sq").doubleValue(), 0.001);
    }
}
