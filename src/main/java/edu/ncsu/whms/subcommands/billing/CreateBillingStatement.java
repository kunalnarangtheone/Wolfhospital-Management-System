package edu.ncsu.whms.subcommands.billing;


// Imports
import edu.ncsu.whms.operations.Billing;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Read and execute command line to generate billing statements.
 * Attribute values are passed by command line from the user.
 * Implements Callable interface.
 * 
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "create-billing-statement", mixinStandardHelpOptions = true)
public class CreateBillingStatement implements Callable<Void> {

    /**
     * Visit ID of the Billing Statement being generated.
     */
    @CommandLine.Option(names = { "--visit-id" }, required = true, description = "Visit ID")
    private int visitID;

    /**
     * SSN of the person paying for the Billing Statement being generated.
     */
    @CommandLine.Option(names = { "--ssn" }, required = true, description = "Recipient SSN")
    private String recipientSSN;

    /**
     * Address related to the paying method for the Billing Statement being generated.
     */
    @CommandLine.Option(names = { "--address" }, description = "Billing Address")
    private String billingAddress;

    /**
     * Method of payment for the billing statement.
     */
    @CommandLine.Option(names = "--payment-method", description = "Payment method")
    private String paymentMethod;

    /**
     * Card number of the paying method for the Billing Statement being generated.
     */
    @CommandLine.Option(names = { "--card-number" }, description = "Credit Card Number")
    private String cardNumber;

    /**
     * Reporting medical billing API for billing statements.
     * Execute the generate billing statement command.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet statement = Billing.generateBillingStatement(visitID, recipientSSN, billingAddress, paymentMethod, cardNumber);
        System.out.println(Table.read().db(statement).print());

        return null;

    }

}
