package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


/**
 * Updates a record in the Patients table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "update-patient", mixinStandardHelpOptions = true)
public class UpdatePatient implements Callable<Void> {

    /**
     * The Patient ID of the patient.
     */
    @Option(names = "--patient-id", description="Patient identifier.", required = true)
    private int id;

    /**
     * The SSN of the patient.
     */
    @Option(names = "--ssn", description = "Patient ssn.")
    private String ssn;

    /**
     * Whether to unset SSN.
     */
    @Option(names = "--unset-ssn", description = "Set SSN to null.")
    private boolean unsetSSN;
    
    /**
     * The Name of the patient.
     */
    @Option(names = "--name", description = "Patient name.")
    private String name;
    
    /**
     * The date of birth of the patient.
     */
    @Option(names = "--dob", description = "Date of birth")
    private String dob;
    
    /**
     * The Gender of the patient.
     */
    @Option(names = "--gender", description = "Gender")
    private String gender;
    
    /**
     * The Phone number of the patient.
     */
    @Option(names = "--phone", description = "Phone number")
    private String phone;
    
    /**
     * The address of the patient.
     */
    @Option(names = "--address", description = "Address")
    private String address;

    /**
     * The treatment plan of the patient.
     */
    @Option(names = "--processing-treatment-plan", description = "Patient treatment plan")
    private Integer processingTreatmentPlan;

    /**
     * Whether the patient is currently completing treatment.
     */
    @Option(names = "--completing-treatment", description = "Whether the patient is currently completing treatment")
    private String completingTreatment;
    
    /**
     * Calls the updatePatientInformation() method.
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

        // Get updated values
        Map<String, Object> values = new HashMap<>();
        if (ssn                     != null) values.put("SSN",                     ssn);
        else if (unsetSSN)                   values.put("SSN",                     null);
        if (name                    != null) values.put("Name",                    name);
        if (sqlDOB                  != null) values.put("DOB",                     sqlDOB);
        if (gender                  != null) values.put("Gender",                  gender);
        if (phone                   != null) values.put("PhoneNumber",             phone);
        if (address                 != null) values.put("Address",                 address);
        if (processingTreatmentPlan != null) values.put("ProcessingTreatmentPlan", processingTreatmentPlan);
        if (completingTreatment     != null) values.put("CompletingTreatment",     completingTreatment);

        InformationProcessing.updatePatientInformation(id, values);

        return null;

    }

}
