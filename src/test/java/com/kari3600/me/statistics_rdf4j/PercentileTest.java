package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentileTest {
    @Test
    public void testEarthquakesMagnitude() {
        assertEquals(7.9, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?magnitude, 0.9) AS ?percentile)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:magnitude ?magnitude .
                }
                """, "percentile").floatValue(), 0.1);
        assertEquals(5.8, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:percentile(?magnitude, 0.1) AS ?percentile)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:magnitude ?magnitude .
                }
                """, "percentile").floatValue(), 0.1);
    }
}
