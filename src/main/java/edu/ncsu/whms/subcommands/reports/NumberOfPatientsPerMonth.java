package edu.ncsu.whms.subcommands.reports;


// Imports
import edu.ncsu.whms.operations.Reports;
import picocli.CommandLine.Command;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Reporting API for patient visits per month.
 *
 * @author ewhorton
 */
@Command(name = "patients-per-month", mixinStandardHelpOptions = true)
public class NumberOfPatientsPerMonth implements Callable<Void> {

    /**
     * Reporting visits per month API.
     * Execute the command to calculate and report number of patients per month.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet stats = Reports.getNumberOfPatientsPerMonth();
        System.out.println(Table.read().db(stats).print());

        return null;

    }

}
