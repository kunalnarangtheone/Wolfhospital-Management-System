package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import tech.tablesaw.api.Table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Enter a new record in the Patients table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "create-patient", mixinStandardHelpOptions = true)
public class CreatePatient implements Callable<Void> {

    /**
     * Patient identifier.
     */
    @Option(names = "--patient-id", description = "Patient identifier. One will be generated if not specified.")
    private Integer id;

    /**
     * The SSN of the patient.
     */
    @Option(names = "--ssn", description = "Patient ssn.")
    private String ssn;
    
    /**
     * The name of the patient.
     */
    @Option(names = "--name", description = "Patient name.", required = true)
    private String name;
    
    /**
     * The date of birth of the patient.
     */
    @Option(names = "--dob", description = "Date of birth.", required = true)
    private String dob;
    
    /**
     * The gender of the patient.
     */
    @Option(names = "--gender", description = "Gender.", required = true)
    private String gender;
    
    /**
     * The phone number of the patient.
     */
    @Option(names = "--phone", description = "Phone number", required = true)
    private String phone;
    
    /**
     * The address of the patient.
     */
    @Option(names = "--address", description = "Address", required = true)
    private String address;
    
    /**
     * The treatment plan of the patient.
     */
    @Option(names = "--processing-treatment-plan", description = "Patient treatment plan", required = true)
    private int processingTreatmentPlan;

    /**
     * Whether the patient is currently completing treatment.
     */
    @Option(names = "--completing-treatment", description = "Whether the patient is currently completing treatment", required = true)
    private String completingTreatment;
    
    /**
     * Calls the enterPatientInformation() 
     */
    public Void call() throws SQLException {

        Date sqlDOB = null;
        if (dob != null) {
            try {
                sqlDOB = Date.valueOf(dob);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to parse date of birth", e);
            }
        }

        ResultSet rs;
        if (id != null) {
            rs = InformationProcessing.enterPatientInformation(id, ssn, name, sqlDOB, gender, phone, address, processingTreatmentPlan, completingTreatment);
        }
        else {
            rs = InformationProcessing.enterPatientInformation(ssn, name, sqlDOB, gender, phone, address, processingTreatmentPlan, completingTreatment);
        }
        System.out.println(Table.read().db(rs).print());

        return null;

    }

}
