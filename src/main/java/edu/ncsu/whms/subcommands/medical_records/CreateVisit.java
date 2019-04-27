package edu.ncsu.whms.subcommands.medical_records;


// Imports
import edu.ncsu.whms.operations.MedicalRecords;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Creates a new record in the Visit table.
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 *
 */
@CommandLine.Command(name = "create-visit", mixinStandardHelpOptions = true)
public class CreateVisit implements Callable<Void> {

    /**
     * Patient ID of the patient.
     */
    @CommandLine.Option(names = { "--patient-id" }, required = true, description = "Patient ID")
    private int patientID;

    /**
     * Start date of the Visit.
     */
    @CommandLine.Option(names = { "--start" }, required = true, description = "Start Date")
    private String start;

    /**
     * End date of the visit.
     */
    @CommandLine.Option(names = { "--end" }, description = "End Date")
    private String end;

    /**
     * Calls the createVisit() method.
     */
    public Void call() throws SQLException {

        Date endDate = null;
        Date startDate = null;

        try {
            startDate = Date.valueOf(start);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to parse start date.");
        }

        if (end != null) {
            try {
                endDate = Date.valueOf(end);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to parse end date.", e);
            }
        }

        ResultSet statement = MedicalRecords.createVisit(patientID, startDate, endDate);
        System.out.println(Table.read().db(statement).print());

        return null;

    }

}
