package edu.ncsu.whms.subcommands.billing;


// Imports
import edu.ncsu.whms.operations.Billing;
import picocli.CommandLine;
import tech.tablesaw.api.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Read and execute command line to generate billing records.
 * Attribute values are passed by command line from the user.
 * Implements the Callable interface. 
 * 
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "create-billing-record", mixinStandardHelpOptions = true)
public class CreateBillingRecord implements Callable<Void> {

    /**
     * Billing Statement ID of the new billing record.
     */
    @CommandLine.Option(names = { "--billing-statement-id" }, required = true, description = "Billing Statement ID")
    private int billingStatementID;

    /**
     * Registration fee for the new billing record.
     */
    @CommandLine.Option(names = { "--registration-fee" }, required = true, description = "Registration Fee")
    private int registrationFee;

    /**
     * Accommodation fee of the new billing record.
     */
    @CommandLine.Option(names = { "--accommodation-fee" }, required = true, description = "Accommodation Fee")
    private int accommodationFee;

    /**
     * Whether medication was prescribed on the billing record.
     */
    @CommandLine.Option(names = "--medication-prescribed", description = "Medication prescribed for record", required = true)
    private String medicationPrescribed;
    
    /**
     * Reporting medical billing API for billing records.
     * Execute the generate billing record command.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet statement = Billing.generateBillingRecord(registrationFee, accommodationFee, billingStatementID, medicationPrescribed);
        System.out.println(Table.read().db(statement).print());

        return null;

    }

}
