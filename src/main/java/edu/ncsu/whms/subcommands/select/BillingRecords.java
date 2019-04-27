package edu.ncsu.whms.subcommands.select;


// Imports
import edu.ncsu.whms.operations.Select;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Execute select statements over BillingRecords table through the command line.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "billing-records", mixinStandardHelpOptions = true)
public class BillingRecords implements Callable<Void> {

    /**
     * Select query API for BillingRecords table.
     * Execute the select query over the BillingRecords table.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet statement = Select.selectBillingRecords();
        System.out.println(Table.read().db(statement).printAll());

        return null;

    }

}
