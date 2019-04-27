package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Deletes a record from the Staff table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "delete-staff", mixinStandardHelpOptions = true)
public class DeleteStaff implements Callable<Void> {

    @Option(names = "--staff-id", description="Staff member identifier.", required = true)
    private int id;
    
    /**
     * Calls the deleteStaffInformation() method
     */
    public Void call() throws SQLException {

        InformationProcessing.deleteStaffInformation(id);

        return null;

    }

}
