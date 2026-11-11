package com.kari3600.me.statistics_rdf4j;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class TestUtil {
    public static void query(String dataset, String queryString, Consumer<TupleQueryResult> resultConsumer) {
        Repository db = new SailRepository(new MemoryStore());

        // Open a connection to the database
        try (RepositoryConnection conn = db.getConnection()) {
            try (InputStream input = TestUtil.class.getResourceAsStream("/" + dataset)) {
                if (input == null) {
                    throw new RuntimeException(dataset + " not found");
                }
                conn.add(input, "", RDFFormat.TURTLE);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load dataset: "+e);
            }

            queryString = "PREFIX stat: <http://me.kari3600.com/statistics-rdf4j#>\n"+queryString;

            TupleQuery query = conn.prepareTupleQuery(queryString);

            try (TupleQueryResult result = query.evaluate()) {
                resultConsumer.accept(result);
            }
        } finally {
            // Before our program exits, make sure the database is properly shut down.
            db.shutDown();
        }
    }

    public static Literal query1Group1Var(String dataset, String queryString, String variable) {
        AtomicReference<Literal> result = new AtomicReference<>();
        query(dataset, queryString, rs -> result.set((Literal) rs.next().getValue(variable)));
        return result.get();
    }
}
