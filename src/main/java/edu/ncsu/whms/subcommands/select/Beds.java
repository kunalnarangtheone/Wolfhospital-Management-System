package edu.ncsu.whms.subcommands.select;


// Imports
import edu.ncsu.whms.operations.Select;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Execute select statements over Beds table through the command line.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "beds", mixinStandardHelpOptions = true)
public class Beds implements Callable<Void> {

    /**
     * Select query API for Beds table.
     * Execute the select query over the Beds table.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet statement = Select.selectBeds();
        System.out.println(Table.read().db(statement).printAll());

        return null;

    }

}
