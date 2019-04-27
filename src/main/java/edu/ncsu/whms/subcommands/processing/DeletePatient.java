package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Deletes a record from the patient table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "delete-patient", mixinStandardHelpOptions = true)
public class DeletePatient implements Callable<Void> {

    @Option(names = "--patient-id", description="Patient identifier.", required = true)
    private int id;
    
    /**
     * Calls the deletePatientInformation() method
     */
    public Void call() throws SQLException {

        InformationProcessing.deletePatientInformation(id);

        return null;

    }

}
