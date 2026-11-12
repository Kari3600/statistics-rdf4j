package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbabilityTest {
    @Test
    public void testEarthquakesCasualtiesGreaterThan1000() {
        assertEquals(0.225, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?casualties > 1000) AS ?prob)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:casualties ?casualties .
                }
                """, "prob").doubleValue(), 0.001);
    }

    @Test
    public void testEarthquakesCasualtiesGreaterThan1000IfMagnitudeGreaterThan8() {
        assertEquals(0.333, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:p(?casualties > 1000, ?magnitude > 8) AS ?prob)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:casualties ?casualties ;
                    dbp:magnitude ?magnitude .
                }
                """, "prob").doubleValue(), 0.001);
    }
}
