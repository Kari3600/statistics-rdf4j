package com.kari3600.me.statistics_rdf4j;

import org.junit.jupiter.api.Test;

import static com.kari3600.me.statistics_rdf4j.TestUtil.query1Group1Var;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CovarianceTest {
    @Test
    public void testCountryHdiGini() {
        assertEquals(-0.4060886594957206, query1Group1Var("countries.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?hdi, ?gini) AS ?cov)
            WHERE {
                ?city a dbo:Country ;
                dbp:gini ?gini ;
                dbp:hdi ?hdi .
            }
            """, "cov").floatValue(), 0.001);
    }

    @Test
    public void testEarthquakesCasualtiesMagnitude() {
        assertEquals(3367.732443881537, query1Group1Var("earthquakes.ttl",
            """
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbp: <http://dbpedia.org/property/>
            SELECT (stat:covariance(?casualties, ?magnitude) AS ?cov)
            WHERE {
                ?city a dbo:Earthquake ;
                dbp:casualties ?casualties ;
                dbp:magnitude ?magnitude .
            }
            """, "cov").floatValue(), 0.001);
    }
}
