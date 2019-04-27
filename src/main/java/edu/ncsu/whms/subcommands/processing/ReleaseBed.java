package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Releases a bed assigned to a patient.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "release-bed", mixinStandardHelpOptions = true)
public class ReleaseBed implements Callable<Void> {

    @Option(names = "--ward-number", description = "Ward identifier", required = true)
    private int wardNumber;

    @Option(names = "--bed-number", description = "Bed identifier", required = true)
    private int bedNumber;
    
    /**
     * Calls the releaseBed() method
     */
    public Void call() throws SQLException {

        InformationProcessing.releaseBed(wardNumber, bedNumber);

        return null;

    }

}