package edu.ncsu.whms.subcommands.processing;


// Imports
import edu.ncsu.whms.operations.InformationProcessing;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.Callable;


/**
 * Updates a record in the StaffMembers table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "update-staff", mixinStandardHelpOptions = true)
public class UpdateStaff implements Callable<Void> {

    /**
     * The Staff ID of the staff member.
     */
    @Option(names = "--staff-id", description="Staff member identifier.", required = true)
    private int id;
    
    /**
     * The name of the staff member.
     */
    @Option(names = "--name", description = "Staff member name.")
    private String name;
    
    /**
     * The date of birth of the staff member.
     */
    @Option(names = "--dob", description = "Date of birth")
    private String dob;
    
    /**
     * The gender of the staff member.
     */
    @Option(names = "--gender", description = "Gender")
    private String gender;
    
    /**
     * The job title of the staff member.
     */
    @Option(names = "--job-title", description = "Job title")
    private String jobTitle;
    
    /**
     * The Professional title of the staff member.
     */
    @Option(names = "--professional-title", description = "Professional title.")
    private String professionalTitle;
    
    /**
     * The department of the staff member.
     */
    @Option(names = "--department", description = "Department")
    private String department;
    
    /**
     * The Phone of the staff member.
     */
    @Option(names = "--phone", description = "Phone number")
    private String phone;
    
    /**
     * The Address of the staff member.
     */
    @Option(names = "--address", description = "Address")
    private String address;
    
    /**
     * Calls the updateStaffInformation() method.
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

        InformationProcessing.updateStaffInformation(id, name, sqlDOB, gender, jobTitle, professionalTitle, department, phone, address);

        return null;

    }

}
