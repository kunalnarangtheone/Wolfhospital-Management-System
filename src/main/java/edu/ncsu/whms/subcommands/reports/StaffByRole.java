package edu.ncsu.whms.subcommands.reports;


// Imports
import edu.ncsu.whms.operations.Reports;
import picocli.CommandLine.Command;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Reporting API for staff members.
 */
@Command(name = "staff-by-role", mixinStandardHelpOptions = true)
public class StaffByRole implements Callable<Void> {

    /**
     * Reporting staff API.
     * Execute the command to report Staff information by role.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet staff = Reports.getStaffInformationByRole();
        System.out.println(Table.read().db(staff).print());

        return null;

    }

}
