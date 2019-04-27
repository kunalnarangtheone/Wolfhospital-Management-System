package edu.ncsu.whms.subcommands.reports;


// Imports
import edu.ncsu.whms.operations.Reports;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import tech.tablesaw.api.Table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Reporting API for medical records.
 *
 * @author ewhorton
 */
@Command(name = "medical-records-in-span", mixinStandardHelpOptions = true)
public class MedicalRecordsInSpan implements Callable<Void> {

    /**
     * Patient ID to search for.
     */
    @Option(names = { "--patient-id" }, required = true, description = "Patient ID")
    private int patientID;

    /**
     * Start date of the date range to search in.
     */
    @Option(names = { "--start" }, required = true, description = "Start Date")
    private String start;

    /**
     * End date for the date range to search in.
     */
    @Option(names = { "--end" }, required = true, description = "End Date")
    private String end;

    /**
     * Reporting Medical Records in a range API.
     * Execute the command to find medical records in a range of time.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        Date startDate = null;
        Date endDate = null;

        if (start != null) {
            try {
                startDate = Date.valueOf(start);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to parse start date", e);
            }
        }

        if (end != null) {
            try {
                endDate = Date.valueOf(end);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to parse end date", e);
            }
        }

        ResultSet medicalRecords = Reports.getMedicalRecordsInRange(patientID, startDate, endDate);
        System.out.println(Table.read().db(medicalRecords).print());

        return null;

    }

}
