package edu.ncsu.whms.operations;


// Imports
import edu.emory.mathcs.backport.java.util.Collections;
import edu.ncsu.whms.ConnectionFactory;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Information processing API.
 */
public class InformationProcessing {

    /**
     * SQL query for getting staff information.
     */
    private static final String GET_STAFF_INFORMATION = "SELECT * FROM StaffMembers WHERE StaffID = ?;";

    /**
     * SQL query for {@link InformationProcessing#enterStaffInformation(String, Date, String, String, String, String, String, String)}.
     */
    private static final String ENTER_STAFF_INFORMATION = "" +
            "INSERT INTO\n" +
            "    StaffMembers(Name, DOB, Gender, JobTitle, ProfessionalTitle, \n" +
            "    Department, PhoneNumber, Address)\n" +
            "VALUES\n" +
            "    (?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * SQL query for {@link InformationProcessing#enterStaffInformation(Integer, String, Date, String, String, String, String, String, String)}.
     */
    private static final String ENTER_STAFF_INFORMATION_WITH_ID = "" +
            "INSERT INTO\n" +
            "    StaffMembers(StaffId, Name, DOB, Gender, JobTitle, ProfessionalTitle, \n" +
            "    Department, PhoneNumber, Address)\n" +
            "VALUES\n" +
            "    (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * SQL query for {@link InformationProcessing#updateStaffInformation(int, String, Date, String, String, String, String, String, String)}.
     */
    private static final String UPDATE_STAFF_INFORMATION = "" +
            "UPDATE StaffMembers\n" +
            "SET\n" +
            "    Name = ?,\n" +
            "    DOB = ?,\n" +
            "    Gender = ?,\n" +
            "    JobTitle = ?,\n" +
            "    ProfessionalTitle = ?,\n" +
            "    Department = ?,\n" +
            "    PhoneNumber = ?,\n" +
            "    Address = ?\n" +
            "WHERE StaffID = ?;";

    /**
     * SQL query for {@link InformationProcessing#deleteStaffInformation(int)}.
     */
    private static final String DELETE_STAFF_INFORMATION = "DELETE FROM StaffMembers WHERE StaffID = ?;";
    
    /**
     * SQL query for getting staff information.
     */
    private static final String GET_PATIENT_INFORMATION = "SELECT * FROM Patients WHERE PatientID = ?;";

    /**
     * SQL query for {@link InformationProcessing#enterPatientInformation(String, String, Date, String, String, String, int, String)}.
     */
    private static final String ENTER_PATIENT_INFORMATION = "" +
            "INSERT INTO Patients\n" +
            "    (SSN, Name, DOB, Gender, PhoneNumber, Address, ProcessingTreatmentPlan, CompletingTreatment)\n" +
            "VALUES\n" +
            "    (?, ?, ?, ?, ?, ?, ?, ?);";
    
    /**
     * SQL query for {@link InformationProcessing#enterPatientInformation(Integer, String, String, Date, String, String, String, int, String)}.
     */
    private static final String ENTER_PATIENT_INFORMATION_WITH_ID = "" +
            "INSERT INTO Patients\n" +
            "    (PatientID, SSN, Name, DOB, Gender, PhoneNumber, Address, ProcessingTreatmentPlan, CompletingTreatment)\n" +
            "VALUES\n" +
            "    (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * SQL query for {@link InformationProcessing#updatePatientInformation(int, Map)}.
     */
    private static final String UPDATE_PATIENT_INFORMATION = "" +
            "UPDATE Patients\n" +
            "SET\n" +
            "    SSN = ?,\n" +
            "    Name = ?,\n" +
            "    DOB = ?,\n" +
            "    Gender = ?,\n" +
            "    PhoneNumber = ?,\n" +
            "    Address = ?,\n" +
            "    ProcessingTreatmentPlan = ?,\n" +
            "    CompletingTreatment = ?\n" +
            "WHERE PatientID = ?;";

    /**
     * SQL query for {@link InformationProcessing#deletePatientInformation(int)}.
     */
    public static final String DELETE_PATIENT_INFORMATION = "DELETE FROM Patients WHERE PatientID = ?;";

