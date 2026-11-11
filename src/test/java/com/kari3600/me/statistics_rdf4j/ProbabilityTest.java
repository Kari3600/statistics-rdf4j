package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbabilityTest {
    @Test
    public void testEarthquakesCasualtiesGreaterThan1000() {
        assertEquals(0.2245088, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?casualties > 1000) AS ?prob)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:casualties ?casualties .
                }
                """, "prob").floatValue(), 0.001);
    }
}
