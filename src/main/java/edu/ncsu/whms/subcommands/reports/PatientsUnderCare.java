package edu.ncsu.whms.subcommands.reports;


// Imports
import edu.ncsu.whms.operations.Reports;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Reporting API for patients under care.
 *
 * @author ewhorton
 */
@Command(name = "patients-under-care", mixinStandardHelpOptions = true)
public class PatientsUnderCare implements Callable<Void> {

    /**
     * Staff ID to search for.
     */
    @Option(names = { "--staff-id" }, required = true, description = "StaffMember ID.")
    private int staffID;

    /**
     * Reporting patients under care API
     * Execute the command to report patients currently under care.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet patients = Reports.getPatientsUnderCare(staffID);
        System.out.println(Table.read().db(patients).print());

        return null;

    }

}