    /**
     * SQL query for getting ward information.
     */
    public static final String GET_WARD_INFORMATION = "SELECT * FROM Wards WHERE WardNumber = ?;";

    /**
     * SQL query for {@link InformationProcessing#enterWardInformation(int, int, int)}.
     */
    public static final String ENTER_WARD_INFORMATION = "" +
            "INSERT INTO Wards\n" +
            "    (Capacity, Charges, StaffID)\n" +
            "VALUES\n" +
            "    (?, ?, ?);";

    /**
     * SQL query for {@link InformationProcessing#updateWardInformation(int, Integer, Integer, Integer)}.
     */
    public static final String UPDATE_WARD_INFORMATION = "" +
            "UPDATE Wards\n" +
            "SET\n" +
            "    Capacity = ?,\n" +
            "    Charges = ?,\n" +
            "    StaffID = ?\n" +
            "WHERE WardNumber = ?;\n";

    /**
     * SQL query for {@link InformationProcessing#deleteWardInformation(int)}.
     */
    public static final String DELETE_WARD_INFORMATION = "DELETE FROM Wards WHERE WardNumber = ?;";

    /**
     * SQL query for finding all beds in a ward, ordered by their number.
     */
    public static final String FIND_ALL_BEDS_IN_WARD = "" +
            "SELECT *\n" +
            "FROM Beds\n" +
            "WHERE WardNumber = ?\n" +
            "ORDER BY BedNumber";

    /**
     * SQL query for creating beds as part of {@link InformationProcessing#enterWardInformation(int, int, int)}.
     */
    public static final String ENTER_BED_INFORMATION = "" +
            "INSERT INTO\n" +
            "    Beds(WardNumber, BedNumber)\n" +
            "VALUES\n" +
            "    (?, ?);";

    /**
     * SQL query for {@link InformationProcessing#findAvailableBed()}.
     */
    public static final String FIND_AVAILABLE_BED = "" +
            "SELECT BedNumber, WardNumber\n" +
            "FROM Beds\n" +
            "WHERE PatientID IS NULL\n" +
            "LIMIT 1;";

    /**
     * SQL query for {@link InformationProcessing#assignAvailableBed(int, List)}.
     */
    public static final String FIND_AVAILABLE_BED_BY_CAPACITY = "" +
            "SELECT Beds.WardNumber, Beds.BedNumber\n" +
            "FROM Beds\n" +
            "JOIN Wards\n" +
            "ON Beds.WardNumber = Wards.WardNumber\n" +
            "WHERE \n" +
            "    PatientID IS NULL\n" +
            "    AND Wards.Capacity IN (%s)\n" +
            "LIMIT 1;";

    /**
     * SQL query for {@link InformationProcessing#assignAvailableBed(int, List)}.
     */
    public static final String ASSIGN_AVAILABLE_BED = "" +
            "UPDATE Beds\n" +
            "SET Beds.PatientID = ?\n" +
            "WHERE\n" +
            "    Beds.WardNumber = ?\n" +
            "    AND Beds.BedNumber = ?;";

    /**
     * SQL query for {@link InformationProcessing#releaseBed(int, int)}.
     */
    public static final String RELEASE_BED = "" +
            "UPDATE Beds\n" +
            "SET Beds.PatientID = NULL\n" +
            "WHERE\n" +
            "    Beds.BedNumber = ?\n" +
            "    AND Beds.WardNumber = ?;";

