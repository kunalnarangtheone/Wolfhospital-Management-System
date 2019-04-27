package edu.ncsu.whms.subcommands.reports;


// Imports
import edu.ncsu.whms.operations.Reports;
import picocli.CommandLine.Command;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Reporting API for occupied beds and capacity.
 *
 * @author ewhorton
 */
@Command(name = "occupied-capacity", mixinStandardHelpOptions = true)
public class OccupiedCapacity implements Callable<Void> {


    /**
     * Reporting occupied beds and capacity API.
     * Execute the command to report occupied beds.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet stats = Reports.getOccupiedBedsAndCapacity();
        System.out.println(Table.read().db(stats).print());

        return null;

    }

}
