package edu.ncsu.whms.operations;


// Imports
import edu.ncsu.whms.ConnectionFactory;

import java.sql.*;
import java.util.Map;


/**
 * WHMS Medical Record operations
 *
 * @author Jack MacDonald
 */
public class MedicalRecords {

    /**
     * SQL String supporting {@link MedicalRecords#createVisit(int, Date, Date)}.
     */
    private static final String CREATE_VISIT = "INSERT INTO\n" +
            "Visits\n" +
            "    (PatientID, StartDate, EndDate)\n" +
            "VALUES\n" +
            "    (?, ?, ?);\n";
    
    /**
     * SQL String supporting {@link MedicalRecords#updateVisit(int, Map)}.
     */
    private static final String UPDATE_VISIT_BASE = "UPDATE Visits\n" +
            "SET\n";

    /**
     * SQL String supporting {@link MedicalRecords#createTest(String, int, int)}.
     */
    private static final String CREATE_TEST = "INSERT INTO\n" +
            "Tests\n" +
            "    (Diagnosis, VisitID, StaffID)\n" +
            "VALUES\n" +
            "    (?, ?, ?);\n";
    
    /**
     * SQL String supporting {@link MedicalRecords#updateTest(int, String, int, int)}.
     */
    private static final String UPDATE_TEST_BASE = "UPDATE Tests\n" +
            "SET\n";
    
    /**
     * SQL String supporting {@link MedicalRecords#createTreatment(String, int, int)}.
     */
    private static final String CREATE_TREATMENT = "INSERT INTO\n" +
            "Treatments\n" +
            "    (Prescription, VisitID, StaffID)\n" +
            "VALUES\n" +
            "    (?, ?, ?);\n";
    
    /**
     * SQL String supporting {@link MedicalRecords#updateTreatment(int, String, int, int)}.
     */
    private static final String UPDATE_TREATMENT_BASE = "UPDATE Treatments\n" +
            "SET\n";
    
    /**
     * Creates a new visit record.
     *
     * @param patientID Patient ID whose new Visit needs to be created.
     * @param start Start Date of the visit.
     * @param end End Date of the visit.
     * @return A new visit record for the patient.
     * @throws SQLException Thrown if unable to create the visit.
     */
    public static ResultSet createVisit(int patientID, Date start, Date end) throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(CREATE_VISIT, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, patientID);
            st.setDate(2, start);
            st.setDate(3, end);
            st.executeUpdate();
            return st.getGeneratedKeys();

        }

    }
    
    /**
     * Update an existing visit record.
     *
     * @param id ID of the Visit record that has to be updated.
     * @param values Visit attributes being updated.
     * @throws SQLException Thrown if unable to update the visit.
     */
    public static void updateVisit(int id, Map<String, Object> values) throws SQLException {

        String statement = UPDATE_VISIT_BASE;
        int i = 0;
        if (values.containsKey("PatientID")) {
            statement += "PatientID = ?";
            i++;
        }
        if (values.containsKey("StartDate")) {
            if (i > 0) {
                statement += ",\n";
            }
            statement += "StartDate = ?";
            i++;
        }
        if (values.containsKey("EndDate")) {
            if (i > 0) {
                statement += ",\n";
            }
            statement += "EndDate = ?";
        }

        statement += "\nWHERE VisitID = ?;";

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {
            int count = 1;

            PreparedStatement st = conn.prepareStatement(statement);
            if (values.containsKey("PatientID")) {
                st.setInt(count, (Integer) values.get("PatientID"));
                count++;
            }
            if (values.containsKey("StartDate")) {
                st.setDate(count, (Date) values.get("StartDate"));
                count++;
            }
            if (values.containsKey("EndDate")) {
                st.setDate(count, (Date) values.get("EndDate"));
                count++;
            }
            st.setInt(count, id);
            st.executeUpdate();

        }
    }
    
    /**
     * Creates a new test.
     *
     * @param diagnosis Diagnosis information.
     * @param visitID Visit ID of the patient.
     * @param staffID Staff ID of the staff member performing the test.
     * @return A new test record for the Patient.
     * @throws SQLException Thrown if unable to create the test.
     */
    public static ResultSet createTest(String diagnosis, int visitID, int staffID) throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(CREATE_TEST, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, diagnosis);
            st.setInt(2, visitID);
            st.setInt(3, staffID);
            st.executeUpdate();
            return st.getGeneratedKeys();

        }

    }
    
    /**
     * Update an existing test.
     *
     * @param id ID of the test that needs to be updated.
     * @param diagnosis Diagnosis information.
     * @param visitID Visit ID of the patient.
     * @param staffID Staff ID of the staff member performing the test.
     * @throws SQLException Thrown if unable to update the test.
     */
    public static void updateTest(int id, String diagnosis, int visitID, int staffID) throws SQLException {

        String statement = UPDATE_TEST_BASE;
        int i = 0;
        if (diagnosis != null) {
            statement += "Diagnosis = ?";
            i++;
        }
        if (visitID != 0) {
            if (i > 0) {
                statement += ",\n";
            }
            statement += "VisitID = ?";
            i++;
        }
        if (staffID != 0) {
            if (i > 0) {
                statement += ",\n";
            }
            statement += "StaffID = ?";
        }

        statement += "\nWHERE TestID = ?;";

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {
            int count = 1;

            PreparedStatement st = conn.prepareStatement(statement);
            if (diagnosis != null) {
                st.setString(count, diagnosis);
                count++;
            }
            if (visitID != 0) {
                st.setInt(count, visitID);
                count++;
            }
            if (staffID != 0) {
                st.setInt(count, staffID);
                count++;
            }
            st.setInt(count, id);
            st.executeUpdate();

        }
    }

    /**
     * Creates a new treatment.
     *
     * @param prescription Prescription Information.
     * @param visitID Visit ID of the patient.
     * @param staffID Staff ID of the staff member performing the test.
     * @return Creates a new treatment record.
     * @throws SQLException Thrown if unable to create the treatment.
     */
    public static ResultSet createTreatment(String prescription, int visitID, int staffID) throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(CREATE_TREATMENT, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, prescription);
            st.setInt(2, visitID);
            st.setInt(3, staffID);
            st.executeUpdate();
            return st.getGeneratedKeys();

        }

    }
    
    /**
     * Update a existing treatment.
     *
     * @param id Treatment ID of the treatment record that has to be updated.
     * @param prescription Prescription Information.
     * @param visitID Visit ID of the patient.
     * @param staffID Staff ID of the staff member performing the test.
     * @throws SQLException Thrown if unable to update the treatment.
     */
    public static void updateTreatment(int id, String prescription, int visitID, int staffID) throws SQLException {

        String statement = UPDATE_TREATMENT_BASE;
        int i = 0;
        if (prescription != null) {
            statement += "Prescription = ?";
            i++;
        }
        if (visitID != 0) {
            if (i > 0) {
                statement += ",\n";
            }
            statement += "VisitID = ?";
            i++;
        }
        if (staffID != 0) {
            if (i > 0) {
                statement += ",\n";
            }
            statement += "StaffID = ?";
        }

        statement += "\nWHERE TreatmentID = ?;";

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {
            int count = 1;

            PreparedStatement st = conn.prepareStatement(statement);
            if (prescription != null) {
                st.setString(count, prescription);
                count++;
            }
            if (visitID != 0) {
                st.setInt(count, visitID);
                count++;
            }
            if (staffID != 0) {
                st.setInt(count, staffID);
                count++;
            }
            st.setInt(count, id);
            st.executeUpdate();

        }
    }

}
