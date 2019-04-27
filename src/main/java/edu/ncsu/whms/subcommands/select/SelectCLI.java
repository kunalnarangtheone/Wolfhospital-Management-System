package edu.ncsu.whms.subcommands.select;


// Imports
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


/**
 * Selecting API.
 *
 * Supports the reports section of tasks and operations.
 *
 * @author jmmacdo4.
 */
@Command(name = "select", mixinStandardHelpOptions = true, subcommands = {
    Beds.class,
    BillingRecords.class,
    BillingStatements.class,
    Patients.class,
    StaffMembers.class,
    Tests.class,
    Treatments.class,
    Visits.class,
    Wards.class
})
public class SelectCLI implements Callable<Void> {

    /**
     * Select API.
     * Execute the select API command through the command line. 
     */
    public Void call() {

        // ALl interaction is through subcommands, so print usage and exit.
        CommandLine.usage(this, System.out);
        return null;

    }

}
