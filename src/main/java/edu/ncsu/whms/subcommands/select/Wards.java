package edu.ncsu.whms.subcommands.select;


// Imports
import edu.ncsu.whms.operations.Select;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Execute select statements over Wards table through the command line.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "wards", mixinStandardHelpOptions = true)
public class Wards implements Callable<Void> {

    /**
     * Select query API for Wards table.
     * Execute the select query over Wards.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet statement = Select.selectWards();
        System.out.println(Table.read().db(statement).printAll());

        return null;

    }

}
