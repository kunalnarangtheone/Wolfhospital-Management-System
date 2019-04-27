package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Enters a new record in the Wards table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "create-ward", mixinStandardHelpOptions = true)
public class CreateWard implements Callable<Void> {

    /**
     * The capacity of the Ward.
     */
    @Option(names = "--capacity", description = "Ward capacity name.", required = true)
    private int capacity;
    
    /**
     * The charges per day of the Ward.
     */
    @Option(names = "--charges", description = "Charges (cost) per day.", required = true)
    private int charges;
    
    /**
     * The Staff ID of the assigned StaffMember of the Ward.
     */
    @Option(names = "--staff-id", description = "Staff member assigned to ward", required = true)
    private int staffID;
    
    /**
     * Calls the enterWardInformation() method.
     */
    public Void call() throws SQLException {

        ResultSet rs = InformationProcessing.enterWardInformation(capacity, charges, staffID);
        System.out.println(Table.read().db(rs).print());

        return null;

    }

}
