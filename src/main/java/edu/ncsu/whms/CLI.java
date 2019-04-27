package edu.ncsu.whms;

// Imports
import java.util.Map;
import java.util.concurrent.Callable;
import edu.ncsu.whms.subcommands.Init;
import edu.ncsu.whms.subcommands.billing.*;
import edu.ncsu.whms.subcommands.medical_records.*;
import edu.ncsu.whms.subcommands.processing.*;
import edu.ncsu.whms.subcommands.reports.*;
import edu.ncsu.whms.subcommands.select.SelectCLI;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ExecutionException;


/**
 * Wolf Hospital Management System CLI interface.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(
    name = "whms",
    version = "0.1.0",
    mixinStandardHelpOptions = true,
    subcommands = {

        // Helper subcommands

        Init.class,
        SelectCLI.class,

        // Reporting

        CurrentBedUsage.class,
        MedicalRecordsInSpan.class,
        NumberOfPatientsPerMonth.class,
        OccupiedCapacity.class,
        PatientsUnderCare.class,
        StaffByRole.class,

        // Bed assignments

        FindAvailableBed.class,
        AssignAvailableBed.class,
        ReleaseBed.class,

        // CRUD

        CreateBillingRecord.class,
        UpdateBillingRecord.class,

        CreateBillingStatement.class,
        UpdateBillingStatement.class,

        CreatePatient.class,
        UpdatePatient.class,
        DeletePatient.class,

        CreateStaff.class,
        UpdateStaff.class,
        DeleteStaff.class,

        CreateTest.class,
        UpdateTest.class,

        CreateTreatment.class,
        UpdateTreatment.class,


        CreateVisit.class,
        UpdateVisit.class,

        CreateWard.class,
        UpdateWard.class,
        DeleteWard.class,

    }
)
public class CLI implements Callable<Void> {

    // System environment configuration
    private static final Map<String, String> ENV = System.getenv();

    // Database parameters
    private static String HOSTNAME = ENV.getOrDefault("WHMS_HOSTNAME", "localhost");
    private static String PORT = ENV.getOrDefault("WHMS_PORT", "3306");
    private static String DATABASE = ENV.getOrDefault("WHMS_DATABASE", "csc540");

    // JDBC database url
    private static String JDBC_URL = String.format("jdbc:mariadb://%s:%s/%s", HOSTNAME, PORT, DATABASE);

    // Credentials
    private static String USERNAME = ENV.getOrDefault("WHMS_USERNAME", "root");
    private static String PASSWORD = ENV.getOrDefault("WHMS_PASSWORD", "");

    /**
     * Run the Wolf Hospital CLI.
     *
     * @param args Command arguments.
     */
    public static void main(String[] args) {

        // Configure connection factory from environment.
        ConnectionFactory.setJdbcUrl(JDBC_URL);
        ConnectionFactory.setUsername(USERNAME);
        ConnectionFactory.setPassword(PASSWORD);

        // Call command line program with arguments.
        try {
            CommandLine.call(new CLI(), args);
        }
        catch (ExecutionException e) {
            System.out.println("Cannot complete request, we detected the following problem:");
            System.out.println("\t" + e.getCause().getLocalizedMessage());
        }

    }

    /**
     * Core API.
     */
    public Void call() {

        // ALl interaction is through subcommands, so print usage and exit.
        CommandLine.usage(this, System.out);
        return null;

    }

}
