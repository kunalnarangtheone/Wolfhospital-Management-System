package edu.ncsu.whms.operations;


// Imports
import edu.ncsu.whms.ConnectionFactory;

import java.sql.*;


/**
 * WHMS Select operations
 *
 * @author Jack MacDonald
 */
public class Select {

    /**
     * SQL Select statement, selecting everything from Beds table.
     * Used in {@link Select#selectBeds()}.
     */
    private static final String SELECT_BEDS = "SELECT WardNumber, BedNumber, PatientID FROM Beds ORDER BY WardNumber, BedNumber;";

    /**
     * SQL Select statement, selecting everything from BillingRecords table.
     * Used in {@link Select#selectBillingRecords()}.
     */
    private static final String SELECT_BILLING_RECORDS = "SELECT * FROM BillingRecords;";

    /**
     * SQL Select statement, selecting everything from BillingStatements table.
     * Used in {@link Select#selectBillingStatements()}.
     */
    private static final String SELECT_BILLING_STATEMENTS = "SELECT * FROM BillingStatements;";

    /**
     * SQL Select statement, selecting everything from Patients table.
     * Used in {@link Select#selectPatients()}.
     */
    private static final String SELECT_PATIENTS = "SELECT * FROM Patients;";

    /**
     * SQL Select statement, selecting everything from StaffMembers table.
     * Used in {@link Select#selectStaffMembers()}.
     */
    private static final String SELECT_STAFF_MEMBERS = "SELECT * FROM StaffMembers;";

    /**
     * SQL Select statement, selecting everything from Tests table.
     * Used in {@link Select#selectTests()}.
     */
    private static final String SELECT_TESTS = "SELECT * FROM Tests;";

    /**
     * SQL Select statement, selecting everything from Treatments table.
     * Used in {@link Select#selectTreatments()}.
     */
    private static final String SELECT_TREATMENTS = "SELECT * FROM Treatments;";

    /**
     * SQL Select statement, selecting everything from Visits table.
     * Used in {@link Select#selectVisits()}.
     */
    private static final String SELECT_VISITS = "SELECT * FROM Visits;";

    /**
     * SQL Select statement, selecting everything from Wards table.
     * Used in {@link Select#selectWards()}.
     */
    private static final String SELECT_WARDS = "SELECT * FROM Wards;";

    /**
     * Executes Select statement selecting everything from Beds table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectBeds() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_BEDS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from BillingRecords table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectBillingRecords() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_BILLING_RECORDS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from BillingStatements table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectBillingStatements() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_BILLING_STATEMENTS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from Patients table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectPatients() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_PATIENTS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from StaffMembers table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectStaffMembers() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_STAFF_MEMBERS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from Tests table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectTests() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_TESTS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from Treatments table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectTreatments() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_TREATMENTS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from Visits table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectVisits() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_VISITS);

            return st.executeQuery();

        }

    }

    /**
     * Executes Select statement selecting everything from Wards table.
     *
     * @return Result of the selection query.
     * @throws SQLException Thrown if error occurs on the execution of the query.
     */
    public static ResultSet selectWards() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(SELECT_WARDS);

            return st.executeQuery();

        }

    }

}
