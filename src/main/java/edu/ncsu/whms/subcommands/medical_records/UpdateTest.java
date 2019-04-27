package edu.ncsu.whms.subcommands.medical_records;


// Imports
import edu.ncsu.whms.operations.MedicalRecords;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Updates a record in the test table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "update-test", mixinStandardHelpOptions = true)
public class UpdateTest implements Callable<Void> {

    /**
     * ID of the test.
     */
    @CommandLine.Option(names = { "--test-id" }, required = true, description = "ID")
    private int id;

    /**
     * Diagnosis for the test.
     */
    @CommandLine.Option(names = { "--diagnosis" }, description = "Diagnosis")
    private String diagnosis;

    /**
     * Visit ID of the test.
     */
    @CommandLine.Option(names = { "--visit-id" }, description = "Visit ID")
    private int visitID;

    /**
     * Staff ID of the staff member.
     */
    @CommandLine.Option(names = { "--staff-id" }, description = "Staff ID")
    private int staffID;

    /**
     * Calls the updateTest() method.
     */
    public Void call() throws SQLException {

        MedicalRecords.updateTest(id, diagnosis, visitID, staffID);

        return null;

    }

}