    /**
     * Creates a staff member.
     *
     * @param name Full name.
     * @param dob Date of birth.
     * @param gender Gender.
     * @param jobTitle Job title.
     * @param professionalTitle Professional title.
     * @param department Department.
     * @param phone Phone number.
     * @param address Address.
     * @return ResultSet.
     * @throws SQLException Thrown if unable to create a staff member.
     */
    public static ResultSet enterStaffInformation(String name, Date dob, String gender, String jobTitle,
                                                  String professionalTitle, String department, String phone,
                                                  String address) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(ENTER_STAFF_INFORMATION, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setDate(  2, dob);
            st.setString(3, gender);
            st.setString(4, jobTitle);
            st.setString(5, professionalTitle);
            st.setString(6, department);
            st.setString(7, phone);
            st.setString(8, address);
            st.executeUpdate();
            return st.getGeneratedKeys();

        }

    }

    /**
     * Creates a staff member.
     *
     * @param id Identifier.
     * @param name Full name.
     * @param dob Date of birth.
     * @param gender Gender.
     * @param jobTitle Job title.
     * @param professionalTitle Professional title.
     * @param department Department.
     * @param phone Phone number.
     * @param address Address.
     * @return ResultSet.
     * @throws SQLException Thrown if unable to create a staff member.
     */
    public static ResultSet enterStaffInformation(Integer id, String name, Date dob, String gender, String jobTitle,
                                                  String professionalTitle, String department, String phone,
                                                  String address) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(ENTER_STAFF_INFORMATION_WITH_ID, Statement.RETURN_GENERATED_KEYS);
            st.setInt(   1, id);
            st.setString(2, name);
            st.setDate(  3, dob);
            st.setString(4, gender);
            st.setString(5, jobTitle);
            st.setString(6, professionalTitle);
            st.setString(7, department);
            st.setString(8, phone);
            st.setString(9, address);
            st.executeUpdate();
            return st.getGeneratedKeys();

        }

    }

    /**
     * Updates a staff member's information.
     *
     * @param staffID Staff identifier.
     * @param name Full name.
     * @param dob Date of birth.
     * @param gender Gender.
     * @param jobTitle Job title.
     * @param professionalTitle Professional title.
     * @param department Department.
     * @param phone Phone number.
     * @param address Address.
     * @throws SQLException Thrown if unable to update the staff member.
     */
    public static void updateStaffInformation(int staffID, String name, Date dob, String gender, String jobTitle,
                                                  String professionalTitle, String department, String phone,
                                                  String address) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement get = conn.prepareStatement(GET_STAFF_INFORMATION);
            get.setInt(1, staffID);
            ResultSet staff = get.executeQuery();

            if (staff.next()) {

                PreparedStatement st = conn.prepareStatement(UPDATE_STAFF_INFORMATION);
                st.setString(1, (name              != null) ? name              : staff.getString("Name"));
                st.setDate(  2, (dob               != null) ? dob               : staff.getDate(  "DOB"));
                st.setString(3, (gender            != null) ? gender            : staff.getString("Gender"));
                st.setString(4, (jobTitle          != null) ? jobTitle          : staff.getString("JobTitle"));
                st.setString(5, (professionalTitle != null) ? professionalTitle : staff.getString("ProfessionalTitle"));
                st.setString(6, (department        != null) ? department        : staff.getString("Department"));
                st.setString(7, (phone             != null) ? phone             : staff.getString("PhoneNumber"));
                st.setString(8, (address           != null) ? address           : staff.getString("Address"));
                st.setInt(   9, staffID);
                st.executeUpdate();

            }
            else {

                throw new SQLException(String.format("No staff member with ID %d.", staffID));

            }

        }

    }

    /**
     * Deletes a staff member.
     *
     * @param staffID Staff identifier.
     * @throws SQLException Thrown if unable to delete the staff member.
     */
    public static void deleteStaffInformation(int staffID) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(DELETE_STAFF_INFORMATION);
            st.setInt(1, staffID);
            st.executeUpdate();

        }

    }

    /**
     * Creates a patient.
     *
     * @param ssn Optional social security number.
     * @param name Full name.
     * @param dob Date of birth.
     * @param gender Gender.
     * @param phone Phone number.
     * @param address Address.
     * @param processingTreatmentPlan Patient treatment plan.
     * @param completingTreatment Whether the patient is currently completing treatment.
     * @return ResultSet.
     * @throws SQLException Thrown if unable to create the patient.
     */
    public static ResultSet enterPatientInformation(String ssn, String name, Date dob, String gender,
                                                    String phone, String address, int processingTreatmentPlan,
                                                    String completingTreatment) throws SQLException {


        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(ENTER_PATIENT_INFORMATION, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, ssn);
            st.setString(2, name);
            st.setDate(  3, dob);
            st.setString(4, gender);
            st.setString(5, phone);
            st.setString(6, address);
            st.setInt(   7, processingTreatmentPlan);
            st.setString(8, completingTreatment);
            st.executeUpdate();
            return st.getGeneratedKeys();

        }

    }

    /**
     * Creates a patient.
     *
     * @param id Optional identifier.
     * @param ssn Optional social security number.
     * @param name Full name.
     * @param dob Date of birth.
     * @param gender Gender.
     * @param phone Phone number.
     * @param address Address.
     * @param processingTreatmentPlan Patient treatment plan.
     * @param completingTreatment Whether the patient is currently completing treatment.
     * @return ResultSet.
     * @throws SQLException Thrown if unable to create the patient.
     */
    public static ResultSet enterPatientInformation(Integer id, String ssn, String name, Date dob, String gender,
                                                    String phone, String address, int processingTreatmentPlan,
                                                    String completingTreatment) throws SQLException {


        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(ENTER_PATIENT_INFORMATION_WITH_ID, Statement.RETURN_GENERATED_KEYS);
            st.setInt(   1, id);
            st.setString(2, ssn);
            st.setString(3, name);
            st.setDate(  4, dob);
            st.setString(5, gender);
            st.setString(6, phone);
            st.setString(7, address);
            st.setInt(   8, processingTreatmentPlan);
            st.setString(9, completingTreatment);
            st.executeUpdate();
            return st.getGeneratedKeys();

        }

    }

    /**
     * Updates patient information.
     *
     * @param patientID Patient identifier.
     * @param values Updated values.
     * @throws SQLException Thrown if unable to update the patient.
     */
    public static void updatePatientInformation(int patientID, Map<String, Object> values) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement get = conn.prepareStatement(GET_PATIENT_INFORMATION);
            get.setInt(1, patientID);
            ResultSet patient = get.executeQuery();

            if (patient.next()) {

                PreparedStatement st = conn.prepareStatement(UPDATE_PATIENT_INFORMATION);
                st.setString(1, (String) values.getOrDefault("SSN",                     patient.getString("SSN")));
                st.setString(2, (String) values.getOrDefault("Name",                    patient.getString("Name")));
                st.setDate(  3, (Date)   values.getOrDefault("DOB",                     patient.getDate(  "DOB")));
                st.setString(4, (String) values.getOrDefault("Gender",                  patient.getString("Gender")));
                st.setString(5, (String) values.getOrDefault("PhoneNumber",             patient.getString("PhoneNumber")));
                st.setString(6, (String) values.getOrDefault("Address",                 patient.getString("Address")));
                st.setInt(   7, (int)    values.getOrDefault("ProcessingTreatmentPlan", patient.getInt(   "ProcessingTreatmentPlan")));
                st.setString(8, (String) values.getOrDefault("CompletingTreatment",     patient.getString("CompletingTreatment")));
                st.setInt(   9, patientID);
                st.executeUpdate();

            }
            else {

                throw new SQLException(String.format("No patient with ID %d.", patientID));

            }

        }

    }

    /**
     * Deletes a patient.
     *
     * @param patientID Patient identifier.
     * @throws SQLException Thrown if unable to delete the patient.
     */
    public static void deletePatientInformation(int patientID) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(DELETE_PATIENT_INFORMATION);
            st.setInt(1, patientID);
            st.executeUpdate();

        }

    }

    /**
     * Creates a ward.
     *
     * @param capacity Ward capacity.
     * @param charges Charges (cost) per day.
     * @param staffID Staff member assigned to the ward.
     * @return ResultSet.
     * @throws SQLException Thrown if unable to create the ward or its associated beds.
     */
    public static ResultSet enterWardInformation(int capacity, int charges, int staffID) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            // Execute as a transaction
            try {

                // Disable autocommit to execute multiple queries in the same transaction
                conn.setAutoCommit(false);

                PreparedStatement ward = conn.prepareStatement(ENTER_WARD_INFORMATION, Statement.RETURN_GENERATED_KEYS);
                ward.setInt(1, capacity);
                ward.setInt(2, charges);
                ward.setInt(3, staffID);
                ward.executeUpdate();
                ResultSet wardRS = ward.getGeneratedKeys();

                // Get the inserted ward number
                wardRS.next();
                int wardNumber = wardRS.getInt("insert_id");

                PreparedStatement bed = conn.prepareStatement(ENTER_BED_INFORMATION, Statement.RETURN_GENERATED_KEYS);
                for (int bedNumber = 1; bedNumber  <= capacity; bedNumber++) {
                    bed.setInt(1, wardNumber);
                    bed.setInt(2, bedNumber);
                    bed.addBatch();
                }
                bed.executeBatch();

                // Commit transaction
                conn.commit();

                // Return inserted keys
                wardRS.beforeFirst();
                return wardRS;

            }
            catch (SQLException e) {

                // Rollback and then propagate error
                conn.rollback();
                throw e;

            }
            finally {

                // Enable autocommit
                conn.setAutoCommit(true);

            }

        }

    }

    /**
     * Updates a ward.
     *
     * @param wardNumber Ward identifier.
     * @param capacity Ward capacity.
     * @param charges Charges (cost) per day.
     * @param staffID Staff member assigned to the ward.
     * @throws SQLException Thrown if the ward does not have enough free beds to be resized, of if updating the ward and
     *                      its associated bed records fails.
     */
    public static void updateWardInformation(int wardNumber, Integer capacity, Integer charges, Integer staffID) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            // Execute as a transaction
            try {

                // Set autocommit to false to allow multiple queries per transaction
                conn.setAutoCommit(false);

                // Query for the existing ward number
                PreparedStatement get = conn.prepareStatement(GET_WARD_INFORMATION);
                get.setInt(1, wardNumber);
                ResultSet wardRS = get.executeQuery();

                // Error if there is no such ward
                if (!wardRS.next()) {
                    throw new SQLException(String.format("No ward with number %d.", wardNumber));
                }

                // Update ward information
                PreparedStatement st = conn.prepareStatement(UPDATE_WARD_INFORMATION);
                st.setInt(1, (capacity != null) ? capacity : wardRS.getInt("capacity"));
                st.setInt(2, (charges  != null) ? charges  : wardRS.getInt("charges"));
                st.setInt(3, (staffID  != null) ? staffID  : wardRS.getInt("staffID"));
                st.setInt(4, wardNumber);
                st.executeUpdate();

                // If capacity was changed, either add new beds or remove beds to match.
                int oldCapacity = wardRS.getInt("capacity");
                if (capacity != null) {

                    // If capacity was increased, add beds with number starting at one more than the previous max bed
                    // number all the way up to capacity.
                    if (capacity > oldCapacity) {

                        PreparedStatement bed = conn.prepareStatement(ENTER_BED_INFORMATION);
                        for (int bedNumber = oldCapacity + 1; bedNumber <= capacity; bedNumber++) {
                            bed.setInt(1, wardNumber);
                            bed.setInt(2, bedNumber);
                            bed.addBatch();
                        }
                        bed.executeBatch();

                    }
                    // If capacity was decreased, remove oldCapacity - capacity beds and normalize bed numbers.
                    else if (capacity < oldCapacity) {

                        // Find all beds belonging to the ward
                        PreparedStatement beds = conn.prepareStatement(FIND_ALL_BEDS_IN_WARD, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        beds.setInt(1, wardNumber);
                        ResultSet bedsRS = beds.executeQuery();

                        // Number of beds to delete
                        int difference = oldCapacity - capacity;

                        // Number of beds deleted
                        int deleted = 0;

                        // Normalized bed number
                        int normalized = 1;

                        // Iterate over beds in the result set, either deleting or normalizing their bed number.
                        while (bedsRS.next()) {

                            // Access PatientID for wasNull check
                            bedsRS.getInt("PatientID");

                            // If difference beds not deleted and PatientID is null, delete this bed.
                            // Otherwise, normalize the bed number
                            if (deleted < difference && bedsRS.wasNull()) {

                                bedsRS.deleteRow();
                                deleted++;

                            }
                            else {

                                bedsRS.updateInt("BedNumber", normalized);
                                bedsRS.updateRow();
                                normalized++;

                            }

                        }

                        // If the number deleted is not equal to difference, fail
                        if (deleted != difference) {
                            throw new SQLException(String.format("Cannot decrease capacity. Need %d free beds to remove, but only %d are free.", difference, deleted));
                        }

                    }

                }

                // Commit
                conn.commit();

            }
            catch (SQLException e) {

                // Rollback transaction and the propagate error
                conn.rollback();
                throw e;

            }
            finally {

                // Enable autocommit
                conn.setAutoCommit(true);

            }

        }

    }

    /**
     * Deletes a ward.
     *
     * @param wardNumber Ward identifier.
     * @throws SQLException Thrown if unable to delete the ward or its beds.
     */
    public static void deleteWardInformation(int wardNumber) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(DELETE_WARD_INFORMATION);
            st.setInt(1, wardNumber);
            st.executeUpdate();

        }

    }

    /**
     * Finds an available bed.
     *
     * @return ResultSet.
     * @throws SQLException Thrown if query for an available bed fails.
     */
    public static ResultSet findAvailableBed() throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            Statement st = conn.createStatement();
            return st.executeQuery(FIND_AVAILABLE_BED);

        }

    }

    /**
     * Assigns an available bed to a patient with no preference on capacity.
     *
     * @param patientID Patient being assigned a bed.
     * @throws SQLException Thrown if there are no available beds, or otherwise unable to assign a bed.
     */
    public static ResultSet assignAvailableBed(int patientID) throws SQLException {

        return assignAvailableBed(patientID, Arrays.asList(1, 2, 3, 4));

    }

    /**
     * Assigns an available bed to a patient. If capacity is not empty, it is taken as the patient's preference on
     * ward capacity.
     *
     * @param patientID Patient being assigned a bed.
     * @param capacity Capacity preference on ward.
     * @throws SQLException Thrown if there are no available beds that meet the patient's capacity preference, or if
     *                      otherwise unable to assign a bed.
     */
    public static ResultSet assignAvailableBed(int patientID, List<Integer> capacity) throws SQLException {

        // If capacity is not specified, default to all
        if (capacity == null || capacity.size() == 0) {
            capacity = Arrays.asList(1, 2, 3, 4);
        }

        try (Connection conn = ConnectionFactory.getConnection()) {

            // Execute as a transaction
            try {

                // Disable autocommit to run multiple statements in the same transaction
                conn.setAutoCommit(false);

                // Search for an available bed
                String capacities = String.join(", ", (List<String>) Collections.nCopies(capacity.size(), "?"));
                String query = String.format(FIND_AVAILABLE_BED_BY_CAPACITY, capacities);
                PreparedStatement bed = conn.prepareStatement(query);
                for (int i = 0; i < capacity.size(); i++) {
                    bed.setInt(i + 1, capacity.get(i));
                }
                ResultSet bedRS = bed.executeQuery();

                // If there is no available bed, throw an error to the client
                // Note: this will be caught locally to rollback the
                // transaction and then propagated.
                if (!bedRS.next()) {
                    throw new SQLException("No available beds");
                }

                // Get ward and bed number
                int wardNumber = bedRS.getInt("wardNumber");
                int bedNumber = bedRS.getInt("bedNumber");

                // Assign the bed to the patient
                PreparedStatement st = conn.prepareStatement(ASSIGN_AVAILABLE_BED);
                st.setInt(1, patientID);
                st.setInt(2, wardNumber);
                st.setInt(3, bedNumber);
                st.executeUpdate();

                // Commit
                conn.commit();

                // Return assigned bed
                bedRS.beforeFirst();
                return bedRS;

            }
            catch (SQLException e) {

                // Rollback the transaction and then propagate the error
                conn.rollback();
                throw e;

            }
            finally {

                // Enable autocommit
                conn.setAutoCommit(true);

            }

        }

    }

    /**
     * Releases a bed that is assigned to a patient.
     *
     * @param wardNumber Ward the bed belongs to.
     * @param bedNumber Bed identifier.
     * @throws SQLException Thrown if releasing the bed fails.
     */
    public static void releaseBed(int wardNumber, int bedNumber) throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(RELEASE_BED);
            st.setInt(1, bedNumber);
            st.setInt(2, wardNumber);
            st.executeUpdate();

        }

    }

}
