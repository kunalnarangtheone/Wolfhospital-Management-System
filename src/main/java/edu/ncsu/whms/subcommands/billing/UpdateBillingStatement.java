package edu.ncsu.whms.subcommands.billing;


// Imports
import edu.ncsu.whms.operations.Billing;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


/**
 * Read and execute command line to update a billing statement.
 * Attribute values are passed by command line from the user.
 * Implements Callable interface.
 * 
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "update-billing-statement", mixinStandardHelpOptions = true)
public class UpdateBillingStatement implements Callable<Void> {

    /**
    * ID of the Billing Statement being updated.
    */
    @CommandLine.Option(names = { "--billing-statement-id" }, required = true, description = "Billing Statement ID")
    private int id;

    /**
     * Visit ID linked to the Billing Statement being updated.
     */
    @CommandLine.Option(names = { "--visit-id" }, description = "Visit ID")
    private Integer visitID;

    /**
     * SSN number to update on the Billing Statement.
     * If null the attribute will not be updated.
     */
    @CommandLine.Option(names = { "--ssn" }, description = "Recipient SSN")
    private String recipientSSN;

    /**
     * Address related to the paying method for the Billing Statement being updated.
     * If null the attribute will not be updated.
     */
    @CommandLine.Option(names = { "--address" }, description = "Billing Address")
    private String billingAddress;

    /**
     * Whether to unset billing address.
     */
    @CommandLine.Option(names = "--unset-address", description = "Set address to null")
    private boolean unsetBillingAddress;

    /**
     * Payment method for the Billing Statement being updated.
     * If null the attribute will not be updated.
     */
    @CommandLine.Option(names = "--payment-method", description = "Payment method")
    private String paymentMethod;

    /**
     * Whether to unset payment method.
     */
    @CommandLine.Option(names = "--unset-payment-method", description = "Set payment method to null")
    private boolean unsetPaymentMethod;

    /**
     * Card number of the paying method for the Billing Statement being updated.
     * If null the attribute will not be updated.
     */
    @CommandLine.Option(names = { "--card-number" }, description = "Credit Card Number")
    private String cardNumber;

    /**
     * Whether to unset card number.
     */
    @CommandLine.Option(names = "--unset-card-number", description = "Set card number to null")
    private boolean unsetCardNumber;

    /**
     * Updating medical billing API for billing statements.
     * Execute the generate billing statement command.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        Map<String, Object> values = new HashMap<>();
        if      (visitID             != null) values.put("VisitID",        visitID);
        if      (recipientSSN        != null) values.put("RecipientSSN",   recipientSSN);
        if      (billingAddress      != null) values.put("BillingAddress", billingAddress);
        else if (unsetBillingAddress)         values.put("BillingAddress", null);
        if      (paymentMethod       != null) values.put("PaymentMethod",  paymentMethod);
        else if (unsetPaymentMethod)          values.put("PaymentMethod",  null);
        if      (cardNumber          != null) values.put("CardNumber",     cardNumber);
        else if (unsetCardNumber)             values.put("CardNumber",     null);

        Billing.updateBillingStatement(id, values);

        return null;

    }

}
