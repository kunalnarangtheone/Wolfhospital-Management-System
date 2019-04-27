package edu.ncsu.whms.subcommands.medical_records;


// Imports
import edu.ncsu.whms.operations.MedicalRecords;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Creates a new record in the treatment table.
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 *
 */
@CommandLine.Command(name = "create-treatment", mixinStandardHelpOptions = true)
public class CreateTreatment implements Callable<Void> {

    /**
     * The Prescription of the Treatment.
     */
    @CommandLine.Option(names = { "--prescription" }, required = true, description = "Prescription")
    private String prescription;

    /**
     * The Visit ID of the patient. 
     */
    @CommandLine.Option(names = { "--visit-id" }, required = true, description = "Visit ID")
    private int visitID;

    /**
     * The staff ID of the StaffMember.
     */
    @CommandLine.Option(names = { "--staff-id" }, required = true, description = "Staff ID")
    private int staffID;

    /**
     * Call the createTreatment() method 
     */
    public Void call() throws SQLException {

        ResultSet statement = MedicalRecords.createTreatment(prescription, visitID, staffID);
        System.out.println(Table.read().db(statement).print());

        return null;

    }

}
