package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Deletes a record from the ward table.
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 *
 */
@Command(name = "delete-ward", mixinStandardHelpOptions = true)
public class DeleteWard implements Callable<Void> {

    @Option(names = "--ward-number",  description = "Ward identifier", required = true)
    private int wardNumber;
    
    /**
     * Calls the deleteWardInformation() method.
     */
    public Void call() throws SQLException {

        InformationProcessing.deleteWardInformation(wardNumber);

        return null;

    }

}
