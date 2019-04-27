package edu.ncsu.whms.subcommands;


// Imports
import edu.ncsu.whms.ConnectionFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


/**
 * Initialization API.
 */
@Command(name = "init", mixinStandardHelpOptions = true)
public class Init implements Callable<Void> {

    /**
     * Map containing dataset file options.
     */
    private static final Map<String, String> DATASETS = new HashMap<>();
    static {
        DATASETS.put("test", "test.sql");
        DATASETS.put("demo", "demo.sql");
    }

    /**
     * Name for the sql file containing DDL.
     */
    private static final String DDL = "ddl.sql";

    /**
     * Name for the default dataset.
     */
    private static final String DEFAULT_DATASET_NAME = "demo";

    /**
     * Default dataset
     */
    @Option(names = "--dataset", defaultValue = DEFAULT_DATASET_NAME, description = "Which dataset to pull initial data from.")
    private String dataset;

    /**
     * Initialize the database.
     */
    public Void call() {

        if (!DATASETS.containsKey(dataset)) {
            System.out.println(String.format("No dataset named: %s", dataset));
            System.exit(1);
        }

        loadAndExecuteSQLFile(DDL);
        loadAndExecuteSQLFile(DATASETS.get(dataset));
        return null;

    }

    /**
     * Load and execute all queries in a SQL file.
     *
     * @param file File to execute.
     */
    private void loadAndExecuteSQLFile(String file) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            // Load file as input stream
            InputStream input = ClassLoader.getSystemResourceAsStream(file);
            if (input == null) {
                throw new RuntimeException(String.format("Unable to load resource file %s", file));
            }

            // Read sql file
            String sql = new BufferedReader(new InputStreamReader(input)).lines().collect(Collectors.joining("\n"));

            // Create statement
            Statement st = conn.createStatement();

            // Get individual queries
            List<String> queries = Arrays.stream(sql.split(";")).map(String::trim).collect(Collectors.toList());

            // Add each individual query
            for (String query : queries) {
                if (!query.isEmpty()) {
                    System.out.println();
                    System.out.println(query);
                    st.addBatch(query);
                }
            }

            // Execute
            st.executeBatch();

        }
        catch (SQLException  e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

}
