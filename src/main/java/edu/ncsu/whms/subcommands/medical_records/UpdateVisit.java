package edu.ncsu.whms.subcommands.medical_records;


// Imports
import edu.ncsu.whms.operations.MedicalRecords;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


/**
 * Updates a record in the Visits table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "update-visit", mixinStandardHelpOptions = true)
public class UpdateVisit implements Callable<Void> {

    /**
     * ID of the Visit.
     */
    @CommandLine.Option(names = { "--visit-id" }, required = true, description = "ID")
    private int id;

    /**
     * Patient ID of the Visit.
     */
    @CommandLine.Option(names = { "--patient-id" }, description = "Patient ID")
    private Integer patientID;

    /**
     * Start date of the Visit.
     */
    @CommandLine.Option(names = { "--start" }, description = "Start Date")
    private String start;
    /**
     * End date of the Visit.
     */
    @CommandLine.Option(names = { "--end" }, description = "End Date")
    private String end;

    /**
     * Whether a visit end date is being unset instead of not updated.
     */
    @CommandLine.Option(names = "--unset-end", description = "Set end date to null.", defaultValue = "false")
    private boolean unsetEnd;

    /**
     * Calls the updateVisit() method.
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

        // Get all parameters being updated
        Map<String, Object> values = new HashMap<>();
        if (patientID != null) values.put("PatientID", patientID);
        if (startDate != null) values.put("StartDate", startDate);
        if (endDate   != null) values.put("EndDate",   endDate);
        else if (unsetEnd)     values.put("EndDate",   null);

        MedicalRecords.updateVisit(id, values);

        return null;

    }

}
