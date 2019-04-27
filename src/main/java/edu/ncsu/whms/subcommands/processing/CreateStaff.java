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
 * Enters a new record in the StaffMembers table.
 *
 * @author ewhorton
 * @author jmmacdo4
 * @author knarang
 * @author gsilvad
 */
@Command(name = "create-staff", mixinStandardHelpOptions = true)
public class CreateStaff implements Callable<Void> {

    /**
     * Staff identifier.
     */
    @Option(names = "--staff-id", description = "Staff identifier. One will be generated if not provided.")
    private Integer id;

    /**
     * The name of the staff member.
     */
    @Option(names = "--name", description = "Staff member name.", required = true)
    private String name;
    
    /**
     * The date of birth of the staff member.
     */
    @Option(names = "--dob", description = "Date of birth", required = true)
    private String dob;
    
    /**
     * The gender of the staff member.
     */
    @Option(names = "--gender", description = "Gender", required = true)
    private String gender;
    
    /**
     * The job title of the staff member.
     */
    @Option(names = "--job-title", description = "Job title", required = true)
    private String jobTitle;
    
    /**
     * The professional title of the staff member.
     */
    @Option(names = "--professional-title", description = "Professional title.", required = true)
    private String professionalTitle;
    
    /**
     * The department of the staff member.
     */
    @Option(names = "--department", description = "Department", required = true)
    private String department;
    
    /**
     * The phone number of the staff member.
     */
    @Option(names = "--phone", description = "Phone number", required = true)
    private String phone;
    
    /**
     * The address of the staff member.
     */
    @Option(names = "--address", description = "Address", required = true)
    private String address;
    
    /**
     * Calls the enterStaffInformation() method
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
            rs = InformationProcessing.enterStaffInformation(id, name, sqlDOB, gender, jobTitle, professionalTitle, department, phone, address);
        }
        else {
            rs = InformationProcessing.enterStaffInformation(name, sqlDOB, gender, jobTitle, professionalTitle, department, phone, address);
        }
        System.out.println(Table.read().db(rs).print());

        return null;

    }

}
