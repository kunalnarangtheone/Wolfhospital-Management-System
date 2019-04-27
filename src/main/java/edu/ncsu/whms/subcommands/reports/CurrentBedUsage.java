package edu.ncsu.whms.subcommands.reports;


// Imports
import edu.ncsu.whms.operations.Reports;
import picocli.CommandLine.Command;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Reporting API for bed usage.
 * 
 * @author ewhorton
 */
@Command(name = "current-bed-usage", mixinStandardHelpOptions = true)
public class CurrentBedUsage implements Callable<Void> {

    /**
     * Reporting current bed usage.
     * Execute the query to search the current usage of beds.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet usage = Reports.getCurrentBedUsage();
        System.out.println(Table.read().db(usage).print());

        return null;

    }

}
