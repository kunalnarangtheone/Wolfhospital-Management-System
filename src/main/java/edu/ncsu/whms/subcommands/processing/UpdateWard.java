package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Updates a record of the Ward Information.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "update-ward", mixinStandardHelpOptions = true)
public class UpdateWard implements Callable<Void> {

    /**
     * The Ward number of the Ward.
     */
    @Option(names = "--ward-number",  description = "Ward identifier", required = true)
    private int wardNumber;
    
    /**
     * The capacity of the Ward.
     */
    @Option(names = "--capacity", description = "Ward capacity name.")
    private Integer capacity;
    
    /**
     * The charges of the Ward.
     */
    @Option(names = "--charges", description = "Charges (cost) per day.")
    private Integer charges;
    
    /**
     * The staffID of the Ward.
     */
    @Option(names = "--staff-id", description = "Staff member assigned to ward")
    private Integer staffID;
    
    /**
     * Calls the updateWardInformation() method.
     */
    public Void call() throws SQLException {

        InformationProcessing.updateWardInformation(wardNumber, capacity, charges, staffID);

        return null;

    }

}
