package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Finds an available bed.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "find-available-bed", mixinStandardHelpOptions = true)
public class FindAvailableBed implements Callable<Void> {

    /**
     * Calls the findAvailableBed() method and prints the bed information.
     */
    public Void call() throws SQLException {

        ResultSet rs = InformationProcessing.findAvailableBed();
        System.out.println(Table.read().db(rs).print());

        return null;

    }

}
