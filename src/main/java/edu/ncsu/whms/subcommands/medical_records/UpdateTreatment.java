package edu.ncsu.whms.subcommands.medical_records;


// Imports
import edu.ncsu.whms.operations.MedicalRecords;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Updates a record in the treatment table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "update-treatment", mixinStandardHelpOptions = true)
public class UpdateTreatment implements Callable<Void> {

    /**
     * ID of the treatment.
     */
    @CommandLine.Option(names = { "--treatment-id" }, required = true, description = "ID")
    private int id;

    /**
     * Prescription of the treatment.
     */
    @CommandLine.Option(names = { "--prescription" }, description = "Prescription")
    private String prescription;

    /**
     * Visit ID of the treatment.
     */
    @CommandLine.Option(names = { "--visit-id" }, description = "Visit ID")
    private int visitID;

    /**
     * Staff ID of the treatment.
     */
    @CommandLine.Option(names = { "--staff-id" }, description = "Staff ID")
    private int staffID;

    /**
     * Calls the updateTreatment() method.
     */
    public Void call() throws SQLException {

        MedicalRecords.updateTreatment(id, prescription, visitID, staffID);

        return null;

    }

}
