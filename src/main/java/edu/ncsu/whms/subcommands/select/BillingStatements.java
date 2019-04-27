package edu.ncsu.whms.subcommands.select;


// Imports
import edu.ncsu.whms.operations.Select;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Execute select statements over BillingStatements table through the command line.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "billing-statements", mixinStandardHelpOptions = true)
public class BillingStatements implements Callable<Void> {

    /**
     * Select query API for BillingStatements table.
     * Execute the select query over the BillingStatements table.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet statement = Select.selectBillingStatements();
        System.out.println(Table.read().db(statement).printAll());

        return null;

    }

}
