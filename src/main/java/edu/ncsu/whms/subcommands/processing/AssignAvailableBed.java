package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import tech.tablesaw.api.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;


/**
 * Assigns an available bed.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "assign-available-bed", mixinStandardHelpOptions = true)
public class AssignAvailableBed implements Callable<Void> {

    @Option(names = "--patient-id", description = "Patient identifier", required = true)
    private int id;

    @Option(names = "--capacity", description = "Capacity preference")
    private List<Integer> capacity;

    /**
     * Calls the assignAvailableBed() method
     */
    public Void call() throws SQLException {

        ResultSet rs;
        if (capacity != null && capacity.size() > 0) {
            rs = InformationProcessing.assignAvailableBed(id, capacity);
        }
        else {
            rs = InformationProcessing.assignAvailableBed(id);
        }

        System.out.println(Table.read().db(rs).print());

        return null;

    }

}