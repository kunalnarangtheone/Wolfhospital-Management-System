package edu.ncsu.whms.subcommands.select;


// Imports
import edu.ncsu.whms.operations.Select;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Execute select statements over StaffMembers table through the command line
 * Implements Callable interface 
 * 
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@CommandLine.Command(name = "staff-members", mixinStandardHelpOptions = true)
public class StaffMembers implements Callable<Void> {

    /**
     * Select query API for StaffMembers table.
     * Execute the select query over Staff Members.
     * If an error occurs, the stack trace is printed and the system exits.
     */
    public Void call() throws SQLException {

        ResultSet statement = Select.selectStaffMembers();
        System.out.println(Table.read().db(statement).printAll());

        return null;

    }

}
