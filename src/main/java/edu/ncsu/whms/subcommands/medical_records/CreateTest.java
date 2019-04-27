package edu.ncsu.whms.subcommands.medical_records;


// Imports
import edu.ncsu.whms.operations.MedicalRecords;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Creates a new record in the test table.
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 *
 */
@CommandLine.Command(name = "create-test", mixinStandardHelpOptions = true)
public class CreateTest implements Callable<Void> {

    /**
     * The Diagnosis for the Test. 
     */
    @CommandLine.Option(names = { "--diagnosis" }, required = true, description = "Diagnosis")
    private String diagnosis;

    /**
     * The Visit ID of the Patient.
     */
    @CommandLine.Option(names = { "--visit-id" }, required = true, description = "Visit ID")
    private int visitID;

    /**
     * The staff ID of the StaffMember.
     */
    @CommandLine.Option(names = { "--staff-id" }, required = true, description = "Staff ID")
    private int staffID;

    /**
     * Calls the createTest() method
     */
    public Void call() throws SQLException {

        ResultSet statement = MedicalRecords.createTest(diagnosis, visitID, staffID);
        System.out.println(Table.read().db(statement).print());

        return null;

    }

}
