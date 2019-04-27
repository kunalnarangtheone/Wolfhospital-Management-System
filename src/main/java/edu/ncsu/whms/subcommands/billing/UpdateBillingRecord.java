package edu.ncsu.whms.subcommands.billing;


// Imports
import edu.ncsu.whms.operations.Billing;
import picocli.CommandLine;

import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Read and execute command line to update a billing record.
 * Attribute values are passed by command line from the user.
 * Implements Callable interface.
 * 
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "update-billing-record", mixinStandardHelpOptions = true)
public class UpdateBillingRecord implements Callable<Void> {

    /**
     * ID of the Billing Record being updated.
     */
    @CommandLine.Option(names = { "--billing-record-id" }, required = true, description = "Billing Record ID")
    private int id;

    /**
     * RegistrationFee value entered to updated the Billing Record
     * If null the value is not updated
     */
    @CommandLine.Option(names = { "--registration-fee" }, description = "Registration Fee")
    private int registrationFee;

    /**
     * Accommodation Fee value entered to updated the Billing Record
     * If null the value is not updated
     */
    @CommandLine.Option(names = { "--accommodation-fee" }, description = "Accommodation Fee")
    private int accommodationFee;

    /**
     * ID of the Billing Statement linked to the Billing Record being updated.
     */
    @CommandLine.Option(names = { "--billing-statement-id" }, description = "Billing Statement ID")
    private int billingStatementID;

    /**
     * Whether medication was prescribed on the billing record.
     */
    @CommandLine.Option(names = "--medication-prescribed", description = "Medication prescribed for record")
    private String medicationPrescribed;

    /**
     * Reporting medical billing API for billing records.
     * Execute the generate billing records command.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        Billing.updateBillingRecord(id, registrationFee, accommodationFee, billingStatementID, medicationPrescribed);

        return null;

    }

}
