package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PearsonTest {
    @Test
    public void testEarthquakesCasualtiesMagnitude() {
        assertEquals(0.1459410339, query1Group1Var("earthquakes.ttl",
                """
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT (stat:pearson(?casualties, ?magnitude) AS ?corr)
                WHERE {
                    ?city a dbo:Earthquake ;
                    dbp:casualties ?casualties ;
                    dbp:magnitude ?magnitude .
                }
                """, "corr").floatValue(), 0.001);
    }
}
